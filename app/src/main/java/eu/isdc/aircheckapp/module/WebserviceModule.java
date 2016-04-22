package eu.isdc.aircheckapp.module;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import eu.isdc.aircheckapp.Constants;
import eu.isdc.aircheckapp.api.AircheckEndpoint;
import eu.isdc.aircheckapp.api.AircheckService;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;

/**
 * Provides common webservices needs.
 * Created by Mihai.Traistaru on 22-Apr-16.
 */
@Module
public class WebserviceModule {

    @Singleton
    @Provides
    AircheckEndpoint provideAircheckEndpoint(RestAdapter restAdapter) {
        return restAdapter.create(AircheckEndpoint.class);
    }

    @Singleton
    @Provides
    RestAdapter provideAircheckRestAdapter(OkClient client, RequestInterceptor requestInterceptor, Gson gson) {
        return new RestAdapter.Builder()
                .setClient(client)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(Constants.ENDPOINT)
                .setRequestInterceptor(requestInterceptor)
                .setConverter(new GsonConverter(gson))
                .build();
    }

    @Singleton
    @Provides
    AircheckService provideAircheckService(Context context) {
        return new AircheckService(context);
    }

    @Singleton
    @Provides
    Gson provideGson() {
        return new GsonBuilder().create();
    }

    @Singleton
    @Provides
    OkClient provideClient() {
        return new OkClient(new OkHttpClient());
    }

    @Singleton
    @Provides
    RequestInterceptor provideRequestInterceptor() {
        return new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade request) {
                request.addHeader("Accept", "application/json");
                request.addHeader("Content-Type", "application/json");
            }
        };
    }
}