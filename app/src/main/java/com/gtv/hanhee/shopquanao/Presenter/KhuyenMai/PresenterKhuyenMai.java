package com.gtv.hanhee.shopquanao.Presenter.KhuyenMai;

import com.gtv.hanhee.shopquanao.Model.KhuyenMai.ModelKhuyenMai;
import com.gtv.hanhee.shopquanao.Model.ObjectClass.KhuyenMai;
import com.gtv.hanhee.shopquanao.View.KhuyenMai.FragmentKhuyenMai;
import com.gtv.hanhee.shopquanao.View.KhuyenMai.ViewKhuyenMai;

import java.util.List;

public class PresenterKhuyenMai {
    ViewKhuyenMai viewKhuyenMai;
    ModelKhuyenMai modelKhuyenMai;

    public PresenterKhuyenMai(ViewKhuyenMai viewKhuyenMai) {
        this.viewKhuyenMai = viewKhuyenMai;
        modelKhuyenMai = new ModelKhuyenMai();
    }

    public void TraVeDSKM(List<KhuyenMai> khuyenMais) {
        viewKhuyenMai.HienThiDSKM(khuyenMais);
    }

    public void LayDanhSachKhuyenMai(ViewKhuyenMai viewKhuyenMai) {
        modelKhuyenMai.LayDanhSachKhuyenMai(viewKhuyenMai);
    }
}
