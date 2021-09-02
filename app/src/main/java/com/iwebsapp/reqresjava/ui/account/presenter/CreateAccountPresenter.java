package com.iwebsapp.reqresjava.ui.account.presenter;

import com.iwebsapp.reqresjava.ui.account.view.CreateAccountView;

public interface CreateAccountPresenter extends CreateAccountView {
    void register(String email, String password);
}
