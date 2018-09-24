package com.gtv.hanhee.shopquanao.Presenter.TrangChu;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;

import com.facebook.AccessToken;
import com.gtv.hanhee.shopquanao._Retrofit.APIUtils;
import com.gtv.hanhee.shopquanao._Retrofit.LayDuLieuTuUrl;
import com.gtv.hanhee.shopquanao.Model.DangNhap.ModelDangNhapFacebook;
import com.gtv.hanhee.shopquanao.Model.XuLyMenu.LoaiSanPham;
import com.gtv.hanhee.shopquanao.View.TrangChu.ViewXuLyMenu;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class PresenterLogicXuLyMenu implements IPresenterXuLyMenu {
    ViewXuLyMenu viewXuLyMenu;

    public PresenterLogicXuLyMenu() {    }

    public PresenterLogicXuLyMenu(ViewXuLyMenu viewXuLyMenu) {
        this.viewXuLyMenu = viewXuLyMenu;
    }

    @Override
    public AccessToken LayTenNguoiDungFacebook(Context context) {
        ModelDangNhapFacebook modelDangNhapFacebook = new ModelDangNhapFacebook();
        AccessToken accessToken = modelDangNhapFacebook.LayTokenFacebookHienTai(context);
        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();
        if (isLoggedIn) {
            Log.d("tokens",accessToken.toString());
        }
        return accessToken;
    }

    @SuppressLint("CheckResult")
    public void LayDanhSachSP(int maloaicha) {
        // lấy dữ liệu sp theo maloaicha
        LayDuLieuTuUrl DanhSachSP = APIUtils.getData();
        Observable<List<LoaiSanPham>> listObservable = DanhSachSP.LayDanhSachSP(maloaicha);
        listObservable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError);
    }

    private void handleResponse(List<LoaiSanPham> loaiSanPhams) {
        if (loaiSanPhams.size() > 0) {
            CallBack(loaiSanPhams);
        }
    }

    private void handleError(Throwable error) {
        Log.d("kiemtra", error.toString());
    }

    @SuppressLint("CheckResult")
    public void CallBack(final List<LoaiSanPham> loaiSanPhams) {
        //lấy dự liệu sản phẩm con của loại sản phẩm truyền vào
        int count = loaiSanPhams.size();
        for (int i = 0; i < count; i++) {
            int maloaisp = Integer.parseInt(loaiSanPhams.get(i).getMALOAISP());
            LayDuLieuTuUrl DanhSachSP = APIUtils.getData();
            Observable<List<LoaiSanPham>> listObservable = DanhSachSP.LayDanhSachSP(maloaisp);
            int finalI1 = i;
            listObservable.observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(listCon -> Result(loaiSanPhams, listCon, finalI1), this::Error);

        }

    }

    private void Result(List<LoaiSanPham> loaiSanPhams, List<LoaiSanPham> listCon, int finalI1) {
    // gán list con của loại sản phẩm = list Con
        loaiSanPhams.get(finalI1).setListCon(listCon);
        if (listCon.size() > 0) {
            CallBack(listCon);
        }
        //kiểm tra nếu loai sản phẩm có maloaicha = 0 tức là loại sản phẩm gốc thì in ra màn hình
        if (Integer.parseInt(loaiSanPhams.get(0).getMALOAICHA()) == 0) {
            viewXuLyMenu.HienThiDanhSachMenu(loaiSanPhams);
        }
    }

    private void Error(Throwable error) {
        Log.d("kiemtra", error.toString());
    }


}
