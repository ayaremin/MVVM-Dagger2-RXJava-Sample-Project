package com.eminayar.mymarketplace.views.login;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.eminayar.mymarketplace.R;
import com.eminayar.mymarketplace.base.BaseActivity;
import com.eminayar.mymarketplace.dagger.util.ViewModelFactory;
import com.eminayar.mymarketplace.data.models.User;
import com.eminayar.mymarketplace.databinding.ActivityLoginBinding;
import com.eminayar.mymarketplace.helpers.SharedPreferenceHelper;
import com.eminayar.mymarketplace.views.main.MainActivity;

import java.util.Objects;

import javax.inject.Inject;

public class LoginActivity extends BaseActivity implements MediaPlayer.OnCompletionListener {

    @Inject
    ViewModelFactory viewModelFactory;
    @Inject
    SharedPreferenceHelper sharedPreferenceHelper;

    private LoginViewModel mViewModel;
    private ActivityLoginBinding mActivityBinding;

    private static final String KEY_USER = "keys_user";
    private static final String KEY_REMEMBER_SELECTED = "keys_remember_selected";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = ViewModelProviders.of(this, viewModelFactory).get(LoginViewModel.class);

        // Check here if user logged in successfully and remember switch works
        if (checkLoginStatus()) {
            startActivity(MainActivity.createIntent(this));
            return;
        }

        mActivityBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        mActivityBinding.setLifecycleOwner(this);
        mActivityBinding.setLoginViewModel(mViewModel);

        setUI();
        // Listen changes at user model to decide if login is successful or not
        listenViewModelChanges();
    }

    private boolean checkLoginStatus() {
        User user = sharedPreferenceHelper.getObject(KEY_USER, User.class);
        Boolean isRememberSelected = sharedPreferenceHelper.getObject(KEY_REMEMBER_SELECTED, Boolean.class);

        if (user != null && isRememberSelected) {
            // That means user already logged in once and also checked switch box for remember next time
            return true;
        }
        return false;
    }

    private void setUI() {

    }

    private void listenViewModelChanges() {
        mViewModel.getUser().observe(this, user -> {
            if (TextUtils.isEmpty(Objects.requireNonNull(user).getUserName())) {
                mActivityBinding.userNameEditText.setError(getString(R.string.error_username_empty));
                mActivityBinding.userNameEditText.requestFocus();
            } else if (!user.isPasswordLongEnough()) {
                mActivityBinding.paswordEditText.setError(getString(R.string.error_password_length));
                mActivityBinding.paswordEditText.requestFocus();
            } else if (user.isUsernamePasswordCorrect()) {
                // this means that we will make user log in to app
                if (mActivityBinding.getRememberSelected()) {
                    // remember me checkbox checked, next time user open app we won't ask login
                    // I'm putting whole user object to shared prefs to see username in home screen
                    sharedPreferenceHelper.putObject(KEY_REMEMBER_SELECTED, true);
                } else {
                    sharedPreferenceHelper.putObject(KEY_REMEMBER_SELECTED, false);
                }
                sharedPreferenceHelper.putObject(KEY_USER, user);
                startActivity(MainActivity.createIntent(this));

            } else {
                Toast.makeText(this, R.string.label_wrong_user_name_or_password, Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        // if footage video comes to end re-start it
        mediaPlayer.start();
    }
}
