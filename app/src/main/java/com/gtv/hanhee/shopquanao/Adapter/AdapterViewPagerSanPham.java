package com.gtv.hanhee.shopquanao.Adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.gtv.hanhee.shopquanao.View.SanPham.FragmentChuongTrinhKhuyenMai;
import com.gtv.hanhee.shopquanao.View.SanPham.FragmentLamDep;
import com.gtv.hanhee.shopquanao.View.SanPham.FragmentMeVaBe;
import com.gtv.hanhee.shopquanao.View.SanPham.FragmentNhaCuaVaDoiSong;
import com.gtv.hanhee.shopquanao.View.SanPham.FragmentNoiBat;
import com.gtv.hanhee.shopquanao.View.SanPham.FragmentTheThaoVaDuLich;
import com.gtv.hanhee.shopquanao.View.SanPham.FragmentThoiTrang;
import com.gtv.hanhee.shopquanao.View.SanPham.FragmentThuongHieu;

import java.util.ArrayList;
import java.util.List;

public class AdapterViewPagerSanPham extends FragmentPagerAdapter {

    List<Fragment> fragmentList = new ArrayList<Fragment>();
    List<String> titleFragment = new ArrayList<String>();

    public AdapterViewPagerSanPham(FragmentManager fm) {
        super(fm);
        fragmentList.add(new FragmentNoiBat());
        fragmentList.add(new FragmentChuongTrinhKhuyenMai());
        fragmentList.add(new FragmentNhaCuaVaDoiSong());
        fragmentList.add(new FragmentMeVaBe());
        fragmentList.add(new FragmentLamDep());
        fragmentList.add(new FragmentThoiTrang());
        fragmentList.add(new FragmentTheThaoVaDuLich());
        fragmentList.add(new FragmentThuongHieu());

        titleFragment.add("Nổi bật");
        titleFragment.add("Chương trình khuyến mãi");
        titleFragment.add("Nhà cửa và đời sống");
        titleFragment.add("Mẹ và bé");
        titleFragment.add("Làm Đẹp");
        titleFragment.add("Thời Trang");
        titleFragment.add("Thể Thao và du lịch");
        titleFragment.add("Thương Hiệu");
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titleFragment.get(position);
    }
}
