package com.gtv.hanhee.shopquanao.View.ThanhToan;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;


import com.gtv.hanhee.shopquanao.Model.ObjectClass.ChiTietHoaDon;
import com.gtv.hanhee.shopquanao.Model.ObjectClass.GioHang;
import com.gtv.hanhee.shopquanao.Model.ObjectClass.HoaDon;
import com.gtv.hanhee.shopquanao.Presenter.GioHang.PresenterGioHang;
import com.gtv.hanhee.shopquanao.Presenter.ThanhToan.PresenterThanhToan;
import com.gtv.hanhee.shopquanao.R;
import com.gtv.hanhee.shopquanao.View.GioHang.GioHangActivity;

import java.util.ArrayList;
import java.util.List;

public class ThanhToanActivity extends AppCompatActivity implements View.OnClickListener,ViewThanhToan {
    Toolbar toolbar;
    EditText edTenNguoiNhan,edDiaChi,edSoDT;
    ImageButton imgNhanTienKhiGiaoHang,imgChuyenKhoan;
    Button btnThanhToan;
    HoaDon hoaDon;
    CheckBox cbThoaThuan;
    PresenterThanhToan presenterThanhToan;
    PresenterGioHang presenterGioHang;
    List<ChiTietHoaDon> chiTietHoaDonList;
    List<GioHang> gioHangList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_thanhtoan);

        toolbar = findViewById(R.id.toolbarThanhToan);
        setSupportActionBar(toolbar);

        edTenNguoiNhan = findViewById(R.id.edTenNguoiNhan);
        edDiaChi = findViewById(R.id.edDiaChiGiaoHang);
        edSoDT = findViewById(R.id.edSoDT);
        imgNhanTienKhiGiaoHang = findViewById(R.id.imgNhanTienKhiGiaoHang);
        imgChuyenKhoan = findViewById(R.id.imgChuyenKhoan);
        btnThanhToan = findViewById(R.id.btnThanhToan);
        cbThoaThuan = findViewById(R.id.cbThoaThuan);
        hoaDon = new HoaDon();
        gioHangList = new ArrayList<>();
        chiTietHoaDonList = new ArrayList<>();
        presenterGioHang = new PresenterGioHang(this);
        presenterThanhToan = new PresenterThanhToan(this);
        btnThanhToan.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnThanhToan:
                if (cbThoaThuan.isChecked()) {
                    hoaDon = new HoaDon();
                    hoaDon.setTennguoinhan(edTenNguoiNhan.getText().toString());
                    hoaDon.setDiachi(edDiaChi.getText().toString());
                    hoaDon.setSodt(edSoDT.getText().toString());
                    hoaDon.setChuyenkhoan(1);
                    gioHangList = presenterGioHang.SanPhamTrongGioHang();
                    for (int i=0;i<gioHangList.size();i++) {
                        ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon();
                        chiTietHoaDon.setSoluong(gioHangList.get(i).getSoluongdangdat());
                        chiTietHoaDon.setMasp((int) gioHangList.get(i).getMasp());
                        chiTietHoaDonList.add(chiTietHoaDon);
                    }
                    presenterThanhToan.ThemChiTietThanhToan(hoaDon, chiTietHoaDonList);


                } else {
                    Toast.makeText(this, "Bạn chưa đồng ý thỏa thuận", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    public void ThemHoaDonThanhCong() {
        Toast.makeText(this, "Thêm hóa đơn thành công", Toast.LENGTH_SHORT).show();
        presenterGioHang.XoaDatabase();
        chiTietHoaDonList.clear();
        finish();
        GioHangActivity.fa.finish();
    }

    @Override
    public void ThemHoaDonThatBai() {
        Toast.makeText(this, "Thêm hóa đơn thất bại", Toast.LENGTH_SHORT).show();
    }
}
