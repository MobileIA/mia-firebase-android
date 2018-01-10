package com.mobileia.firebase.listener;

import com.firebase.ui.auth.IdpResponse;

/**
 * Created by matiascamiletti on 9/1/18.
 */

public interface OnSuccessLogin {
    void onSuccess(IdpResponse profile);
}
