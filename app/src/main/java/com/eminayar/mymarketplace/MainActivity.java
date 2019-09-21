package com.eminayar.mymarketplace;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.eminayar.mymarketplace.base.BaseActivity;
import com.eminayar.mymarketplace.dagger.util.ViewModelFactory;
import com.eminayar.mymarketplace.databinding.ActivityMainBinding;
import com.eminayar.mymarketplace.helpers.SharedPreferenceHelper;
import com.eminayar.mymarketplace.viewmodels.ExpandableListViewModel;

import javax.inject.Inject;

import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @Inject
    ViewModelFactory viewModelFactory;
    @Inject
    SharedPreferenceHelper sharedPreferenceHelper;

    private ExpandableListViewModel mViewModel;
    private ActivityMainBinding mActivityBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = ViewModelProviders.of(this, viewModelFactory).get(ExpandableListViewModel.class);

        mActivityBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        ButterKnife.bind(this);
        mActivityBinding.setLifecycleOwner(this);
        mActivityBinding.setListViewModel(mViewModel);

        observableViewModel();
    }

    public static Intent createIntent (Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        return intent;
    }

    private void observableViewModel() {
        mViewModel.getShoppingDetails().observe(this, list -> {
            if(list != null) mActivityBinding.listView.setVisibility(View.VISIBLE);
        });

        mViewModel.getError().observe(this, isError -> {
            if (isError != null) if(isError) {
                mActivityBinding.errorLayout.setVisibility(View.VISIBLE);
                mActivityBinding.listView.setVisibility(View.GONE);
            }else {
                mActivityBinding.errorLayout.setVisibility(View.GONE);
            }
        });

        mViewModel.getLoading().observe(this, isLoading -> {
            if (isLoading != null) {
                mActivityBinding.progressBar.setVisibility(isLoading ? View.VISIBLE : View.GONE);
                if (isLoading) {
                    mActivityBinding.errorLayout.setVisibility(View.GONE);
                    mActivityBinding.listView.setVisibility(View.GONE);
                }
            }
        });
    }
}
