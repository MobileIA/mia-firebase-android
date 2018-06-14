package com.mobileia.firebase.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.IdpResponse;
import com.mobileia.firebase.MobileiaFirebase;

import java.util.Arrays;

public class PhoneVerificationActivity extends AppCompatActivity {

    /**
     * Request code para determinar el login con el telefono
     */
    private static final int SIGN_IN_WITH_PHONE = 5411;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        // Abrimos Activity para pedir el numero de telefono
        openFirebaseActivity();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Verificamos si el codigo es el correspondiente a la verificacion del telefono
        if (requestCode == SIGN_IN_WITH_PHONE) {
            // Obtenemos informacion de la peticion
            IdpResponse response = IdpResponse.fromResultIntent(data);
            // Successfully signed in
            if (resultCode == RESULT_OK) {
                // Llamamos al listener
                MobileiaFirebase.getInstance().processSuccessResponse(response);
            } else {
                // Sign in failed
                if (response == null) {
                    // Llamamos al listener
                    MobileiaFirebase.getInstance().processErrorResponse("El usuario ha cancelado el proceso.");
                }else if (response.getError().getErrorCode() == ErrorCodes.NO_NETWORK) {
                    // Llamamos al listener
                    MobileiaFirebase.getInstance().processErrorResponse("No se dispone de conexion a internet.");
                } else if (response.getError().getErrorCode() == ErrorCodes.UNKNOWN_ERROR) {
                    // Llamamos al listener
                    MobileiaFirebase.getInstance().processErrorResponse("Se ha producido un error inesperado");
                }
            }
            finish();
        }
    }

    protected void openFirebaseActivity(){
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(
                                Arrays.asList(
                                        new AuthUI.IdpConfig.PhoneBuilder().build()
                                ))
                        .build(),
                SIGN_IN_WITH_PHONE);
    }
}
