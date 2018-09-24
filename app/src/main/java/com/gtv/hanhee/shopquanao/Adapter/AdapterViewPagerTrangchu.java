package com.gtv.hanhee.shopquanao.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.gtv.hanhee.shopquanao.View.FragmentsManHinhChinh.FragmentNew;
import com.gtv.hanhee.shopquanao.View.KhuyenMai.FragmentKhuyenMai;
import com.gtv.hanhee.shopquanao.View.SanPham.FragmentSanPham;
import com.gtv.hanhee.shopquanao.View.TaiKhoan.FragmentTaiKhoan;
import com.gtv.hanhee.shopquanao.View.TrangChu.FragmentTrangChu;


public class AdapterViewPagerTrangchu extends FragmentStatePagerAdapter {
    FragmentTrangChu fragmentTrangChu;
    FragmentSanPham fragmentSanPham;
    FragmentNew fragmentNew;
    FragmentKhuyenMai fragmentKhuyenMai;
    FragmentTaiKhoan fragmentTaiKhoan;

    public AdapterViewPagerTrangchu(FragmentManager fm) {
        super(fm);
        fragmentTrangChu = new FragmentTrangChu();
        fragmentSanPham = new FragmentSanPham();
        fragmentNew = new FragmentNew();
        fragmentKhuyenMai = new FragmentKhuyenMai();
        fragmentTaiKhoan = new FragmentTaiKhoan();
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return fragmentTrangChu;
            case 1:
                return fragmentSanPham;
            case 2:
                return fragmentNew;
            case 3:
                return fragmentKhuyenMai;
            case 4:
                return fragmentTaiKhoan;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 5;
    }
}
