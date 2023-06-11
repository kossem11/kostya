package com.example.kostya.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.kostya.Data.PaymentRecord;
import com.example.kostya.R;

import java.util.List;

public class MenuFragment extends Fragment {

    private PaymentViewModel paymentViewModel;
    private EditText txtDate;
    private EditText txtValue;
    private Button btnAdd;
    private Button btnGet;
    private RecyclerView recyclerView;
    private PaymentListAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_menu, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Инициализируем Views
        txtDate = view.findViewById(R.id.txtdate);
        txtValue = view.findViewById(R.id.txtvalue);
        btnAdd = view.findViewById(R.id.btnadd);
        btnGet = view.findViewById(R.id.btnget);
        recyclerView = view.findViewById(R.id.recyclerView);

        // Инициализируем RecyclerView и его адаптер
        adapter = new PaymentListAdapter();
        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext());
        layoutManager.setReverseLayout(true); // Элементы будут отображаться в обратном порядке
        layoutManager.setStackFromEnd(true); // Элементы будут начинаться с конца списка
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        // Инициализируем ViewModel и наблюдаем за данными
        paymentViewModel = new ViewModelProvider(this).get(PaymentViewModel.class);
        paymentViewModel.getAllPayments().observe(getViewLifecycleOwner(), paymentRecords -> {
            // Обновляем данные в адаптере, когда данные меняются
            adapter.setPayments(paymentRecords);
        });


        // Настраиваем обработчик нажатия кнопки "Добавить"
        btnAdd.setOnClickListener(v -> {
            String date = txtDate.getText().toString();
            String value = txtValue.getText().toString();

            if (!TextUtils.isEmpty(date) && !TextUtils.isEmpty(value)) {
                PaymentRecord paymentRecord = new PaymentRecord(date, value);
                paymentViewModel.insert(paymentRecord);

                txtDate.setText("");
                txtValue.setText("");
            } else {
                Toast.makeText(requireContext(), "Please fill out all fields", Toast.LENGTH_SHORT).show();
            }
        });

        btnGet.setOnClickListener(v -> {
            paymentViewModel.deleteLastPayment();
        });
    }
}
