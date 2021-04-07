package com.menu.androidcourseproject.ui.purchase_list;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.menu.androidcourseproject.database.room.repository.PurchaseRepository;
import com.menu.androidcourseproject.model.PurchaseListAdapter;

import java.util.List;

public class PurchaseListViewModel extends AndroidViewModel {
    private PurchaseRepository purchaseRepository;
    private LiveData<List<PurchaseListAdapter>> purchaseListAdapterLiveData;

    public PurchaseListViewModel(@NonNull Application application) {
        super(application);
        purchaseRepository = new PurchaseRepository(application);

    }

    public LiveData<List<PurchaseListAdapter>> getPurchaseListAdapterLiveData(int id) {
        purchaseListAdapterLiveData = purchaseRepository.getAllPurchases(id);
        return purchaseListAdapterLiveData;
    }
}