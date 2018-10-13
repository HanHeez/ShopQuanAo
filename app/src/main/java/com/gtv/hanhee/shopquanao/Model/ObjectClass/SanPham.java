package com.gtv.hanhee.shopquanao.Model.ObjectClass;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class SanPham implements Serializable {
    @SerializedName("MASP")
    @Expose
    private Integer masp;
    @SerializedName("TENSP")
    @Expose
    private String tensp;
    @SerializedName("GIA")
    @Expose
    private Integer gia;
    @SerializedName("ANHLON")
    @Expose
    private String anhlon;
    @SerializedName("ANHNHO")
    @Expose
    private String anhnho;
    @SerializedName("THONGTIN")
    @Expose
    private String thongtin;
    @SerializedName("SOLUONG")
    @Expose
    private Integer soluong;
    @SerializedName("MALOAISP")
    @Expose
    private Integer maloaisp;
    @SerializedName("MATHUONGHIEU")
    @Expose
    private Integer mathuonghieu;
    @SerializedName("LUOTMUA")
    @Expose
    private Integer luotmua;
    @SerializedName("MANV")
    @Expose
    private Integer manv;
    @SerializedName("TENNV")
    @Expose
    private String tennv;
    @SerializedName("PHANTRAMKM")
    @Expose
    private Integer phantramkm;
    @SerializedName("THONGSOKYTHUAT")
    @Expose
    private List<ChiTietSanPham> chiTietSanPhamList;

    public Integer getMasp() {
        return masp;
    }

    public void setMasp(Integer masp) {
        this.masp = masp;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public Integer getGia() {
        return gia;
    }

    public void setGia(Integer gia) {
        this.gia = gia;
    }

    public String getAnhlon() {
        return anhlon;
    }

    public void setAnhlon(String anhlon) {
        this.anhlon = anhlon;
    }

    public String getAnhnho() {
        return anhnho;
    }

    public void setAnhnho(String anhnho) {
        this.anhnho = anhnho;
    }

    public String getThongtin() {
        return thongtin;
    }

    public void setThongtin(String thongtin) {
        this.thongtin = thongtin;
    }

    public Integer getSoluong() {
        return soluong;
    }

    public void setSoluong(Integer soluong) {
        this.soluong = soluong;
    }

    public Integer getMaloaisp() {
        return maloaisp;
    }

    public void setMaloaisp(Integer maloaisp) {
        this.maloaisp = maloaisp;
    }

    public Integer getMathuonghieu() {
        return mathuonghieu;
    }

    public void setMathuonghieu(Integer mathuonghieu) {
        this.mathuonghieu = mathuonghieu;
    }

    public Integer getLuotmua() {
        return luotmua;
    }

    public void setLuotmua(Integer luotmua) {
        this.luotmua = luotmua;
    }

    public Integer getManv() {
        return manv;
    }

    public void setManv(Integer manv) {
        this.manv = manv;
    }

    public String getTennv() {
        return tennv;
    }

    public void setTennv(String tennv) {
        this.tennv = tennv;
    }

    public Integer getPhantramkm() {
        return phantramkm;
    }

    public void setPhantramkm(Integer phantramkm) {
        this.phantramkm = phantramkm;
    }

    public List<ChiTietSanPham> getChiTietSanPhamList() {
        return chiTietSanPhamList;
    }

    public void setChiTietSanPhamList(List<ChiTietSanPham> chiTietSanPhamList) {
        this.chiTietSanPhamList = chiTietSanPhamList;
    }
}
