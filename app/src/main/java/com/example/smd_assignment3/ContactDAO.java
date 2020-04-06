package com.example.smd_assignment3;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ContactDAO {

        @Query("SELECT * FROM Contact")
        List<ContactEntity> getAll();
        @Insert
        void insertAll(ContactEntity... users);
}
