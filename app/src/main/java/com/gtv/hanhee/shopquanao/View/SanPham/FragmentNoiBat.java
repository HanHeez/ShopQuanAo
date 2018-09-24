package com.gtv.hanhee.shopquanao.View.SanPham;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.test.ViewAsserts;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gtv.hanhee.shopquanao.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentNoiBat extends Fragment {
    View view;

    public FragmentNoiBat() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.layout_sanpham_fragment_noibat, container, false);
        return view;
    }

}
