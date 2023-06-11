package com.example.kostya.Fragments;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.example.kostya.Data.PaymentDao;
import com.example.kostya.Data.PaymentDatabase;
import com.example.kostya.Data.PaymentRecord;

import java.util.List;

public class PaymentViewModel extends AndroidViewModel {

    private final PaymentRepository repository;
    private final LiveData<List<PaymentRecord>> allPayments;

    public PaymentViewModel(@NonNull Application application) {
        super(application);
        PaymentDao dao = PaymentDatabase.getDatabase(application).paymentDao();
        repository = new PaymentRepository(dao);
        allPayments = repository.getAllPayments();
    }

    LiveData<List<PaymentRecord>> getAllPayments() { return allPayments; }

    public void insert(PaymentRecord paymentRecord) { repository.insert(paymentRecord); }

    public void deleteLastPayment() {
        LiveData<PaymentRecord> lastPaymentLiveData = repository.getLastPayment();
        Observer<PaymentRecord> observer = new Observer<PaymentRecord>() {
            @Override
            public void onChanged(PaymentRecord paymentRecord) {
                if (paymentRecord != null) {
                    repository.delete(paymentRecord);
                    lastPaymentLiveData.removeObserver(this); //отписка после удаления
                }
            }
        };
        lastPaymentLiveData.observeForever(observer);
    }

}