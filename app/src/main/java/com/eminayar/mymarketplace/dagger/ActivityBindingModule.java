package com.eminayar.mymarketplace.dagger;

import com.eminayar.mymarketplace.LoginActivity;
import com.eminayar.mymarketplace.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;


@Module
public abstract class ActivityBindingModule {

    @ContributesAndroidInjector
    abstract LoginActivity provideLoginActivity();

    @ContributesAndroidInjector
    abstract MainActivity provideMainActivity();
}