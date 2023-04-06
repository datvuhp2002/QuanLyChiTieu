package com.example.moneymanager.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.moneymanager.dao.AppDatabase;
import com.example.moneymanager.dao.ChiDao;
import com.example.moneymanager.entity.ThongKeLoaiChi;
import com.example.moneymanager.entity.Chi;

import java.util.List;

public class ChiReponsitory {
    private ChiDao mChiDao;
    private LiveData<List<Chi>> mAllChi;

    public ChiReponsitory(Application application){
        this.mChiDao = AppDatabase.getDatabase(application).chiDao();
        mAllChi = mChiDao.findAll();

    }

    public LiveData<List<Chi>> getAllChi(){
        return mAllChi;
    }
    public LiveData<Float> sumTongChi(){
        return mChiDao.sumTongChi();
    }
    public LiveData<List<ThongKeLoaiChi>> sumByLoaiChi(){
        return mChiDao.sumByLoaiChi();
    }

    public void insert(Chi loaiChi){
        new InsertAsyncTask(mChiDao).execute(loaiChi);
    }
    public void delete(Chi loaiChi){
        new DeleteAsyncTask(mChiDao).execute(loaiChi);
    }
    public void update(Chi loaiChi){
        new UpdateAsyncTask(mChiDao).execute(loaiChi);
    }
    class InsertAsyncTask extends AsyncTask<Chi, Void, Void>{
        private ChiDao mChiDao;
        public InsertAsyncTask(ChiDao ChiDao){
            this.mChiDao = ChiDao;
        }
        @Override
        protected Void doInBackground(Chi... loaiChis) {
            mChiDao.insert(loaiChis[0]);
            return null;
        }
    }
    class DeleteAsyncTask extends AsyncTask<Chi, Void, Void>{
        private ChiDao mChiDao;
        public DeleteAsyncTask(ChiDao ChiDao){
            this.mChiDao = ChiDao;
        }
        @Override
        protected Void doInBackground(Chi... loaiChis) {
            mChiDao.delete(loaiChis[0]);
            return null;
        }
    }
    class UpdateAsyncTask extends AsyncTask<Chi, Void, Void>{
        private ChiDao mChiDao;
        public UpdateAsyncTask(ChiDao ChiDao){
            this.mChiDao = ChiDao;
        }
        @Override
        protected Void doInBackground(Chi... loaiChis) {
            mChiDao.update(loaiChis[0]);
            return null;
        }
    }
}
