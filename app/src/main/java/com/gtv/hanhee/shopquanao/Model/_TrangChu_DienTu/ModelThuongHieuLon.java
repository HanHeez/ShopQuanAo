package com.gtv.hanhee.shopquanao.Model._TrangChu_DienTu;

import android.annotation.SuppressLint;
import android.util.Log;


import com.gtv.hanhee.shopquanao.Model.ObjectClass.ThuongHieu;
import com.gtv.hanhee.shopquanao.Presenter.SanPham_DienTu.PresenterLogicDienTu;
import com.gtv.hanhee.shopquanao._Retrofit.APIUtils;
import com.gtv.hanhee.shopquanao._Retrofit.LayDuLieuTuUrl;
import com.gtv.hanhee.shopquanao.View.FragmentsManHinhChinh.ViewDienTu;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ModelThuongHieuLon {

    @SuppressLint("CheckResult")
    public void LayLogoThuongHieuLon(ViewDienTu viewDienTu) {
        LayDuLieuTuUrl DanhSachSP = APIUtils.getData();
        Observable<List<ThuongHieu>> thuonghieuObservable = DanhSachSP.LayLogoThuongHieuLon();
        thuonghieuObservable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(thuongHieus->KetQuaLogoTHL(thuongHieus, viewDienTu), this::ErrorTHL);
    }

    private void KetQuaLogoTHL(List<ThuongHieu> thuongHieus, ViewDienTu viewDienTu) {
        PresenterLogicDienTu presenterLogicDienTu = new PresenterLogicDienTu(viewDienTu);
        presenterLogicDienTu.TraVeLogoThuongHieu(thuongHieus);
    }

    private void ErrorTHL(Throwable error) {
        Log.d("kiemtra", error.toString());
    }

}
