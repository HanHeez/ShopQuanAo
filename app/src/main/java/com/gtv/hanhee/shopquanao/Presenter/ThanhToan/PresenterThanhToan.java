package com.gtv.hanhee.shopquanao.Presenter.ThanhToan;


import com.gtv.hanhee.shopquanao.Model.ObjectClass.ChiTietHoaDon;
import com.gtv.hanhee.shopquanao.Model.ObjectClass.HoaDon;
import com.gtv.hanhee.shopquanao.Model.ThanhToan.ModelThanhToan;
import com.gtv.hanhee.shopquanao.View.ThanhToan.ViewThanhToan;

import java.util.List;

public class PresenterThanhToan {
    ModelThanhToan modelThanhToan;
    ViewThanhToan viewThanhToan;
    String chuoijson;

    public PresenterThanhToan(ViewThanhToan viewThanhToan) {
        this.viewThanhToan = viewThanhToan;
        modelThanhToan = new ModelThanhToan();
    }


    public void ThemChiTietThanhToan(HoaDon hoaDon, List<ChiTietHoaDon> chiTietHoaDonList) {
        chuoijson = "";
        chuoijson = "{\"DANHSACHSANPHAM\": [ ";
        for (int i=0;i<chiTietHoaDonList.size();i++) {
            chuoijson+= "{";
            chuoijson+="\"masp\" : "+chiTietHoaDonList.get(i).getMasp()+",";
            chuoijson+="\"soluong\" : "+chiTietHoaDonList.get(i).getSoluong();
            if (i==chiTietHoaDonList.size() - 1) {
                chuoijson+="}";
            } else {
                chuoijson+="},";
            }
        }
        chuoijson+="]}";
        hoaDon.setDanhsachsanpham(chuoijson);
        modelThanhToan.ThemHoaDon(hoaDon,viewThanhToan);
    }

    public void TraVeKetQuaThemHoaDon(String ketqua) {
        if (ketqua.equals("true")) {
            viewThanhToan.ThemHoaDonThanhCong();
        } else {
            viewThanhToan.ThemHoaDonThatBai();
        }
    }
}
