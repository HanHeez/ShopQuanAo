package com.gtv.hanhee.shopquanao.View.FragmentsManHinhChinh;

import com.gtv.hanhee.shopquanao.Model.ObjectClass.DienTu;
import com.gtv.hanhee.shopquanao.Model.ObjectClass.SanPham;
import com.gtv.hanhee.shopquanao.Model.ObjectClass.ThuongHieu;


import java.util.List;

public interface ViewDienTu {
    void HienThiDanhSach(DienTu dienTu);

    void HienThiLogo(List<ThuongHieu> thuongHieus);

    void HienThiSanPhamMoiVe(List<SanPham> sanPhams);

    void HienThiDSPK(DienTu dienTu);
}
