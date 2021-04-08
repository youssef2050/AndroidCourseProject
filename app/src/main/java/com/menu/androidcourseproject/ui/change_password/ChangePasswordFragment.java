package com.menu.androidcourseproject.ui.change_password;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.menu.androidcourseproject.R;
import com.menu.androidcourseproject.databinding.ChangePasswordFragmentBinding;

public class ChangePasswordFragment extends Fragment {

    private ChangePasswordViewModel mViewModel;
    private ChangePasswordFragmentBinding changePasswordFragmentBinding;

    public static ChangePasswordFragment newInstance() {
        return new ChangePasswordFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        changePasswordFragmentBinding = ChangePasswordFragmentBinding.inflate(getLayoutInflater());
        return changePasswordFragmentBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        changePasswordFragmentBinding.save.setOnClickListener(v -> {


            if (getArguments() == null) {
                mViewModel.checkOldPassword(changePasswordFragmentBinding.oldPassword.getText().toString()
                        , requireActivity().getSharedPreferences(getString(R.string.shared_key),
                                Context.MODE_PRIVATE).getInt(getString(R.string.login), 0)).observe(getViewLifecycleOwner(), user -> {
                    if (user != null) {
                        //update
                        if (changePasswordFragmentBinding.newPassword.getText().toString().equals(changePasswordFragmentBinding.reNewPassword.getText().toString())) {
                            user.setPassword(changePasswordFragmentBinding.newPassword.getText().toString());
                            mViewModel.update(user);
                            Navigation.findNavController(requireView()).navigate(R.id.settingsFragment);
                        } else {
                            Toast.makeText(requireContext(), "not match password", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(requireContext(), "password is error", Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
                int id = getArguments().getInt(getString(R.string.user_id));
                changePasswordFragmentBinding.oldPassword.setVisibility(View.GONE);
                mViewModel.getUser(id).observe(getViewLifecycleOwner(), user -> {
                    if (changePasswordFragmentBinding.newPassword.getText().toString().equals(changePasswordFragmentBinding.reNewPassword.getText().toString())) {
                        user.setPassword(changePasswordFragmentBinding.newPassword.getText().toString());
                        mViewModel.update(user);
                        Navigation.findNavController(requireView()).navigate(R.id.loginFragment);
                    } else {
                        Toast.makeText(requireContext(), "not match password", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (getArguments() == null)
                    Navigation.findNavController(requireView()).navigate(R.id.settingsFragment);
                else
                    Navigation.findNavController(requireView()).navigate(R.id.loginFragment);
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(requireActivity(), callback);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ChangePasswordViewModel.class);
    }
}