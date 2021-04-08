package com.menu.androidcourseproject.ui.verified;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.menu.androidcourseproject.R;
import com.menu.androidcourseproject.databinding.VerifiedFragmentBinding;
import com.menu.androidcourseproject.model.User;

import java.util.concurrent.TimeUnit;

public class VerifiedFragment extends Fragment {

    private VerifiedViewModel mViewModel;
    private VerifiedFragmentBinding verifiedFragmentBinding;
    private User user;
    private FirebaseAuth mAuth;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    private static final String TAG = "VerifiedFragment";
    private String mVerificationId;
    private PhoneAuthProvider.ForceResendingToken mResendToken;
    private boolean update = false;
    private String phone;
    private String CodePhone;

    public static VerifiedFragment newInstance() {
        return new VerifiedFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        verifiedFragmentBinding = VerifiedFragmentBinding.inflate(getLayoutInflater());
        return verifiedFragmentBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        verifiedFragmentBinding.reSendCode.setEnabled(false);
        verifiedFragmentBinding.reSendCode.setTextColor(Color.GRAY);
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() == null) {
            int id = requireActivity().getSharedPreferences(getString(R.string.shared_key),
                    Context.MODE_PRIVATE).getInt(getString(R.string.login), 0);
            mViewModel.getUser(id).observe(getViewLifecycleOwner(), user -> {
                this.user = user;
                phone = user.getPhone();
                CodePhone = user.getCodePhone();
                update = true;
            });
        } else {
            phone = getArguments().getString(getString(R.string.phoneNumber));
            CodePhone = getArguments().getString(getString(R.string.codePhone));
        }
        send(phone, CodePhone);
        verifiedFragmentBinding.reSendCode.setOnClickListener(v -> {
            resendVerificationCode("+" + CodePhone + phone, mResendToken);
        });
        verifiedFragmentBinding.next.setOnClickListener(v -> {
            verifyPhoneNumberWithCode(mVerificationId, verifiedFragmentBinding.numOne.getText().toString());
        });
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (getArguments() == null)
                    Navigation.findNavController(requireView()).navigate(R.id.profileFragment);
                else
                    Navigation.findNavController(requireView()).navigate(R.id.loginFragment);
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(requireActivity(), callback);
    }

    private void send(String phone, String codePhone) {
        startPhoneNumberVerification("+" + codePhone + phone);
        timer();

    }

    private void timer() {
        new CountDownTimer(30000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                verifiedFragmentBinding.time.setText("00:" + millisUntilFinished / 1000);
            }

            @Override
            public void onFinish() {
                verifiedFragmentBinding.reSendCode.setEnabled(true);
                verifiedFragmentBinding.reSendCode.setTextColor(requireActivity().getResources().getColor(R.color.yellow500));
            }
        }.start();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(VerifiedViewModel.class);
        mAuth = FirebaseAuth.getInstance();
        mAuth.useAppLanguage();
        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential credential) {
                Log.d(TAG, "onVerificationCompleted:" + credential);
                signInWithPhoneAuthCredential(credential);
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                Log.w(TAG, "onVerificationFailed", e);

                if (e instanceof FirebaseAuthInvalidCredentialsException) {
                    // Invalid request

                } else if (e instanceof FirebaseTooManyRequestsException) {
                    // The SMS quota for the project has been exceeded

                }
            }

            @Override
            public void onCodeSent(@NonNull String verificationId,
                                   @NonNull PhoneAuthProvider.ForceResendingToken token) {
                Log.d(TAG, "onCodeSent:" + verificationId);
                mVerificationId = verificationId;
                mResendToken = token;
            }
        };
    }

    private void startPhoneNumberVerification(String phoneNumber) {
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(phoneNumber)
                        .setTimeout(30L, TimeUnit.SECONDS)
                        .setActivity(requireActivity())
                        .setCallbacks(mCallbacks)
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(requireActivity(), task -> {
                    if (task.isSuccessful()) {
                        if (update) {
                            user.setVerified(true);
                            mViewModel.update(user);
                            Navigation.findNavController(requireView()).navigate(R.id.profileFragment);
                        } else {
                            Bundle bundle = new Bundle();
                            bundle.putInt(getString(R.string.numberFragment), getArguments().getInt(getString(R.string.user_id)));
                            Navigation.findNavController(requireView()).navigate(R.id.changePasswordFragment, bundle);
                        }
                    } else {
                        Log.w(TAG, "signInWithCredential:failure", task.getException());
                        if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                        }
                    }
                });
    }

    private void verifyPhoneNumberWithCode(String verificationId, String code) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);
        signInWithPhoneAuthCredential(credential);
    }

    private void resendVerificationCode(String phoneNumber,
                                        PhoneAuthProvider.ForceResendingToken token) {
        timer();
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(phoneNumber)       // Phone number to verify
                        .setTimeout(30L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(requireActivity())                 // Activity (for callback binding)
                        .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                        .setForceResendingToken(token)     // ForceResendingToken from callbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }
}