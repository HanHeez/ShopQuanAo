package com.gtv.hanhee.shopquanao.Model.TimKiem;

import android.annotation.SuppressLint;
import android.util.Log;

import com.gtv.hanhee.shopquanao.Model.ObjectClass.SanPham;
import com.gtv.hanhee.shopquanao.Presenter.HienThiSanPhamTheoDanhMuc.PresenterHienThiSanPhamTheoDanhMuc;
import com.gtv.hanhee.shopquanao.Presenter.TimKiem.PresenterTimKiem;
import com.gtv.hanhee.shopquanao.View.HienThiSanPhamTheoDanhMuc.ViewHienThiSPTheoDanhMuc;
import com.gtv.hanhee.shopquanao.View.TimKiem.ViewTimKiem;
import com.gtv.hanhee.shopquanao._Retrofit.APIUtils;
import com.gtv.hanhee.shopquanao._Retrofit.LayDuLieuTuUrl;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ModelTimKiem {
    @SuppressLint("CheckResult")
    public void LayDSSanPhamTheoTenSP(int limit, String tensp, ViewTimKiem viewTimKiem) {
        LayDuLieuTuUrl DanhSachSP = APIUtils.getData();
        Observable<List<SanPham>> dssptheotensp = DanhSachSP.LayDSSPTheoTenSP(tensp,limit);
        dssptheotensp.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(sanPhams->KetQuaSPTheoMaLoai(sanPhams, viewTimKiem), this::ErrorSPTML);
    }


    private void KetQuaSPTheoMaLoai(List<SanPham> sanPhams, ViewTimKiem viewTimKiem) {
        PresenterTimKiem presenterTimKiem = new PresenterTimKiem(viewTimKiem);
        presenterTimKiem.TraVeDSSPTheoTenSP(sanPhams);
    }

    private void ErrorSPTML(Throwable error) {
        Log.d("kiemtra", error.toString());
    }


}
