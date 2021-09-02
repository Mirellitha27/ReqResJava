package com.iwebsapp.reqresjava.ui.login.interactor;

import android.util.Log;

import androidx.annotation.NonNull;

import com.iwebsapp.reqresjava.api.ApiService;
import com.iwebsapp.reqresjava.api.RetrofitClient;
import com.iwebsapp.reqresjava.model.home.Datum;
import com.iwebsapp.reqresjava.model.login.ResponseLogin;
import com.iwebsapp.reqresjava.ui.login.presenter.LoginPresenter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginInteractorImpl implements LoginInteractor {
    private LoginPresenter presenter;
    private final String TAG= this.getClass().getSimpleName();
    private final ApiService apiService = RetrofitClient.getApiClient().create(ApiService.class);

    public LoginInteractorImpl(LoginPresenter presenter) {
        this.presenter = presenter;
    }

    public void login(String email, String password) {
        Call<ResponseLogin> call;
        call = apiService.login(email, password);
        call.enqueue(new Callback<ResponseLogin>() {
            @Override
            public void onResponse(@NonNull Call<ResponseLogin> call, @NonNull Response<ResponseLogin> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.d(TAG, "response " + response.body().getToken());
                    presenter.saveUser(response.body().getToken());
                    presenter.goMain();
                }
            }
            @Override
            public void onFailure(@NonNull Call<ResponseLogin> call, @NonNull Throwable t) {
                Log.d(TAG, "onFailure " + t.getMessage());
            }
        });
    }
}
