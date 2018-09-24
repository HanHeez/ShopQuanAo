package com.gtv.hanhee.shopquanao.Model._TrangChu_DienTu;

import android.annotation.SuppressLint;
import android.util.Log;


import com.gtv.hanhee.shopquanao.Model.ObjectClass.DienTu;
import com.gtv.hanhee.shopquanao.Model.ObjectClass.SanPham;
import com.gtv.hanhee.shopquanao.Model.ObjectClass.ThuongHieu;
import com.gtv.hanhee.shopquanao.Presenter.SanPham_DienTu.PresenterLogicDienTu;
import com.gtv.hanhee.shopquanao._Retrofit.APIUtils;
import com.gtv.hanhee.shopquanao._Retrofit.LayDuLieuTuUrl;
import com.gtv.hanhee.shopquanao.View.FragmentsManHinhChinh.ViewDienTu;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ModelPhuKien {

    @SuppressLint("CheckResult")
    public void LayDSPhuKien(ViewDienTu viewDienTu) {
        LayDuLieuTuUrl DSPK = APIUtils.getData();
        Observable<List<ThuongHieu>> dspkObservable = DSPK.LayDSPhuKien();
        dspkObservable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(thuongHieus->KetQuaTH(thuongHieus, viewDienTu), this::ErrorTH);
    }

    private void KetQuaTH(List<ThuongHieu> thuongHieus, ViewDienTu viewDienTu) {
        LayDanhSachSanPhamTop(thuongHieus, viewDienTu);
    }

    private void ErrorTH(Throwable error) {
        Log.d("kiemtra", error.toString());
    }

    @SuppressLint("CheckResult")
    public void LayDanhSachSanPhamTop(List<ThuongHieu> thuongHieus, ViewDienTu viewDienTu) {
        LayDuLieuTuUrl DSPK = APIUtils.getData();
        Observable<List<SanPham>> dspkObservable = DSPK.LayDSTopPhuKien();
        dspkObservable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(sanPhams ->KetQuaSP(sanPhams, thuongHieus, viewDienTu), this::ErrorSP);

    }

    private void KetQuaSP(List<SanPham> sanPhams,List<ThuongHieu> thuongHieus,ViewDienTu viewDienTu) {
        PresenterLogicDienTu presenterLogicDienTu = new PresenterLogicDienTu(viewDienTu);
        DienTu dienTu = new DienTu();
        dienTu.setSanPhamList(sanPhams);
        dienTu.setThuongHieuList(thuongHieus);
        dienTu.setTennoibat("Phụ kiện điện thoại");
        dienTu.setTentopnoibat("Top phụ kiện");
        presenterLogicDienTu.TraVeDSPK(dienTu);
    }

    private void ErrorSP(Throwable error) {
        Log.d("kiemtra", error.toString());
    }
}
