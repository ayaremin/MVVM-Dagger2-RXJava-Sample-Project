package com.eminayar.mymarketplace;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.eminayar.mymarketplace.base.BaseActivity;
import com.eminayar.mymarketplace.helpers.SharedPreferenceHelper;

import javax.inject.Inject;

public class MainActivity extends BaseActivity {

    @Inject
    SharedPreferenceHelper sharedPreferenceHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public static Intent createIntent (Context context) {
        return new Intent(context, MainActivity.class);
    }
}
