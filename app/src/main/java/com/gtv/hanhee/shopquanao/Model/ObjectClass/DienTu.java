package com.gtv.hanhee.shopquanao.Model.ObjectClass;

import java.util.List;

public class DienTu {
    List<ThuongHieu> thuongHieuList;
    List<SanPham> sanPhamList;
    String tennoibat,tentopnoibat;
    Boolean thuonghieu;

    public Boolean getThuonghieu() {
        return thuonghieu;
    }

    public void setThuonghieu(Boolean thuonghieu) {
        this.thuonghieu = thuonghieu;
    }

    public String getTennoibat() {
        return tennoibat;
    }

    public void setTennoibat(String tennoibat) {
        this.tennoibat = tennoibat;
    }

    public String getTentopnoibat() {
        return tentopnoibat;
    }

    public void setTentopnoibat(String tentopnoibat) {
        this.tentopnoibat = tentopnoibat;
    }

    public List<ThuongHieu> getThuongHieuList() {
        return thuongHieuList;
    }

    public void setThuongHieuList(List<ThuongHieu> thuongHieuList) {
        this.thuongHieuList = thuongHieuList;
    }

    public List<SanPham> getSanPhamList() {
        return sanPhamList;
    }

    public void setSanPhamList(List<SanPham> sanPhamList) {
        this.sanPhamList = sanPhamList;
    }
}
