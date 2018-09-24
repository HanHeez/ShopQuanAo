package com.gtv.hanhee.shopquanao.Presenter.GioHang;

import android.content.Context;

import com.gtv.hanhee.shopquanao.Model.GioHang.GioHangApp;
import com.gtv.hanhee.shopquanao.Model.ObjectClass.DaoMaster;
import com.gtv.hanhee.shopquanao.Model.ObjectClass.DaoSession;
import com.gtv.hanhee.shopquanao.Model.ObjectClass.GioHang;
import com.gtv.hanhee.shopquanao.Model.ObjectClass.GioHangDao;
import com.gtv.hanhee.shopquanao.Model.ObjectClass.SanPham;
import com.gtv.hanhee.shopquanao._Retrofit.APIUtils;

import org.greenrobot.greendao.query.DeleteQuery;
import org.greenrobot.greendao.query.Query;

import java.util.List;

public class PresenterGioHang {
    DaoSession daoSession;
    GioHangDao gioHangDao;
    List<GioHang> tatcasanpham;
    GioHangApp gioHangApp;
    Context context;

    public PresenterGioHang(Context context) {
        this.context = context;
        gioHangApp = (GioHangApp) context.getApplicationContext();
        daoSession = gioHangApp.getDaoSession();
        gioHangDao = daoSession.getGioHangDao();
    }

    public int soLuongSanPhamTrongGioHang() {
        tatcasanpham = gioHangDao.loadAll();
        tatcasanpham.size();
        return tatcasanpham.size();
    }


    public void ThemGioHangVaoDatabase(SanPham sanPhams) {
        GioHang gioHang = new GioHang();
        gioHang.setTensp(sanPhams.getTensp());
        gioHang.setMasp((long) sanPhams.getMasp());
        gioHang.setGiatien(sanPhams.getGia());
        gioHang.setAnhlon(APIUtils.Base_url + sanPhams.getAnhlon());
        gioHang.setAnhnho(APIUtils.Base_url + sanPhams.getAnhnho());
        gioHang.setSoluong(sanPhams.getSoluong());
        gioHang.setSoluongdangdat(1);
        gioHangDao.insert(gioHang);

    }

    public List<GioHang> SanPhamTrongGioHang() {
        List<GioHang> tatcasanpham = gioHangDao.loadAll();
        return tatcasanpham;
    }

    public void XoaSPTrongGioHang(int masp) {
        DeleteQuery<GioHang> gioHangQuery = gioHangDao.queryBuilder()
                .where(GioHangDao.Properties.Masp.eq(masp)).buildDelete();
        gioHangQuery.executeDeleteWithoutDetachingEntities();
        daoSession.clear();
    }

    public void XoaDatabase() {
        DaoMaster.dropAllTables(daoSession.getDatabase(), true);
        DaoMaster.createAllTables(daoSession.getDatabase(), true);
    }

    public void CapNhatSLDangDatMuaGioHang(GioHang gioHang, int soluongdangdat) {
        gioHang.setSoluongdangdat(soluongdangdat);
        gioHangDao.update(gioHang);
    }

    public List<GioHang> LaySPTheoMaspGioHang(int masp) {
        Query<GioHang> gioHangQuery = gioHangDao.queryBuilder()
                .where(GioHangDao.Properties.Masp.eq((long) masp)).build();
        return gioHangQuery.list();
    }


}
