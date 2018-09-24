package com.gtv.hanhee.shopquanao.View.TaiKhoan;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.gtv.hanhee.shopquanao.Adapter.AdapterViewPagerTaiKhoan;
import com.gtv.hanhee.shopquanao.R;

public class TaiKhoanActivity extends AppCompatActivity {
    ViewPager viewPager;
    TabLayout tabLayout;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_taikhoan);
        tabLayout = findViewById(R.id.tabTaiKhoan);
        viewPager = findViewById(R.id.viewPagerTaiKhoan);
        toolbar = findViewById(R.id.toolbarTaiKhoan);
        setSupportActionBar(toolbar);
        AdapterViewPagerTaiKhoan adapterViewPagerTaiKhoan = new AdapterViewPagerTaiKhoan(getSupportFragmentManager());
        viewPager.setAdapter(adapterViewPagerTaiKhoan);
        adapterViewPagerTaiKhoan.notifyDataSetChanged();
        tabLayout.setupWithViewPager(viewPager);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setTabsFromPagerAdapter(adapterViewPagerTaiKhoan);
    }
}
