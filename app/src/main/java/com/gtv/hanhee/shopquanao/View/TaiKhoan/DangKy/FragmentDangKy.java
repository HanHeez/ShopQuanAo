package com.gtv.hanhee.shopquanao.View.TaiKhoan.DangKy;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;


import com.gtv.hanhee.shopquanao.Model.ObjectClass.NhanVien;
import com.gtv.hanhee.shopquanao.Presenter.DangKy.PresenterLogicDangKy;
import com.gtv.hanhee.shopquanao.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FragmentDangKy extends Fragment implements ViewDangKy,View.OnClickListener,View.OnFocusChangeListener{
    PresenterLogicDangKy presenterLogicDangKy;
    Button btnDangKy;
    EditText edHoTen,edMatKhau,edNhapLaiMatKhau,edDiaChiEmail;
    SwitchCompat swEmailDocQuyen;
    TextInputLayout input_edHoTen,input_edMatKhau,input_edNhapLaiMatKhau,input_edDiaChiEmail;
    Boolean kiemtrathongtin = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_dangky,container,false);
        presenterLogicDangKy = new PresenterLogicDangKy(this);
        btnDangKy = view.findViewById(R.id.btnDK);
        edHoTen = view.findViewById(R.id.edHoTenDK);
        edMatKhau = view.findViewById(R.id.edMatKhauDK);
        edDiaChiEmail = view.findViewById(R.id.edDiaChiEmailDK);
        edNhapLaiMatKhau = view.findViewById(R.id.edNhapLaiMatKhauDK);
        swEmailDocQuyen = view.findViewById(R.id.swEmailDocQuyen);
        input_edHoTen = view.findViewById(R.id.input_edHoTenDK);
        input_edMatKhau = view.findViewById(R.id.input_edMatKhauDK);
        input_edNhapLaiMatKhau = view.findViewById(R.id.input_edNhapLaiMatKhauDK);
        input_edDiaChiEmail = view.findViewById(R.id.input_edDiaChiEmailDK);
        emaildocquyen ="true";
        swEmailDocQuyen.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                emaildocquyen = isChecked + "";
            }
        });
        btnDangKy.setOnClickListener(this);
        edHoTen.setOnFocusChangeListener(this);
        edMatKhau.setOnFocusChangeListener(this);
        edNhapLaiMatKhau.setOnFocusChangeListener(this);
        edDiaChiEmail.setOnFocusChangeListener(this);
        return view;
    }

    @Override
    public void DangKyThanhCong() {
    }

    @Override
    public void DangKyThatBai() {
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnDK:
                btnDangKy();
                break;
        }
    }

    String emaildocquyen;
    private void btnDangKy() {
        String hoten = edHoTen.getText().toString();
        String email = edDiaChiEmail.getText().toString();
        String matkhau = edMatKhau.getText().toString();
        String nhaplaimatkhau = edNhapLaiMatKhau.getText().toString();
        if (kiemtrathongtin) {
            NhanVien nhanVien = new NhanVien();
            nhanVien.setTenNV(hoten);
            nhanVien.setTendangnhap(email);
            nhanVien.setMatkhau(matkhau);
            nhanVien.setEmaildocquyen(emaildocquyen);
            nhanVien.setMaloaiNV(2);
            presenterLogicDangKy.ThucHienDangKy(nhanVien);
        } else {
            Log.d("kiemtra","Dang ky that bai");
        }
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        switch (v.getId()) {
            case R.id.edHoTenDK:
                if (!hasFocus) {
                    String chuoi = ((EditText) v).getText().toString();
                    if (chuoi.trim().equals("") || chuoi.equals(null)) {
                        input_edHoTen.setErrorEnabled(true);
                        input_edHoTen.setError("Bạn chưa nhập mục này");
                        kiemtrathongtin = false;
                    } else {
                        input_edHoTen.setErrorEnabled(false);
                        input_edHoTen.setError("");
                        kiemtrathongtin = true;
                    }
                }
                break;
            case R.id.edDiaChiEmailDK:
                if (!hasFocus) {
                    String chuoi = ((EditText) v).getText().toString();

                    if (chuoi.trim().equals("") || chuoi.equals(null)) {
                        input_edDiaChiEmail.setErrorEnabled(true);
                        input_edDiaChiEmail.setError("Bạn chưa nhập mục này");
                        kiemtrathongtin = false;
                    } else {
                        Boolean kiemtraemail = Patterns.EMAIL_ADDRESS.matcher(chuoi).matches();
                        if (!kiemtraemail) {
                            input_edDiaChiEmail.setErrorEnabled(true);
                            input_edDiaChiEmail.setError("Đây không phải là địa chỉ Email");
                            kiemtrathongtin = false;
                        } else {
                            input_edDiaChiEmail.setErrorEnabled(false);
                            input_edDiaChiEmail.setError("");
                            kiemtrathongtin = true;
                        }
                    }
                }
                break;
            case R.id.edMatKhauDK:
                if (!hasFocus) {
                    String chuoi = ((EditText) v).getText().toString();
                    String MATCHER_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[a-z]).{6,20}$";
                    Pattern pattern = Pattern.compile(MATCHER_PATTERN);
                    Matcher matcher = pattern.matcher(chuoi);
                    if (!matcher.matches()) {

                        input_edMatKhau.setErrorEnabled(true);
                        input_edMatKhau.setError("Mật khẩu phải bao gồm 6 ký tự, một chữ hoa và một số");
                        kiemtrathongtin = false;
                    } else {
                        input_edMatKhau.setErrorEnabled(false);
                        input_edMatKhau.setError("");
                        kiemtrathongtin = true;
                    }
                }
                break;
            case R.id.edNhapLaiMatKhauDK:
                if (!hasFocus) {
                    String chuoi = ((EditText) v).getText().toString();
                    String matkhau = edMatKhau.getText().toString();
                    if (!chuoi.equals(matkhau)) {
                        input_edNhapLaiMatKhau.setErrorEnabled(true);
                        input_edNhapLaiMatKhau.setError("Mật khẩu không trùng khớp");
                        kiemtrathongtin = false;

                    } else {
                        input_edNhapLaiMatKhau.setErrorEnabled(false);
                        input_edNhapLaiMatKhau.setError("");
                        kiemtrathongtin = true;

                    }
                }
                break;
        }
    }
}
