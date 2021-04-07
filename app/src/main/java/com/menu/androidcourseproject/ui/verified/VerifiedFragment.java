package com.menu.androidcourseproject.ui.verified;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.menu.androidcourseproject.databinding.VerifiedFragmentBinding;

public class VerifiedFragment extends Fragment {

    private VerifiedViewModel mViewModel;
    private VerifiedFragmentBinding verifiedFragmentBinding;

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

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(VerifiedViewModel.class);
    }
}