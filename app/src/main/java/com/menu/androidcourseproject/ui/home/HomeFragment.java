package com.menu.androidcourseproject.ui.home;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.menu.androidcourseproject.R;
import com.menu.androidcourseproject.adapters.MealAdapter;
import com.menu.androidcourseproject.databinding.HomeFragmentBinding;
import com.menu.androidcourseproject.general.AddImages;
import com.menu.androidcourseproject.model.Meal;

import java.io.File;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static com.menu.androidcourseproject.general.AddImages.requestCodePermission;

public class HomeFragment extends Fragment implements MealAdapter.onClickButtons {

    private HomeViewModel mViewModel;
    private HomeFragmentBinding homeFragmentBinding;
    private boolean isClick = true;
    private AddImages addImages;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        homeFragmentBinding = HomeFragmentBinding.inflate(getLayoutInflater());
        return homeFragmentBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Meal.activity = requireActivity();
        getPermission();
        mViewModel = new ViewModelProvider(requireActivity()).get(HomeViewModel.class);
        MealAdapter mealAdapter = new MealAdapter(this);
        homeFragmentBinding.rvMeal.setItemViewCacheSize(20);
        homeFragmentBinding.rvMeal.setAdapter(mealAdapter);
        mViewModel.meals.observe(getViewLifecycleOwner(), meals -> {
            if (meals != null) {
                mealAdapter.setMeals(meals);
                Meal.MEALS = meals;
            }
        });
        homeFragmentBinding.edSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mealAdapter.filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        homeFragmentBinding.butFilter.setOnClickListener(v -> {
            if (isClick) {
                homeFragmentBinding.list.setVisibility(View.VISIBLE);
            } else {
                homeFragmentBinding.cash.setChecked(false);
                homeFragmentBinding.installment.setChecked(false);
                mealAdapter.send("");
                homeFragmentBinding.list.setVisibility(View.GONE);
            }
            isClick = !isClick;
        });
        homeFragmentBinding.rgList.setOnCheckedChangeListener((group, checkedId) -> {
            mealAdapter.send("");
            if (checkedId == homeFragmentBinding.cash.getId()) {
                mealAdapter.send(homeFragmentBinding.cash.getText());
            } else if (checkedId == homeFragmentBinding.installment.getId()) {
                mealAdapter.send(homeFragmentBinding.installment.getText());
            }
        });
        homeFragmentBinding.butSetting.setOnClickListener(v -> Navigation.findNavController(requireView()).navigate(R.id.settingsFragment));
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                requireActivity().finish();
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(requireActivity(), callback);

    }


    @Override
    public void share(Meal meal) {
        String text = meal.getMealTitle();
        Uri path = Uri.parse(meal.getURLImage());
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_TEXT, text);
        shareIntent.putExtra(Intent.EXTRA_STREAM, path);
        shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        shareIntent.setType("image/*");
        startActivity(Intent.createChooser(shareIntent, "Share..."));

    }

    @Override
    public void fav(Meal meal) {
        meal.setFavorite(!meal.isFavorite());
        mViewModel.update(meal);
    }

    @Override
    public void details(Meal meal) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(getString(R.string.key_meal), meal);
        Navigation.findNavController(getView()).navigate(R.id.detailsFragment, bundle);
    }

    private void getPermission() {
        if (Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(requireActivity(), READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission(requireActivity(), WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(requireActivity(), new String[]{READ_EXTERNAL_STORAGE,
                                WRITE_EXTERNAL_STORAGE},
                        requestCodePermission);
            }
        }
    }
}