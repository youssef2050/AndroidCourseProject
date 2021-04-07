package com.menu.androidcourseproject.ui.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.menu.androidcourseproject.databinding.ProfileFragmentBinding;
import com.menu.androidcourseproject.model.Meal;

public class ProfileFragment extends Fragment {

    private ProfileViewModel mViewModel;
    private ProfileFragmentBinding profileFragmentBinding;

    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        profileFragmentBinding = ProfileFragmentBinding.inflate(getLayoutInflater());
        return profileFragmentBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
        Meal.activity = requireActivity();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel.getUser(1).observe(getViewLifecycleOwner(), user -> {
            assert user != null;
            System.out.println(user.getURLImage());
            profileFragmentBinding.setUser(user);
        });
    }
}