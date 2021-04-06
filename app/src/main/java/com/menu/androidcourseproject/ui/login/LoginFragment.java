package com.menu.androidcourseproject.ui.login;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.menu.androidcourseproject.R;
import com.menu.androidcourseproject.databinding.LoginFragmentBinding;
import com.menu.androidcourseproject.model.User;

public class LoginFragment extends Fragment {

    private LoginViewModel mViewModel;
    private LoginFragmentBinding loginFragmentBinding;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        loginFragmentBinding = LoginFragmentBinding.inflate(getLayoutInflater());
        return loginFragmentBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        sharedPreferences = getActivity().getSharedPreferences(getString(R.string.shared_key), Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (sharedPreferences.getInt(getString(R.string.login), 0) >= 1) {
            transition(view, R.id.newItemFragment);
        }
        if (getArguments() != null) {
            loginFragmentBinding.loginUsername.setText(getArguments().getString(getString(R.string.username_key)));
            loginFragmentBinding.loginPassword.setText(getArguments().getString(getString(R.string.password_key)));
        }
        loginFragmentBinding.butLogin.setOnClickListener(v -> login(view));

        loginFragmentBinding.butSinup.setOnClickListener(v -> transition(view, R.id.registerFragment));

    }

    private void transition(@NonNull View view, int FragmentId) {
        Navigation.findNavController(view).navigate(FragmentId);
    }

    private void login(@NonNull View view) {
        String username = loginFragmentBinding.loginUsername.getText().toString();
        String password = loginFragmentBinding.loginPassword.getText().toString();
        if (TextUtils.isEmpty(username)) {
            Toast.makeText(getContext(), "username is empty", Toast.LENGTH_SHORT).show();
        } else if (!isValidPassword(password)) {
            Toast.makeText(getContext(), "password is not valid", Toast.LENGTH_SHORT).show();
        } else {
            mViewModel.login(username, password).observe(getViewLifecycleOwner(), new Observer<User>() {
                @Override
                public void onChanged(User user) {
                    if (user != null) {
                        transition(view, R.id.homeFragment);
                        if (loginFragmentBinding.rememberMe.isChecked()) {
                            editor.putInt(getString(R.string.login), user.getId());
                            editor.apply();
                        }
                    } else {
                        Toast.makeText(getContext(), "error in username or password", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    public boolean isValidPassword(String password) {
        System.out.println("is password");
        return password != null && !TextUtils.isEmpty(password) && password.length() >= 8;
    }
}