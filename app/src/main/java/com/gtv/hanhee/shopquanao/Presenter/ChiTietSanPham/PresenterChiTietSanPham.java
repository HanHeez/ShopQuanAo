package com.gtv.hanhee.shopquanao.Presenter.ChiTietSanPham;

import com.gtv.hanhee.shopquanao.Model.ChiTietSanPham.ModelChiTietSanPham;

import com.gtv.hanhee.shopquanao.Model.ObjectClass.DanhGia;
import com.gtv.hanhee.shopquanao.Model.ObjectClass.SanPham;
import com.gtv.hanhee.shopquanao.View.ChiTietSanPham.ViewChiTietSanPham;

import java.util.List;

public class PresenterChiTietSanPham implements IPresenterChiTietSanPham {
    ViewChiTietSanPham viewChiTietSanPham;
    ModelChiTietSanPham modelChiTietSanPham;

    public PresenterChiTietSanPham(ViewChiTietSanPham viewChiTietSanPham) {
        this.viewChiTietSanPham = viewChiTietSanPham;
        modelChiTietSanPham = new ModelChiTietSanPham();
    }


    public void TraVeSPTheoMaSP(SanPham sanPhams) {
        viewChiTietSanPham.HienThiSPTheoMaSP(sanPhams);

    }

    public void LaySPVaChiTietSPTheoMaSP(int masp, ViewChiTietSanPham viewChiTietSanPham) {
        modelChiTietSanPham.LaySPVaChiTietSPTheoMaSP(masp, viewChiTietSanPham);

    }

    public void LayDSDanhGiaTheoMa(int masp, int limit, int gioihanload, ViewChiTietSanPham viewChiTietSanPham) {
        modelChiTietSanPham.LayDSDanhGiaTheoMa(masp,limit,gioihanload,viewChiTietSanPham);
    }

    public void TraVeDSDGTheoMaSP(List<DanhGia> danhGiaList) {
        viewChiTietSanPham.HienThiDSDanhGiaTheoMa(danhGiaList);
    }

}
