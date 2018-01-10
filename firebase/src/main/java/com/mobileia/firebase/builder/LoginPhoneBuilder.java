package com.mobileia.firebase.builder;

import android.app.Activity;

import com.mobileia.firebase.MobileiaFirebase;
import com.mobileia.firebase.listener.OnErrorLogin;
import com.mobileia.firebase.listener.OnSuccessLogin;

/**
 * Created by matiascamiletti on 9/1/18.
 */

public class LoginPhoneBuilder {
    /**
     * Configura el activity
     * @param activity
     * @return
     */
    public LoginPhoneBuilder withActivity(Activity activity){
        MobileiaFirebase.getInstance().setActivity(activity);
        return this;
    }
    /**
     * Configura el manejador cuando el usuario se logueado correctamente
     * @param listener
     * @return
     */
    public LoginPhoneBuilder withSuccessResult(OnSuccessLogin listener){
        MobileiaFirebase.getInstance().setSuccessListener(listener);
        return this;
    }

    /**
     * Configura el manejador para cuando el usuario no se pudo loguear
     * @param listener
     * @return
     */
    public LoginPhoneBuilder withErrorResult(OnErrorLogin listener){
        MobileiaFirebase.getInstance().setErrorListener(listener);
        return this;
    }

    /**
     * Ejecuta el login a traves de celular
     */
    public void build(){
        MobileiaFirebase.getInstance().initVerifyPhone();
    }
}
