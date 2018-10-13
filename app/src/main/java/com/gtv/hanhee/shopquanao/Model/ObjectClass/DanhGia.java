package com.gtv.hanhee.shopquanao.Model.ObjectClass;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DanhGia {
    @SerializedName("MADG")
    @Expose
    private String madg;
    @SerializedName("MASP")
    @Expose
    private int masp;
    @SerializedName("TENTHIETBI")
    @Expose
    private String tenthietbi;
    @SerializedName("TIEUDE")
    @Expose
    private String tieude;
    @SerializedName("NOIDUNG")
    @Expose
    private String noidung;
    @SerializedName("SOSAO")
    @Expose
    private int sosao;
    @SerializedName("NGAYDANHGIA")
    @Expose
    private String ngaydang;

    public String getMadg() {
        return madg;
    }

    public void setMadg(String madg) {
        this.madg = madg;
    }

    public int getMasp() {
        return masp;
    }

    public void setMasp(int masp) {
        this.masp = masp;
    }

    public String getTenthietbi() {
        return tenthietbi;
    }

    public void setTenthietbi(String tenthietbi) {
        this.tenthietbi = tenthietbi;
    }

    public String getTieude() {
        return tieude;
    }

    public void setTieude(String tieude) {
        this.tieude = tieude;
    }

    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }

    public int getSosao() {
        return sosao;
    }

    public void setSosao(int sosao) {
        this.sosao = sosao;
    }

    public String getNgaydang() {
        return ngaydang;
    }

    public void setNgaydang(String ngaydang) {
        this.ngaydang = ngaydang;
    }
}
