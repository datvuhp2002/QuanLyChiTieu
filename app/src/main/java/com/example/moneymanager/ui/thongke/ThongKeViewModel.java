package com.example.moneymanager.ui.thongke;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.moneymanager.entity.ThongKeLoaiThu;
import com.example.moneymanager.repository.ThuReponsitory;

import java.util.List;

public class ThongKeViewModel extends AndroidViewModel {
    private ThuReponsitory mThuReponsitory;
    private LiveData<Float> mTongThu;
    private LiveData<List<ThongKeLoaiThu>> mThongKeLoaiThus;
    public ThongKeViewModel(@NonNull Application application) {
        super(application);
        mThuReponsitory =new ThuReponsitory(application);
        mTongThu = mThuReponsitory.sumTongThu();
        mThongKeLoaiThus = mThuReponsitory.sumByLoaiThu();
    }

    public LiveData<Float> getTongThu() {
        return mTongThu;
    }
    public LiveData<List<ThongKeLoaiThu>> getThongKeLoaiThus(){
        return mThongKeLoaiThus;
    }

}
