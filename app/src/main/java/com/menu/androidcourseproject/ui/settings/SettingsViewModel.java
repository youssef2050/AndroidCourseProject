package com.menu.androidcourseproject.ui.settings;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.menu.androidcourseproject.database.room.repository.PurchaseRepository;
import com.menu.androidcourseproject.database.room.repository.UserRepository;
import com.menu.androidcourseproject.model.PurchaseListAdapter;

import java.util.List;

public class SettingsViewModel extends AndroidViewModel {
    private PurchaseRepository purchaseRepository;
    private UserRepository userRepository;

    public SettingsViewModel(@NonNull Application application) {
        super(application);
        purchaseRepository = new PurchaseRepository(application);
        userRepository = new UserRepository(application);
    }

    public void deleteAllPurchase() {
        purchaseRepository.deleteAll();
    }

    public void update(String password, int id) {
        if (password.equals("")) {
            Toast.makeText(getApplication(), "error password changed", Toast.LENGTH_SHORT).show();
        } else
            userRepository.update(password, id);
    }
    public LiveData<List<PurchaseListAdapter>> getLastPurchase(int id){
        return purchaseRepository.getAllPurchases(id);
    }

}