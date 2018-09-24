package com.gtv.hanhee.shopquanao.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.gtv.hanhee.shopquanao.Model.ObjectClass.SanPham;
import com.gtv.hanhee.shopquanao.R;
import com.gtv.hanhee.shopquanao.View.ChiTietSanPham.ChiTietSanPhamActivity;
import com.gtv.hanhee.shopquanao._Retrofit.APIUtils;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

public class AdapterDienTuTopDienThoai extends RecyclerView.Adapter<AdapterDienTuTopDienThoai.ViewHolder> {
    Context context;
    List<SanPham> sanPhamList;
    int layout;

    public AdapterDienTuTopDienThoai(int layout, Context context, List<SanPham> sanPhamList) {
        this.context = context;
        this.sanPhamList = sanPhamList;
        this.layout = layout;
    }

    @NonNull
    @Override
    public AdapterDienTuTopDienThoai.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(layout, parent, false);
        AdapterDienTuTopDienThoai.ViewHolder viewHolder = new AdapterDienTuTopDienThoai.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterDienTuTopDienThoai.ViewHolder holder, int position) {
        SanPham sanPham = sanPhamList.get(position);

        GlideApp.with(context).load(APIUtils.Base_url+sanPham.getAnhlon())
                .apply(new RequestOptions().override(90, 90))
                .transition(new DrawableTransitionOptions().crossFade(500)).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {

                return false;
            }
        }).into(holder.imgHinhSanPham);
        holder.txtTenSP.setText(sanPham.getTensp());

        NumberFormat numberFormat = new DecimalFormat("###,###");
        String gia = numberFormat.format(sanPham.getGia());
        holder.txtGiaTien.setText(gia + " VND");

        holder.cardView.setTag(sanPham.getMasp());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iChiTietSanpham = new Intent(context, ChiTietSanPhamActivity.class);
                iChiTietSanpham.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                iChiTietSanpham.putExtra("masp", (int) v.getTag());
                context.startActivity(iChiTietSanpham);
            }
        });
    }

    @Override
    public int getItemCount() {
        return sanPhamList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgHinhSanPham;
        TextView txtTenSP, txtGiaTien, txtGiamGia;
        CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);

            imgHinhSanPham = itemView.findViewById(R.id.imgHinhTopDienTu);
            txtTenSP = itemView.findViewById(R.id.txtTieuDeTopDienThoaiDienTu);
            txtGiaTien = itemView.findViewById(R.id.txtGiaDienTu);
            txtGiamGia = itemView.findViewById(R.id.txtGiamGiaDienTu);
            cardView = itemView.findViewById(R.id.cardView);
        }
    }


}
