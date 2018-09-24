package com.gtv.hanhee.shopquanao.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
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
import com.gtv.hanhee.shopquanao.Model.ObjectClass.ChiTietKhuyenMai;

import com.gtv.hanhee.shopquanao.R;
import com.gtv.hanhee.shopquanao.View.ChiTietSanPham.ChiTietSanPhamActivity;
import com.gtv.hanhee.shopquanao._Retrofit.APIUtils;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

public class AdapterChiTietKhuyenMai extends RecyclerView.Adapter<AdapterChiTietKhuyenMai.ViewHolder> {
    Context context;
    List<ChiTietKhuyenMai> chiTietKhuyenMaiList;

    public AdapterChiTietKhuyenMai(Context context, List<ChiTietKhuyenMai> chiTietKhuyenMaiList) {
        this.context = context;
        this.chiTietKhuyenMaiList = chiTietKhuyenMaiList;
    }

    @NonNull
    @Override
    public AdapterChiTietKhuyenMai.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.layout_sanpham_custom_recycle_topdienthoaimaytinhbang, parent, false);
        AdapterChiTietKhuyenMai.ViewHolder viewHolder = new AdapterChiTietKhuyenMai.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterChiTietKhuyenMai.ViewHolder holder, int position) {
        ChiTietKhuyenMai chiTietKhuyenMai = chiTietKhuyenMaiList.get(position);

        GlideApp.with(context).load(APIUtils.Base_url+chiTietKhuyenMai.getAnhlon())
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

        holder.txtTenSP.setText(chiTietKhuyenMai.getTensp());

        int giatien = chiTietKhuyenMai.getGia();
        int phantramkm = chiTietKhuyenMai.getPhantramkm();

        NumberFormat numberFormat = new DecimalFormat("###,###");
        String giagiam = numberFormat.format(giatien * phantramkm/100);
        holder.txtGiaTien.setText(giagiam + " VND");

        String giadung = numberFormat.format(chiTietKhuyenMai.getGia());
        holder.txtGiaDung.setVisibility(View.VISIBLE);
        holder.txtGiaDung.setPaintFlags(holder.txtGiaDung.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        holder.txtGiaDung.setText(giadung + " VND");

        holder.cardView.setTag(chiTietKhuyenMai.getMasp());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iChiTietSanpham = new Intent(context, ChiTietSanPhamActivity.class);
                iChiTietSanpham.putExtra("masp", (int) v.getTag());
                context.startActivity(iChiTietSanpham);
            }
        });
    }

    @Override
    public int getItemCount() {
        return chiTietKhuyenMaiList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgHinhSanPham;
        TextView txtTenSP, txtGiaTien, txtGiaDung;
        CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);

            imgHinhSanPham = itemView.findViewById(R.id.imgHinhTopDienTu);
            txtTenSP = itemView.findViewById(R.id.txtTieuDeTopDienThoaiDienTu);
            txtGiaTien = itemView.findViewById(R.id.txtGiaDienTu);
            txtGiaDung = itemView.findViewById(R.id.txtGiaDungDienTu);
            cardView = itemView.findViewById(R.id.cardView);
        }
    }
}
