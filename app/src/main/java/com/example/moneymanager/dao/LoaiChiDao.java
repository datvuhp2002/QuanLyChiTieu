package com.example.moneymanager.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.moneymanager.entity.LoaiChi;

import java.util.List;

@Dao
public interface LoaiChiDao {
    @Query("Select * FROM loaichi")
    LiveData<List<LoaiChi>> findAll();

    @Insert
    void insert(LoaiChi loaiChi);

    @Update
    void update(LoaiChi loaiChi);

    @Delete
    void delete(LoaiChi loaiChi);
}
