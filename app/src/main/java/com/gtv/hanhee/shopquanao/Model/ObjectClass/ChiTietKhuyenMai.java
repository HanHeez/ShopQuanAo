package com.gtv.hanhee.shopquanao.Model.ObjectClass;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChiTietKhuyenMai {
    @SerializedName("MASP")
    @Expose
    private int masp;
    @SerializedName("MAKM")
    @Expose
    private int makm;
    @SerializedName("PHANTRAMKM")
    @Expose
    private int phantramkm;
    @SerializedName("TENSP")
    @Expose
    private String tensp;
    @SerializedName("GIA")
    @Expose
    private int gia;
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
    private int soluong;
    @SerializedName("MALOAISP")
    @Expose
    private int maloaisp;
    @SerializedName("MATHUONGHIEU")
    @Expose
    private int mathuonghieu;
    @SerializedName("LUOTMUA")
    @Expose
    private int luotmua;
    @SerializedName("MANV")
    @Expose
    private int manv;

    public int getMasp() {
        return masp;
    }

    public void setMasp(int masp) {
        this.masp = masp;
    }

    public int getMakm() {
        return makm;
    }

    public void setMakm(int makm) {
        this.makm = makm;
    }

    public int getPhantramkm() {
        return phantramkm;
    }

    public void setPhantramkm(int phantramkm) {
        this.phantramkm = phantramkm;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
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

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public int getMaloaisp() {
        return maloaisp;
    }

    public void setMaloaisp(int maloaisp) {
        this.maloaisp = maloaisp;
    }

    public int getMathuonghieu() {
        return mathuonghieu;
    }

    public void setMathuonghieu(int mathuonghieu) {
        this.mathuonghieu = mathuonghieu;
    }

    public int getLuotmua() {
        return luotmua;
    }

    public void setLuotmua(int luotmua) {
        this.luotmua = luotmua;
    }

    public int getManv() {
        return manv;
    }

    public void setManv(int manv) {
        this.manv = manv;
    }
}
