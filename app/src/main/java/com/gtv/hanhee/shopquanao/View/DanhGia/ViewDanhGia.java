package com.gtv.hanhee.shopquanao.View.DanhGia;



import com.gtv.hanhee.shopquanao.Model.ObjectClass.DanhGia;

import java.util.List;

public interface ViewDanhGia {
    void ThemDGThanhCong();
    void ThemDGThatBai();

    void HienThiDSDanhGiaTheoMa(List<DanhGia> danhGiaList);
}
