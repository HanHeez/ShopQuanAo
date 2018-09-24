package com.gtv.hanhee.shopquanao.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gtv.hanhee.shopquanao.R;

import java.util.List;

public class AdapterNew extends RecyclerView.Adapter<AdapterNew.ViewHolder> {
    Context context;
    List<String> stringList;

    public AdapterNew(Context context, List<String> stringList) {
        this.context = context;
        this.stringList = stringList;
    }

    //Chay dau tien
    @NonNull
    @Override
    public AdapterNew.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.layout_new_recycleview, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    //Chay thu 3
    @Override
    public void onBindViewHolder(@NonNull AdapterNew.ViewHolder holder, int position) {
        holder.textView.setText(stringList.get(position));
    }

    @Override
    public int getItemCount() {
        return stringList.size();
    }

    //chay thu 2
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.txtTieuDeNew);
        }
    }


}
