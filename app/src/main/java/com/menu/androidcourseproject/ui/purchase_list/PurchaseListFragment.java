package com.menu.androidcourseproject.ui.purchase_list;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.menu.androidcourseproject.R;

public class PurchaseListFragment extends Fragment {

    private PurchaseListViewModel mViewModel;

    public static PurchaseListFragment newInstance() {
        return new PurchaseListFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.purchase_list_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(PurchaseListViewModel.class);
        // TODO: Use the ViewModel
    }

}