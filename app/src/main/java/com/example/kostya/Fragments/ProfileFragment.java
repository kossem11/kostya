package com.example.kostya.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.kostya.Activity.LogActivity;
import com.example.kostya.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileFragment extends Fragment {
    public Button signOut;
    private FirebaseAuth mAuth;
    private FirebaseDatabase db;
    private DatabaseReference myRef;
    TextView nameProf , emailProf;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance("https://semenikhin-89088-default-rtdb.europe-west1.firebasedatabase.app/");
        myRef = db.getReference();
        FirebaseUser user = mAuth.getCurrentUser();
        nameProf = view.findViewById(R.id.nameProf);
        emailProf = view.findViewById(R.id.emailProf);
        //Button
        signOut = view.findViewById(R.id.signOutButton);
        signOut.setOnClickListener(view12 -> {
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(getContext(), LogActivity.class);
            startActivity(intent);
        });

        myRef.child("Users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                assert user != null;
                Object nameFrom = snapshot.child(user.getUid()).child("name").getValue();
                nameProf.setText(nameFrom.toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        myRef.child("Users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Object emailFrom = snapshot.child(user.getUid()).child("email").getValue();
                emailProf.setText(emailFrom.toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ImageButton BtnHome = view.findViewById(R.id.BtnHome);
        ImageButton BtnSettings = view.findViewById(R.id.BtnSettings);
        ImageButton BtnMenu = view.findViewById(R.id.BtnMenu);
        Button BtnPrivacy = view.findViewById(R.id.BtnPrivacy);
        BtnHome.setOnClickListener(viewCreate -> {
            Bundle bundleHome = new Bundle();
            Navigation.findNavController(view).navigate(R.id.action_profileFragment_to_mainFragment, bundleHome);
        });
        BtnSettings.setOnClickListener(viewCreate -> {
            Bundle bundleSettings = new Bundle();
            Navigation.findNavController(view).navigate(R.id.action_profileFragment_to_settingsFragment, bundleSettings);
        });
        BtnMenu.setOnClickListener(viewCreate -> {
            Bundle bundleMenu = new Bundle();
            Navigation.findNavController(view).navigate(R.id.action_profileFragment_to_menuFragment, bundleMenu);
        });
        BtnPrivacy.setOnClickListener(viewCreate -> {
            Bundle bundlePrivacy = new Bundle();
            Navigation.findNavController(view).navigate(R.id.action_profileFragment_to_privacyFragment, bundlePrivacy);
        });
    }
}