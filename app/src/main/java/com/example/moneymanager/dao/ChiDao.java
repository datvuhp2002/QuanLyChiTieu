package com.example.moneymanager.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.moneymanager.entity.ThongKeLoaiChi;
import com.example.moneymanager.entity.Chi;


import java.util.List;

@Dao
public interface ChiDao {
    @Query("Select * from chi")
    LiveData<List<Chi>> findAll();

    @Query("Select sum(sotien) FROM chi")
    LiveData<Float> sumTongChi();

    @Query("Select b.cid, b.ten, sum(a.sotien) as tong FROM chi a INNER JOIN loaichi b on a.lcid = b.cid" + " GROUP BY b.cid, b.ten")
    LiveData<List<ThongKeLoaiChi>> sumByLoaiChi();
    @Insert
    void insert(Chi chi);

    @Update
    void update(Chi chi);

    @Delete
    void delete(Chi chi);
}
