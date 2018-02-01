package com.mobileia.firebase.authentication;

import android.app.Activity;

import com.google.gson.JsonObject;
import com.mobileia.authentication.core.MobileiaAuthBase;
import com.mobileia.authentication.core.rest.AuthRestBase;
import com.mobileia.core.entity.Error;
import com.mobileia.firebase.builder.LoginPhoneBuilder;
import com.mobileia.firebase.listener.OnErrorLogin;
import com.mobileia.firebase.listener.OnSuccessLogin;

/**
 * Created by matiascamiletti on 1/2/18.
 */

public class FirebaseMobileiaAuth extends MobileiaAuthBase {
    /**
     * Almacena el telefono del usuario
     */
    protected String mPhone;
    /**
     * Almacena el secret del usuario
     */
    protected String mToken;

    /**
     * Constructor
     *
     * @param activity
     */
    public FirebaseMobileiaAuth(Activity activity) {
        super(activity);
    }

    @Override
    public void requestAccessToken() {
        // Generamos objeto para enviar los parametros
        JsonObject params = new JsonObject();
        params.addProperty("grant_type", "firebase-phone");
        params.addProperty("phone", mPhone);
        params.addProperty("token", mToken);
        new AuthRestBase().oauth(params, mAccessTokenResult);
    }

    @Override
    public void requestNewAccount() {
        // Generamos objeto para enviar los parametros
        JsonObject params = new JsonObject();
        params.addProperty("register_type", "firebase-phone");
        params.addProperty("phone", mPhone);
        params.addProperty("token", mToken);
        new AuthRestBase().register(params, mRegisterResult);
    }

    @Override
    public void openSocial() {
        new LoginPhoneBuilder()
                .withActivity(mActivity)
                .withSuccessResult(new OnSuccessLogin() {
                    @Override
                    public void onSuccess(com.firebase.ui.auth.IdpResponse profile) {
                        // Guardamos datos del usuario
                        mPhone = profile.getPhoneNumber();
                        mToken = profile.getIdpToken();
                        // Pedimos los datos
                        requestAccessToken();
                    }
                })
                .withErrorResult(new OnErrorLogin() {
                    @Override
                    public void onError(String message) {
                        // Llamamos al callback con error
                        mCallback.onError(new Error(-1, message));
                    }
                })
                .build();
    }
}
