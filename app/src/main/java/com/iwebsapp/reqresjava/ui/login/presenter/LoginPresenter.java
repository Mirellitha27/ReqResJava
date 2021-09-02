package com.iwebsapp.reqresjava.ui.login.presenter;

import com.iwebsapp.reqresjava.ui.login.view.LoginView;

public interface LoginPresenter extends LoginView {
    void login(String email, String password);
}
