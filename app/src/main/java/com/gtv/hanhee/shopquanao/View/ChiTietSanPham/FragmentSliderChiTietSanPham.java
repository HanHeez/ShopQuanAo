package com.gtv.hanhee.shopquanao.View.ChiTietSanPham;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.gtv.hanhee.shopquanao.Adapter.GlideApp;
import com.gtv.hanhee.shopquanao.R;
import com.gtv.hanhee.shopquanao._Retrofit.APIUtils;

public class FragmentSliderChiTietSanPham extends Fragment {
    ImageView imgHinh;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_slider_chitietsanpham, container, false);
        Bundle bundle = getArguments();
        String linkhinh = bundle.getString("linkhinh");
        imgHinh = view.findViewById(R.id.imgSanPham);
        GlideApp.with(getContext()).load(APIUtils.Base_url+linkhinh).apply(new RequestOptions().override(350, 350))
                .transition(new DrawableTransitionOptions().crossFade(500)).into(imgHinh);
        return view;
    }

}
