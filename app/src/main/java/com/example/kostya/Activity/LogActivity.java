package com.example.kostya.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.kostya.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class LogActivity extends AppCompatActivity {

    public EditText input_email2, input_password2;
    public Button signInBtn2;
    public TextView signUpBtn2;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);

        input_email2 = findViewById(R.id.input_email2);
        input_password2 = findViewById(R.id.input_pass2);
        signInBtn2 = findViewById(R.id.signInBtn2);
        signUpBtn2 = findViewById(R.id.signUpBtn2);
        mAuth = FirebaseAuth.getInstance();
        FirebaseDatabase db = FirebaseDatabase.getInstance("https://semenikhin-89088-default-rtdb.europe-west1.firebasedatabase.app/");
        DatabaseReference users = db.getReference("users");

        signInBtn2.setOnClickListener(view -> {
            if (!TextUtils.isEmpty(input_email2.getText().toString()) && !TextUtils.isEmpty(input_password2.getText().toString())) {
                mAuth.signInWithEmailAndPassword(input_email2.getText().toString(), input_password2.getText().toString()).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Intent intentLog = new Intent(LogActivity.this, MainActivity.class);
                        startActivity(intentLog);
                    } else {
                        Toast.makeText(LogActivity.this, "Почта или пароль введены неверно", Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
                Toast.makeText(LogActivity.this, "Заполните все поля", Toast.LENGTH_SHORT).show();
            }
        });

        signUpBtn2.setOnClickListener(view -> {
            Intent intent = new Intent(LogActivity.this, RegActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
            this.finish();
        });
    }
}