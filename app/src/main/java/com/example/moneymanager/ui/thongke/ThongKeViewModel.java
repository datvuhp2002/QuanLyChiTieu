package com.example.moneymanager.ui.thongke;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.moneymanager.entity.ThongKeLoaiChi;
import com.example.moneymanager.entity.ThongKeLoaiThu;
import com.example.moneymanager.repository.ChiReponsitory;
import com.example.moneymanager.repository.ThuReponsitory;

import java.util.List;

public class ThongKeViewModel extends AndroidViewModel {
    private ThuReponsitory mThuReponsitory;
    private ChiReponsitory mChiReponsitory;
    private LiveData<Float> mTongThu;
    private LiveData<Float> mTongChi;

    private LiveData<List<ThongKeLoaiThu>> mThongKeLoaiThus;
    private LiveData<List<ThongKeLoaiChi>> mThongKeLoaiChis;
    public ThongKeViewModel(@NonNull Application application) {
        super(application);
        mThuReponsitory = new ThuReponsitory(application);
        mChiReponsitory = new ChiReponsitory(application);
        mTongThu = mThuReponsitory.sumTongThu();
        mTongChi = mChiReponsitory.sumTongChi();
        mThongKeLoaiThus = mThuReponsitory.sumByLoaiThu();
        mThongKeLoaiChis = mChiReponsitory.sumByLoaiChi();
    }

    public LiveData<Float> getTongThu() {
        return mTongThu;
    }
    public LiveData<Float> getTongChi() {return mTongChi;}
    public LiveData<List<ThongKeLoaiThu>> getThongKeLoaiThus(){
        return mThongKeLoaiThus;
    }
    public LiveData<List<ThongKeLoaiChi>> getThongKeLoaiChis(){
        return mThongKeLoaiChis;
    }

}
