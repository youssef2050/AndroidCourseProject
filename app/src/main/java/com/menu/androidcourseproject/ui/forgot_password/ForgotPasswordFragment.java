package com.menu.androidcourseproject.ui.forgot_password;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.menu.androidcourseproject.R;
import com.menu.androidcourseproject.databinding.ForgotPasswordFragmentBinding;

public class ForgotPasswordFragment extends Fragment {

    private ForgotPasswordViewModel mViewModel;
    private ForgotPasswordFragmentBinding forgotPasswordFragmentBinding;

    public static ForgotPasswordFragment newInstance() {
        return new ForgotPasswordFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        forgotPasswordFragmentBinding = ForgotPasswordFragmentBinding.inflate(getLayoutInflater());
        return forgotPasswordFragmentBinding.getRoot();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ForgotPasswordViewModel.class);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        forgotPasswordFragmentBinding.butSend.setOnClickListener(v -> {
            String phone = forgotPasswordFragmentBinding.edPhoneNumber.getText().toString();
            String codePhone = forgotPasswordFragmentBinding.ccpCodePhone.getSelectedCountryCode();
            mViewModel.checkPhoneNumber(phone, codePhone).observe(getViewLifecycleOwner(), user -> {
                if (user != null) {
                    Bundle bundle = new Bundle();
                    bundle.putString(getString(R.string.phoneNumber), forgotPasswordFragmentBinding.edPhoneNumber.getText().toString());
                    bundle.putString(getString(R.string.codePhone), forgotPasswordFragmentBinding.ccpCodePhone.getSelectedCountryCode());
                    Navigation.findNavController(requireView()).navigate(R.id.verifiedFragment, bundle);
                } else {
                    Toast.makeText(requireContext(), "not found!", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}