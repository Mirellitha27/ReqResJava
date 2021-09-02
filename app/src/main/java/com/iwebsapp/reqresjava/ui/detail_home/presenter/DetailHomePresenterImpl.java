package com.iwebsapp.reqresjava.ui.detail_home.presenter;

import com.iwebsapp.reqresjava.ui.detail_home.interactor.DetailHomeInteractor;
import com.iwebsapp.reqresjava.ui.detail_home.interactor.DetailHomeInteractorImpl;
import com.iwebsapp.reqresjava.ui.detail_home.view.DetailHomeView;

public class DetailHomePresenterImpl implements DetailHomePresenter {
    private final DetailHomeView view;
    private final DetailHomeInteractor interactor;

    public DetailHomePresenterImpl(DetailHomeView view) {
        this.view = view;
        interactor = new DetailHomeInteractorImpl(this);
    }

    @Override
    public void getUser(String idUser) {
        interactor.getUser(idUser);
    }

    @Override
    public void setData(String id, String email, String name, String lastName, String avatar) {
        view.setData(id, email, name, lastName, avatar);
    }
}
