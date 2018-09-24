package com.gtv.hanhee.shopquanao.Adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.gtv.hanhee.shopquanao.Model.ObjectClass.ThuongHieu;
import com.gtv.hanhee.shopquanao.R;
import com.gtv.hanhee.shopquanao._Retrofit.APIUtils;

import java.util.List;

public class AdapterDienTuLogoThuongHieuLon extends RecyclerView.Adapter<AdapterDienTuLogoThuongHieuLon.ViewHolder> {
    Context context;
    List<ThuongHieu> thuongHieuList;

    public AdapterDienTuLogoThuongHieuLon(Context context, List<ThuongHieu> thuongHieuList) {
        this.context = context;
        this.thuongHieuList = thuongHieuList;
    }

    @NonNull
    @Override
    public AdapterDienTuLogoThuongHieuLon.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.layout_sanpham_custom_recycle_logothuonghieulon, parent, false);
        AdapterDienTuLogoThuongHieuLon.ViewHolder viewHolder = new AdapterDienTuLogoThuongHieuLon.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterDienTuLogoThuongHieuLon.ViewHolder holder, int position) {
        ThuongHieu thuongHieu = thuongHieuList.get(position);

        GlideApp.with(context).load(APIUtils.Base_url+thuongHieuList.get(position).getAnhlon())
                .apply(new RequestOptions().override(80, 80))
                .transition(new DrawableTransitionOptions().crossFade(500)).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {

                return false;
            }
        }).into(holder.imgLogoThuongHieuLon);
    }

    @Override
    public int getItemCount() {
        return thuongHieuList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgLogoThuongHieuLon;

        public ViewHolder(View itemView) {
            super(itemView);
            imgLogoThuongHieuLon = itemView.findViewById(R.id.imgHinhLogoThuongHieuLon);
        }
    }

}
