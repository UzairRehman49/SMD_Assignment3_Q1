package com.example.smd_assignment3;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;
@Dao
public interface ContactNumDAO {
    @Query("SELECT * FROM ContactNum where numId=:id")
    List<ContactNum> getAll(long id);
    @Insert
    void insertAll(ContactNum ... number);
}
