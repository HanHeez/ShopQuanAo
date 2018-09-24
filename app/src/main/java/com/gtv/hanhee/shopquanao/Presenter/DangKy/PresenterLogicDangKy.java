package com.gtv.hanhee.shopquanao.Presenter.DangKy;

import android.annotation.SuppressLint;
import android.util.Log;

import com.gtv.hanhee.shopquanao.Model.ObjectClass.NhanVien;
import com.gtv.hanhee.shopquanao._Retrofit.APIUtils;
import com.gtv.hanhee.shopquanao._Retrofit.LayDuLieuTuUrl;
import com.gtv.hanhee.shopquanao.Model.DangKy.ModelDangKy;

import com.gtv.hanhee.shopquanao.View.TaiKhoan.DangKy.ViewDangKy;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class PresenterLogicDangKy implements IPresenterDangKy {
    ViewDangKy viewDangKy;
    ModelDangKy modelDangKy;

    public PresenterLogicDangKy(ViewDangKy viewDangKy) {
        this.viewDangKy = viewDangKy;
        modelDangKy =new ModelDangKy();
    }

    @SuppressLint("CheckResult")
    @Override
    public void ThucHienDangKy(NhanVien nhanVien) {
        LayDuLieuTuUrl DangKyThanhVien = APIUtils.getData();
        Observable<String> dangKyObservable = DangKyThanhVien.DangKyNhanVien(nhanVien.getTenNV(),nhanVien.getTendangnhap(),
        nhanVien.getMatkhau(),nhanVien.getMaloaiNV(),nhanVien.getEmaildocquyen());
        dangKyObservable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError);

    }

    private void handleResponse(String s) {
        if (s.equals("true")) {
            Log.d("kiemtra","Dang ky thanh cong");
        } else {
            Log.d("kiemtra","Dang ky that bai");
        }
    }

    private void handleError(Throwable error) {
        Log.d("kiemtra", error.toString());
    }
}
