package com.example.kostya.Splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.kostya.Activity.LogActivity;
import com.example.kostya.Activity.MainActivity;
import com.example.kostya.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Splash extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mAuth = FirebaseAuth.getInstance();

        new Handler().postDelayed(() -> {
            FirebaseUser currentUser = mAuth.getCurrentUser();
            if (currentUser != null){
                startActivity(new Intent(Splash.this, MainActivity.class));
                this.finish();
            }
            else {
                startActivity(new Intent(Splash.this, LogActivity.class));
                this.finish();
            }
        }, 2000);
    }
}