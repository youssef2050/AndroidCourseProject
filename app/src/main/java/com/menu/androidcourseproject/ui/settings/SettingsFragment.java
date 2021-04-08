package com.menu.androidcourseproject.ui.settings;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.menu.androidcourseproject.R;
import com.menu.androidcourseproject.databinding.SettingsFragmentBinding;
import com.menu.androidcourseproject.model.PurchaseListAdapter;

public class SettingsFragment extends Fragment {

    private SettingsViewModel mViewModel;
    private SettingsFragmentBinding settingsFragmentBinding;
    private SharedPreferences sharedPreferences;
    private int user_id;

    public static SettingsFragment newInstance() {
        return new SettingsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        settingsFragmentBinding = SettingsFragmentBinding.inflate(getLayoutInflater());
        return settingsFragmentBinding.getRoot();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(SettingsViewModel.class);
        sharedPreferences = requireActivity().getSharedPreferences(getString(R.string.shared_key), Context.MODE_PRIVATE);
        user_id = sharedPreferences.getInt(getString(R.string.login), 0);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        settingsFragmentBinding.clearAllPurchase.setOnClickListener(v -> mViewModel.deleteAllPurchase());
        settingsFragmentBinding.logout.setOnClickListener(v -> logout());
        settingsFragmentBinding.showAllPurchase.setOnClickListener(v -> getNavigate(R.id.purchaseListFragment));
        settingsFragmentBinding.showProfile.setOnClickListener(v -> getNavigate(R.id.profileFragment));
        settingsFragmentBinding.addNewItem.setOnClickListener(v -> getNavigate(R.id.newItemFragment));
        settingsFragmentBinding.changePassword.setOnClickListener(v -> Navigation.findNavController(requireView()).
                navigate(R.id.changePasswordFragment));
        settingsFragmentBinding.showLastPurchase.setOnClickListener(v -> getAlertLastPurchase());
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                Navigation.findNavController(requireView()).navigate(R.id.homeFragment);
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(requireActivity(), callback);
    }

    private void getAlertLastPurchase() {
        mViewModel.getLastPurchase(user_id).observe(getViewLifecycleOwner(), purchaseListAdapters -> {
            if (purchaseListAdapters.size() != 0) {
                PurchaseListAdapter purchaseListAdapterMax = purchaseListAdapters.get(0);
                for (int i = 1; i < purchaseListAdapters.size(); i++) {
                    if (purchaseListAdapters.get(i).getTimeInMillis() > purchaseListAdapterMax.getTimeInMillis()) {
                        purchaseListAdapterMax = purchaseListAdapters.get(i);
                    }
                }
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(requireContext());
                alertDialog.setTitle("last Purchase");
                final TextView price = new TextView(requireContext());
                final TextView mealTitle = new TextView(requireContext());
                final TextView date = new TextView(requireContext());
                price.setBackground(requireActivity().getResources().getDrawable(R.drawable.price_background));
                price.setGravity(Gravity.CENTER);
                price.setText(String.valueOf(purchaseListAdapterMax.getPrise() * purchaseListAdapterMax.getNumberMeal()));
                mealTitle.setText(purchaseListAdapterMax.getMealTitle());
                date.setText(purchaseListAdapterMax.getDate());
                LinearLayout layout = new LinearLayout(requireContext());
                layout.setPadding(10, 10, 10, 10);
                layout.setOrientation(LinearLayout.HORIZONTAL);
                LinearLayout ll = new LinearLayout(requireContext());
                ll.setOrientation(LinearLayout.VERTICAL);
                ll.addView(mealTitle);
                ll.addView(date);
                layout.addView(price);
                layout.addView(ll);
                alertDialog.setView(layout);
                AlertDialog alert11 = alertDialog.create();
                alert11.show();
            } else
                Toast.makeText(requireContext(), "is Empty", Toast.LENGTH_SHORT).show();
        });
    }


    private void getNavigate(int id) {
        Navigation.findNavController(requireView()).navigate(id);
    }

    private void logout() {

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(getString(R.string.login), 0);
        editor.apply();
        getNavigate(R.id.loginFragment);
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(requireActivity(), callback);
    }

}