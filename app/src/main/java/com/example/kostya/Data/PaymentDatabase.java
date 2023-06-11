package com.example.kostya.Data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {PaymentRecord.class}, version = 1, exportSchema = false)
public abstract class PaymentDatabase extends RoomDatabase {

    public abstract PaymentDao paymentDao();

    private static volatile PaymentDatabase INSTANCE;

    public static PaymentDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (PaymentDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    PaymentDatabase.class, "payment_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
