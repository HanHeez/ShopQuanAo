package com.gtv.hanhee.shopquanao.Model.DangNhap;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelDangNhap {
    @SerializedName("ketqua")
    @Expose
    private Boolean ketqua;
    @SerializedName("tennv")
    @Expose
    private String tennv;

    public Boolean getKetqua() {
        return ketqua;
    }

    public void setKetqua(Boolean ketqua) {
        this.ketqua = ketqua;
    }

    public String getTennv() {
        return tennv;
    }

    public void setTennv(String tennv) {
        this.tennv = tennv;
    }
}
