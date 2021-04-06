package com.menu.androidcourseproject.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.menu.androidcourseproject.databinding.PurchaseItemBinding;
import com.menu.androidcourseproject.model.PurchaseListAdapter;

import java.util.List;

public class PurchaseAdapter extends RecyclerView.Adapter<PurchaseAdapter.MyHolder> {
    private List<PurchaseListAdapter> purchaseList;

    public PurchaseAdapter() {
    }

    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        PurchaseItemBinding purchaseItemBinding = PurchaseItemBinding.inflate(layoutInflater);
        return new MyHolder(purchaseItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        PurchaseListAdapter purchaseListAdapter = purchaseList.get(position);
        holder.purchaseItemBinding.setPurchase(purchaseListAdapter);
        holder.purchaseItemBinding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        if (purchaseList != null)
            return purchaseList.size();
        return 0;
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        private PurchaseItemBinding purchaseItemBinding;

        public MyHolder(@NonNull PurchaseItemBinding purchaseItemBinding) {
            super(purchaseItemBinding.getRoot());
            this.purchaseItemBinding = purchaseItemBinding;
        }
    }

    public void setPurchaseList(List<PurchaseListAdapter> purchaseList) {
        this.purchaseList = purchaseList;
        notifyDataSetChanged();
    }

}