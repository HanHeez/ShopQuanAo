package com.gtv.hanhee.shopquanao.View.TaiKhoan;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.gtv.hanhee.shopquanao.Model.DangNhap.ModelDangNhapGoogle;
import com.gtv.hanhee.shopquanao.Presenter.DangNhap.PresenterLogicDangNhap;
import com.gtv.hanhee.shopquanao.Presenter.TrangChu.PresenterLogicXuLyMenu;
import com.gtv.hanhee.shopquanao.R;

import org.json.JSONException;
import org.json.JSONObject;

public class FragmentTaiKhoan extends Fragment implements View.OnClickListener{
    View view;
    Button btnDangKy,btnLogout;
    AccessToken accessToken;
    TextView txtHello;
    PresenterLogicXuLyMenu logicXuLyMenu;
    ModelDangNhapGoogle modelDangNhapGoogle;
    String tennguoidung ="";
    LayoutInflater inflater;
    ViewGroup container;
    Bundle savedInstanceState;
    GoogleSignInAccount currentAccount;
    PresenterLogicDangNhap presenterLogicDangNhap;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.layout_fragment_taikhoan, container, false);
        this.inflater = inflater;
        this.container = container;
        this.savedInstanceState = savedInstanceState;
        AddControls();
        return view;
    }

    private void AddControls() {
        btnDangKy = view.findViewById(R.id.btnDangKy);
        txtHello = view.findViewById(R.id.txtHello);
        btnLogout = view.findViewById(R.id.btnLogOut);
        btnDangKy.setOnClickListener(this);
        btnLogout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnDangKy:
                accessToken = AccessToken.getCurrentAccessToken();
                if (accessToken == null && currentAccount == null ) {
                    Intent iDangKy = new Intent(getActivity(), TaiKhoanActivity.class);
                    startActivity(iDangKy);
                    btnLogout.setVisibility(View.INVISIBLE);
                    btnDangKy.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.btnLogOut:
                if (accessToken != null) {
                    LoginManager.getInstance().logOut();
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    ft.detach(this).attach(this).commit();
                } else {
                    if (currentAccount != null) {
                        modelDangNhapGoogle.LayGoogleSignInClient(getContext()).signOut()
                                .addOnCompleteListener(getActivity(), new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        FragmentTransaction ft = getFragmentManager().beginTransaction();
                                        ft.detach(FragmentTaiKhoan.this).attach(FragmentTaiKhoan.this).commit();
                                    }
                                });
                    } else {
                        String tennv = presenterLogicDangNhap.LayCachedTenDangNhap(getActivity());
                        if (!tennv.equals("")) {
                            presenterLogicDangNhap.CapNhatCacheDangNhap(getActivity(),"");
                            FragmentTransaction ft = getFragmentManager().beginTransaction();
                            ft.detach(this).attach(this).commit();
                        }
                }
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        XuLyDangNhapFacebook();
        XuLyDangNhapGoogle();
        XuLyDangNhap();
    }

    private void XuLyDangNhap() {
        presenterLogicDangNhap = new PresenterLogicDangNhap();
        String tennv = presenterLogicDangNhap.LayCachedTenDangNhap(getActivity());
        if (!tennv.equals("")) {
            txtHello.setText(tennv);
            btnLogout.setVisibility(View.VISIBLE);
            btnDangKy.setVisibility(View.INVISIBLE);
        }
    }

    private void XuLyDangNhapGoogle() {
        modelDangNhapGoogle = new ModelDangNhapGoogle();
        currentAccount = modelDangNhapGoogle.ThongTinTaiKhoan(getContext());
        if (currentAccount!= null) {
            tennguoidung = currentAccount.getDisplayName();
            txtHello.setText(tennguoidung);
            btnLogout.setVisibility(View.VISIBLE);
            btnDangKy.setVisibility(View.INVISIBLE);

        }

    }

    private void XuLyDangNhapFacebook() {
        logicXuLyMenu = new PresenterLogicXuLyMenu();
        accessToken = logicXuLyMenu.LayTenNguoiDungFacebook(getContext());
        if (accessToken != null) {
            GraphRequest graphRequest = GraphRequest.newMeRequest(accessToken, new GraphRequest.GraphJSONObjectCallback() {
                @Override
                public void onCompleted(JSONObject object, GraphResponse response) {
                    try {
                        tennguoidung = object.getString("name");
                        XuLyKhiDangDangNhap();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
            Bundle parameter = new Bundle();
            parameter.putString("fields", "name");
            graphRequest.setParameters(parameter);
            graphRequest.executeAsync();
        }
    }

    private void XuLyKhiDangDangNhap() {
        txtHello.setText(tennguoidung);
        btnLogout.setVisibility(View.VISIBLE);
        btnDangKy.setVisibility(View.INVISIBLE);
    }

}
