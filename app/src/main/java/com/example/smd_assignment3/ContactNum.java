package com.example.smd_assignment3;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey(entity = ContactEntity.class,
        parentColumns = "uid",
        childColumns = "numId",
        onDelete = ForeignKey.CASCADE))
public class ContactNum {
    @PrimaryKey
    @NonNull
    public String Number;
    @ColumnInfo
    public  long numId;

}
