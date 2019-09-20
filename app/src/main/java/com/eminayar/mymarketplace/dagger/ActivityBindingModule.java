package com.eminayar.mymarketplace.dagger;

import com.eminayar.mymarketplace.LoginActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;


@Module
public abstract class ActivityBindingModule {

    @ContributesAndroidInjector
    abstract LoginActivity provideLoginActivity();
}