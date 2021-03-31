package com.menu.androidcourseproject.ui.Home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.menu.androidcourseproject.Adapters.MealAdapter;
import com.menu.androidcourseproject.Model.Meal;
import com.menu.androidcourseproject.databinding.HomeFragmentBinding;

import java.util.List;

public class HomeFragment extends Fragment implements MealAdapter.onClickButtons {

    private HomeViewModel mViewModel;
    private HomeFragmentBinding homeFragmentBinding;
    private NavController navController;

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
        mViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        for (int i = 0; i < 8; i++) {
            Meal meal = new Meal("test" + i, "ymym" + i, 15.4, "https://picsum.photos/id/" + (237 + i) + "/200", true, 3.5);
            mViewModel.insert(meal);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        MealAdapter mealAdapter = new MealAdapter(this);
        homeFragmentBinding.rvMeal.setHasFixedSize(true);
        homeFragmentBinding.rvMeal.setItemViewCacheSize(15);
        homeFragmentBinding.rvMeal.setAdapter(mealAdapter);
        mViewModel.meals.observe(getViewLifecycleOwner(), new Observer<List<Meal>>() {
            @Override
            public void onChanged(List<Meal> meals) {
                mealAdapter.setMeals(meals);
            }
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