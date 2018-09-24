package com.gtv.hanhee.shopquanao.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.gtv.hanhee.shopquanao.Model.ObjectClass.DanhGia;
import com.gtv.hanhee.shopquanao.R;

import java.util.List;

public class AdapterRecycleDanhGia extends RecyclerView.Adapter<AdapterRecycleDanhGia.ViewHolder> {
    Context context;
    List<DanhGia> danhGiaList;
    int limit;

    public AdapterRecycleDanhGia(Context context, List<DanhGia> danhGiaList, int limit) {
        this.context = context;
        this.danhGiaList = danhGiaList;
        this.limit = limit;
    }

    @NonNull
    @Override
    public AdapterRecycleDanhGia.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.layout_custom_recycler_danhgiachitiet, parent, false);
        AdapterRecycleDanhGia.ViewHolder viewHolder = new AdapterRecycleDanhGia.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterRecycleDanhGia.ViewHolder holder, int position) {
        DanhGia danhGia = danhGiaList.get(position);

        holder.txtTieuDeDG.setText(danhGia.getTieude());
        holder.txtNoiDungDG.setText(danhGia.getNoidung());
        holder.txtDuocDGBoi.setText("Được đánh giá bởi: " + danhGia.getTenthietbi() + " ngày " + danhGia.getNgaydang());
        holder.rbDG.setRating(danhGia.getSosao());
    }

    @Override
    public int getItemCount() {
        int dong = 0;
        if (danhGiaList.size() >= limit) {
            dong = danhGiaList.size();
        } else {
            if (limit != 0) {
                dong = limit;
            } else {
                dong = danhGiaList.size();
            }
        }


        return dong;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTieuDeDG, txtNoiDungDG, txtDuocDGBoi;
        RatingBar rbDG;

        public ViewHolder(View itemView) {
            super(itemView);

            txtTieuDeDG = itemView.findViewById(R.id.txtTieuDeDG);
            txtNoiDungDG = itemView.findViewById(R.id.txtNoiDungDG);
            txtDuocDGBoi = itemView.findViewById(R.id.txtDuocDGBoi);
            rbDG = itemView.findViewById(R.id.rbDG);
        }
    }


}
