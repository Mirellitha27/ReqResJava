package com.iwebsapp.reqresjava.ui.account.interactor;

import android.util.Log;

import androidx.annotation.NonNull;

import com.iwebsapp.reqresjava.api.ApiService;
import com.iwebsapp.reqresjava.api.RetrofitClient;
import com.iwebsapp.reqresjava.model.account.ResponseRegister;
import com.iwebsapp.reqresjava.ui.account.presenter.CreateAccountPresenter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateAccountInteractorImpl implements CreateAccountInteractor {
    private CreateAccountPresenter presenter;
    private final String TAG= this.getClass().getSimpleName();
    private final ApiService apiService = RetrofitClient.getApiClient().create(ApiService.class);

    public CreateAccountInteractorImpl(CreateAccountPresenter presenter) {
        this.presenter = presenter;
    }

    public void register(String email, String password) {
        Call<ResponseRegister> call;
        call = apiService.register(email, password);
        call.enqueue(new Callback<ResponseRegister>() {
            @Override
            public void onResponse(@NonNull Call<ResponseRegister> call, @NonNull Response<ResponseRegister> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.d(TAG, "response " + response.body().getToken());
                }
            }
            @Override
            public void onFailure(@NonNull Call<ResponseRegister> call, @NonNull Throwable t) {
                Log.d(TAG, "onFailure " + t.getMessage());
            }
        });
    }
}
