package com.gtv.hanhee.shopquanao.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.gtv.hanhee.shopquanao.Model.XuLyMenu.LoaiSanPham;
import com.gtv.hanhee.shopquanao.R;
import com.gtv.hanhee.shopquanao.View.HienThiSanPhamTheoDanhMuc.HienThiSanPhamTheoDanhMucActivity;

import java.util.List;

public class ExpandAdapter extends BaseExpandableListAdapter {
    Context context;
    List<LoaiSanPham> loaiSanPhamList;
    ViewHolderMenu viewHolderMenu;

    public ExpandAdapter(Context context, final List<LoaiSanPham> loaiSanPhamList) {
        this.context = context;
        this.loaiSanPhamList = loaiSanPhamList;
    }

    @Override
    public int getGroupCount() {
        return loaiSanPhamList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        if (loaiSanPhamList.get(groupPosition).getListCon().size() != 0) {
            return 1;
        } else {
            return 0;
        }
//        return loaiSanPhamList.get(groupPosition).getListCon().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return loaiSanPhamList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return loaiSanPhamList.get(groupPosition).getListCon().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return Integer.parseInt(loaiSanPhamList.get(groupPosition).getMALOAISP());
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return Integer.parseInt(loaiSanPhamList.get(groupPosition).getListCon().get(childPosition).getMALOAISP());
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(final int groupPosition, final boolean isExpanded, View convertView, ViewGroup parent) {
        View viewGroupCha = convertView;
        if (viewGroupCha == null) {
            viewHolderMenu = new ViewHolderMenu();
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            viewGroupCha = layoutInflater.inflate(R.layout.layout_custom_groupcha, parent, false);
            viewHolderMenu.txtTenLoaiSP = viewGroupCha.findViewById(R.id.txtTenLoaiSP);
            viewHolderMenu.imgMenuPlus = viewGroupCha.findViewById(R.id.imgMenuPlus);
            viewGroupCha.setTag(viewHolderMenu);
        } else {
            viewHolderMenu = (ViewHolderMenu) viewGroupCha.getTag();
        }


        viewHolderMenu.txtTenLoaiSP.setText(loaiSanPhamList.get(groupPosition).getTENLOAISP());
        if (loaiSanPhamList.get(groupPosition).getListCon() != null) {
            if (loaiSanPhamList.get(groupPosition).getListCon().size() > 0) {
                viewHolderMenu.imgMenuPlus.setVisibility(View.VISIBLE);
                if (isExpanded) {
                    viewHolderMenu.imgMenuPlus.setImageResource(R.drawable.ic_remove_black_24dp);
                    viewGroupCha.setBackgroundResource(R.color.colorGrey);
                } else {
                    viewHolderMenu.imgMenuPlus.setImageResource(R.drawable.ic_add_black_24dp);
                    viewGroupCha.setBackgroundResource(R.color.colorWhite);
                }
            } else {
                viewHolderMenu.imgMenuPlus.setVisibility(View.INVISIBLE);
            }
        }

        viewGroupCha.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });

        return viewGroupCha;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
//        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //        View viewGroupCon = layoutInflater.inflate(R.layout.layout_custom_groupcon,parent,false);
//        ExpandableListView expandableListView = viewGroupCon.findViewById(R.id.epMenuCon);
        SecondExpandable secondExpandable = new SecondExpandable(context);

//            Log.d("kiemtra", String.valueOf(groupPosition));
        ExpandAdapter secondAdapter = new ExpandAdapter(context, loaiSanPhamList.get(groupPosition).getListCon());
        secondExpandable.setAdapter(secondAdapter);
        secondExpandable.setGroupIndicator(null);
        secondAdapter.notifyDataSetChanged();


        return secondExpandable;

    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    public class ViewHolderMenu {
        TextView txtTenLoaiSP;
        ImageView imgMenuPlus;

    }

//Set chieu cao chieu rong cho ExpandListView

    public class SecondExpandable extends ExpandableListView {
        public SecondExpandable(Context context) {
            super(context);
        }

        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            Display display = windowManager.getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            int width = size.x;
            int heigh = size.y;
//            widthMeasureSpec = MeasureSpec.makeMeasureSpec(width,MeasureSpec.AT_MOST);
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(99999, MeasureSpec.AT_MOST);
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }
// level 2 expand list view
//
//    public class SecondAdapter extends BaseExpandableListAdapter {
//        List<LoaiSanPham> listCon;
//
//        public SecondAdapter(final List<LoaiSanPham> listCon) {
//            this.listCon = listCon;
//
//            final PresenterLogicXuLyMenu presenterLogicXuLyMenu = new PresenterLogicXuLyMenu();
//            int count = listCon.size();
//
//            for (int i=0;i<count;i++) {
//                int maloaisp = Integer.parseInt(listCon.get(i).getMALOAISP());
//
//                LayDuLieuTuUrl DanhSachLoaiSP = APIUtils.getData();
//                Call<List<LoaiSanPham>> call = DanhSachLoaiSP.LayDanhSachLoaiSanPham(maloaisp);
//
//                final int finalI = i;
//                call.enqueue(new Callback<List<LoaiSanPham>>() {
//                    @Override
//                    public void onResponse(Call<List<LoaiSanPham>> call, Response<List<LoaiSanPham>> response) {
//                        if (response.isSuccessful()) {
//                            listCon.get(finalI).setListCon(response.body());
//
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<List<LoaiSanPham>> call, Throwable t) {
//
//                    }
//                });
//            }
//
//        }
//
//        @Override
//        public int getGroupCount() {
//            return listCon.size();
//        }
//
//        @Override
//        public int getChildrenCount(int groupPosition) {
//            return listCon.get(groupPosition).getListCon().size();
//        }
//
//        @Override
//        public Object getGroup(int groupPosition) {
//            return listCon.get(groupPosition);
//        }
//
//        @Override
//        public Object getChild(int groupPosition, int childPosition) {
//            return listCon.get(groupPosition).getListCon().get(childPosition);
//        }
//
//        @Override
//        public long getGroupId(int groupPosition) {
//            return Integer.parseInt(listCon.get(groupPosition).getMALOAISP());
//        }
//
//        @Override
//        public long getChildId(int groupPosition, int childPosition) {
//            return Integer.parseInt(listCon.get(groupPosition).getListCon().get(childPosition).getMALOAISP());
//        }
//
//        @Override
//        public boolean hasStableIds() {
//            return false;
//        }
//
//        @Override
//        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
//            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            View view = layoutInflater.inflate(R.layout.layout_custom_groupcha,parent,false);
//            TextView txtTenLoaiSP = view.findViewById(R.id.txtTenLoaiSP);
//            txtTenLoaiSP.setText(listCon.get(groupPosition).getTENLOAISP());
//            return view;
//        }
//
//        @Override
//        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
////            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
////            View view = layoutInflater.inflate(R.layout.layout_custom_groupcha,parent,false);
//            TextView tv = new TextView(context);
//            tv.setText(listCon.get(groupPosition).getListCon().get(childPosition).getTENLOAISP());
//            tv.setPadding(15,5,5,5);
//            tv.setLayoutParams(new ListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));
//            return tv;
//        }
//
//        @Override
//        public boolean isChildSelectable(int groupPosition, int childPosition) {
//            return false;
//        }
//    }
}
