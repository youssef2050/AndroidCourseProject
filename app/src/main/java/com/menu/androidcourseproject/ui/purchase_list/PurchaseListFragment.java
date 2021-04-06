package com.menu.androidcourseproject.ui.purchase_list;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

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
        mViewModel.getPurchaseListAdapterLiveData().observe(getViewLifecycleOwner(), purchaseListAdapters -> {
            purchaseAdapter.setPurchaseList(purchaseListAdapters);
        });

    }
}