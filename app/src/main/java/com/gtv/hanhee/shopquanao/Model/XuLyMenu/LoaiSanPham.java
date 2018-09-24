package com.gtv.hanhee.shopquanao.Model.XuLyMenu;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class LoaiSanPham implements Serializable {
    List<LoaiSanPham> listCon;
    @SerializedName("TENLOAISP")
    @Expose
    private String tENLOAISP;
    @SerializedName("MALOAISP")
    @Expose
    private String mALOAISP;
    @SerializedName("MALOAI_CHA")
    @Expose
    private String mALOAICHA;

    public List<LoaiSanPham> getListCon() {
        return listCon;
    }

    public void setListCon(List<LoaiSanPham> listCon) {
        this.listCon = listCon;
    }

    public String getTENLOAISP() {
        return tENLOAISP;
    }

    public void setTENLOAISP(String tENLOAISP) {
        this.tENLOAISP = tENLOAISP;
    }

    public String getMALOAISP() {
        return mALOAISP;
    }

    public void setMALOAISP(String mALOAISP) {
        this.mALOAISP = mALOAISP;
    }

    public String getMALOAICHA() {
        return mALOAICHA;
    }

    public void setMALOAICHA(String mALOAICHA) {
        this.mALOAICHA = mALOAICHA;
    }

}
