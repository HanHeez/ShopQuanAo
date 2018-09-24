package com.gtv.hanhee.shopquanao.View.DanhGia;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telecom.TelecomManager;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.gtv.hanhee.shopquanao.Model.ObjectClass.DanhGia;
import com.gtv.hanhee.shopquanao.Presenter.DanhGia.PresenterDanhGia;
import com.gtv.hanhee.shopquanao.R;

import java.util.List;

public class DanhGiaActivity extends AppCompatActivity implements RatingBar.OnRatingBarChangeListener, ViewDanhGia, View.OnClickListener {
    TextInputLayout input_edTieuDe, input_edNoiDung;
    EditText edTieuDe, edNoiDung;
    RatingBar rbDanhGia;
    PresenterDanhGia presenterDanhGia;
    Button btnDongYDG;
    DanhGia danhGia;
    int masp = 0;
    int sosao = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_danhgia);
        input_edTieuDe = findViewById(R.id.input_edTieuDeDG);
        input_edNoiDung = findViewById(R.id.input_edNoiDungDG);
        edTieuDe = findViewById(R.id.edTieuDe);
        edNoiDung = findViewById(R.id.edNoiDung);
        rbDanhGia = findViewById(R.id.rbDanhGia);
        btnDongYDG = findViewById(R.id.btnDongYDG);

        masp = getIntent().getIntExtra("masp", 0);

        btnDongYDG.setOnClickListener(this);
        rbDanhGia.setOnRatingBarChangeListener(this);
        presenterDanhGia = new PresenterDanhGia(this);
    }

    @Override
    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
        sosao = (int) rating;
    }

    @Override
    public void onClick(View v) {
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
            @SuppressLint("MissingPermission") String madg = telephonyManager.getDeviceId();
            String tenthietbi = Build.MODEL;
            String tieude = edTieuDe.getText().toString();
            String noidung = edNoiDung.getText().toString();

            if (tieude.trim().length() > 0) {
                input_edTieuDe.setErrorEnabled(false);
                input_edTieuDe.setError("");

            } else {
                input_edTieuDe.setErrorEnabled(true);
                input_edTieuDe.setError("Bạn chưa nhập tiêu đề!");

            }
            if (noidung.trim().length() > 0) {
                input_edTieuDe.setErrorEnabled(false);
                input_edTieuDe.setError("");
            } else {
                input_edTieuDe.setErrorEnabled(true);
                input_edTieuDe.setError("Bạn chưa nhập nội dung!");
            }

            if (!input_edTieuDe.isErrorEnabled() && !input_edNoiDung.isErrorEnabled()) {
                danhGia = new DanhGia();
                danhGia.setMasp(masp);
                danhGia.setMadg(madg);
                danhGia.setTieude(tieude);
                danhGia.setNoidung(noidung);
                danhGia.setSosao(sosao);
                danhGia.setTenthietbi(tenthietbi);
                presenterDanhGia.ThemDanhGia(danhGia, this);
            }

    }

    @Override
    public void ThemDGThanhCong() {
        Toast.makeText(this, "Thêm đánh giá thành công", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void ThemDGThatBai() {
        Toast.makeText(this, "Bạn không thể đánh giá sản phẩm này nữa", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void HienThiDSDanhGiaTheoMa(List<DanhGia> danhGiaList) {
        
    }
}
