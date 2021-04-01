package com.menu.androidcourseproject.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.menu.androidcourseproject.adapters.MealAdapter;
import com.menu.androidcourseproject.databinding.HomeFragmentBinding;
import com.menu.androidcourseproject.model.Meal;

public class HomeFragment extends Fragment implements MealAdapter.onClickButtons {

    private HomeViewModel mViewModel;
    private HomeFragmentBinding homeFragmentBinding;

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

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(getActivity()).get(HomeViewModel.class);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MealAdapter mealAdapter = new MealAdapter(this);
        homeFragmentBinding.rvMeal.setHasFixedSize(true);
        homeFragmentBinding.rvMeal.setItemViewCacheSize(15);
        homeFragmentBinding.rvMeal.setAdapter(mealAdapter);
        mViewModel.meals.observe(getViewLifecycleOwner(), meals -> {
            if (meals != null)
                mealAdapter.setMeals(meals);
        });
    }

    @Override
    public void share(Meal meal) {
        Toast.makeText(getContext(), "share", Toast.LENGTH_LONG).show();
    }

    @Override
    public void fav(Meal meal) {
        Toast.makeText(getContext(), "fav", Toast.LENGTH_SHORT).show();
    }
}