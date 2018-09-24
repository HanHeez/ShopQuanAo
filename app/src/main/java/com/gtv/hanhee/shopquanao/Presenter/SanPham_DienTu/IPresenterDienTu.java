package com.gtv.hanhee.shopquanao.Presenter.SanPham_DienTu;


import com.gtv.hanhee.shopquanao.Model.ObjectClass.DienTu;
import com.gtv.hanhee.shopquanao.View.FragmentsManHinhChinh.ViewDienTu;

public interface IPresenterDienTu {
    void LayDanhSachDienTu(ViewDienTu viewDienTu);

    void LayLogoThuongHieuLon(ViewDienTu viewDienTu);

    void TraVeDienTuList(DienTu dienTu);
}
