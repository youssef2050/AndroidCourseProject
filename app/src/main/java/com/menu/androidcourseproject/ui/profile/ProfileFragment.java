package com.menu.androidcourseproject.ui.profile;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.menu.androidcourseproject.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ProfileFragment extends Fragment {

    private ProfileViewModel mViewModel;

    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.profile_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
        // TODO: Use the ViewModel
    }

    public Bitmap loadImageFromStorage(String path, String name) {
        if (!path.equals("")) {
            try {
                File f = new File(path, name + ".jpg");
                return BitmapFactory.decodeStream(new FileInputStream(f));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        return BitmapFactory.decodeResource(getResources(), R.drawable.ic_account);
    }
}