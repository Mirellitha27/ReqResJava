package com.iwebsapp.reqresjava.ui.account.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.iwebsapp.reqresjava.R;
import com.iwebsapp.reqresjava.databinding.ActivityCreateAccountBinding;
import com.iwebsapp.reqresjava.ui.account.presenter.CreateAccountPresenter;
import com.iwebsapp.reqresjava.ui.account.presenter.CreateAccountPresenterImpl;
import com.iwebsapp.reqresjava.ui.login.presenter.LoginPresenterImpl;

public class CreateAccountActivity extends AppCompatActivity implements CreateAccountView {
    private ActivityCreateAccountBinding binding;
    private CreateAccountPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_create_account);
        setPresenter();

        binding.txtGetInto.setOnClickListener(view -> presenter.register(binding.editEmail.getText().toString(), binding.editPassword.getText().toString()));

    }

    private void setPresenter() {
        if (presenter == null) {
            presenter = new CreateAccountPresenterImpl(this);
        }
    }

}