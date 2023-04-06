package com.example.moneymanager.ui.chi;


import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.moneymanager.entity.Chi;
import com.example.moneymanager.repository.ChiReponsitory;

import java.util.List;

public class ChiViewModel extends AndroidViewModel {
    private ChiReponsitory mChiReponsitory;
    private LiveData<List<Chi>> mAllChi;
    public ChiViewModel(@NonNull Application application) {
        super(application);
        mChiReponsitory = new ChiReponsitory(application);
        mAllChi = mChiReponsitory.getAllChi();
    }

    public LiveData<List<Chi>> getAllChi() {
        return mAllChi;
    }
    public void insert(Chi Chi){
        mChiReponsitory.insert(Chi);
    }
    public void delete(Chi Chi){
        mChiReponsitory.delete(Chi);
    }
    public void update(Chi Chi){
        mChiReponsitory.update(Chi);
    }
}