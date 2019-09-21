package com.eminayar.mymarketplace.data.network;

import com.eminayar.mymarketplace.data.models.ShoppingDetail;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface ProductService {

    @GET("/")
    Single<List<ShoppingDetail>> getShoppingList();

}