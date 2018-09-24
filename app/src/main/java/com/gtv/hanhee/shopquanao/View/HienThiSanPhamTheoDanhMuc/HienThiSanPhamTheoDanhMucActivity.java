package com.gtv.hanhee.shopquanao.View.HienThiSanPhamTheoDanhMuc;

import android.app.Fragment;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.gtv.hanhee.shopquanao.Adapter.AdapterDienTuTopDienThoai;

import com.gtv.hanhee.shopquanao.Model.ObjectClass.ILoadMore;
import com.gtv.hanhee.shopquanao.Model.ObjectClass.LoadMoreScroll;
import com.gtv.hanhee.shopquanao.Model.ObjectClass.SanPham;
import com.gtv.hanhee.shopquanao.Presenter.GioHang.PresenterGioHang;
import com.gtv.hanhee.shopquanao.Presenter.HienThiSanPhamTheoDanhMuc.PresenterHienThiSanPhamTheoDanhMuc;
import com.gtv.hanhee.shopquanao.R;
import com.gtv.hanhee.shopquanao.View.GioHang.GioHangActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class HienThiSanPhamTheoDanhMucActivity extends AppCompatActivity implements ViewHienThiSPTheoDanhMuc,View.OnClickListener,ILoadMore {
    PresenterHienThiSanPhamTheoDanhMuc presenterHienThiSanPhamTheoDanhMuc;
    RecyclerView recycleDSSPTheoDanhMuc;
    Button btnThayDoiTrangThai;
    boolean dangGrid = true;
    RecyclerView.LayoutManager layoutManager;
    TextView txtGioHang;
    int maloaisp;
    boolean kiemtra;
    AdapterDienTuTopDienThoai adapterDienTuTopDienThoai;
    Toolbar toolbar;
    List<SanPham> sanPhamList;
    LoadMoreScroll loadMoreScroll;
    ProgressBar progressBar;
    PresenterGioHang presenterGioHang;
    Boolean onPause=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_hienthisanphamtheodanhmuc);

        Intent intent = getIntent();
        maloaisp = intent.getIntExtra("MALOAI",0);
        String tensanpham = intent.getStringExtra("TENLOAI");
        kiemtra = intent.getBooleanExtra("KIEMTRA",false);

        recycleDSSPTheoDanhMuc = findViewById(R.id.recycleDSSPTheoDanhMuc);
        btnThayDoiTrangThai = findViewById(R.id.btnThayDoiTrangThaiRecycle);
        progressBar = findViewById(R.id.pgHienThiSPTheoDanhMuc);

        presenterGioHang = new PresenterGioHang(this);

        toolbar = findViewById(R.id.toolbarHienThiSanPham);
        toolbar.setTitle(tensanpham);
        setSupportActionBar(toolbar);
        sanPhamList = new ArrayList<>();

        if (dangGrid) {
            layoutManager = new GridLayoutManager(getApplicationContext(),2);
            adapterDienTuTopDienThoai = new AdapterDienTuTopDienThoai(R.layout.layout_sanpham_custom_recycle_topdienthoaimaytinhbang,this, sanPhamList);
        } else {
            layoutManager = new LinearLayoutManager(getApplicationContext());
            adapterDienTuTopDienThoai = new AdapterDienTuTopDienThoai(R.layout.layout_sanpham_custom_linear_recycle_topdienthoaivamaytinhbang,this, sanPhamList);
        }

        recycleDSSPTheoDanhMuc.setLayoutManager(layoutManager);
        recycleDSSPTheoDanhMuc.setAdapter(adapterDienTuTopDienThoai);
        loadMoreScroll = new LoadMoreScroll(layoutManager,this,false);
        recycleDSSPTheoDanhMuc.addOnScrollListener(loadMoreScroll);
        adapterDienTuTopDienThoai.notifyDataSetChanged();

        presenterHienThiSanPhamTheoDanhMuc = new PresenterHienThiSanPhamTheoDanhMuc(this);
        presenterHienThiSanPhamTheoDanhMuc.LayDanhSachSanPhamTheoMaLoai(maloaisp, kiemtra, 0, this);

        btnThayDoiTrangThai.setOnClickListener(this);

        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_trangchu,menu);
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
                Intent iGioHang = new Intent(HienThiSanPhamTheoDanhMucActivity.this,GioHangActivity.class);
                startActivity(iGioHang);
            }
        });

        return true;
    }


    @Override
    public void HienThiSPTheoMaLoai(List<SanPham> sanPhams) {
        sanPhamList.addAll(sanPhams);
        progressBar.setVisibility(View.GONE);
        Log.d("kiemtra", String.valueOf(sanPhamList.size()));
        loadMoreScroll.setLoaded();
        adapterDienTuTopDienThoai.notifyDataSetChanged();
    }

    @Override
    public void LoiHienThiDanhSachSanPham() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnThayDoiTrangThaiRecycle:
                dangGrid = !dangGrid;
                    presenterHienThiSanPhamTheoDanhMuc.LayDanhSachSanPhamTheoMaLoai(maloaisp, kiemtra, 0,this);
                break;
        }
    }

    @Override
    public void LoadMore(int tongitem) {
        progressBar.setVisibility(View.VISIBLE);
        presenterHienThiSanPhamTheoDanhMuc.LayDanhSachSanPhamTheoMaLoaiLoadMore(maloaisp,kiemtra,tongitem,this);

    }

    @Override
    protected void onPause() {
        super.onPause();
        onPause = true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (onPause) {
            txtGioHang.setVisibility(View.VISIBLE);
            if (presenterGioHang.soLuongSanPhamTrongGioHang()==0) {
                txtGioHang.setVisibility(View.GONE);
            } else {
                txtGioHang.setVisibility(View.VISIBLE);
                txtGioHang.setText(String.valueOf(presenterGioHang.soLuongSanPhamTrongGioHang()));
            }
        }
    }
}
