package com.iwebsapp.reqresjava.ui.splash;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.iwebsapp.reqresjava.R;
import com.iwebsapp.reqresjava.databinding.ActivitySplashBinding;
import com.iwebsapp.reqresjava.model.UserEntiti;
import com.iwebsapp.reqresjava.ui.MainActivity;
import com.iwebsapp.reqresjava.ui.login.view.LoginActivity;

import io.realm.Realm;

public class SplashActivity extends AppCompatActivity {
    private ActivitySplashBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash);

        int DURACION_SPLASH = 3000;
        new Handler().postDelayed(this::validateUser, DURACION_SPLASH);

    }

    private void validateUser() {
        Realm realm;
        realm = Realm.getDefaultInstance();
        UserEntiti userEntiti = realm.where(UserEntiti.class).findFirst();
        if (userEntiti != null) {
            Log.d("TAG", "token " + userEntiti.getToken());
            goMain();
        } else {
            goLogin();
        }
    }

    public void goLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    public void goMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}