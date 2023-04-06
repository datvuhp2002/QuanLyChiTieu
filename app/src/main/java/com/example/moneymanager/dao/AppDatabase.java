package com.example.moneymanager.dao;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.moneymanager.entity.Chi;
import com.example.moneymanager.entity.LoaiChi;
import com.example.moneymanager.entity.LoaiThu;
import com.example.moneymanager.entity.Thu;

@Database(entities = {LoaiThu.class,Thu.class,Chi.class,LoaiChi.class},version = 4)
public abstract class AppDatabase extends RoomDatabase {
    public abstract  LoaiChiDao loaiChiDao();
    public abstract ChiDao chiDao();
    public abstract LoaiThuDao loaiThuDao();
    public abstract ThuDao thuDao();

    private static RoomDatabase.Callback callback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new PopulateData(INSTANCE).execute();
        }
    };
    public static AppDatabase INSTANCE;

    public static AppDatabase getDatabase(final Context context) {
        if(INSTANCE == null){
            synchronized (AppDatabase.class){
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "personal_db")
                        .fallbackToDestructiveMigration()
                        .addCallback(callback)
                        .build();
            }
        }
        return INSTANCE;
    }
    public static class PopulateData extends AsyncTask<Void, Void , Void>{
        private LoaiChiDao loaiChiDao;
        private ChiDao chiDao;
        private LoaiThuDao loaiThuDao;
        private ThuDao thuDao;
        public PopulateData(AppDatabase db){
            loaiChiDao = db.loaiChiDao();
            chiDao = db.chiDao();
            loaiThuDao = db.loaiThuDao();
            thuDao = db.thuDao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            String[] loaithus = new String[]{"Lương","Thưởng","Cổ phiếu"};
            String[] loaichis = new String[]{"Tiền nhà", "Đi lại", "Ăn uống", "Tiền học", "Mua sắm", "Giải trí", "Trả nợ", "Tiết kiệm", "Hoá đơn tiện ích"};
            for(String it : loaithus){
                LoaiThu lt = new LoaiThu();
                lt.ten = it;
                loaiThuDao.insert(lt);
            }
            for(String it : loaichis){
                LoaiChi lc = new LoaiChi();
                lc.ten = it;
                loaiChiDao.insert(lc);
            }
            Chi chi = new Chi();
            chi.ten = "Tiền Đóng học tháng 4";
            chi.sotien = 4000;
            chi.lcid = 2;
            chiDao.insert(chi);
            Log.d("BudgetPro: ","insert Data");
            Thu thu = new Thu();
            thu.ten = "Lương tháng 1";
            thu.sotien = 3000;
            thu.ltid = 2;
            thu.ghichu = "";
            thuDao.insert(thu);
            Log.d("BudgetPro: ","insert Data");
            return null;
        }
    }
}
