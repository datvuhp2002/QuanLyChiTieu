package com.example.moneymanager.ui.home;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.moneymanager.entity.ThongKeLoaiChi;
import com.example.moneymanager.entity.ThongKeLoaiThu;
import com.example.moneymanager.repository.ChiReponsitory;
import com.example.moneymanager.repository.ThuReponsitory;

import java.util.List;

public class HomeViewModel extends AndroidViewModel {
    private ThuReponsitory mThuReponsitory;
    private ChiReponsitory mChiReponsitory;
    private LiveData<Float> mTongThu;
    private LiveData<Float> mTongChi;

    public HomeViewModel(@NonNull Application application) {
        super(application);
        mThuReponsitory = new ThuReponsitory(application);
        mChiReponsitory = new ChiReponsitory(application);
        mTongThu = mThuReponsitory.sumTongThu();
        mTongChi = mChiReponsitory.sumTongChi();
    }

    public LiveData<Float> getTongThu() {
        return mTongThu;
    }
    public LiveData<Float> getTongChi() {return mTongChi;}
    public void updateTongThu() {
        mTongThu = mThuReponsitory.sumTongThu();
    }
    public void updateTongChi() {
        mTongChi = mChiReponsitory.sumTongChi();
    }

}