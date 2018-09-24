package com.gtv.hanhee.shopquanao.Model.DangNhap;

import android.content.Context;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

public class ModelDangNhapGoogle {
    GoogleSignInClient mGoogleSignInClient;

    public GoogleSignInClient LayGoogleSignInClient(Context context) {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(context, gso);
        return  mGoogleSignInClient;
    }

    public GoogleSignInAccount ThongTinTaiKhoan(Context context) {
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(context);
        if (acct != null) {
            return acct;
        } else {
            return null;
        }
    }
}
