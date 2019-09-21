package com.eminayar.mymarketplace.viewmodels;

import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.eminayar.mymarketplace.data.models.User;

import javax.inject.Inject;

public class LoginViewModel extends ViewModel {

    public MutableLiveData<String> userName = new MutableLiveData<>();
    public MutableLiveData<String> password = new MutableLiveData<>();

    private MutableLiveData<User> userMutableLiveData;

    @Inject
    public LoginViewModel() {
    }

    public MutableLiveData<User> getUser() {
        if (userMutableLiveData == null) {
            userMutableLiveData = new MutableLiveData<>();
        }

        return userMutableLiveData;
    }

    public void onLoginClicked(View view) {
        User user = new User(userName.getValue(), password.getValue());
        userMutableLiveData.setValue(user);
    }
}