package com.gtv.hanhee.shopquanao.Presenter.DanhGia;

import com.gtv.hanhee.shopquanao.Model.DanhGia.ModelDanhGia;

import com.gtv.hanhee.shopquanao.Model.ObjectClass.DanhGia;
import com.gtv.hanhee.shopquanao.View.DanhGia.ViewDanhGia;

import java.util.List;

public class PresenterDanhGia {
    ViewDanhGia viewDanhGia;
    ModelDanhGia modelDanhGia;

    public PresenterDanhGia(ViewDanhGia viewDanhGia) {
        this.viewDanhGia = viewDanhGia;
        modelDanhGia = new ModelDanhGia();
    }

    public void TraVeDanhGia(String ketqua) {
        if (ketqua.equals("true")) {
            viewDanhGia.ThemDGThanhCong();
        } else {
            viewDanhGia.ThemDGThatBai();
        }
    }

    public void ThemDanhGia(DanhGia danhGia, ViewDanhGia viewDanhGia) {
        modelDanhGia.ThemDanhGia(danhGia, viewDanhGia);
    }

    public void LayDSDGCuaSP(int masp, int limit,int gioihanload, ViewDanhGia viewDanhGia) {
        modelDanhGia.LayDSDanhGiaTheoMa(masp,limit,gioihanload,viewDanhGia);

    }

    public void TraVeDSDGTheoMaSP(List<DanhGia> danhGiaList) {
        viewDanhGia.HienThiDSDanhGiaTheoMa(danhGiaList);
    }

}
