package com.menu.androidcourseproject.ui.new_meal;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.menu.androidcourseproject.R;
import com.menu.androidcourseproject.databinding.NewItemFragmentBinding;
import com.menu.androidcourseproject.general.AddImages;
import com.menu.androidcourseproject.model.Meal;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Random;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.app.Activity.RESULT_OK;
import static com.menu.androidcourseproject.general.AddImages.requestCodeImage;
import static com.menu.androidcourseproject.general.AddImages.requestCodePermission;

public class NewItemFragment extends Fragment {

    private NewItemViewModel mViewModel;
    private NewItemFragmentBinding newItemFragmentBinding;
    private AddImages addImages;
    private Bitmap image;

    public static NewItemFragment newInstance() {
        return new NewItemFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        newItemFragmentBinding = NewItemFragmentBinding.inflate(getLayoutInflater());
        return newItemFragmentBinding.getRoot();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(NewItemViewModel.class);
        addImages = new AddImages(requireActivity());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        newItemFragmentBinding.newMealAdd.setOnClickListener(v -> {
            getPermission();
        });
        newItemFragmentBinding.newMealSave.setOnClickListener(v -> {
            saveMeal();
        });
    }

    private void saveMeal() {
        Meal meal = new Meal(
                newItemFragmentBinding.newMealName.getText().toString(),
                newItemFragmentBinding.newMealDescription.getText().toString(),
                newItemFragmentBinding.newMealPrice.getText().toString().equals("") ? 0 : Double.parseDouble(Objects.requireNonNull(newItemFragmentBinding.newMealPrice.getText().toString())),
                image != null ? addImages.saveImageInStorage(image, "mealImages", System.currentTimeMillis() + "") :
                        requireActivity().getSharedPreferences(getString(R.string.shared_key), Context.MODE_PRIVATE).getString(getString(R.string.url_default), ""),
                newItemFragmentBinding.newMealCash.isChecked(),
                new Random().nextInt(5),
                false
        );
        if (meal.isOK()) {
            mViewModel.insert(meal);
            Navigation.findNavController(getView()).navigate(R.id.homeFragment);
        } else {
            Toast.makeText(getContext(), "Make sure to input", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED &&
                grantResults[1] == PackageManager.PERMISSION_GRANTED &&
                requestCode == requestCodePermission) {
            openGallery();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        System.out.println("URI : " + data);
        if (resultCode == RESULT_OK && requestCode == requestCodeImage && data != null) {
            Uri uri = data.getData();
            try {
                InputStream is = getActivity().getContentResolver().openInputStream(uri);
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inPreferredConfig = Bitmap.Config.RGB_565;
                image = BitmapFactory.decodeStream(is, null, options);
                newItemFragmentBinding.newMealImage.setImageBitmap(image);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private void getPermission() {
        if (Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(requireActivity(), READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission(requireActivity(), WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(requireActivity(), new String[]{READ_EXTERNAL_STORAGE,
                                WRITE_EXTERNAL_STORAGE},
                        requestCodePermission);
            } else {
                openGallery();
            }
        }
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        if (intent.resolveActivity(requireActivity().getPackageManager()) != null)
            startActivityForResult(intent, requestCodeImage);
    }
}