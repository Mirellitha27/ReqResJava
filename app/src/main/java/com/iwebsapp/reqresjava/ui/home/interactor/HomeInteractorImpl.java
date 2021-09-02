package com.iwebsapp.reqresjava.ui.home.interactor;


import android.util.Log;

import androidx.annotation.NonNull;

import com.iwebsapp.reqresjava.api.ApiService;
import com.iwebsapp.reqresjava.api.RetrofitClient;
import com.iwebsapp.reqresjava.model.home.Datum;
import com.iwebsapp.reqresjava.model.home.HomeModel;
import com.iwebsapp.reqresjava.ui.home.presenter.HomePresenter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeInteractorImpl implements HomeInteractor {
    private final HomePresenter presenter;
    private final String TAG= this.getClass().getSimpleName();
    private List<Datum> data = new ArrayList<>();
    private final ApiService apiService = RetrofitClient.getApiClient().create(ApiService.class);

    public HomeInteractorImpl(HomePresenter presenter) {
        this.presenter = presenter;
    }

    public void init() {
        Call<HomeModel> call;
        call = apiService.getUser();
        call.enqueue(new Callback<HomeModel>() {
            @Override
            public void onResponse(@NonNull Call<HomeModel> call, @NonNull Response<HomeModel> response) {
                if (response.isSuccessful() && response.body() != null) {
                    data = response.body().getData();
                    presenter.setDataAdapter(data);
                }
            }

            @Override
            public void onFailure(@NonNull Call<HomeModel> call, @NonNull Throwable t) {
                Log.d(TAG, "onFailure " + t.getMessage());
            }
        });

    }
}
