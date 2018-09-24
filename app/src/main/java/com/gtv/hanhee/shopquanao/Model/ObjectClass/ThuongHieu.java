package com.gtv.hanhee.shopquanao.Model.ObjectClass;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ThuongHieu {
    @SerializedName("MASP")
    @Expose
    private String masp;
    @SerializedName("MALOAISP")
    @Expose
    private String maloaisp;
    @SerializedName("TENSP")
    @Expose
    private String tensp;
    @SerializedName("ANHLON")
    @Expose
    private String anhlon;

    public String getMaloaisp() {
        return maloaisp;
    }

    public void setMaloaisp(String maloaisp) {
        this.maloaisp = maloaisp;
    }

    public String getMasp() {
        return masp;
    }

    public void setMasp(String masp) {
        this.masp = masp;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public String getAnhlon() {
        return anhlon;
    }

    public void setAnhlon(String anhlon) {
        this.anhlon = anhlon;
    }
}
