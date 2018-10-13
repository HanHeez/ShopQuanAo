package com.gtv.hanhee.shopquanao.View.ChiTietSanPham;


import android.content.Intent;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gtv.hanhee.shopquanao.Adapter.AdapterRecycleDanhGia;
import com.gtv.hanhee.shopquanao.Adapter.AdapterViewPagerSlider;
import com.gtv.hanhee.shopquanao.Model.GioHang.GioHangApp;
import com.gtv.hanhee.shopquanao.Model.ObjectClass.ChiTietSanPham;
import com.gtv.hanhee.shopquanao.Model.ObjectClass.DanhGia;
import com.gtv.hanhee.shopquanao.Model.ObjectClass.DaoSession;
import com.gtv.hanhee.shopquanao.Model.ObjectClass.GioHang;
import com.gtv.hanhee.shopquanao.Model.ObjectClass.GioHangDao;
import com.gtv.hanhee.shopquanao.Model.ObjectClass.SanPham;
import com.gtv.hanhee.shopquanao.Presenter.ChiTietSanPham.PresenterChiTietSanPham;
import com.gtv.hanhee.shopquanao.Presenter.GioHang.PresenterGioHang;
import com.gtv.hanhee.shopquanao.R;
import com.gtv.hanhee.shopquanao.View.DanhGia.DanhGiaActivity;
import com.gtv.hanhee.shopquanao.View.DanhGia.DanhSachDanhGiaActivity;
import com.gtv.hanhee.shopquanao.View.GioHang.GioHangActivity;
import com.gtv.hanhee.shopquanao.View.ThanhToan.ThanhToanActivity;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class ChiTietSanPhamActivity extends AppCompatActivity implements ViewChiTietSanPham,ViewPager.OnPageChangeListener,View.OnClickListener {
    PresenterChiTietSanPham presenterChiTietSanPham;
    int masp;
    ViewPager viewPager;
    List<Fragment> fragmentList;
    TextView[] txtDots;
    LinearLayout layoutDots,lnThongSoKyThuat;
    TextView txtGiaTien,txtTenSanPham,txtTenCHDongGoi,txtThongTinChiTiet,txtVietDG,txtXemTatCaNhanXet,txtGioHang,txtGiaDung;
    Toolbar toolbar;
    ImageView imgXemThemChiTiet,imgThemGioHang;
    Boolean kiemtraExpandChiTiet = false;
    RecyclerView recycleDanhGia;
    int limit;
    DaoSession daoSession;
    GioHangDao gioHangDao;
    SanPham sanphamdangxem;
    PresenterGioHang presenterGioHang;
    Boolean onPause = false;
    Button btnMuaNgay;
    List<GioHang> gioHangList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_chitietsanpham);

        viewPager = findViewById(R.id.viewPagerSlider);
        layoutDots = findViewById(R.id.layoutDots);
        txtGiaTien = findViewById(R.id.txtGiaTien);
        txtTenSanPham = findViewById(R.id.txtTenSanPham);
        txtTenCHDongGoi = findViewById(R.id.txtTenCHDongGoi);
        txtXemTatCaNhanXet = findViewById(R.id.txtXemTatCaNhanXet);
        txtThongTinChiTiet = findViewById(R.id.txtThongTinChiTiet);
        txtGiaDung = findViewById(R.id.txtGiaDung);
        imgXemThemChiTiet = findViewById(R.id.imgXemThemChiTiet);
        imgThemGioHang = findViewById(R.id.imgThemGioHang);
        lnThongSoKyThuat = findViewById(R.id.lnThongSoKyThuat);
        txtVietDG = findViewById(R.id.txtVietDG);
        recycleDanhGia = findViewById(R.id.recycleDanhGia);
        btnMuaNgay = findViewById(R.id.btnMuaNgay);

        toolbar = findViewById(R.id.toolbarChiTietSanPham);
        setSupportActionBar(toolbar);

        masp = getIntent().getIntExtra("masp",0);
        presenterChiTietSanPham = new PresenterChiTietSanPham(this);
        presenterChiTietSanPham.LaySPVaChiTietSPTheoMaSP(masp,this);
        GioHangApp application = (GioHangApp) getApplication();
        daoSession = application.getDaoSession();
        presenterGioHang = new PresenterGioHang(this);

    }

    @Override
    public void HienThiSPTheoMaSP(SanPham sanPhams) {
        txtTenSanPham.setText(sanPhams.getTensp());
        txtTenCHDongGoi.setText(sanPhams.getTennv());
        txtThongTinChiTiet.setText(sanPhams.getThongtin().substring(0,100));
        sanphamdangxem = sanPhams;

        txtXemTatCaNhanXet.setOnClickListener(this);
        masp = sanPhams.getMasp();
        txtVietDG.setOnClickListener(this);
        imgThemGioHang.setOnClickListener(this);
        btnMuaNgay.setOnClickListener(this);

        if (sanPhams.getThongtin().length()<100) {
            imgXemThemChiTiet.setVisibility(View.GONE);
        } else {
            imgXemThemChiTiet.setVisibility(View.VISIBLE);
            imgXemThemChiTiet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    kiemtraExpandChiTiet = !kiemtraExpandChiTiet;
                    if (kiemtraExpandChiTiet) {
                        txtThongTinChiTiet.setText(sanPhams.getThongtin());
                        imgXemThemChiTiet.setImageDrawable(getHinhChiTiet(R.drawable.ic_keyboard_arrow_up_black_24dp));
                        lnThongSoKyThuat.setVisibility(View.VISIBLE);
                        HienThiThongSoKyThuat(sanPhams);
                    } else {
                        txtThongTinChiTiet.setText(sanPhams.getThongtin().substring(0,100));
                        imgXemThemChiTiet.setImageDrawable(getHinhChiTiet(R.drawable.ic_keyboard_arrow_down_black_24dp));
                        lnThongSoKyThuat.setVisibility(View.GONE);
                    }
                }
            });
        }

        int giatien = sanPhams.getGia();
        int phantramkm = sanPhams.getPhantramkm();

        NumberFormat numberFormat = new DecimalFormat("###,###");
        String giagiam = numberFormat.format(giatien - (giatien * phantramkm/100));
        txtGiaTien.setText(giagiam + " VND");

        String giadung = numberFormat.format(sanPhams.getGia());

        if (phantramkm>0) {
            txtGiaDung.setVisibility(View.VISIBLE);
            txtGiaDung.setPaintFlags(txtGiaDung.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            txtGiaDung.setText(giadung + " VND");
        }

        ArrayList<String> linkhinhanh =new ArrayList<>();
        linkhinhanh.add(sanPhams.getAnhlon());
        String[] linkanhnho = sanPhams.getAnhnho().split(",");
        if (!linkanhnho[0].equals("")) {
            for (int i = 0; i <= linkanhnho.length-1; i++) {
                linkhinhanh.add(linkanhnho[i]);
            }
        }
        HienSliderSanPham(linkhinhanh);
        presenterChiTietSanPham.LayDSDanhGiaTheoMa(masp,0, 4,this);
    }

    @Override
    public void HienThiDSDanhGiaTheoMa(List<DanhGia> danhGiaList) {
        AdapterRecycleDanhGia adapterRecycleDanhGia = new AdapterRecycleDanhGia(getApplicationContext(),danhGiaList,limit);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);

        recycleDanhGia.setLayoutManager(layoutManager);
        recycleDanhGia.setAdapter(adapterRecycleDanhGia);
        adapterRecycleDanhGia.notifyDataSetChanged();
    }

    private void HienThiThongSoKyThuat(SanPham sanPhams) {
        List<ChiTietSanPham> chiTietSanPhamList = sanPhams.getChiTietSanPhamList();
        lnThongSoKyThuat.removeAllViews();
        TextView txtTieuDeThongSoKyThuat = new TextView(this);
        txtTieuDeThongSoKyThuat.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        txtTieuDeThongSoKyThuat.setText("Thông số kỹ thuật");
        txtTieuDeThongSoKyThuat.setPadding(0,0,0,5);
        txtTieuDeThongSoKyThuat.setTypeface(Typeface.DEFAULT_BOLD);
        lnThongSoKyThuat.addView(txtTieuDeThongSoKyThuat);

        for (int i=0;i<chiTietSanPhamList.size();i++) {
            LinearLayout lnChiTiet = new LinearLayout(this);
            lnChiTiet.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            lnChiTiet.setOrientation(LinearLayout.HORIZONTAL);

            TextView txtTenThongSo = new TextView(this);
            txtTenThongSo.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT,1.0f));
            txtTenThongSo.setText(chiTietSanPhamList.get(i).getTenchitiet()+": ");

            TextView txtGiaTriThongSo = new TextView(this);
            txtGiaTriThongSo.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT,1.0f));
            txtGiaTriThongSo.setText(chiTietSanPhamList.get(i).getGiatri());

            lnChiTiet.addView(txtTenThongSo);
            lnChiTiet.addView(txtGiaTriThongSo);

            lnThongSoKyThuat.addView(lnChiTiet);
        }
    }

    private Drawable getHinhChiTiet(int idDrawable) {
        Drawable drawable;
        if (Build.VERSION.SDK_INT > 21) {
            drawable = ContextCompat.getDrawable(this,idDrawable);
        } else {
            drawable = getResources().getDrawable(idDrawable);
        }
        return drawable;
    }

    private void HienSliderSanPham(ArrayList<String> linkhinhanh) {

        fragmentList = new ArrayList<>();
        AdapterViewPagerSlider adapterViewPagerSlider = new AdapterViewPagerSlider(getSupportFragmentManager(),fragmentList);
        viewPager.setAdapter(adapterViewPagerSlider);
        for (int i=0;i<linkhinhanh.size();i++) {
            FragmentSliderChiTietSanPham fragmentSliderChiTietSanPham = new FragmentSliderChiTietSanPham();
            Bundle bundle = new Bundle();
            bundle.putString("linkhinh",linkhinhanh.get(i));
            fragmentSliderChiTietSanPham.setArguments(bundle);
            fragmentList.add(fragmentSliderChiTietSanPham);
            adapterViewPagerSlider.notifyDataSetChanged();
        }
        ThemDotSlider(0);
        viewPager.addOnPageChangeListener(this);
    }

    public void ThemDotSlider(int vitrihientai) {
        txtDots = new TextView[fragmentList.size()];
        layoutDots.removeAllViews();
        for (int i =0; i<fragmentList.size();i++) {
            txtDots[i] = new TextView(this);
            txtDots[i].setText(Html.fromHtml("&#8226;"));
            txtDots[i].setTextSize(40);
            txtDots[i].setTextColor(getIdColor(R.color.colorSliderInActive));
            layoutDots.addView(txtDots[i]);
        }
        txtDots[vitrihientai].setTextColor(getIdColor(R.color.colorSliderActive));
    }

    public int getIdColor(int idcolor) {
        int color = 0;
        if (Build.VERSION.SDK_INT > 21) {
            color = ContextCompat.getColor(this,idcolor);
        } else {
            color = getResources().getColor(idcolor);
        }
        return color;
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
                Intent iGioHang = new Intent(ChiTietSanPhamActivity.this,GioHangActivity.class);
                startActivity(iGioHang);
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        ThemDotSlider(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txtVietDG:
                Intent iThemDanhGia = new Intent(this, DanhGiaActivity.class);
                iThemDanhGia.putExtra("masp",masp);
                startActivity(iThemDanhGia);
                break;
            case R.id.txtXemTatCaNhanXet:
                Intent iDSDG = new Intent(this, DanhSachDanhGiaActivity.class);
                iDSDG.putExtra("masp", masp);
                startActivity(iDSDG);
                break;
            case R.id.imgThemGioHang:
                gioHangList = new ArrayList<>();
                gioHangList = presenterGioHang.LaySPTheoMaspGioHang(sanphamdangxem.getMasp());

                if (gioHangList.size() > 0) {
                    Toast.makeText(this, "Sản phẩm đã có trong giỏ hàng!", Toast.LENGTH_SHORT).show();
                } else {
                    presenterGioHang.ThemGioHangVaoDatabase(sanphamdangxem);
                    txtGioHang.setVisibility(View.VISIBLE);
                    txtGioHang.setText(String.valueOf(presenterGioHang.soLuongSanPhamTrongGioHang()));
                    Toast.makeText(this, "Thêm sản phẩm vào giỏ hàng thành công", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnMuaNgay:
                gioHangList = new ArrayList<>();
                gioHangList = presenterGioHang.LaySPTheoMaspGioHang(sanphamdangxem.getMasp());
                if (gioHangList.size() > 0) {
                    Toast.makeText(this, "Sản phẩm đã có trong giỏ hàng và bắt đầu thanh toán", Toast.LENGTH_SHORT).show();
                } else {
                    presenterGioHang.ThemGioHangVaoDatabase(sanphamdangxem);
                    Toast.makeText(this, "Thêm sản phẩm vào giỏ hàng và bắt đầu thanh toán", Toast.LENGTH_SHORT).show();

                }
                Intent iThanhToan = new Intent(this, ThanhToanActivity.class);
                startActivity(iThanhToan);
                break;
        }
    }
}
