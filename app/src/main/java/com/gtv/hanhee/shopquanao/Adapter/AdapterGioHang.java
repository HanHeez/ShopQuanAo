package com.gtv.hanhee.shopquanao.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.gtv.hanhee.shopquanao.Model.ObjectClass.GioHang;
import com.gtv.hanhee.shopquanao.Presenter.GioHang.PresenterGioHang;
import com.gtv.hanhee.shopquanao.R;
import com.gtv.hanhee.shopquanao._Retrofit.APIUtils;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

public class AdapterGioHang extends RecyclerView.Adapter<AdapterGioHang.ViewHolder> {
    Context context;
    List<GioHang> gioHangList;
    PresenterGioHang presenterGioHang;

    public AdapterGioHang(Context context, List<GioHang> gioHangList) {
        this.context = context;
        this.gioHangList = gioHangList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.layout_custom_giohang, parent, false);
        AdapterGioHang.ViewHolder viewHolder = new AdapterGioHang.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GioHang gioHang = gioHangList.get(position);
        presenterGioHang = new PresenterGioHang(context);

        GlideApp.with(context).load(APIUtils.Base_url+gioHang.getAnhlon())
                .apply(new RequestOptions().override(90, 90))
                .transition(new DrawableTransitionOptions().crossFade(500)).into(holder.imgHinhGioHang);
        holder.txtTenGioHang.setText(gioHang.getTensp());
        NumberFormat numberFormat = new DecimalFormat("###,###");
        String gia = numberFormat.format(gioHang.getGiatien());
        holder.txtGiaGioHang.setText(gia + " VND");

        holder.imgXoaSPGioHang.setTag((int) gioHang.getMasp());
        holder.imgXoaSPGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenterGioHang.XoaSPTrongGioHang((int) v.getTag());
                gioHangList.remove(position);
                notifyDataSetChanged();
            }
        });
        holder.txtSLSP.setText(gioHang.getSoluongdangdat() + "");
        holder.imgGiamSLSPGH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int soluongdat = Integer.parseInt(holder.txtSLSP.getText().toString());

                GioHang itemgiohanghientai = presenterGioHang.LaySPTheoMaspGioHang((int) gioHang.getMasp()).get(0);
                if (soluongdat > 1) {
                    soluongdat--;
                    presenterGioHang.CapNhatSLDangDatMuaGioHang(itemgiohanghientai, soluongdat);
                }
                holder.txtSLSP.setText(soluongdat + "");
                Log.d("kiemtra", (int) gioHang.getMasp() + " " + itemgiohanghientai.getSoluongdangdat() + "");
            }
        });

        holder.imgTangSLSPGH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int soluongdat = Integer.parseInt(holder.txtSLSP.getText().toString());
                GioHang itemgiohanghientai = presenterGioHang.LaySPTheoMaspGioHang((int) gioHang.getMasp()).get(0);

                if (soluongdat < itemgiohanghientai.getSoluong()) {
                    soluongdat++;
                    presenterGioHang.CapNhatSLDangDatMuaGioHang(itemgiohanghientai, soluongdat);
                } else {
                    Toast.makeText(context, "Số lượng sản phẩm bạn mua quá số lượng trong kho cửa hàng", Toast.LENGTH_SHORT).show();
                }
                holder.txtSLSP.setText(soluongdat + "");
                Log.d("kiemtra", gioHang.getMasp() + " " + itemgiohanghientai.getSoluongdangdat() + "");

            }
        });


    }

    @Override
    public int getItemCount() {
        return gioHangList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgHinhGioHang, imgXoaSPGioHang;
        TextView txtTenGioHang, txtGiaGioHang, txtSLSP;
        ImageButton imgTangSLSPGH, imgGiamSLSPGH;

        public ViewHolder(View itemView) {
            super(itemView);
            imgHinhGioHang = itemView.findViewById(R.id.imgHinhGioHang);
            imgXoaSPGioHang = itemView.findViewById(R.id.imgXoaSPGioHang);
            txtTenGioHang = itemView.findViewById(R.id.txtTenGioHang);
            txtGiaGioHang = itemView.findViewById(R.id.txtGiaGioHang);
            txtSLSP = itemView.findViewById(R.id.txtSoLuongSPGioHang);
            imgTangSLSPGH = itemView.findViewById(R.id.imgTangSLSPGH);
            imgGiamSLSPGH = itemView.findViewById(R.id.imgGiamSLSPGH);
        }
    }
}
