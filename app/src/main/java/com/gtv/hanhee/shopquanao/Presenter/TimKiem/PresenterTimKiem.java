package com.gtv.hanhee.shopquanao.Presenter.TimKiem;

import android.util.Log;
import android.view.View;

import com.gtv.hanhee.shopquanao.Model.ObjectClass.SanPham;
import com.gtv.hanhee.shopquanao.Model.TimKiem.ModelTimKiem;
import com.gtv.hanhee.shopquanao.View.TimKiem.ViewTimKiem;

import java.util.List;

public class PresenterTimKiem {
    ModelTimKiem modelTimKiem;
    ViewTimKiem viewTimKiem;

    public PresenterTimKiem(ViewTimKiem viewTimKiem) {
        this.viewTimKiem = viewTimKiem;
        modelTimKiem = new ModelTimKiem();
    }

    public void TraVeDSSPTheoTenSP(List<SanPham> sanPhams) {
        viewTimKiem.HienThiDSSPTheoTenSP(sanPhams);
    }

    public void LayDSSanPhamTheoTenSP(int limit, String tensp, ViewTimKiem viewTimKiem) {
        modelTimKiem.LayDSSanPhamTheoTenSP(limit,tensp,viewTimKiem);
    }

    public void LayDSSPTheoTenSPLoadMore(String tensp, int tongitem, ViewTimKiem viewTimKiem) {
        modelTimKiem.LayDSSanPhamTheoTenSP(tongitem,tensp,viewTimKiem);
    }
}
