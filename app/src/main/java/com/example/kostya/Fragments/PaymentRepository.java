package com.example.kostya.Fragments;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.kostya.Data.PaymentDao;
import com.example.kostya.Data.PaymentRecord;

import java.util.List;

public class PaymentRepository {

    private PaymentDao paymentDao;
    private LiveData<List<PaymentRecord>> allPayments;

    PaymentRepository(PaymentDao paymentDao) {
        this.paymentDao = paymentDao;
        allPayments = paymentDao.getAllPayments();
    }

    LiveData<List<PaymentRecord>> getAllPayments() {
        return allPayments;
    }

    LiveData<PaymentRecord> getLastPayment() {
        return paymentDao.getLastPayment();
    }

    void insert(final PaymentRecord paymentRecord) {
        new insertAsyncTask(paymentDao).execute(paymentRecord);
    }

    void delete(final PaymentRecord paymentRecord) {
        new deleteAsyncTask(paymentDao).execute(paymentRecord);
    }

    private static class insertAsyncTask extends AsyncTask<PaymentRecord, Void, Void> {

        private PaymentDao asyncTaskDao;

        insertAsyncTask(PaymentDao dao) {
            asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final PaymentRecord... params) {
            asyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class deleteAsyncTask extends AsyncTask<PaymentRecord, Void, Void> {

        private PaymentDao asyncTaskDao;

        deleteAsyncTask(PaymentDao dao) {
            asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final PaymentRecord... params) {
            asyncTaskDao.delete(params[0]);
            return null;
        }
    }
}
