package com.menu.androidcourseproject.ui.profile;

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

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.menu.androidcourseproject.R;
import com.menu.androidcourseproject.databinding.ProfileFragmentBinding;
import com.menu.androidcourseproject.model.Meal;

public class ProfileFragment extends Fragment {

    private ProfileViewModel mViewModel;
    private ProfileFragmentBinding profileFragmentBinding;
    private Snackbar snackbar;

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
        mViewModel.getUser(
                requireActivity().getSharedPreferences(getString(R.string.shared_key),
                        Context.MODE_PRIVATE).getInt(getString(R.string.login), 0))
                .observe(getViewLifecycleOwner(), user -> {
                    assert user != null;
                    System.out.println(user.getCountry());
                    System.out.println(user.getURLImage());
                    profileFragmentBinding.setUser(user);
                    if (!user.isVerified()) {
                        snackbar = Snackbar.make(getView(), "verified my account", BaseTransientBottomBar.LENGTH_INDEFINITE);
                        snackbar.setAction("verified", v -> {
                            Navigation.findNavController(requireView()).navigate(R.id.verifiedFragment);
                        });
                        snackbar.show();
                    }
                });
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (snackbar != null)
                    snackbar.dismiss();
                Navigation.findNavController(requireView()).navigate(R.id.settingsFragment);
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(requireActivity(), callback);

    }
}