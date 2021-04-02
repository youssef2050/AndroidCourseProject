package com.menu.androidcourseproject.ui.home;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
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
    private boolean isClick = true;

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
//        Meal meal1 = new Meal("1", "1111", 150, "https://www.picsum.photos/id/238/200", true, 1.5);
//        Meal meal2 = new Meal("2", "2222", 250, "https://www.picsum.photos/id/239/200", false, 2.5);
//        Meal meal3 = new Meal("3", "3333", 350, "https://www.picsum.photos/id/240/200", false, 3.5);
//        Meal meal4 = new Meal("4", "4444", 450, "https://www.picsum.photos/id/241/200", true, 4.5);
//        Meal meal5 = new Meal("5", "5555", 550, "https://www.picsum.photos/id/242/200", false, 5);
//        Meal meal6 = new Meal("6", "6666", 650, "https://www.picsum.photos/id/243/200", true, 4.6);
//        Meal meal7 = new Meal("7", "7777", 750, "https://www.picsum.photos/id/244/200", true, 3.7);
//        mViewModel.insert(meal1);
//        mViewModel.insert(meal2);
//        mViewModel.insert(meal3);
//        mViewModel.insert(meal4);
//        mViewModel.insert(meal5);
//        mViewModel.insert(meal6);
//        mViewModel.insert(meal7);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MealAdapter mealAdapter = new MealAdapter(this);
        homeFragmentBinding.rvMeal.setHasFixedSize(true);
        homeFragmentBinding.rvMeal.setItemViewCacheSize(15);
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
        homeFragmentBinding.butFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isClick) {
                    homeFragmentBinding.list.setVisibility(View.VISIBLE);
                    isClick = !isClick;
                } else {
                    homeFragmentBinding.cash.setChecked(false);
                    homeFragmentBinding.installment.setChecked(false);
                    mealAdapter.send("");
                    homeFragmentBinding.list.setVisibility(View.GONE);
                    isClick = !isClick;
                }
            }
        });
        homeFragmentBinding.rgList.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                mealAdapter.send("");
                if (checkedId == homeFragmentBinding.cash.getId()) {
                    mealAdapter.send(homeFragmentBinding.cash.getText());
                } else if (checkedId == homeFragmentBinding.installment.getId()) {
                    mealAdapter.send(homeFragmentBinding.installment.getText());
                }
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