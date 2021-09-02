package com.iwebsapp.reqresjava.ui.login.presenter;

import com.iwebsapp.reqresjava.ui.login.interactor.LoginInteractor;
import com.iwebsapp.reqresjava.ui.login.interactor.LoginInteractorImpl;
import com.iwebsapp.reqresjava.ui.login.view.LoginView;

public class LoginPresenterImpl implements LoginPresenter {
    private LoginView view;
    private LoginInteractor interactor;

    public LoginPresenterImpl(LoginView view) {
        this.view = view;
        interactor = new LoginInteractorImpl(this);
    }

    @Override
    public void login(String email, String password) {
        interactor.login(email, password);
    }

    @Override
    public void goMain() {
        view.goMain();
    }

    @Override
    public void saveUser(String token) {
        view.saveUser(token);
    }
}
