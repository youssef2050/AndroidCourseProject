package com.menu.androidcourseproject.ui.register;

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

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.menu.androidcourseproject.R;
import com.menu.androidcourseproject.databinding.RegisterFragmentBinding;
import com.menu.androidcourseproject.general.AddImages;
import com.menu.androidcourseproject.general.DateAndTime;
import com.menu.androidcourseproject.model.User;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.app.Activity.RESULT_OK;
import static com.menu.androidcourseproject.general.AddImages.requestCodeImage;
import static com.menu.androidcourseproject.general.AddImages.requestCodePermission;

public class RegisterFragment extends Fragment {

    private RegisterViewModel mViewModel;
    private RegisterFragmentBinding registerFragmentBinding;
    private AddImages addImages;
    private String url = "";
    private Bitmap image;

    public static RegisterFragment newInstance() {
        return new RegisterFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        registerFragmentBinding = RegisterFragmentBinding.inflate(getLayoutInflater());
        return registerFragmentBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(RegisterViewModel.class);
        addImages = new AddImages(requireActivity());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        registerFragmentBinding.regButSinup.setOnClickListener(v -> sinIn());
        registerFragmentBinding.regButCalendar.setOnClickListener(v -> {
            DateAndTime dateAndTime = new DateAndTime(getContext());
            dateAndTime.updateDate(registerFragmentBinding.regBirthDate);
        });
        registerFragmentBinding.regAddImage.setOnClickListener(v -> {
            getPermission();
        });
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                Navigation.findNavController(requireView()).navigate(R.id.loginFragment);
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(requireActivity(), callback);
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
        System.out.println("requestCode :" + requestCode);
        System.out.println("resultCode : " + resultCode);
        System.out.println("data : " + data);
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == requestCodeImage && data != null) {
            Uri uri = data.getData();
            try {
                InputStream is = getActivity().getContentResolver().openInputStream(uri);
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inPreferredConfig = Bitmap.Config.RGB_565;
                image = BitmapFactory.decodeStream(is, null, options);
                registerFragmentBinding.regImageProfile.setImageBitmap(image);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }


    private void sinIn() {
        User user = new User(registerFragmentBinding.regFullName.getText().toString(),
                registerFragmentBinding.regEmail.getText().toString(),
                registerFragmentBinding.regUsername.getText().toString(),
                registerFragmentBinding.regPassword.getText().toString().equals(registerFragmentBinding.regRePassword.getText().toString()) ? registerFragmentBinding.regPassword.getText().toString() : "",
                registerFragmentBinding.regCodePhone.getSelectedCountryEnglishName(),
                registerFragmentBinding.regBirthDate.getText().toString(),
                registerFragmentBinding.regCodePhone.getSelectedCountryCode(),
                registerFragmentBinding.regPhoneNumber.getText().toString(),
                registerFragmentBinding.regAdministrator.isChecked(),
                registerFragmentBinding.regMale.isChecked(),
                getImage(),
                false);
        if (user.isOk()) {
            mViewModel.checkUsernameAndEmail(user.getEmail(), user.getUsername()).observe(getViewLifecycleOwner(), new Observer<User>() {
                @Override
                public void onChanged(User users) {
                    if (users == null) {
                        user.setURLImage(addImages.saveImageInStorage(image, "profileImage", user.getUsername()));
                        mViewModel.insert(user);
                        Bundle bundle = new Bundle();
                        bundle.putString(getString(R.string.username_key), user.getUsername());
                        bundle.putString(getString(R.string.password_key), user.getPassword());
                        Navigation.findNavController(getView()).navigate(R.id.loginFragment, bundle);
                    } else
                        Toast.makeText(requireContext(), "the username or email is found!", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Map<String, Boolean> errors = user.getErrors();
            for (String key : user.getErrors().keySet()) {
                if (!errors.get(key)) {
                    Toast.makeText(getContext(), "Make sure your " + key + "is correct", Toast.LENGTH_LONG).show();
                    return;
                }
            }

        }
    }

    private String getImage() {
        return url;
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