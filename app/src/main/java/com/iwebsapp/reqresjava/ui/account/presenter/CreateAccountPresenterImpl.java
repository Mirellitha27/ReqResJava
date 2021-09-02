package com.iwebsapp.reqresjava.ui.account.presenter;

import com.iwebsapp.reqresjava.ui.account.interactor.CreateAccountInteractor;
import com.iwebsapp.reqresjava.ui.account.interactor.CreateAccountInteractorImpl;
import com.iwebsapp.reqresjava.ui.account.view.CreateAccountView;

public class CreateAccountPresenterImpl implements CreateAccountPresenter {
    private CreateAccountView view;
    private CreateAccountInteractor interactor;

    public CreateAccountPresenterImpl(CreateAccountView view) {
        this.view = view;
        interactor = new CreateAccountInteractorImpl(this);
    }

    @Override
    public void register(String email, String password) {
        interactor.register(email, password);
    }
}
