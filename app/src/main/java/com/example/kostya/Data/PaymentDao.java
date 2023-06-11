package com.example.kostya.Data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PaymentDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(PaymentRecord paymentRecord);

    @Delete
    void delete(PaymentRecord paymentRecord);

    @Query("SELECT * FROM payment_table ORDER BY id ASC")
    LiveData<List<PaymentRecord>> getAllPayments();

    @Query("SELECT * FROM payment_table ORDER BY id DESC LIMIT 1")
    LiveData<PaymentRecord> getLastPayment();
}
