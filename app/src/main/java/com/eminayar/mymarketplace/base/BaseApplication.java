package com.eminayar.mymarketplace.base;

import com.eminayar.mymarketplace.dagger.ApplicationComponent;
import com.eminayar.mymarketplace.dagger.DaggerApplicationComponent;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

public class BaseApplication extends DaggerApplication {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        ApplicationComponent component = DaggerApplicationComponent.builder().application(this).build();
        component.inject(this);

        return component;
    }
}