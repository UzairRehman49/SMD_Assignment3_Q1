package com.example.smd_assignment3;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {ContactEntity.class,ContactNum.class}, version = 7,exportSchema = false)
public abstract class ContactRoomDatabse extends RoomDatabase {
        public abstract ContactDAO userDao();
        public abstract ContactNumDAO NumDao();

}
