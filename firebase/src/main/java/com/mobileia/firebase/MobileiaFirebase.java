package com.mobileia.firebase;

import android.app.Activity;
import android.content.Intent;

import com.firebase.ui.auth.IdpResponse;
import com.mobileia.firebase.activity.PhoneVerificationActivity;
import com.mobileia.firebase.listener.OnErrorLogin;
import com.mobileia.firebase.listener.OnSuccessLogin;

/**
 * Created by matiascamiletti on 2/1/18.
 */

public class MobileiaFirebase {

    /**
     * Almacena la Activity que lo inicia
     */
    protected Activity mActivity;
    /**
     * Almacena listener para las respuestas correctas
     */
    protected OnSuccessLogin mSuccessListener;
    /**
     * Almacena listener para las respuestas erroneas
     */
    protected OnErrorLogin mErrorListener;
    /**
     * Almacena la unica instancia de la libreria
     */
    private static final MobileiaFirebase sOurInstance = new MobileiaFirebase();

    /**
     * Inicia proceso de verificacion de celular
     */
    public void initVerifyPhone(){
        startTranparentActivity();
    }
    /**
     * Funcion que se llama una vez logueado al usuario
     * @param profile
     */
    public void processSuccessResponse(IdpResponse profile){
        if(mSuccessListener != null){
            mSuccessListener.onSuccess(profile);
        }
    }

    /**
     * Funcion que se llama si no se pudo loguear
     */
    public void processErrorResponse(String message){
        if(mErrorListener != null){
            mErrorListener.onError(message);
        }
    }
    /**
     * Setea el Activity
     * @param activity
     */
    public void setActivity(Activity activity){
        this.mActivity = activity;
    }
    /**
     * Setea listener cuando se loguea
     * @param listener
     */
    public void setSuccessListener(OnSuccessLogin listener){
        this.mSuccessListener = listener;
    }

    /**
     * Setea el listener cuando no se loguea
     * @param listener
     */
    public void setErrorListener(OnErrorLogin listener){ this.mErrorListener = listener; }
    /**
     * Obtiene la instancia creada
     * @return
     */
    public static MobileiaFirebase getInstance() {
        return sOurInstance;
    }

    protected void startTranparentActivity(){
        Intent intent = new Intent(mActivity, PhoneVerificationActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mActivity.startActivity(intent);
    }

    private MobileiaFirebase() {}


}
