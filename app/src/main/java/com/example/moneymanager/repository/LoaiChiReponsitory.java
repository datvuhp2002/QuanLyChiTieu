package com.example.moneymanager.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.moneymanager.dao.AppDatabase;
import com.example.moneymanager.dao.LoaiChiDao;
import com.example.moneymanager.entity.LoaiChi;

import java.util.List;

public class LoaiChiReponsitory {
    private LoaiChiDao mLoaiChiDao;
    private LiveData<List<LoaiChi>> mAllLoaiChi;

    public LoaiChiReponsitory(Application application){
        this.mLoaiChiDao = AppDatabase.getDatabase(application).loaiChiDao();
        mAllLoaiChi = mLoaiChiDao.findAll();

    }

    public LiveData<List<LoaiChi>> getAllLoaiChi(){
        return mAllLoaiChi;
    }

    public void insert(LoaiChi LoaiChi){
        new InsertAsyncTask(mLoaiChiDao).execute(LoaiChi);
    }
    public void delete(LoaiChi LoaiChi){
        new DeleteAsyncTask(mLoaiChiDao).execute(LoaiChi);
    }
    public void update(LoaiChi LoaiChi){
        new UpdateAsyncTask(mLoaiChiDao).execute(LoaiChi);
    }
    class InsertAsyncTask extends AsyncTask<LoaiChi, Void, Void>{
        private LoaiChiDao mLoaiChiDao;
        public InsertAsyncTask(LoaiChiDao LoaiChiDao){
            this.mLoaiChiDao = LoaiChiDao;
        }
        @Override
        protected Void doInBackground(LoaiChi... LoaiChis) {
            mLoaiChiDao.insert(LoaiChis[0]);
            return null;
        }
    }
    class DeleteAsyncTask extends AsyncTask<LoaiChi, Void, Void>{
        private LoaiChiDao mLoaiChiDao;
        public DeleteAsyncTask(LoaiChiDao LoaiChiDao){
            this.mLoaiChiDao = LoaiChiDao;
        }
        @Override
        protected Void doInBackground(LoaiChi... LoaiChis) {
            mLoaiChiDao.delete(LoaiChis[0]);
            return null;
        }
    }
    class UpdateAsyncTask extends AsyncTask<LoaiChi, Void, Void>{
        private LoaiChiDao mLoaiChiDao;
        public UpdateAsyncTask(LoaiChiDao LoaiChiDao){
            this.mLoaiChiDao = LoaiChiDao;
        }
        @Override
        protected Void doInBackground(LoaiChi... LoaiChis) {
            mLoaiChiDao.update(LoaiChis[0]);
            return null;
        }
    }
}
