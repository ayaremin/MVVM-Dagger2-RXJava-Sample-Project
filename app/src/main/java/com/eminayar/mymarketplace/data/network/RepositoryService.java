package com.eminayar.mymarketplace.data.network;

import com.eminayar.mymarketplace.data.models.User;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface RepositoryService {

    @GET("orgs/Google/repos")
    Single<List<User>> getRepositories();

}