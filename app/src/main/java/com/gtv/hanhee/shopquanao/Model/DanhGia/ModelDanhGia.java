package com.gtv.hanhee.shopquanao.Model.DanhGia;

import android.annotation.SuppressLint;
import android.util.Log;


import com.gtv.hanhee.shopquanao.Model.ObjectClass.DanhGia;
import com.gtv.hanhee.shopquanao.Presenter.DanhGia.PresenterDanhGia;
import com.gtv.hanhee.shopquanao._Retrofit.APIUtils;
import com.gtv.hanhee.shopquanao._Retrofit.LayDuLieuTuUrl;
import com.gtv.hanhee.shopquanao.View.DanhGia.ViewDanhGia;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ModelDanhGia {
    @SuppressLint("CheckResult")
    public void ThemDanhGia(DanhGia danhGia, ViewDanhGia viewDanhGia) {
        LayDuLieuTuUrl DanhSachSP = APIUtils.getData();
        Observable<String> dgObservable = DanhSachSP.ThemDanhGia(danhGia.getMadg(),danhGia.getMasp(),danhGia.getTenthietbi(),
                danhGia.getTieude(),danhGia.getNoidung(),danhGia.getSosao());
        dgObservable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(ketqua->KetQuaTDG(ketqua, viewDanhGia), this::ErrorTDG);
    }

    private void KetQuaTDG(String ketqua, ViewDanhGia viewDanhGia) {
        PresenterDanhGia presenterDanhGia = new PresenterDanhGia(viewDanhGia);
        presenterDanhGia.TraVeDanhGia(ketqua);
    }

    private void ErrorTDG(Throwable error) {
        Log.d("kiemtra", error.toString());
    }

    @SuppressLint("CheckResult")
    public void LayDSDanhGiaTheoMa(int masp, int limit, int gioihanload, ViewDanhGia viewDanhGia) {
        LayDuLieuTuUrl DanhSachSP = APIUtils.getData();
        Observable<List<DanhGia>> spObservable = DanhSachSP.LayDSDanhGiaTheoMa(masp,limit,gioihanload);
        spObservable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(danhGiaList->KetQuaCTSP(danhGiaList, viewDanhGia), this::ErrorCTSP);
    }

    private void KetQuaCTSP(List<DanhGia> danhGiaList, ViewDanhGia viewDanhGia) {
        PresenterDanhGia presenterDanhGia = new PresenterDanhGia(viewDanhGia);
        presenterDanhGia.TraVeDSDGTheoMaSP(danhGiaList);
    }

    private void ErrorCTSP(Throwable error) {
        Log.d("kiemtra", error.toString());
    }
}
