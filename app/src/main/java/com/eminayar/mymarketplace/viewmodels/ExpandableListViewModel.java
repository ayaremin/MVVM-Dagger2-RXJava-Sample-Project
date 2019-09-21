package com.eminayar.mymarketplace.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.eminayar.mymarketplace.data.models.ShoppingDetail;
import com.eminayar.mymarketplace.data.network.ProductService;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class ExpandableListViewModel extends ViewModel {

    private CompositeDisposable disposable;
    private ProductService productService;

    private final MutableLiveData<List<ShoppingDetail>> shoppingDetails = new MutableLiveData<>();
    private final MutableLiveData<Boolean> error = new MutableLiveData<>();
    private final MutableLiveData<Boolean> loading = new MutableLiveData<>();

    @Inject
    public ExpandableListViewModel(ProductService productService) {
        this.productService = productService;
        disposable = new CompositeDisposable();
        fetchShoppingDetailList();
    }

    public MutableLiveData<List<ShoppingDetail>> getShoppingDetails() {
        return shoppingDetails;
    }

    public MutableLiveData<Boolean> getError() {
        return error;
    }

    public MutableLiveData<Boolean> getLoading() {
        return loading;
    }

    private void fetchShoppingDetailList() {
        loading.setValue(true);
        disposable.add(productService.getShoppingList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<ShoppingDetail>>() {
                    @Override
                    public void onSuccess(List<ShoppingDetail> value) {
                        error.setValue(false);
                        shoppingDetails.setValue(value);
                        loading.setValue(false);
                    }

                    @Override
                    public void onError(Throwable e) {
                        error.setValue(true);
                        loading.setValue(false);
                    }
                }));
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (disposable != null) {
            disposable.clear();
            disposable = null;
        }
    }
}