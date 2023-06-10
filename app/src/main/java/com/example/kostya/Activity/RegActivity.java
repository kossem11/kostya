package com.example.kostya.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.kostya.Data.User;
import com.example.kostya.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;

public class RegActivity extends AppCompatActivity {

    public EditText input_email, input_password, input_name;
    public Button signUpBtn;
    public TextView signInBtn;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;
    private DocumentReference users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);

        input_email = findViewById(R.id.input_email);
        input_name = findViewById(R.id.input_name);
        input_password = findViewById(R.id.input_pass);
        signUpBtn = findViewById(R.id.signUpBtn);
        signInBtn = findViewById(R.id.signInBtn);
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        users = db.collection("Users").document();

        signUpBtn.setOnClickListener(view -> {
            if(!TextUtils.isEmpty(input_email.getText().toString()) && !TextUtils.isEmpty(input_password.getText().toString()) && !TextUtils.isEmpty(input_name.getText().toString())){
                mAuth.createUserWithEmailAndPassword(input_email.getText().toString(), input_password.getText().toString()).addOnCompleteListener(task -> {
                    if (task.isSuccessful()){
                        FirebaseUser firebaseUser = mAuth.getCurrentUser();
                        User user = new User();
                        user.setEmail(input_email.getText().toString());
                        user.setName(input_name.getText().toString());
                        user.setPassword(input_password.getText().toString());
                        if (firebaseUser != null) {
                            db.collection("Users").document(firebaseUser.getUid()).set(user);
                            Toast.makeText(RegActivity.this, "Регистрация пройдена", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(RegActivity.this, "Ошибка создания пользователя", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(RegActivity.this, "Ошибка", Toast.LENGTH_SHORT).show();
                    }
                });

            }
            else {
                Toast.makeText(RegActivity.this, "Заполните все поля", Toast.LENGTH_SHORT).show();
            }
        });

        signInBtn.setOnClickListener(view -> {
            Intent intent = new Intent(RegActivity.this, LogActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
            this.finish();
        });
    }
}
