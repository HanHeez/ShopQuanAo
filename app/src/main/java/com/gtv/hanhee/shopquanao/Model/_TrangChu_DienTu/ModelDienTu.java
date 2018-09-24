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

public class ModelDienTu {

    @SuppressLint("CheckResult")
    public void LayDanhSachThuongHieuLon(ViewDienTu viewDienTu) {
        LayDuLieuTuUrl DanhSachSP = APIUtils.getData();
        Observable<List<ThuongHieu>> thuonghieuObservable = DanhSachSP.LayDanhSachCAcThuongHieuLon();
        thuonghieuObservable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(thuongHieus->KetQuaTHL(thuongHieus, viewDienTu), this::ErrorTHL);
    }

    private void KetQuaTHL(List<ThuongHieu> thuongHieus,ViewDienTu viewDienTu) {
        LayDanhSachSanPhamTop(thuongHieus, viewDienTu);
    }

    private void ErrorTHL(Throwable error) {
        Log.d("kiemtra", error.toString());
    }

    @SuppressLint("CheckResult")
    public void LayDanhSachSanPhamTop(List<ThuongHieu> thuongHieus, ViewDienTu viewDienTu) {
        LayDuLieuTuUrl DanhSachSP = APIUtils.getData();
        Observable<List<SanPham>> sanphamtopObservable = DanhSachSP.LayDanhSachSanPhamTop();
        sanphamtopObservable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(sanPhams ->KetQuaTop(sanPhams, thuongHieus, viewDienTu), this::ErrorTop);

    }

    private void KetQuaTop(List<SanPham> sanPhams,List<ThuongHieu> thuongHieus,ViewDienTu viewDienTu) {
        PresenterLogicDienTu presenterLogicDienTu = new PresenterLogicDienTu(viewDienTu);
        DienTu dienTu = new DienTu();
        dienTu.setSanPhamList(sanPhams);
        dienTu.setThuongHieuList(thuongHieus);
        dienTu.setTennoibat("Danh sách thương hiệu lớn");
        dienTu.setTentopnoibat("Top điện thoại và máy tỉnh bảng");
        presenterLogicDienTu.TraVeDienTuList(dienTu);
    }

    private void ErrorTop(Throwable error) {
        Log.d("kiemtra", error.toString());
    }


}
