package com.example.moneymanager.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.moneymanager.dao.AppDatabase;
import com.example.moneymanager.dao.ThuDao;
import com.example.moneymanager.entity.ThongKeLoaiThu;
import com.example.moneymanager.entity.Thu;

import java.util.List;

public class ThuReponsitory {
    private ThuDao mThuDao;
    private LiveData<List<Thu>> mAllThu;

    public ThuReponsitory(Application application){
        this.mThuDao = AppDatabase.getDatabase(application).thuDao();
        mAllThu = mThuDao.findAll();

    }

    public LiveData<List<Thu>> getAllThu(){
        return mAllThu;
    }
    public LiveData<Float> sumTongThu(){
        return mThuDao.sumTongThu();
    }
    public LiveData<List<ThongKeLoaiThu>> sumByLoaiThu(){
        return mThuDao.sumByLoaiThu();
    }

    public void insert(Thu loaiThu){
        new InsertAsyncTask(mThuDao).execute(loaiThu);
    }
    public void delete(Thu loaiThu){
        new DeleteAsyncTask(mThuDao).execute(loaiThu);
    }
    public void update(Thu loaiThu){
        new UpdateAsyncTask(mThuDao).execute(loaiThu);
    }
    class InsertAsyncTask extends AsyncTask<Thu, Void, Void>{
        private ThuDao mThuDao;
        public InsertAsyncTask(ThuDao thuDao){
            this.mThuDao = thuDao;
        }
        @Override
        protected Void doInBackground(Thu... loaiThus) {
            mThuDao.insert(loaiThus[0]);
            return null;
        }
    }
    class DeleteAsyncTask extends AsyncTask<Thu, Void, Void>{
        private ThuDao mThuDao;
        public DeleteAsyncTask(ThuDao thuDao){
            this.mThuDao = thuDao;
        }
        @Override
        protected Void doInBackground(Thu... loaiThus) {
            mThuDao.delete(loaiThus[0]);
            return null;
        }
    }
    class UpdateAsyncTask extends AsyncTask<Thu, Void, Void>{
        private ThuDao mThuDao;
        public UpdateAsyncTask(ThuDao thuDao){
            this.mThuDao = thuDao;
        }
        @Override
        protected Void doInBackground(Thu... loaiThus) {
            mThuDao.update(loaiThus[0]);
            return null;
        }
    }
}
