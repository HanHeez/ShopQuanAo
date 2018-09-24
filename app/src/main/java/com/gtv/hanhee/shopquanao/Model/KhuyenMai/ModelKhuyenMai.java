package com.gtv.hanhee.shopquanao.Model.KhuyenMai;

import android.annotation.SuppressLint;
import android.util.Log;

import com.gtv.hanhee.shopquanao.Model.ObjectClass.KhuyenMai;
import com.gtv.hanhee.shopquanao.Model.ObjectClass.SanPham;
import com.gtv.hanhee.shopquanao.Presenter.HienThiSanPhamTheoDanhMuc.PresenterHienThiSanPhamTheoDanhMuc;
import com.gtv.hanhee.shopquanao.Presenter.KhuyenMai.PresenterKhuyenMai;
import com.gtv.hanhee.shopquanao.View.HienThiSanPhamTheoDanhMuc.ViewHienThiSPTheoDanhMuc;
import com.gtv.hanhee.shopquanao.View.KhuyenMai.ViewKhuyenMai;
import com.gtv.hanhee.shopquanao._Retrofit.APIUtils;
import com.gtv.hanhee.shopquanao._Retrofit.LayDuLieuTuUrl;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ModelKhuyenMai {

    @SuppressLint("CheckResult")
    public void LayDanhSachKhuyenMai(ViewKhuyenMai viewKhuyenMai) {
        LayDuLieuTuUrl DanhSachSP = APIUtils.getData();
        Observable<List<KhuyenMai>> dskmObservable = DanhSachSP.LayDanhSachKhuyenMai();
        dskmObservable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(khuyenMais->KetQuaSPTheoMaLoai(khuyenMais, viewKhuyenMai), this::ErrorSPTML);
    }

    private void KetQuaSPTheoMaLoai(List<KhuyenMai> khuyenMais, ViewKhuyenMai viewKhuyenMai) {
        PresenterKhuyenMai presenterKhuyenMai = new PresenterKhuyenMai(viewKhuyenMai);
        presenterKhuyenMai.TraVeDSKM(khuyenMais);
    }

    private void ErrorSPTML(Throwable error) {
        Log.d("kiemtra", error.toString());
    }
}
