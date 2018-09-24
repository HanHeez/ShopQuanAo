package com.gtv.hanhee.shopquanao.Model.ObjectClass;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.gtv.hanhee.shopquanao.Model.ObjectClass.SanPham;

import java.util.List;

public class KhuyenMai {
    @SerializedName("MAKM")
    @Expose
    private int makm;
    @SerializedName("MALOAISP")
    @Expose
    private int maloaisp;
    @SerializedName("TENLOAISP")
    @Expose
    private String tenloaisp;
    @SerializedName("TENKM")
    @Expose
    private String tenkn;
    @SerializedName("NGAYBATDAU")
    @Expose
    private String ngaybatdau;
    @SerializedName("NGAYKETTHUC")
    @Expose
    private String NGAYKETTHUC;
    @SerializedName("HINHKHUYENMAI")
    @Expose
    private String hinhkm;
    @SerializedName("DANHSACHSANPHAM")
    @Expose
    private List<ChiTietKhuyenMai> danhsachsanphamkhuyenmai;

    public int getMakm() {
        return makm;
    }

    public void setMakm(int makm) {
        this.makm = makm;
    }

    public int getMaloaisp() {
        return maloaisp;
    }

    public void setMaloaisp(int maloaisp) {
        this.maloaisp = maloaisp;
    }

    public String getTenloaisp() {
        return tenloaisp;
    }

    public void setTenloaisp(String tenloaisp) {
        this.tenloaisp = tenloaisp;
    }

    public String getTenkn() {
        return tenkn;
    }

    public void setTenkn(String tenkn) {
        this.tenkn = tenkn;
    }

    public String getNgaybatdau() {
        return ngaybatdau;
    }

    public void setNgaybatdau(String ngaybatdau) {
        this.ngaybatdau = ngaybatdau;
    }

    public String getNGAYKETTHUC() {
        return NGAYKETTHUC;
    }

    public void setNGAYKETTHUC(String NGAYKETTHUC) {
        this.NGAYKETTHUC = NGAYKETTHUC;
    }

    public String getHinhkm() {
        return hinhkm;
    }

    public void setHinhkm(String hinhkm) {
        this.hinhkm = hinhkm;
    }

    public List<ChiTietKhuyenMai> getDanhsachsanphamkhuyenmai() {
        return danhsachsanphamkhuyenmai;
    }

    public void setDanhsachsanphamkhuyenmai(List<ChiTietKhuyenMai> danhsachsanphamkhuyenmai) {
        this.danhsachsanphamkhuyenmai = danhsachsanphamkhuyenmai;
    }
}
