package com.eminayar.mymarketplace.dagger;

import android.content.Context;
import android.preference.PreferenceManager;

import com.eminayar.mymarketplace.data.network.ProductService;
import com.eminayar.mymarketplace.helpers.SharedPreferenceHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Singleton
@Module(includes = {ViewModelModule.class})
class ApplicationModule {

    //We may define our Base Url and can create retrofit instance in singleton app module because it will be used everywhere
    private static final String BASE_URL = "https://kariyertechchallenge.mockable.io/";

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
    static ProductService provideRetrofitService(Retrofit retrofit) {
        return retrofit.create(ProductService.class);
    }

    @Singleton
    @Provides
    static SharedPreferenceHelper provideSharedPreferenceHelper(Context context) {
        return new SharedPreferenceHelper(PreferenceManager.getDefaultSharedPreferences(context));
    }
}