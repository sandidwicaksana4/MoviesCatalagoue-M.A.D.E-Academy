package com.app.aplikasiku.moviex.Network;

import com.app.aplikasiku.moviex.BuildConfig;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIItem {

    private APIServer apiServer;
    public APIItem() {
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request original = chain.request();
                        HttpUrl httpUrl = original.url()
                                .newBuilder()
                                .addQueryParameter("api_key", BuildConfig.TMDB_API_KEY)
                                .build();

                        original = original.newBuilder()
                                .url(httpUrl)
                                .build();

                        return chain.proceed(original);
                    }
                })
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(BuildConfig.BASE_URL_TMDB)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiServer = retrofit.create(APIServer.class);
    }

    public APIServer getService() {
        return apiServer;
    }
}
