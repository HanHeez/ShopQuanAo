package com.gtv.hanhee.shopquanao.Presenter.HienThiSanPhamTheoDanhMuc;

import com.gtv.hanhee.shopquanao.Model.HienThiSanPhamTheoDanhMuc.ModelHienThiSanPhamTheoDanhMuc;

import com.gtv.hanhee.shopquanao.Model.ObjectClass.SanPham;
import com.gtv.hanhee.shopquanao.View.HienThiSanPhamTheoDanhMuc.ViewHienThiSPTheoDanhMuc;

import java.util.List;

public class PresenterHienThiSanPhamTheoDanhMuc implements IPresenterHienThiSanPhamTheoDanhMuc {
    ViewHienThiSPTheoDanhMuc viewHienThiSPTheoDanhMuc;
    ModelHienThiSanPhamTheoDanhMuc modelHienThiSanPhamTheoDanhMuc;

    public PresenterHienThiSanPhamTheoDanhMuc(ViewHienThiSPTheoDanhMuc viewHienThiSPTheoDanhMuc) {
        this.viewHienThiSPTheoDanhMuc = viewHienThiSPTheoDanhMuc;
        modelHienThiSanPhamTheoDanhMuc = new ModelHienThiSanPhamTheoDanhMuc();

    }


    @Override
    public void LayDanhSachSanPhamTheoMaLoai(int masp,boolean kiemtra, int tongitem, ViewHienThiSPTheoDanhMuc viewHienThiSPTheoDanhMuc) {
        if (kiemtra==false) {
            modelHienThiSanPhamTheoDanhMuc.LayDanhSachSPTheoMaLoai(masp,tongitem, viewHienThiSPTheoDanhMuc);
        } else {
            modelHienThiSanPhamTheoDanhMuc.LayDanhSachSPTheoMaThuongHieu(masp,tongitem, viewHienThiSPTheoDanhMuc);
        }

    }

    public void TraVeDSSPTheoMaLoai(List<SanPham> sanPhams) {
        viewHienThiSPTheoDanhMuc.HienThiSPTheoMaLoai(sanPhams);
    }

    public void LayDanhSachSanPhamTheoMaLoaiLoadMore(int masp,boolean kiemtra, int tongitem, ViewHienThiSPTheoDanhMuc viewHienThiSPTheoDanhMuc) {

        if (kiemtra==false) {
            modelHienThiSanPhamTheoDanhMuc.LayDanhSachSPTheoMaLoai(masp, tongitem, viewHienThiSPTheoDanhMuc);
        } else {
            modelHienThiSanPhamTheoDanhMuc.LayDanhSachSPTheoMaThuongHieu(masp, tongitem, viewHienThiSPTheoDanhMuc);
        }

    }
}
