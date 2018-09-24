package com.gtv.hanhee.shopquanao.View.GioHang;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.gtv.hanhee.shopquanao.Adapter.AdapterGioHang;
import com.gtv.hanhee.shopquanao.Presenter.GioHang.PresenterGioHang;
import com.gtv.hanhee.shopquanao.R;
import com.gtv.hanhee.shopquanao.View.ThanhToan.ThanhToanActivity;

import java.util.List;

public class GioHangActivity extends AppCompatActivity implements View.OnClickListener {
    RecyclerView recyclerView;
    PresenterGioHang presenterGioHang;
    Toolbar toolbar;
    Button btnMuaNgay;
    Boolean onPause=false;
    AdapterGioHang adapterGioHang;
    public static Activity fa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fa = this;
        setContentView(R.layout.layout_giohang);
        presenterGioHang = new PresenterGioHang(this);
        btnMuaNgay = findViewById(R.id.btnMuaNgay);
        toolbar = findViewById(R.id.toolbarGioHang);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.recycleGioHang);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        adapterGioHang = new AdapterGioHang(this,presenterGioHang.SanPhamTrongGioHang());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterGioHang);
        adapterGioHang.notifyDataSetChanged();
        btnMuaNgay.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnMuaNgay:
                if (presenterGioHang.SanPhamTrongGioHang().size()>0) {
                    Intent iThanhToan = new Intent(this, ThanhToanActivity.class);
                    startActivity(iThanhToan);
                } else {
                    Toast.makeText(this, "Chưa có sản phẩm trong giỏ hàng", Toast.LENGTH_SHORT).show();
                }
                break;
        }
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
            adapterGioHang = new AdapterGioHang(this,presenterGioHang.SanPhamTrongGioHang());
            recyclerView.setAdapter(adapterGioHang);
            adapterGioHang.notifyDataSetChanged();
        }
    }
}
