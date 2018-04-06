package com.example.ftc.ftc.API;

import android.util.Log;

import com.example.ftc.ftc.Model.Login.User;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RemoteDataSource  {


    HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY);

    OkHttpClient.Builder okBuilder = new OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor);

    Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl("https://elmhackhub.com/")
            .client(okBuilder.build())
            .addConverterFactory(GsonConverterFactory.create());

    Retrofit retrofit = builder.build();

    DataSource dataSource = retrofit.create(DataSource.class);

    public void sendMobileNumber(String mobile) {
        dataSource.sendNumber(mobile).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Log.e("RemoteDataSource", "onResponse: "+response.body());


            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }

    private void sendSMS(String code) {
        dataSource.sendVerification(code).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Log.e("RemoteDataSource", "onResponse: "+response.body());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });

    }
}