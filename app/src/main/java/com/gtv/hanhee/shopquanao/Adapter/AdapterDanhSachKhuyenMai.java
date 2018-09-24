package com.gtv.hanhee.shopquanao.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gtv.hanhee.shopquanao.Model.ObjectClass.KhuyenMai;
import com.gtv.hanhee.shopquanao.R;

import java.util.List;

public class AdapterDanhSachKhuyenMai extends RecyclerView.Adapter<AdapterDanhSachKhuyenMai.ViewHolder> {
    Context context;
    List<KhuyenMai> khuyenMais;

    public AdapterDanhSachKhuyenMai(Context context, List<KhuyenMai> khuyenMais) {
        this.context = context;
        this.khuyenMais = khuyenMais;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RecyclerView recycleItemKM;
        TextView txtTieuDeKM;

        public ViewHolder(View itemView) {
            super(itemView);
            recycleItemKM = itemView.findViewById(R.id.recycleItemKM);
            txtTieuDeKM = itemView.findViewById(R.id.txtTieuDeKM);
        }
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.layout_custom_danhsachkhuyenmai,parent,false);
        AdapterDanhSachKhuyenMai.ViewHolder viewHolder = new AdapterDanhSachKhuyenMai.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        KhuyenMai khuyenMai = khuyenMais.get(position);
        holder.txtTieuDeKM.setText(khuyenMai.getTenloaisp());

        AdapterChiTietKhuyenMai adapterChiTietKhuyenMai = new AdapterChiTietKhuyenMai(context,khuyenMai.getDanhsachsanphamkhuyenmai());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false);

        holder.recycleItemKM.setLayoutManager(layoutManager);
        holder.recycleItemKM.setAdapter(adapterChiTietKhuyenMai);        ;
        adapterChiTietKhuyenMai.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return khuyenMais.size();
    }

}
