package com.gtv.hanhee.shopquanao.View.SanPham;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gtv.hanhee.shopquanao.Adapter.AdapterViewPagerSanPham;
import com.gtv.hanhee.shopquanao.Adapter.AdapterViewPagerTaiKhoan;
import com.gtv.hanhee.shopquanao.R;

public class FragmentSanPham extends android.support.v4.app.Fragment {
    ViewPager viewPager;
    TabLayout tabLayout;
    Toolbar toolbar;
    View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.layout_fragment_sanpham, container, false);
//        tabLayout = view.findViewById(R.id.tabSanPham);
//        viewPager = view.findViewById(R.id.viewPagerSanPham);
//        toolbar = view.findViewById(R.id.toolbarSanPham);
//        AppCompatActivity activity = (AppCompatActivity) getActivity();
//        activity.setSupportActionBar(toolbar);
//        AdapterViewPagerSanPham adapterViewPagerSanPham = new AdapterViewPagerSanPham(activity.getSupportFragmentManager());
//        viewPager.setAdapter(adapterViewPagerSanPham);
//        adapterViewPagerSanPham.notifyDataSetChanged();
//        tabLayout.setupWithViewPager(viewPager);
//        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
//        tabLayout.setTabsFromPagerAdapter(adapterViewPagerSanPham);
        return view;
    }
}
