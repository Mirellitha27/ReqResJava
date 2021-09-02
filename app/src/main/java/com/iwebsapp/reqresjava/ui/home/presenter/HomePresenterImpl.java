package com.iwebsapp.reqresjava.ui.home.presenter;

import android.widget.ImageView;

import com.iwebsapp.reqresjava.model.home.Datum;
import com.iwebsapp.reqresjava.ui.home.interactor.HomeInteractor;
import com.iwebsapp.reqresjava.ui.home.interactor.HomeInteractorImpl;
import com.iwebsapp.reqresjava.ui.home.view.HomeView;

import java.util.List;

public class HomePresenterImpl implements HomePresenter {
    private final HomeView view;
    private final HomeInteractor interactor;

    public HomePresenterImpl(HomeView view) {
        this.view = view;
        interactor = new HomeInteractorImpl(this);
    }

    @Override
    public void setDataAdapter(List<Datum> data) {
        view.setDataAdapter(data);
    }

    @Override
    public void showImage(String urlImg, ImageView imageView) {
        view.showImage(urlImg, imageView);
    }

    @Override
    public void showDetail(String idUser) {
        view.showDetail(idUser);
    }

    @Override
    public void init() {
        interactor.init();
    }
}
