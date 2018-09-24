package com.gtv.hanhee.shopquanao.Presenter.DangNhap;

import android.content.Context;
import android.content.SharedPreferences;

public class PresenterLogicDangNhap implements IPresenterDangNhap {

    public String LayCachedTenDangNhap(Context context) {
        SharedPreferences cachedDangNhap = context.getSharedPreferences("dangnhap", Context.MODE_PRIVATE);
        String tennv = cachedDangNhap.getString("tennv","");
        return tennv;
    }

    public void CapNhatCacheDangNhap(Context context,String tennv) {
        SharedPreferences cachedDangNhap = context.getSharedPreferences("dangnhap", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = cachedDangNhap.edit();
        editor.putString("tennv",tennv);
        editor.commit();
    }
}
