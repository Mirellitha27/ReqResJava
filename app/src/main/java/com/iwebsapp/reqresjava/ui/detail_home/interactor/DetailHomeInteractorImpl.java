package com.iwebsapp.reqresjava.ui.detail_home.interactor;

import android.util.Log;

import androidx.annotation.NonNull;

import com.iwebsapp.reqresjava.api.ApiService;
import com.iwebsapp.reqresjava.api.RetrofitClient;
import com.iwebsapp.reqresjava.model.detail_home.DetailHomeModel;
import com.iwebsapp.reqresjava.ui.detail_home.presenter.DetailHomePresenter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailHomeInteractorImpl implements DetailHomeInteractor {
    private final DetailHomePresenter presenter;
    private final String TAG= this.getClass().getSimpleName();
    private final ApiService apiService = RetrofitClient.getApiClient().create(ApiService.class);

    public DetailHomeInteractorImpl(DetailHomePresenter presenter) {
        this.presenter = presenter;
    }

    public void getUser(String idUser) {
        Call<DetailHomeModel> call;
        call = apiService.getCurrentUser(idUser);
        call.enqueue(new Callback<DetailHomeModel>() {
            @Override
            public void onResponse(@NonNull Call<DetailHomeModel> call, @NonNull Response<DetailHomeModel> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.d(TAG, "response " + response.body().getData().getEmail());
                    presenter.setData(String.valueOf(response.body().getData().getId()),
                            response.body().getData().getEmail(),
                            response.body().getData().getFirst_name(),
                            response.body().getData().getLast_name(),
                            response.body().getData().getAvatar());
                }
            }
            @Override
            public void onFailure(@NonNull Call<DetailHomeModel> call, @NonNull Throwable t) {
                Log.d(TAG, "onFailure " + t.getMessage());
            }
        });
    }
}
