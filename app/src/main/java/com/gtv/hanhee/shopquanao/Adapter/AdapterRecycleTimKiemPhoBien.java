package com.gtv.hanhee.shopquanao.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gtv.hanhee.shopquanao.Model.TimKiemPhoBien.TimKiemPhoBienModel;
import com.gtv.hanhee.shopquanao.R;

import java.util.List;

public class AdapterRecycleTimKiemPhoBien extends RecyclerView.Adapter<AdapterRecycleTimKiemPhoBien.ViewHolder> {
    Context context;
    int resource;
    List<TimKiemPhoBienModel> timKiemPhoBienModelList;

    public AdapterRecycleTimKiemPhoBien(Context context, int resource, List<TimKiemPhoBienModel> timKiemPhoBienModelList) {
        this.context = context;
        this.resource = resource;
        this.timKiemPhoBienModelList = timKiemPhoBienModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(resource, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return timKiemPhoBienModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTKPBTenSp, txtTKPBSoLuongSp;
        ImageView imgTKPBHinhAnhSp;

        public ViewHolder(View itemView) {
            super(itemView);
            txtTKPBTenSp = itemView.findViewById(R.id.txtTKPBTenSp);
            txtTKPBSoLuongSp = itemView.findViewById(R.id.txtTKPBSoLuongSp);
            imgTKPBHinhAnhSp = itemView.findViewById(R.id.imgTKPBHinhAnhSp);
        }
    }


}
