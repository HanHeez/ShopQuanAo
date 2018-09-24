package com.gtv.hanhee.shopquanao.View.TrangChu;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gigamole.infinitecycleviewpager.HorizontalInfiniteCycleViewPager;
import com.gtv.hanhee.shopquanao.Adapter.ExpandAdapter;
import com.gtv.hanhee.shopquanao.Adapter.HorizontalPagerAdapter;
import com.gtv.hanhee.shopquanao.Model.XuLyMenu.LoaiSanPham;
import com.gtv.hanhee.shopquanao.Presenter.GioHang.PresenterGioHang;
import com.gtv.hanhee.shopquanao.Presenter.TrangChu.PresenterLogicXuLyMenu;
import com.gtv.hanhee.shopquanao.R;
import com.gtv.hanhee.shopquanao.View.GioHang.GioHangActivity;
import com.gtv.hanhee.shopquanao.View.TimKiem.TimKiemActivity;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class FragmentTrangChu extends Fragment implements ViewXuLyMenu,View.OnClickListener {
    Toolbar mainToolbar;
    View view;
    HorizontalInfiniteCycleViewPager horizontalInfiniteCycleViewPager;
    Timer timer;
    DrawerLayout drawerLayout;
    TextView txtGioHang;
    ActionBarDrawerToggle drawerToggle;
    ExpandableListView expandableListView;
    PresenterLogicXuLyMenu presenterLogicXuLyMenu;
    PresenterGioHang presenterGioHang;
    LinearLayout lnSearch;
    Boolean onPause=false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
//        FacebookSdk.sdkInitialize(getActivity());
//        presenterLogicXuLyMenu.LayTenNguoiDungFacebook(getContext());
        view = inflater.inflate(R.layout.layout_fragment_trangchu,container,false);
        presenterLogicXuLyMenu = new PresenterLogicXuLyMenu(this);
        presenterGioHang = new PresenterGioHang(getContext());
        presenterGioHang.XoaDatabase();
        AddControls();
        AddEvents();
        return view;
    }

    @Override
    public void HienThiDanhSachMenu(List<LoaiSanPham> loaiSanPhamList) {
        if (loaiSanPhamList.size() > 0) {
            ExpandAdapter expandAdapter = new ExpandAdapter(getContext(), loaiSanPhamList);
            expandableListView.setAdapter(expandAdapter);
            expandAdapter.notifyDataSetChanged();
        }
    }

    private void AddEvents() {
        presenterLogicXuLyMenu.LayDanhSachSP(0);
    }

    private void AddControls() {
        lnSearch = view.findViewById(R.id.lnSearch);
        lnSearch.setOnClickListener(this);
        mainToolbar = view.findViewById(R.id.mainToolbar);
        expandableListView = view.findViewById(R.id.epMenu);
        setHasOptionsMenu(true);
        final AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(mainToolbar);
        activity.getSupportActionBar().setHomeButtonEnabled(true);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        drawerLayout = view.findViewById(R.id.drawerLayout);
        drawerToggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, R.string.open, R.string.close);
//        drawerToggle.setDrawerIndicatorEnabled(false);
//        drawerToggle.setHomeAsUpIndicator(R.drawable.icon_new);
        activity.getSupportActionBar().setHomeButtonEnabled(true);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_trangchu, menu);
        MenuItem itemGioHang = menu.findItem(R.id.itemCart);
        View view = itemGioHang.getActionView();
        txtGioHang = view.findViewById(R.id.txtSLSPGioHang);
        if (presenterGioHang.soLuongSanPhamTrongGioHang()==0) {
            txtGioHang.setVisibility(View.GONE);
        } else {
            txtGioHang.setVisibility(View.VISIBLE);
            txtGioHang.setText(String.valueOf(presenterGioHang.soLuongSanPhamTrongGioHang()));
        }

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iGioHang = new Intent(getActivity(),GioHangActivity.class);
                startActivity(iGioHang);
            }
        });

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return true;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        horizontalInfiniteCycleViewPager = (HorizontalInfiniteCycleViewPager) view.findViewById(R.id.hicvp);
        horizontalInfiniteCycleViewPager.setAdapter(new HorizontalPagerAdapter(getContext(), false));
        horizontalInfiniteCycleViewPager.setScrollDuration(0);
        horizontalInfiniteCycleViewPager.setScrollDuration(500);

        final Handler mHandler = new Handler();
        final Runnable mUpdateResults = new Runnable() {
            public void run() {
                horizontalInfiniteCycleViewPager.setCurrentItem(horizontalInfiniteCycleViewPager.getRealItem() + 1);
            }
        };
        int delay = 0; // delay for 1 sec.
        int period = 3500; // repeat every 4 sec.
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                mHandler.post(mUpdateResults);
            }

        }, delay, period);
    }


    @Override
    public void onPause() {
        super.onPause();
        timer.cancel();
        onPause = true;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (onPause) {
            if (presenterGioHang.soLuongSanPhamTrongGioHang()==0) {
                txtGioHang.setVisibility(View.GONE);
            } else {
                txtGioHang.setVisibility(View.VISIBLE);
                txtGioHang.setText(String.valueOf(presenterGioHang.soLuongSanPhamTrongGioHang()));
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.lnSearch:
                Intent iTimKiem = new Intent(getContext(), TimKiemActivity.class);
                startActivity(iTimKiem);
                break;
        }
    }
}