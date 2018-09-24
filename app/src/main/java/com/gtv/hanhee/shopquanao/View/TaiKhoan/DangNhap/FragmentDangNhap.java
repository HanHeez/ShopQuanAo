package com.gtv.hanhee.shopquanao.View.TaiKhoan.DangNhap;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookAuthorizationException;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.gtv.hanhee.shopquanao._Retrofit.APIUtils;
import com.gtv.hanhee.shopquanao._Retrofit.LayDuLieuTuUrl;
import com.gtv.hanhee.shopquanao.Model.DangNhap.ModelDangNhap;
import com.gtv.hanhee.shopquanao.Model.DangNhap.ModelDangNhapGoogle;
import com.gtv.hanhee.shopquanao.Presenter.DangNhap.PresenterLogicDangNhap;
import com.gtv.hanhee.shopquanao.R;
import com.gtv.hanhee.shopquanao.View.TrangChu.TrangChuActivity;

import java.util.Arrays;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class FragmentDangNhap extends Fragment implements View.OnClickListener {

    View view;
    Button btnDangNhapFb,btnDangnhapGoogle,btnDangNhapQuara;
    EditText edDiaChiEmailDangNhap, edMatKhauDangNhap;
    TextInputLayout input_edDiaChiEmailDangNhap, input_edMatKhauDangNhap;
    CallbackManager callbackManager;
    GoogleSignInClient mGoogleSignInClient;
    ProgressDialog progressDialog;
    public static int RC_SIGN_IN = 111;

    public FragmentDangNhap() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.layout_fragment_dangnhap,container,false);
        QuaraLogin();
        GoogleLogin();
        FacebookLogin();
        return view;
    }

    private void QuaraLogin() {
        btnDangNhapQuara = view.findViewById(R.id.btnDangNhapQuara);
        edDiaChiEmailDangNhap = view.findViewById(R.id.edtDiaChiEmailDangNhap);
        edMatKhauDangNhap = view.findViewById(R.id.edtMatKhauDangNhap);
        input_edDiaChiEmailDangNhap = view.findViewById(R.id.input_edDiaChiEmailDangNhap);
        input_edMatKhauDangNhap = view.findViewById(R.id.input_edMatKhauDangNhap);
        btnDangNhapQuara.setOnClickListener(this);
    }

    public void showProgressDialog() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(getContext(),R.style.MyTheme);
            progressDialog.setCancelable(false);
            progressDialog.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            progressDialog.setIndeterminate(true);
            progressDialog.show();
        }
    }

    private void FacebookLogin() {
        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                getActivity().finish();
                Intent iTrangChu = new Intent(getActivity(),TrangChuActivity.class);
                startActivity(iTrangChu);
            }

            @Override
            public void onCancel() {
            }

            @Override
            public void onError(FacebookException error) {
                if (error instanceof FacebookAuthorizationException) {
                    if (AccessToken.getCurrentAccessToken() != null) {
                        LoginManager.getInstance().logOut();
                    }
                }
                Log.d("tokens",error.toString());
            }
        });
        btnDangNhapFb = view.findViewById(R.id.btnDangNhapFacebook);
        btnDangNhapFb.setOnClickListener(this);
    }

    private void GoogleLogin() {
        ModelDangNhapGoogle modelDangNhapGoogle = new ModelDangNhapGoogle();
        mGoogleSignInClient = modelDangNhapGoogle.LayGoogleSignInClient(getActivity());
        btnDangnhapGoogle = view.findViewById(R.id.btnDangNhapGoogle);
        btnDangnhapGoogle.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnDangNhapQuara:
                String tendangnhap = edDiaChiEmailDangNhap.getText().toString();
                String matkhau = edMatKhauDangNhap.getText().toString();
                   KiemTraDangNhap(tendangnhap, matkhau);
                break;
            case R.id.btnDangNhapFacebook:
                LoginManager.getInstance().logInWithReadPermissions(FragmentDangNhap.this, Arrays.asList("public_profile"));
                break;
            case R.id.btnDangNhapGoogle:
                showProgressDialog();
                Intent iGoogle = mGoogleSignInClient.getSignInIntent();
                startActivityForResult(iGoogle, RC_SIGN_IN);
                break;
        }
    }

    @SuppressLint("CheckResult")
    private void KiemTraDangNhap(String tendangnhap, String matkhau) {
        LayDuLieuTuUrl kiemtraDangnhap = APIUtils.getData();
        Observable<ModelDangNhap> kiemtraDangNhapObservable = kiemtraDangnhap.KiemTraDangNhap(tendangnhap,matkhau);
        kiemtraDangNhapObservable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError);
    }

    private void handleResponse(ModelDangNhap modelDangNhaps) {
        if (modelDangNhaps.getKetqua()) {
            PresenterLogicDangNhap presenterLogicDangNhap = new PresenterLogicDangNhap();
            presenterLogicDangNhap.CapNhatCacheDangNhap(getActivity(),modelDangNhaps.getTennv());

            Intent iTrangChu = new Intent(getActivity(), TrangChuActivity.class);
            startActivity(iTrangChu);
        } else {
            Toast.makeText(getActivity(), "Tên đăng nhập hoặc mật khẩu không đúng", Toast.LENGTH_SHORT).show();
        }
    }

    private void handleError(Throwable error) {
        Log.d("kiemtra", error.toString());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode,resultCode,data);

        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                progressDialog.cancel();
                getActivity().finish();
                GoogleSignInAccount account = task.getResult(ApiException.class);
                Intent iTrangChu = new Intent(getActivity(),TrangChuActivity.class);
                startActivity(iTrangChu);
            } catch (ApiException e) {
                e.printStackTrace();
            }
        }
    }
}

