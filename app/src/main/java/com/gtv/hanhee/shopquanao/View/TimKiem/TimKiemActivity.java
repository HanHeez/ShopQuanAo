package com.gtv.hanhee.shopquanao.View.TimKiem;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;


import com.gtv.hanhee.shopquanao.Adapter.AdapterDienTuTopDienThoai;
import com.gtv.hanhee.shopquanao.Adapter.AdapterRecycleDanhGia;
import com.gtv.hanhee.shopquanao.Model.ObjectClass.ILoadMore;
import com.gtv.hanhee.shopquanao.Model.ObjectClass.LoadMoreScroll;
import com.gtv.hanhee.shopquanao.Model.ObjectClass.SanPham;
import com.gtv.hanhee.shopquanao.Presenter.TimKiem.PresenterTimKiem;
import com.gtv.hanhee.shopquanao.R;

import java.util.ArrayList;
import java.util.List;

public class TimKiemActivity extends AppCompatActivity implements ViewTimKiem,ILoadMore,SearchView.OnQueryTextListener {
    Toolbar toolbar;
    RecyclerView recycleTimKiem;
    PresenterTimKiem presenterTimKiem;
    ProgressBar progressBar;
    List<SanPham> sanPhamList;
    LoadMoreScroll loadMoreScroll;
    SearchView searchView;
    AdapterDienTuTopDienThoai adapterDienTuTopDienThoai;
    String tensp;
    int limit;
    MenuItem itSearch;
    CountDownTimer cdtm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_timkiem);
        toolbar = findViewById(R.id.toolbarTimKiem);
        recycleTimKiem = findViewById(R.id.recycleTimKiem);
        progressBar = findViewById(R.id.pgbTimKiem);

        sanPhamList = new ArrayList<>();
        limit = 0;

        adapterDienTuTopDienThoai = new AdapterDienTuTopDienThoai(R.layout.layout_sanpham_custom_recycle_topdienthoaimaytinhbang,this,sanPhamList);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),2);

        recycleTimKiem.setLayoutManager(layoutManager);
        recycleTimKiem.setAdapter(adapterDienTuTopDienThoai);
        loadMoreScroll = new LoadMoreScroll(layoutManager,this,false);
        adapterDienTuTopDienThoai.notifyDataSetChanged();

        presenterTimKiem = new PresenterTimKiem(this);
        setSupportActionBar(toolbar);
        recycleTimKiem.addOnScrollListener(loadMoreScroll);

        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_timkiem,menu);

        itSearch = menu.findItem(R.id.itSearch);
        searchView = (SearchView) itSearch.getActionView();
        searchView.setIconified(false);
        searchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public void HienThiDSSPTheoTenSP(List<SanPham> sanPhams) {
        sanPhamList.addAll(sanPhams);
        progressBar.setVisibility(View.GONE);
        loadMoreScroll.setLoaded();
        adapterDienTuTopDienThoai.notifyDataSetChanged();
    }

    @Override
    public void LoadMore(int tongitem) {
        progressBar.setVisibility(View.VISIBLE);
        presenterTimKiem.LayDSSPTheoTenSPLoadMore(tensp,tongitem,this);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {

        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        sanPhamList.clear();

        tensp = newText;
        if(cdtm != null){
            cdtm.cancel();
        }
        cdtm = new CountDownTimer(700, 700) {

            public void onTick(long millisUntilFinished) {
                Log.d("TIME","seconds remaining: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                 presenterTimKiem.LayDSSanPhamTheoTenSP(0, tensp, TimKiemActivity.this);
            }
        };
        cdtm.start();
        return false;
    }
}
