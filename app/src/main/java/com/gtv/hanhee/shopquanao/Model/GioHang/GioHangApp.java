package com.gtv.hanhee.shopquanao.Model.GioHang;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.gtv.hanhee.shopquanao.Model.ObjectClass.DaoMaster;
import com.gtv.hanhee.shopquanao.Model.ObjectClass.DaoMaster;
import com.gtv.hanhee.shopquanao.Model.ObjectClass.DaoSession;

public class GioHangApp extends Application {
    DaoSession daoSession;
    DaoMaster.DevOpenHelper helper;
    SQLiteDatabase db;
    DaoMaster daoMaster;

    @Override
    public void onCreate() {
        super.onCreate();
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "sqlgiohang", null);
        db = helper.getWritableDatabase();
        daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();

    }
    public DaoSession getDaoSession() {
        return daoSession;
    }
}
