package com.gtv.hanhee.shopquanao.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gtv.hanhee.shopquanao.Model.ObjectClass.DienTu;
import com.gtv.hanhee.shopquanao.R;

import java.util.List;

public class AdapterDienTu extends RecyclerView.Adapter<AdapterDienTu.ViewHolder> {
    Context context;
    List<DienTu> dienTuList;

    public AdapterDienTu(Context context, List<DienTu> dienTuList) {
        this.context = context;
        this.dienTuList = dienTuList;
    }

    @NonNull
    @Override
    public AdapterDienTu.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.layout_sanpham_custom_recycle_dientu, parent, false);
        AdapterDienTu.ViewHolder viewHolder = new AdapterDienTu.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterDienTu.ViewHolder holder, int position) {
        DienTu dienTu = dienTuList.get(position);

        holder.txtTopnoibat.setText(dienTu.getTentopnoibat());
        holder.txtTennoibat.setText(dienTu.getTennoibat());
        AdapterDienTuThuongHieuLon adapterDienTuThuongHieuLon = new AdapterDienTuThuongHieuLon(context, dienTu.getThuongHieuList(), dienTu.getThuonghieu());
        RecyclerView.LayoutManager layoutManagerTH = new GridLayoutManager(context, 3, GridLayoutManager.HORIZONTAL, false);

        holder.recyclerViewThuongHieuLon.setLayoutManager(layoutManagerTH);
        holder.recyclerViewThuongHieuLon.setAdapter(adapterDienTuThuongHieuLon);
        adapterDienTuThuongHieuLon.notifyDataSetChanged();

        AdapterDienTuTopDienThoai adapterDienTuTopDienThoai = new AdapterDienTuTopDienThoai(R.layout.layout_sanpham_custom_recycle_topdienthoaimaytinhbang, context, dienTu.getSanPhamList());
        RecyclerView.LayoutManager layoutManagerTop = new GridLayoutManager(context, 1, GridLayoutManager.HORIZONTAL, false);

        holder.recyclerViewTopSanPham.setLayoutManager(layoutManagerTop);
        holder.recyclerViewTopSanPham.setAdapter(adapterDienTuTopDienThoai);
        adapterDienTuTopDienThoai.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return dienTuList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgHinhKhuyenMaiDienTu;
        TextView txtTennoibat, txtTopnoibat;
        RecyclerView recyclerViewThuongHieuLon, recyclerViewTopSanPham;

        public ViewHolder(View itemView) {
            super(itemView);
            recyclerViewThuongHieuLon = itemView.findViewById(R.id.recycleThuongHieuLon);
            recyclerViewTopSanPham = itemView.findViewById(R.id.recycleTopDienThoaiMayTinhBang);
            imgHinhKhuyenMaiDienTu = itemView.findViewById(R.id.imgKhuyenMaiDienTu);
            txtTennoibat = itemView.findViewById(R.id.txtTennoibat);
            txtTopnoibat = itemView.findViewById(R.id.txtTopnoibat);
        }
    }


}
