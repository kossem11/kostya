package com.example.kostya.Fragments;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kostya.Data.PaymentRecord;
import com.example.kostya.R;

import java.util.ArrayList;
import java.util.List;

public class PaymentListAdapter extends RecyclerView.Adapter<PaymentListAdapter.PaymentViewHolder> {
    private List<PaymentRecord> paymentList = new ArrayList<>();

    @NonNull
    @Override
    public PaymentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_value, parent, false);
        return new PaymentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PaymentViewHolder holder, int position) {
        PaymentRecord currentPayment = paymentList.get(position);
        holder.dateTextView.setText(currentPayment.getDate());
        holder.valueTextView.setText(String.valueOf(currentPayment.getValue()));
    }

    @Override
    public int getItemCount() {
        return paymentList.size();
    }

    public void setPayments(List<PaymentRecord> payments) {
        this.paymentList = payments;
        notifyDataSetChanged();
    }

    static class PaymentViewHolder extends RecyclerView.ViewHolder {
        private final TextView dateTextView;
        private final TextView valueTextView;

        private PaymentViewHolder(@NonNull View itemView) {
            super(itemView);
            dateTextView = itemView.findViewById(R.id.date);
            valueTextView = itemView.findViewById(R.id.value);
        }
    }
}
