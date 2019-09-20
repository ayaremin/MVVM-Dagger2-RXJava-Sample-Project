package com.eminayar.mymarketplace.dagger;

import android.content.Context;

import com.eminayar.mymarketplace.base.BaseApplication;
import com.eminayar.mymarketplace.data.network.RepositoryService;
import com.eminayar.mymarketplace.helpers.SharedPreferenceHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Singleton
@Module(includes = ViewModelModule.class)
class ApplicationModule {

    //We may define our Base Url and can create retrofit instance in singleton app module because it will be used everywhere
    private static final String BASE_URL = "https://api.github.com/";

    @Singleton
    @Provides
    static Retrofit provideRetrofit() {
        return new Retrofit.Builder().baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Singleton
    @Provides
    static RepositoryService provideRetrofitService(Retrofit retrofit) {
        return retrofit.create(RepositoryService.class);
    }

    @Singleton
    @Provides
    Context provideContext(BaseApplication application){
        return application;
    }
}