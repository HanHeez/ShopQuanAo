package com.gtv.hanhee.shopquanao.View.DanhGia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.gtv.hanhee.shopquanao.Adapter.AdapterRecycleDanhGia;

import com.gtv.hanhee.shopquanao.Model.ObjectClass.DanhGia;
import com.gtv.hanhee.shopquanao.Model.ObjectClass.ILoadMore;
import com.gtv.hanhee.shopquanao.Model.ObjectClass.LoadMoreScroll;
import com.gtv.hanhee.shopquanao.Presenter.DanhGia.PresenterDanhGia;
import com.gtv.hanhee.shopquanao.R;

import java.util.ArrayList;
import java.util.List;

public class DanhSachDanhGiaActivity extends AppCompatActivity implements ViewDanhGia,ILoadMore {
    RecyclerView recycleDSDG;
    PresenterDanhGia presenterDanhGia;
    int masp;
    int limit=0;
    ProgressBar pgbDSDG;
    List<DanhGia> tatcadanhgia;
    AdapterRecycleDanhGia adapterRecycleDanhGia;
    LoadMoreScroll loadMoreScroll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_danhsachdanhgia);
        recycleDSDG = findViewById(R.id.recycleDSDG);
        pgbDSDG = findViewById(R.id.pgbDSDG);
        tatcadanhgia = new ArrayList<>();
        adapterRecycleDanhGia = new AdapterRecycleDanhGia(getApplicationContext(),tatcadanhgia,limit);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        recycleDSDG.setLayoutManager(layoutManager);
        recycleDSDG.setAdapter(adapterRecycleDanhGia);
        loadMoreScroll = new LoadMoreScroll(layoutManager,this,false);
        recycleDSDG.addOnScrollListener(loadMoreScroll);


        masp = getIntent().getIntExtra("masp",0);

        presenterDanhGia = new PresenterDanhGia(this);
        pgbDSDG.setVisibility(View.VISIBLE);
        presenterDanhGia.LayDSDGCuaSP(masp,limit, 7, this);
    }

    @Override
    public void ThemDGThanhCong() {

    }

    @Override
    public void ThemDGThatBai() {

    }

    @Override
    public void HienThiDSDanhGiaTheoMa(List<DanhGia> danhGiaList) {
        tatcadanhgia.addAll(danhGiaList);
        pgbDSDG.setVisibility(View.GONE);
        loadMoreScroll.setLoaded();
        adapterRecycleDanhGia.notifyDataSetChanged();
    }

    @Override
    public void LoadMore(int tongitem) {
        pgbDSDG.setVisibility(View.VISIBLE);
        presenterDanhGia.LayDSDGCuaSP(masp,tongitem, 7,this);

    }
}
