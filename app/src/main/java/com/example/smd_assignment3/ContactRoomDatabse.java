package com.example.smd_assignment3;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {ContactEntity.class}, version = 4,exportSchema = false)
public abstract class ContactRoomDatabse extends RoomDatabase {
        public abstract ContactDAO userDao();

}
