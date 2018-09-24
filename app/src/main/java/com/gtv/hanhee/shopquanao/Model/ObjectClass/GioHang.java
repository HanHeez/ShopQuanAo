package com.gtv.hanhee.shopquanao.Model.ObjectClass;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

@Entity(nameInDb = "user")
public class GioHang {
    @Id
    long masp;


    String tensp;
    int giatien;
    String anhlon;
    String anhnho;
    int soluong;
    int soluongdangdat;
    @Generated(hash = 1550317950)
    public GioHang(long masp, String tensp, int giatien, String anhlon,
            String anhnho, int soluong, int soluongdangdat) {
        this.masp = masp;
        this.tensp = tensp;
        this.giatien = giatien;
        this.anhlon = anhlon;
        this.anhnho = anhnho;
        this.soluong = soluong;
        this.soluongdangdat = soluongdangdat;
    }
    @Generated(hash = 682832776)
    public GioHang() {
    }
    public long getMasp() {
        return this.masp;
    }
    public void setMasp(long masp) {
        this.masp = masp;
    }
    public String getTensp() {
        return this.tensp;
    }
    public void setTensp(String tensp) {
        this.tensp = tensp;
    }
    public int getGiatien() {
        return this.giatien;
    }
    public void setGiatien(int giatien) {
        this.giatien = giatien;
    }
    public String getAnhlon() {
        return this.anhlon;
    }
    public void setAnhlon(String anhlon) {
        this.anhlon = anhlon;
    }
    public String getAnhnho() {
        return this.anhnho;
    }
    public void setAnhnho(String anhnho) {
        this.anhnho = anhnho;
    }
    public int getSoluong() {
        return this.soluong;
    }
    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }
    public int getSoluongdangdat() {
        return this.soluongdangdat;
    }
    public void setSoluongdangdat(int soluongdangdat) {
        this.soluongdangdat = soluongdangdat;
    }
    
}
