package com.gtv.hanhee.shopquanao.View.ChiTietSanPham;



import com.gtv.hanhee.shopquanao.Model.ObjectClass.DanhGia;
import com.gtv.hanhee.shopquanao.Model.ObjectClass.SanPham;

import java.util.List;

public interface ViewChiTietSanPham {
    void HienThiSPTheoMaSP(SanPham sanPhams);

    void HienThiDSDanhGiaTheoMa(List<DanhGia> danhGiaList);
}
