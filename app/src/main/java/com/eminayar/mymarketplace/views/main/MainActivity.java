package com.eminayar.mymarketplace.views.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.eminayar.mymarketplace.R;
import com.eminayar.mymarketplace.base.BaseActivity;
import com.eminayar.mymarketplace.dagger.util.ViewModelFactory;
import com.eminayar.mymarketplace.databinding.ActivityMainBinding;
import com.eminayar.mymarketplace.helpers.SharedPreferenceHelper;
import com.eminayar.mymarketplace.views.main.adapters.ShoppingListAdapter;

import javax.inject.Inject;

public class MainActivity extends BaseActivity {

    @Inject
    ViewModelFactory viewModelFactory;
    @Inject
    SharedPreferenceHelper sharedPreferenceHelper;

    private ShoppingListViewModel mViewModel;
    private ActivityMainBinding mActivityBinding;

    private static final String KEY_USER = "keys_user";
    private static final String KEY_REMEMBER_SELECTED = "keys_remember_selected";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = ViewModelProviders.of(this, viewModelFactory).get(ShoppingListViewModel.class);

        mActivityBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mActivityBinding.setLifecycleOwner(this);
        mActivityBinding.setListViewModel(mViewModel);

        mActivityBinding.listView.setAdapter(new ShoppingListAdapter(mViewModel, this));

        observableViewModel();
    }

    public static Intent createIntent(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        return intent;
    }

    private void observableViewModel() {
        mViewModel.getShoppingDetails().observe(this, list -> {
            if (list != null) mActivityBinding.listView.setVisibility(View.VISIBLE);
        });

        //I'm showing an error layout if somehow network request dont work, you may try it by disabling
        // internet connection at your device
        mViewModel.getError().observe(this, isError -> {
            if (isError != null) if (isError) {
                mActivityBinding.errorLayout.setVisibility(View.VISIBLE);
                mActivityBinding.listView.setVisibility(View.GONE);
            } else {
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

        mViewModel.getShowExitDialogEvent().observe(this, willShow -> {
            if (willShow != null) {
                if (willShow) {
                    //I am setting it false when I show dialog to be able to show it next time
                    mViewModel.getShowExitDialogEvent().setValue(false);
                    new AlertDialog.Builder(MainActivity.this)
                            .setTitle(getString(R.string.dialog_title_exit))
                            .setMessage(getString(R.string.dialog_message_exit_confirmation))
                            .setPositiveButton(getString(R.string.label_yes), (dialogInterface, i) -> {
                                // Remove shared pref values to make user login next time
                                sharedPreferenceHelper.removeObject(KEY_USER);
                                sharedPreferenceHelper.removeObject(KEY_REMEMBER_SELECTED);
                                finish();
                            })
                            .setNegativeButton(getString(R.string.label_no), (dialogInterface, i) -> {
                                dialogInterface.dismiss();
                            })
                            .show();
                }
            }
        });
    }
}
