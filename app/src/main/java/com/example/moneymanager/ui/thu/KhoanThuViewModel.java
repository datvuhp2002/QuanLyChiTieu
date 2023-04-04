package com.example.moneymanager.ui.thu;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.moneymanager.entity.LoaiThu;
import com.example.moneymanager.entity.Thu;
import com.example.moneymanager.repository.LoaiThuReponsitory;
import com.example.moneymanager.repository.ThuReponsitory;

import java.util.List;

public class KhoanThuViewModel extends AndroidViewModel {
    private ThuReponsitory mThuReponsitory;
    private LoaiThuReponsitory mloaiThuReponsitory;
    private LiveData<List<Thu>> mAllThu;
    private LiveData<List<LoaiThu>> mAllLoaiThu;
    public KhoanThuViewModel(@NonNull Application application) {
        super(application);
        mThuReponsitory = new ThuReponsitory(application);
        mAllThu = mThuReponsitory.getAllThu();
        mloaiThuReponsitory = new LoaiThuReponsitory(application);
        mAllLoaiThu = mloaiThuReponsitory.getAllLoaiThu();
    }

    public LiveData<List<Thu>> getAllThu() {
        return mAllThu;
    }
    public LiveData<List<LoaiThu>> getAllLoaiThu(){return mAllLoaiThu;};
    public void insert(Thu thu){
        mThuReponsitory.insert(thu);
    }
    public void delete(Thu thu){
        mThuReponsitory.delete(thu);
    }
    public void update(Thu thu){
        mThuReponsitory.update(thu);
    }
};