package com.menu.androidcourseproject.ui.details;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.menu.androidcourseproject.R;
import com.menu.androidcourseproject.databinding.DetailsFragmentBinding;
import com.menu.androidcourseproject.model.Meal;
import com.menu.androidcourseproject.model.Purchase;

import java.util.Calendar;
import java.util.Objects;

import static android.content.Context.MODE_PRIVATE;

public class DetailsFragment extends Fragment {

    private DetailsViewModel mViewModel;
    private DetailsFragmentBinding detailsFragmentBinding;
    private double number = 1;

    public static DetailsFragment newInstance() {
        return new DetailsFragment();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        detailsFragmentBinding = DetailsFragmentBinding.inflate(getLayoutInflater());
        return detailsFragmentBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(DetailsViewModel.class);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Meal meal = getArguments().getParcelable(getString(R.string.key_meal));
        detailsFragmentBinding.setMeal(meal);
        detailsFragmentBinding.detailsnumber.setText(number + "");
        double price = meal.getPrise();
        detailsFragmentBinding.detailsSum.setText("Total bill :" + price + "");
        detailsFragmentBinding.incNumber.setOnClickListener(v -> {
            detailsFragmentBinding.detailsnumber.setText(++number + "");
            detailsFragmentBinding.detailsSum.setText("Total bill :" + (number * price) + "");
        });
        detailsFragmentBinding.decNumber.setOnClickListener(v -> {
            if (number > 1) {
                detailsFragmentBinding.detailsnumber.setText(--number + "");
                detailsFragmentBinding.detailsSum.setText("Total bill :" + (number * price) + "");
            }
        });
        detailsFragmentBinding.detailsFav.setOnClickListener(v -> {
            meal.setFavorite(!meal.isFavorite());
            mViewModel.Update(meal);
            if (meal.isFavorite()) {
                detailsFragmentBinding.detailsFav.setIcon(getActivity().getDrawable(R.drawable.ic_favorite));
            } else {
                detailsFragmentBinding.detailsFav.setIcon(getActivity().getDrawable(R.drawable.ic_favorite_border));
            }
        });
        detailsFragmentBinding.detailsSave.setOnClickListener(v -> {
            @SuppressLint("UseRequireInsteadOfGet") SharedPreferences sharedPreferences = Objects.requireNonNull(getActivity()).getSharedPreferences(getString(R.string.shared_key), MODE_PRIVATE);
            System.out.println(getDate());
            Purchase purchase = new Purchase(meal.getId(), sharedPreferences.getInt(getString(R.string.login), 0), getDate(), (int) number, Calendar.getInstance().getTimeInMillis());
            mViewModel.insert(purchase);
            Navigation.findNavController(getView()).navigate(R.id.homeFragment);
            Toast.makeText(getContext(), "it's OK ", Toast.LENGTH_SHORT).show();
        });
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                Navigation.findNavController(requireView()).navigate(R.id.homeFragment);
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(requireActivity(), callback);
    }

    private String getDate() {
        return Calendar.getInstance().get(Calendar.DAY_OF_MONTH) + "/" + (Calendar.getInstance().get(Calendar.MONTH) + 1) + "/" + Calendar.getInstance().get(Calendar.YEAR) + "-" + Calendar.getInstance().get(Calendar.HOUR_OF_DAY) + ":" + Calendar.getInstance().get(Calendar.MINUTE);
    }
}