package com.example.moneymanager.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class LoaiChi {
    @PrimaryKey(autoGenerate = true)
    public int cid;
    @ColumnInfo(name = "ten")
    public String ten;
}
