package com.menu.androidcourseproject.ui.purchase_list;

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

import com.menu.androidcourseproject.R;
import com.menu.androidcourseproject.adapters.PurchaseAdapter;
import com.menu.androidcourseproject.databinding.PurchaseListFragmentBinding;

public class PurchaseListFragment extends Fragment {

    private PurchaseListViewModel mViewModel;
    private PurchaseListFragmentBinding purchaseListFragmentBinding;
    private PurchaseAdapter purchaseAdapter;

    public static PurchaseListFragment newInstance() {
        return new PurchaseListFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        purchaseListFragmentBinding = PurchaseListFragmentBinding.inflate(getLayoutInflater());
        return purchaseListFragmentBinding.getRoot();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(PurchaseListViewModel.class);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        purchaseAdapter = new PurchaseAdapter();
        purchaseListFragmentBinding.purchaseRecyclerView.setItemViewCacheSize(20);
        purchaseListFragmentBinding.purchaseRecyclerView.setHasFixedSize(true);
        purchaseListFragmentBinding.purchaseRecyclerView.setAdapter(purchaseAdapter);
        mViewModel.getPurchaseListAdapterLiveData(requireActivity().getSharedPreferences(
                getString(R.string.shared_key), Context.MODE_PRIVATE)
                .getInt(getString(R.string.login), 0))
                // get all purchase for id user just!
                .observe(getViewLifecycleOwner(), purchaseListAdapters -> {
                    purchaseAdapter.setPurchaseList(purchaseListAdapters);
                });
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                Navigation.findNavController(requireView()).navigate(R.id.settingsFragment);
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(requireActivity(), callback);

    }
}