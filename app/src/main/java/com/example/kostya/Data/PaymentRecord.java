package com.example.kostya.Data;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "payment_table")
public class PaymentRecord {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    @ColumnInfo(name = "date")
    private String date;

    @NonNull
    @ColumnInfo(name = "value")
    private String value;

    public PaymentRecord(@NonNull String date, @NonNull String value) {
        this.date = date;
        this.value = value;
    }

    public String getDate() {
        return this.date;
    }

    public String getValue() {
        return this.value;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
