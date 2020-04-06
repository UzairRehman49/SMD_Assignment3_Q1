package com.example.smd_assignment3;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Contact")

public class ContactEntity {


        @PrimaryKey(autoGenerate = true)
        public int uid;

        @ColumnInfo(name = "first_name")
        public String firstName;

        @ColumnInfo(name = "last_name")
        public String lastName;

        @ColumnInfo(name = "Contact No")
        public String Phone;

        @ColumnInfo(name = "Email")
        public String Email;

        @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
        byte[] image;

}
