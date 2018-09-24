package com.gtv.hanhee.shopquanao.Model.HienThiSanPhamTheoDanhMuc;

import android.annotation.SuppressLint;
import android.util.Log;


import com.gtv.hanhee.shopquanao.Model.ObjectClass.SanPham;
import com.gtv.hanhee.shopquanao.Presenter.HienThiSanPhamTheoDanhMuc.PresenterHienThiSanPhamTheoDanhMuc;
import com.gtv.hanhee.shopquanao._Retrofit.APIUtils;
import com.gtv.hanhee.shopquanao._Retrofit.LayDuLieuTuUrl;
import com.gtv.hanhee.shopquanao.View.HienThiSanPhamTheoDanhMuc.ViewHienThiSPTheoDanhMuc;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ModelHienThiSanPhamTheoDanhMuc {

    @SuppressLint("CheckResult")
    public void LayDanhSachSPTheoMaLoai(int maloaisp, int tongitem, ViewHienThiSPTheoDanhMuc viewHienThiSPTheoDanhMuc) {
        LayDuLieuTuUrl DanhSachSP = APIUtils.getData();
        Observable<List<SanPham>> dSSLTheoMaloaiObservable = DanhSachSP.LayDSSPTheoMaLoai(maloaisp,tongitem);
        dSSLTheoMaloaiObservable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(sanPhams->KetQuaSPTheoMaLoai(sanPhams, viewHienThiSPTheoDanhMuc), this::ErrorSPTML);
    }

    private void KetQuaSPTheoMaLoai(List<SanPham> sanPhams, ViewHienThiSPTheoDanhMuc viewHienThiSPTheoDanhMuc) {
        PresenterHienThiSanPhamTheoDanhMuc presenterHienThiSanPhamTheoDanhMuc = new PresenterHienThiSanPhamTheoDanhMuc(viewHienThiSPTheoDanhMuc);
        presenterHienThiSanPhamTheoDanhMuc.TraVeDSSPTheoMaLoai(sanPhams);
    }

    private void ErrorSPTML(Throwable error) {
        Log.d("kiemtra", error.toString());
    }

    @SuppressLint("CheckResult")
    public void LayDanhSachSPTheoMaThuongHieu(int maloaisp, int tongitem, ViewHienThiSPTheoDanhMuc viewHienThiSPTheoDanhMuc) {

        LayDuLieuTuUrl DanhSachSP = APIUtils.getData();
        Observable<List<SanPham>> dSSLTheoMaloaiObservable = DanhSachSP.LayDSSPTheoMaTH(maloaisp,tongitem);
        dSSLTheoMaloaiObservable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(sanPhams->KetQuaSPTheoMaTH(sanPhams, viewHienThiSPTheoDanhMuc), this::ErrorSPTMTH);
    }

    private void KetQuaSPTheoMaTH(List<SanPham> sanPhams, ViewHienThiSPTheoDanhMuc viewHienThiSPTheoDanhMuc) {
        PresenterHienThiSanPhamTheoDanhMuc presenterHienThiSanPhamTheoDanhMuc = new PresenterHienThiSanPhamTheoDanhMuc(viewHienThiSPTheoDanhMuc);
        presenterHienThiSanPhamTheoDanhMuc.TraVeDSSPTheoMaLoai(sanPhams);
    }

    private void ErrorSPTMTH(Throwable error) {
        Log.d("kiemtra", error.toString());
    }

}
