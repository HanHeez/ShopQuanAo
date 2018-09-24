package com.gtv.hanhee.shopquanao.Model.DangNhap;

import android.content.Context;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;

public class ModelDangNhapFacebook {
    AccessToken accessToken;
    AccessTokenTracker accessTokenTracker;


    public AccessToken LayTokenFacebookHienTai(Context context) {
        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(
                    AccessToken oldAccessToken,
                    AccessToken currentAccessToken) {
                accessToken = currentAccessToken;
            }
        };
        // If the access token is available already assign it.
        accessToken = AccessToken.getCurrentAccessToken();
        return accessToken;
    }

    public void HuyTokenTracker() {
        accessTokenTracker.stopTracking();
    }
}
