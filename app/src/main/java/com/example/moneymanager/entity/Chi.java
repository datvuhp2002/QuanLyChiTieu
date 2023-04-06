package com.example.moneymanager.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Chi {
    @PrimaryKey(autoGenerate = true)
    public int cid;
    @ColumnInfo(name = "lcid")
    public int lcid;
    @ColumnInfo(name = "ten")
    public String ten;
    @ColumnInfo(name = "sotien")
    public float sotien;
    @ColumnInfo(name = "ghi chu")
    public String ghichu;
}
