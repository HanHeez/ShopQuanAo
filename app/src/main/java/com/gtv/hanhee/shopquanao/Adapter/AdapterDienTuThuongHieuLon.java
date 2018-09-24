package com.gtv.hanhee.shopquanao.Adapter;


import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.gtv.hanhee.shopquanao.Model.ObjectClass.ThuongHieu;
import com.gtv.hanhee.shopquanao.R;
import com.gtv.hanhee.shopquanao.View.HienThiSanPhamTheoDanhMuc.HienThiSanPhamTheoDanhMucActivity;
import com.gtv.hanhee.shopquanao._Retrofit.APIUtils;

import java.util.List;


public class AdapterDienTuThuongHieuLon extends RecyclerView.Adapter<AdapterDienTuThuongHieuLon.ViewHolder> {
    Context context;
    List<ThuongHieu> thuongHieuList;
    boolean kiemtra;

    public AdapterDienTuThuongHieuLon(Context context, List<ThuongHieu> thuongHieuList, boolean kiemtra) {
        this.context = context;
        this.thuongHieuList = thuongHieuList;
        this.kiemtra = kiemtra;
    }

    @NonNull
    @Override
    public AdapterDienTuThuongHieuLon.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.layout_sanpham_custom_recycle_thuonghieulon, parent, false);
        AdapterDienTuThuongHieuLon.ViewHolder viewHolder = new AdapterDienTuThuongHieuLon.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterDienTuThuongHieuLon.ViewHolder holder, int position) {
        ThuongHieu thuongHieu = thuongHieuList.get(position);
        holder.txtTieuDeThuongHieu.setText(thuongHieu.getTensp());
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iHienThiSanPhamTheoDanhMuc = new Intent(context, HienThiSanPhamTheoDanhMucActivity.class);
                iHienThiSanPhamTheoDanhMuc.putExtra("MALOAI", Integer.parseInt(thuongHieu.getMaloaisp()));
                iHienThiSanPhamTheoDanhMuc.putExtra("TENLOAI", thuongHieu.getTensp());
                iHienThiSanPhamTheoDanhMuc.putExtra("KIEMTRA", kiemtra);
                context.startActivity(iHienThiSanPhamTheoDanhMuc);
            }
        });
        GlideApp.with(context).load(APIUtils.Base_url+thuongHieu.getAnhlon())
                .apply(new RequestOptions().override(140, 140))
                .transition(new DrawableTransitionOptions().crossFade(500)).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                holder.progressBar.setVisibility(View.GONE);
                return false;
            }
        }).into(holder.imgHinhThuongHieu);
    }

    @Override
    public int getItemCount() {
        return thuongHieuList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTieuDeThuongHieu;
        ImageView imgHinhThuongHieu;
        ProgressBar progressBar;
        LinearLayout linearLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            txtTieuDeThuongHieu = itemView.findViewById(R.id.txtTieuDeThuongHieuLonDienTu);
            imgHinhThuongHieu = itemView.findViewById(R.id.imgHinhThuongHieuLonDienTu);
            progressBar = itemView.findViewById(R.id.pgThuongHieuLon);
            linearLayout = itemView.findViewById(R.id.lnThuongHieuLon);
        }
    }

}
