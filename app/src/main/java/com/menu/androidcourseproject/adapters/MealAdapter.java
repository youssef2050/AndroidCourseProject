package com.menu.androidcourseproject.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.menu.androidcourseproject.model.Meal;
import com.menu.androidcourseproject.R;
import com.menu.androidcourseproject.databinding.MealItemBinding;

import java.util.ArrayList;
import java.util.List;

public class MealAdapter extends RecyclerView.Adapter<MealAdapter.MyHolder> {
    private List<Meal> meals;
    private onClickButtons onClickButtons;

    public MealAdapter(MealAdapter.onClickButtons onClickButtons) {
        this.onClickButtons = onClickButtons;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        MealItemBinding mealItemBinding = DataBindingUtil.inflate(layoutInflater, R.layout.meal_item, parent, false);
        return new MyHolder(mealItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        Meal meal = meals.get(position);
        if (meal != null) {
            holder.mealItemBinding.setMeal(meal);
            holder.mealItemBinding.executePendingBindings();
        }
    }

    @Override
    public int getItemCount() {
        if (meals == null)
            meals = new ArrayList<>();
        return meals.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        MealItemBinding mealItemBinding;

        public MyHolder(@NonNull MealItemBinding mealItemBinding) {
            super(mealItemBinding.getRoot());
            this.mealItemBinding = mealItemBinding;
            mealItemBinding.butShare.setOnClickListener(v -> onClickButtons.share(meals.get(getAdapterPosition())));
            mealItemBinding.butFav.setOnClickListener(v -> onClickButtons.fav(meals.get(getAdapterPosition())));
        }
    }

    public void setMeals(List<Meal> meals) {
        this.meals = meals;
        notifyDataSetChanged();
    }

    public interface onClickButtons {

        void share(Meal meal);

        void fav(Meal meal);
    }
}
