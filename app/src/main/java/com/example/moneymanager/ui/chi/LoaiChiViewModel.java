package com.example.moneymanager.ui.chi;


import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.moneymanager.entity.LoaiChi;
import com.example.moneymanager.repository.LoaiChiReponsitory;

import java.io.Closeable;
import java.util.List;

public class LoaiChiViewModel extends AndroidViewModel {
    private LoaiChiReponsitory mLoaiChiReponsitory;
    private LiveData<List<LoaiChi>> mAllLoaiChi;
    public LoaiChiViewModel(@NonNull Application application) {
        super(application);
        mLoaiChiReponsitory = new LoaiChiReponsitory(application);
        mAllLoaiChi = mLoaiChiReponsitory.getAllLoaiChi();
    }

    public LiveData<List<LoaiChi>> getAllLoaiChi() {
        return mAllLoaiChi;
    }
    public void insert(LoaiChi LoaiChi){
        mLoaiChiReponsitory.insert(LoaiChi);
    }
    public void delete(LoaiChi LoaiChi){
        mLoaiChiReponsitory.delete(LoaiChi);
    }
    public void update(LoaiChi LoaiChi){
        mLoaiChiReponsitory.update(LoaiChi);
    }
}