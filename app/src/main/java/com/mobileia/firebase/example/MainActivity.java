package com.mobileia.firebase.example;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.IdpResponse;
import com.mobileia.firebase.MobileiaFirebase;
import com.mobileia.firebase.builder.LoginPhoneBuilder;
import com.mobileia.firebase.listener.OnErrorLogin;
import com.mobileia.firebase.listener.OnSuccessLogin;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    // Choose an arbitrary request code value
    private static final int RC_SIGN_IN = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickButton(View v){
        new LoginPhoneBuilder().withActivity(this)
                .withSuccessResult(new OnSuccessLogin() {
                    @Override
                    public void onSuccess(IdpResponse profile) {
                        System.out.println("Se logueo correctamente");

                        System.out.println("USER: " + profile.getPhoneNumber());
                        System.out.println("USER: " + profile.getIdpToken());
                        System.out.println("USER: " + profile.getIdpSecret());

                        System.out.println("USER: " + profile.getProviderType());
                    }
                })
                .withErrorResult(new OnErrorLogin() {
                    @Override
                    public void onError(String message) {
                        System.out.println("Problema al login: " + message);
                    }
                }).build();
    }
}
