package com.iwebsapp.reqresjava.ui.login.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.iwebsapp.reqresjava.R;
import com.iwebsapp.reqresjava.databinding.ActivityLoginBinding;
import com.iwebsapp.reqresjava.model.UserEntiti;
import com.iwebsapp.reqresjava.ui.MainActivity;
import com.iwebsapp.reqresjava.ui.account.view.CreateAccountActivity;
import com.iwebsapp.reqresjava.ui.login.presenter.LoginPresenter;
import com.iwebsapp.reqresjava.ui.login.presenter.LoginPresenterImpl;

import io.realm.Realm;

public class LoginActivity extends AppCompatActivity implements LoginView {
    private LoginPresenter presenter;
    private ActivityLoginBinding binding;
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        setPresenter();
        realm = Realm.getDefaultInstance();
        binding.textViewAccount.setOnClickListener(view -> showCreateAccount());
        binding.txtGetInto.setOnClickListener(view -> presenter.login(binding.editEmail.getText().toString(), binding.editPassword.getText().toString()));

    }

    private void setPresenter() {
        if (presenter == null) {
            presenter = new LoginPresenterImpl(this);
        }
    }

    private void showCreateAccount(){
        Intent intent = new Intent(this, CreateAccountActivity.class);
        startActivity(intent);
        finish();
    }

    public void goMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void saveUser(String token) {
        try {
            Thread.sleep(100);
            realm.executeTransactionAsync(realm1 -> {
                UserEntiti user = realm1.createObject(UserEntiti.class);
                user.setToken(token);
                user.setEmail(binding.editEmail.getText().toString());
            }, () -> Log.d("TAG", "Realm OnSuccess"),
                    error -> Log.d("TAG", "Realm onError" + error));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}