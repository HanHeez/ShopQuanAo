package com.gtv.hanhee.shopquanao.Model.ChiTietSanPham;

import android.annotation.SuppressLint;
import android.util.Log;


import com.gtv.hanhee.shopquanao.Model.ObjectClass.DanhGia;
import com.gtv.hanhee.shopquanao.Model.ObjectClass.SanPham;
import com.gtv.hanhee.shopquanao.Presenter.ChiTietSanPham.PresenterChiTietSanPham;
import com.gtv.hanhee.shopquanao._Retrofit.APIUtils;
import com.gtv.hanhee.shopquanao._Retrofit.LayDuLieuTuUrl;
import com.gtv.hanhee.shopquanao.View.ChiTietSanPham.ViewChiTietSanPham;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ModelChiTietSanPham {

    @SuppressLint("CheckResult")
    public void LaySPVaChiTietSPTheoMaSP(int masp, ViewChiTietSanPham viewChiTietSanPham) {
        LayDuLieuTuUrl DanhSachSP = APIUtils.getData();
        Observable<List<SanPham>> spObservable = DanhSachSP.LaySPVaChiTietTheoMaSP(masp);
        spObservable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(sanPhams->KetQuaSP(sanPhams, viewChiTietSanPham), this::ErrorSP);
    }

    private void KetQuaSP(List<SanPham> sanPhams, ViewChiTietSanPham viewChiTietSanPham) {
        PresenterChiTietSanPham presenterChiTietSanPham = new PresenterChiTietSanPham(viewChiTietSanPham);
        presenterChiTietSanPham.TraVeSPTheoMaSP(sanPhams.get(0));
    }

    private void ErrorSP(Throwable error) {
        Log.d("kiemtra", error.toString());
    }


    @SuppressLint("CheckResult")
    public void LayDSDanhGiaTheoMa(int masp, int limit, int gioihanload, ViewChiTietSanPham viewChiTietSanPham) {
        LayDuLieuTuUrl DanhSachSP = APIUtils.getData();
        Observable<List<DanhGia>> spObservable = DanhSachSP.LayDSDanhGiaTheoMa(masp,limit,gioihanload);
        spObservable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(danhGiaList->KetQuaCTSP(danhGiaList, viewChiTietSanPham), this::ErrorCTSP);
    }

    private void KetQuaCTSP(List<DanhGia> danhGiaList, ViewChiTietSanPham viewChiTietSanPham) {
        PresenterChiTietSanPham presenterChiTietSanPham = new PresenterChiTietSanPham(viewChiTietSanPham);
        presenterChiTietSanPham.TraVeDSDGTheoMaSP(danhGiaList);
    }

    private void ErrorCTSP(Throwable error) {
        Log.d("kiemtra", error.toString());
    }
}
