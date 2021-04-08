package com.menu.androidcourseproject.ui.change_password;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

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
       int fragment = getArguments().getInt(getString(R.string.numberFragment));
       if (fragment == 0){

       }else {

       }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ChangePasswordViewModel.class);
    }
}