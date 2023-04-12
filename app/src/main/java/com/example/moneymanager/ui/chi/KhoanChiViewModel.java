package com.example.moneymanager.ui.chi;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.moneymanager.entity.LoaiChi;
import com.example.moneymanager.entity.Chi;
import com.example.moneymanager.repository.LoaiChiReponsitory;
import com.example.moneymanager.repository.ChiReponsitory;

import java.io.Closeable;
import java.util.List;

public class KhoanChiViewModel extends AndroidViewModel {
        private ChiReponsitory mChiReponsitory;
        private LoaiChiReponsitory mLoaiChiReponsitory;


    private LiveData<List<Chi>> mAllChi;
        private LiveData<List<LoaiChi>> mAllLoaiChi;
    private LiveData<Float> mTongChi;

    public KhoanChiViewModel(@NonNull Application application) {
            super( application);
            mChiReponsitory = new ChiReponsitory(application);
            mAllChi = mChiReponsitory.getAllChi();
            mLoaiChiReponsitory = new LoaiChiReponsitory(application);
            mAllLoaiChi = mLoaiChiReponsitory.getAllLoaiChi();
            mTongChi = mChiReponsitory.sumTongChi();
        }

        public LiveData<List<Chi>> getAllChi() {
            return mAllChi;
        }
        public LiveData<List<LoaiChi>> getAllLoaiChi(){return mAllLoaiChi;};
        public LiveData<Float> getTongChi() {
                return  mTongChi;

        }

        public void insert(Chi Chi){
            mChiReponsitory.insert(Chi);
        }
        public void delete(Chi Chi){
            mChiReponsitory.delete(Chi);
        }
        public void update(Chi Chi) {
            mChiReponsitory.update(Chi);
        }
    };