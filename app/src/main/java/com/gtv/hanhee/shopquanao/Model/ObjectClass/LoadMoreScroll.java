package com.gtv.hanhee.shopquanao.Model.ObjectClass;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class LoadMoreScroll extends RecyclerView.OnScrollListener{
    int vitriItemHienThidauTien = 0;
    int tongitem = 0;
    int itemloadtruoc = 10;
    int vitriItemHienThiCuoiCung = 0;
    RecyclerView.LayoutManager layoutManager;
    ILoadMore iLoadMore;
    Boolean loading;

    public LoadMoreScroll(RecyclerView.LayoutManager layoutManager, ILoadMore iLoadMore, Boolean loading) {
        this.layoutManager = layoutManager;
        this.iLoadMore = iLoadMore;
        this.loading = loading;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        tongitem = layoutManager.getItemCount();

        if (layoutManager instanceof LinearLayoutManager) {
            vitriItemHienThidauTien = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
            vitriItemHienThiCuoiCung = ((LinearLayoutManager) layoutManager).findLastCompletelyVisibleItemPosition();

        } else if (layoutManager instanceof GridLayoutManager) {
            vitriItemHienThidauTien = ((GridLayoutManager) layoutManager).findFirstVisibleItemPosition();
            vitriItemHienThiCuoiCung = ((LinearLayoutManager) layoutManager).findLastCompletelyVisibleItemPosition();
        }

        if (!loading && tongitem <= vitriItemHienThidauTien + itemloadtruoc) {
            if (iLoadMore != null) {
                iLoadMore.LoadMore(tongitem);
                loading = true;
            }
        }
    }

    public void setLoaded(){
        loading = false;
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
    }
}
