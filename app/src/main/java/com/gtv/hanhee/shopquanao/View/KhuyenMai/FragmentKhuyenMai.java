package com.gtv.hanhee.shopquanao.View.KhuyenMai;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.gtv.hanhee.shopquanao.Adapter.AdapterDanhSachKhuyenMai;
import com.gtv.hanhee.shopquanao.Adapter.AdapterDienTuLogoThuongHieuLon;
import com.gtv.hanhee.shopquanao.Adapter.GlideApp;
import com.gtv.hanhee.shopquanao.Model.ObjectClass.KhuyenMai;
import com.gtv.hanhee.shopquanao.Presenter.KhuyenMai.PresenterKhuyenMai;
import com.gtv.hanhee.shopquanao.R;
import com.gtv.hanhee.shopquanao._Retrofit.APIUtils;

import java.util.List;

public class FragmentKhuyenMai extends Fragment implements ViewKhuyenMai {
    LinearLayout lnHinhKM;
    RecyclerView recycleDSKM;
    View view;
    PresenterKhuyenMai presenterKhuyenMai;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.layout_fragment_khuyenmai, container, false);
        AddControl();
        presenterKhuyenMai = new PresenterKhuyenMai(this);
        presenterKhuyenMai.LayDanhSachKhuyenMai(this);
        return view;
    }

    private void AddControl() {
        lnHinhKM = view.findViewById(R.id.lnHinhKM);
        recycleDSKM = view.findViewById(R.id.recycleDSKM);
    }

    @SuppressLint("CheckResult")
    @Override
    public void HienThiDSKM(List<KhuyenMai> khuyenMais) {
        int demhinh = khuyenMais.size();
        if (demhinh >5) {
            demhinh = 5;
        }

        lnHinhKM.removeAllViews();
        for (int i=0;i<demhinh;i++) {
            ImageView imageView = new ImageView(getContext());
            imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,200);
            layoutParams.setMargins(0,10,0,10);
            imageView.setLayoutParams(layoutParams);
            GlideApp.with(getContext()).load(APIUtils.Base_url+ khuyenMais.get(i).getHinhkm())
            .apply(new RequestOptions().override(720, 200))
                    .transition(new DrawableTransitionOptions().crossFade(500)).into(imageView);

            lnHinhKM.addView(imageView);
        }
        AdapterDanhSachKhuyenMai adapterDanhSachKhuyenMai = new AdapterDanhSachKhuyenMai(getActivity(), khuyenMais);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);

        recycleDSKM.setLayoutManager(layoutManager);
        recycleDSKM.setAdapter(adapterDanhSachKhuyenMai);
        recycleDSKM.setNestedScrollingEnabled(false);
        adapterDanhSachKhuyenMai.notifyDataSetChanged();
    }
}
