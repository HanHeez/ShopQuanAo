package com.gtv.hanhee.shopquanao.Model._TrangChu_DienTu;

import android.annotation.SuppressLint;
import android.util.Log;


import com.gtv.hanhee.shopquanao.Model.ObjectClass.SanPham;
import com.gtv.hanhee.shopquanao.Presenter.SanPham_DienTu.PresenterLogicDienTu;
import com.gtv.hanhee.shopquanao._Retrofit.APIUtils;
import com.gtv.hanhee.shopquanao._Retrofit.LayDuLieuTuUrl;
import com.gtv.hanhee.shopquanao.View.FragmentsManHinhChinh.ViewDienTu;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ModelSanPhamMoi {

    @SuppressLint("CheckResult")
    public void LaySanPhamMoiVe(ViewDienTu viewDienTu) {
        LayDuLieuTuUrl DanhSachSP = APIUtils.getData();
        Observable<List<SanPham>> spObservable = DanhSachSP.LaySanPhamMoiVe();
        spObservable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(sanPhams->KetQuaLogoTHL(sanPhams, viewDienTu), this::ErrorTHL);
    }

    private void KetQuaLogoTHL(List<SanPham> sanPhams, ViewDienTu viewDienTu) {
        PresenterLogicDienTu presenterLogicDienTu = new PresenterLogicDienTu(viewDienTu);
        presenterLogicDienTu.TraVeSanPhamMoiVe(sanPhams);
    }

    private void ErrorTHL(Throwable error) {
        Log.d("kiemtra", error.toString());
    }

}
