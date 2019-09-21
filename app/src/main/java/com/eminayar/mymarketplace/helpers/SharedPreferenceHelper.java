package com.eminayar.mymarketplace.helpers;


import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import javax.inject.Inject;

public class SharedPreferenceHelper {

    private SharedPreferences mSharedPreferences;
    private Gson mGson;
    private SharedPreferences.Editor mEditor;

    @Inject
    public SharedPreferenceHelper(SharedPreferences sharedPreferences) {
        this.mSharedPreferences = sharedPreferences;
        this.mEditor = mSharedPreferences.edit();
        this.mGson = new Gson();
    }

    public void putObject(String key, Object object) {
        mEditor.putString(key,mGson.toJson(object));
        mEditor.apply();
    }

    public <T> T getObject(String key, Class<T> a) {
        String gson = mSharedPreferences.getString(key, null);
        if (gson == null) {
            return null;
        } else {
            try {
                return mGson.fromJson(gson, a);
            } catch (Exception e) {
                throw e;
            }
        }
    }
}