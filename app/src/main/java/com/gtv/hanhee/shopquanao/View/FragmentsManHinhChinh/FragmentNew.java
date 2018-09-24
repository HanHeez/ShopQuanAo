package com.gtv.hanhee.shopquanao.View.FragmentsManHinhChinh;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.gtv.hanhee.shopquanao.Adapter.AdapterDienTu;
import com.gtv.hanhee.shopquanao.Adapter.AdapterDienTuLogoThuongHieuLon;
import com.gtv.hanhee.shopquanao.Adapter.AdapterDienTuTopDienThoai;
import com.gtv.hanhee.shopquanao.Adapter.GlideApp;
import com.gtv.hanhee.shopquanao.Model.ObjectClass.DienTu;
import com.gtv.hanhee.shopquanao.Model.ObjectClass.SanPham;
import com.gtv.hanhee.shopquanao.Model.ObjectClass.ThuongHieu;
import com.gtv.hanhee.shopquanao.Presenter.SanPham_DienTu.PresenterLogicDienTu;
import com.gtv.hanhee.shopquanao.R;
import com.gtv.hanhee.shopquanao._Retrofit.APIUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FragmentNew extends Fragment implements ViewDienTu {
    RecyclerView recyclerDienTu,recyclerLogo,recyclerSanPhamMoiVe;
    List<DienTu> dienTuList;
    PresenterLogicDienTu presenterLogicDienTu;
    ImageView imgSanPham1,imgSanPham2,imgSanPham3;
    TextView txtSanPham1,txtSanPham2,txtSanPham3;
    AdapterDienTu adapterDienTu;
    RecyclerView.LayoutManager layoutManager;

    public FragmentNew() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_new, container, false);
        recyclerDienTu = view.findViewById(R.id.recycleDienTu);
        recyclerLogo = view.findViewById(R.id.recycleLogoThuongHieuLon);
        recyclerSanPhamMoiVe = view.findViewById(R.id.recycleHangMoiVe);
        imgSanPham1 = view.findViewById(R.id.imgSanPham1);
        imgSanPham2 = view.findViewById(R.id.imgSanPham2);
        imgSanPham3 = view.findViewById(R.id.imgSanPham3);
        txtSanPham1 = view.findViewById(R.id.txtSanPham1);
        txtSanPham2 = view.findViewById(R.id.txtSanPham2);
        txtSanPham3 = view.findViewById(R.id.txtSanPham3);

        dienTuList = new ArrayList<>();

        adapterDienTu = new AdapterDienTu(getActivity(), dienTuList);
        layoutManager = new LinearLayoutManager(getContext());

        recyclerDienTu.setLayoutManager(layoutManager);
        recyclerDienTu.setAdapter(adapterDienTu);
        recyclerDienTu.setNestedScrollingEnabled(false);
        adapterDienTu.notifyDataSetChanged();

        presenterLogicDienTu = new PresenterLogicDienTu(this);
        presenterLogicDienTu.LayDanhSachDienTu(this);
        presenterLogicDienTu.LayLogoThuongHieuLon(this);
        presenterLogicDienTu.LaySanPhamMoiVe(this);
        return view;
    }

    @Override
    public void HienThiDanhSach(DienTu dienTu) {
        dienTu.setThuonghieu(true);
        dienTuList.add(dienTu);
        adapterDienTu.notifyDataSetChanged();
        presenterLogicDienTu.LayDSPhuKien(this);
    }


    @Override
    public void HienThiDSPK(DienTu dienTu) {
        dienTu.setThuonghieu(false);
        dienTuList.add(dienTu);
        adapterDienTu.notifyDataSetChanged();

    }

    @Override
    public void HienThiLogo(List<ThuongHieu> thuongHieus) {
        AdapterDienTuLogoThuongHieuLon adapterDienTuLogoThuongHieuLon = new AdapterDienTuLogoThuongHieuLon(getActivity(), thuongHieus);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);

        recyclerLogo.setLayoutManager(layoutManager);
        recyclerLogo.setAdapter(adapterDienTuLogoThuongHieuLon);
        recyclerLogo.setNestedScrollingEnabled(false);
        adapterDienTuLogoThuongHieuLon.notifyDataSetChanged();
    }

    @Override
    public void HienThiSanPhamMoiVe(List<SanPham> sanPhams) {
        AdapterDienTuTopDienThoai adapterDienTuTopDienThoai = new AdapterDienTuTopDienThoai(R.layout.layout_sanpham_custom_recycle_topdienthoaimaytinhbang,getActivity(), sanPhams);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);

        recyclerSanPhamMoiVe.setLayoutManager(layoutManager);
        recyclerSanPhamMoiVe.setAdapter(adapterDienTuTopDienThoai);
        recyclerSanPhamMoiVe.setNestedScrollingEnabled(false);
        adapterDienTuTopDienThoai.notifyDataSetChanged();

        Random random = new Random();
        int vitri1 = random.nextInt(sanPhams.size());
        GlideApp.with(getContext()).load(sanPhams.get(vitri1).getAnhlon())
                .apply(new RequestOptions().override(120, 120))
                .transition(new DrawableTransitionOptions().crossFade(500))
                .into(imgSanPham1);
        txtSanPham1.setText(sanPhams.get(vitri1).getTensp());

        int vitri2 = random.nextInt(sanPhams.size());
        while (vitri2 == vitri1) {
            vitri2 = random.nextInt(sanPhams.size());
        }
        GlideApp.with(getContext()).load(APIUtils.Base_url+sanPhams.get(vitri2).getAnhlon())
                .apply(new RequestOptions().override(120, 120))
                .transition(new DrawableTransitionOptions().crossFade(500))
                .into(imgSanPham2);
        txtSanPham2.setText(sanPhams.get(vitri2).getTensp());

        int vitri3 = random.nextInt(sanPhams.size());
        while (vitri3 == vitri1 || vitri3 == vitri2) {
            vitri3 = random.nextInt(sanPhams.size());
        }
        GlideApp.with(getContext()).load(APIUtils.Base_url+sanPhams.get(vitri3).getAnhlon())
                .apply(new RequestOptions().override(120, 120))
                .transition(new DrawableTransitionOptions().crossFade(500))
                .into(imgSanPham3);
        txtSanPham3.setText(sanPhams.get(vitri3).getTensp());
    }



}
