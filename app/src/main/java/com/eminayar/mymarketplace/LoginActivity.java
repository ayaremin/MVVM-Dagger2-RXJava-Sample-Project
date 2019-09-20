package com.eminayar.mymarketplace;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.TextUtils;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.eminayar.mymarketplace.helpers.SharedPreferenceHelper;
import com.eminayar.mymarketplace.viewmodels.LoginViewModel;
import com.eminayar.mymarketplace.base.BaseActivity;
import com.eminayar.mymarketplace.dagger.util.ViewModelFactory;
import com.eminayar.mymarketplace.data.models.User;
import com.eminayar.mymarketplace.databinding.ActivityLoginBinding;

import java.util.Objects;

import javax.inject.Inject;

import butterknife.ButterKnife;

public class LoginActivity extends BaseActivity implements MediaPlayer.OnCompletionListener {

    @Inject
    ViewModelFactory viewModelFactory;
    @Inject
    private SharedPreferenceHelper mSharedPreferenceHelper;

    private LoginViewModel mViewModel;
    private ActivityLoginBinding mActivityBinding;

    private static final String KEY_USER = "keys_user";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = ViewModelProviders.of(this, viewModelFactory).get(LoginViewModel.class);

        mActivityBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        ButterKnife.bind(this);
        mActivityBinding.setLifecycleOwner(this);
        mActivityBinding.setLoginViewModel(mViewModel);

        setUI();

        listenViewModelChanges ();
    }

    private void setUI() {

    }

    private void listenViewModelChanges() {
        mViewModel.getUser().observe(this, user -> {
            if (TextUtils.isEmpty(Objects.requireNonNull(user).getUserName())) {
                mActivityBinding.userNameEditText.setError("Bir kullanıcı adı girmelisiniz");
                mActivityBinding.userNameEditText.requestFocus();
            }
            else if (!user.isPasswordLongEnough()) {
                mActivityBinding.paswordEditText.setError("Parolanız 6 karakter veya daha uzun olmalıdır.");
                mActivityBinding.paswordEditText.requestFocus();
            }
            else if (user.isUsernamePasswordCorrect()) {
                // this means that we will make user log in to app
                if (user.isCredentialsRemembered()) {
                    // remember me checkbox checked, next time user open app we won't ask login
                    // I'm putting whole user object to shared prefs to see username in home screen
                    mSharedPreferenceHelper.putObject(KEY_USER, user);
                }

                //TODO start other activity

            }
        });
    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        // if footage video comes to end re-start it
        mediaPlayer.start();
    }
}
