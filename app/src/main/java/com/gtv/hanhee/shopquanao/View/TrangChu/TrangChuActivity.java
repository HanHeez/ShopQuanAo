package com.gtv.hanhee.shopquanao.View.TrangChu;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;

import com.facebook.appevents.AppEventsLogger;
import com.gtv.hanhee.shopquanao.Adapter.AdapterViewPagerTrangchu;
import com.gtv.hanhee.shopquanao.Presenter.TrangChu.PresenterLogicXuLyMenu;
import com.gtv.hanhee.shopquanao.R;
import com.gtv.hanhee.shopquanao._CustomView.NonSwipeableViewPager;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import q.rorbin.badgeview.Badge;
import q.rorbin.badgeview.QBadgeView;

public class TrangChuActivity extends AppCompatActivity {
    AdapterViewPagerTrangchu adapter;
    NonSwipeableViewPager viewPagerTrangchu;
    BottomNavigationViewEx bnve;
    PresenterLogicXuLyMenu logicXuLyMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showSystemUI();
        AppEventsLogger.activateApp(getApplication());
        logicXuLyMenu = new PresenterLogicXuLyMenu();
        logicXuLyMenu.LayTenNguoiDungFacebook(this);
        setContentView(R.layout.layout_trangchu);
        AddControls();
    }

    private void AddControls() {
        bnve = findViewById(R.id.bnve);
        Typeface font = ResourcesCompat.getFont(getApplicationContext(), R.font.museo_sans_regular);
        bnve.setTypeface(font);
        bnve.setIconSize(35, 35);
        bnve.setItemHeight(80);
        bnve.enableShiftingMode(false);
        bnve.enableItemShiftingMode(false);

        viewPagerTrangchu = findViewById(R.id.viewPagerTrangchu);
        viewPagerTrangchu.setOffscreenPageLimit(5);
        adapter = new AdapterViewPagerTrangchu(getSupportFragmentManager());
        viewPagerTrangchu.setAdapter(adapter);
        bnve.setupWithViewPager(viewPagerTrangchu);
        addBadgeAt(1, 5);
        bnve.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.itemCart:
                        viewPagerTrangchu.setCurrentItem(0);
                        //click Cart
                        break;
                    case R.id.itemComment:
                        viewPagerTrangchu.setCurrentItem(1);
                        //click Comment
                        break;
                }
                return true;
            }
        });
    }

    private void showSystemUI() {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
    }

    private Badge addBadgeAt(int position, int number) {
        return new QBadgeView(TrangChuActivity.this)
                .setBadgeNumber(number)
                .setGravityOffset(35, 0, true).setBadgeGravity(Gravity.END | Gravity.TOP)
                .bindTarget(bnve.getBottomNavigationItemView(position));
    }
}
