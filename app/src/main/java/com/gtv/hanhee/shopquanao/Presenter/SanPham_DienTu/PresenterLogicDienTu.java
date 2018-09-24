package com.gtv.hanhee.shopquanao.Presenter.SanPham_DienTu;

import com.gtv.hanhee.shopquanao.Model.HienThiSanPhamTheoDanhMuc.ModelHienThiSanPhamTheoDanhMuc;

import com.gtv.hanhee.shopquanao.Model.ObjectClass.DienTu;
import com.gtv.hanhee.shopquanao.Model.ObjectClass.SanPham;
import com.gtv.hanhee.shopquanao.Model.ObjectClass.ThuongHieu;
import com.gtv.hanhee.shopquanao.Model._TrangChu_DienTu.ModelDienTu;
import com.gtv.hanhee.shopquanao.Model._TrangChu_DienTu.ModelPhuKien;
import com.gtv.hanhee.shopquanao.Model._TrangChu_DienTu.ModelSanPhamMoi;
import com.gtv.hanhee.shopquanao.Model._TrangChu_DienTu.ModelThuongHieuLon;
import com.gtv.hanhee.shopquanao.View.FragmentsManHinhChinh.ViewDienTu;

import java.util.List;

public class PresenterLogicDienTu implements IPresenterDienTu {
    ViewDienTu viewDienTu;
    ModelDienTu modelDienTu;
    ModelThuongHieuLon modelThuongHieuLon;
    ModelSanPhamMoi modelSanPhamMoi;
    ModelPhuKien modelPhuKien;
    ModelHienThiSanPhamTheoDanhMuc modelHienThiSanPhamTheoDanhMuc;

    public PresenterLogicDienTu(ViewDienTu viewDienTu) {
        this.viewDienTu = viewDienTu;
        modelDienTu = new ModelDienTu();
        modelThuongHieuLon = new ModelThuongHieuLon();
        modelSanPhamMoi = new ModelSanPhamMoi();
        modelPhuKien = new ModelPhuKien();
    }

    @Override
    public void LayDanhSachDienTu(ViewDienTu viewDienTu) {
        modelDienTu.LayDanhSachThuongHieuLon(viewDienTu);
    }

    @Override
    public void TraVeDienTuList(DienTu dienTu) {
        viewDienTu.HienThiDanhSach(dienTu);
    }

    public void LayDSPhuKien(ViewDienTu viewDienTu) {
        modelPhuKien.LayDSPhuKien(viewDienTu);
    }

    public void TraVeDSPK(DienTu dienTu) {
        viewDienTu.HienThiDSPK(dienTu);
    }

    @Override
    public void LayLogoThuongHieuLon(ViewDienTu viewDienTu) {
        modelThuongHieuLon.LayLogoThuongHieuLon(viewDienTu);
    }
    public void TraVeLogoThuongHieu(List<ThuongHieu> thuongHieus) {
        viewDienTu.HienThiLogo(thuongHieus);
    }

    public void TraVeSanPhamMoiVe(List<SanPham> sanPhams) {
        viewDienTu.HienThiSanPhamMoiVe(sanPhams);
    }
    public void LaySanPhamMoiVe(ViewDienTu viewDienTu) {
        modelSanPhamMoi.LaySanPhamMoiVe(viewDienTu);
    }


}
