package com.eminayar.mymarketplace.dagger;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.eminayar.mymarketplace.viewmodels.ShoppingListViewModel;
import com.eminayar.mymarketplace.viewmodels.LoginViewModel;
import com.eminayar.mymarketplace.dagger.util.ViewModelFactory;
import com.eminayar.mymarketplace.dagger.util.ViewModelKey;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Singleton
@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel.class)
    abstract ViewModel bindLoginViewModel(LoginViewModel loginViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(ShoppingListViewModel.class)
    abstract ViewModel bindExpandableListViewModel(ShoppingListViewModel shoppingListViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);
}