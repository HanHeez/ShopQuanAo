package com.gtv.hanhee.shopquanao.Model.ThanhToan;

import android.annotation.SuppressLint;
import android.util.Log;


import com.gtv.hanhee.shopquanao.Model.ObjectClass.HoaDon;
import com.gtv.hanhee.shopquanao.Presenter.ThanhToan.PresenterThanhToan;
import com.gtv.hanhee.shopquanao._Retrofit.APIUtils;
import com.gtv.hanhee.shopquanao._Retrofit.LayDuLieuTuUrl;
import com.gtv.hanhee.shopquanao.View.ThanhToan.ViewThanhToan;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ModelThanhToan {

    @SuppressLint("CheckResult")
    public void ThemHoaDon(HoaDon hoaDon, ViewThanhToan viewThanhToan) {
        LayDuLieuTuUrl DanhSachSP = APIUtils.getData();
        Observable<String> dgObservable = DanhSachSP.ThemHoaDon(hoaDon.getDanhsachsanpham(),hoaDon.getTennguoinhan()
        ,hoaDon.getDiachi(),hoaDon.getSodt(),hoaDon.getChuyenkhoan());
        dgObservable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(ketqua->KetQuaTHD(ketqua, viewThanhToan), this::ErrorTHD);
    }

    private void KetQuaTHD(String ketqua, ViewThanhToan viewThanhToan) {
        PresenterThanhToan presenterThanhToan = new PresenterThanhToan(viewThanhToan);
        presenterThanhToan.TraVeKetQuaThemHoaDon(ketqua);
    }

    private void ErrorTHD(Throwable error) {
        Log.d("kiemtra", error.toString());
    }

}
