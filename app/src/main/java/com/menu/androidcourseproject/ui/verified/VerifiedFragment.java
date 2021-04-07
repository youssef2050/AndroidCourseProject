package com.menu.androidcourseproject.ui.verified;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.menu.androidcourseproject.R;
import com.menu.androidcourseproject.databinding.VerifiedFragmentBinding;
import com.menu.androidcourseproject.model.User;

public class VerifiedFragment extends Fragment {

    private VerifiedViewModel mViewModel;
    private VerifiedFragmentBinding verifiedFragmentBinding;
    private User user;

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
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() != null) {
            int id = requireActivity().getSharedPreferences(getString(R.string.shared_key),
                    Context.MODE_PRIVATE).getInt(getString(R.string.login), 0);
            mViewModel.getUser(id).observe(getViewLifecycleOwner(), user -> {
                this.user = user;
                send(user.getPhone(), user.getCodePhone(), true);
            });
        } else {
            String phone = getArguments().getString(getString(R.string.phoneNumber));
            String CodePhone = getArguments().getString(getString(R.string.codePhone));
            send(phone, CodePhone, false);
        }
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

    private void send(String phone, String codePhone, boolean update) {
        if (update) {
            user.setVerified(true);
            mViewModel.update(user);
        } else {
            Navigation.findNavController(requireView()).navigate(R.id.changePasswordFragment);
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(VerifiedViewModel.class);
    }
}