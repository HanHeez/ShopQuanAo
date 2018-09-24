package com.gtv.hanhee.shopquanao.Model.DangKy;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelDangKy {
    @SerializedName("ketqua")
    @Expose
    private String ketqua;

    public String getKetqua() {
        return ketqua;
    }

    public void setKetqua(String ketqua) {
        this.ketqua = ketqua;
    }

}
