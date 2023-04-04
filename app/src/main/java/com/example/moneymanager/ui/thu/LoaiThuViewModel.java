package com.example.moneymanager.ui.thu;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.moneymanager.entity.LoaiThu;
import com.example.moneymanager.repository.LoaiThuReponsitory;

import java.util.List;

public class LoaiThuViewModel extends AndroidViewModel {
    private LoaiThuReponsitory mLoaiThuReponsitory;
    private LiveData<List<LoaiThu>> mAllLoaiThu;
    public LoaiThuViewModel(@NonNull Application application) {
        super(application);
        mLoaiThuReponsitory = new LoaiThuReponsitory(application);
        mAllLoaiThu = mLoaiThuReponsitory.getAllLoaiThu();
    }

    public LiveData<List<LoaiThu>> getAllLoaiThu() {
        return mAllLoaiThu;
    }
    public void insert(LoaiThu loaiThu){
        mLoaiThuReponsitory.insert(loaiThu);
    }
    public void delete(LoaiThu loaiThu){
        mLoaiThuReponsitory.delete(loaiThu);
    }
    public void update(LoaiThu loaithu){
        mLoaiThuReponsitory.update(loaithu);
    }
}