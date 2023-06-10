package com.example.kostya.Fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.kostya.R;


public class SettingsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button BtnProfile = view.findViewById(R.id.BtnProfile);
        Button BtnDevs = view.findViewById(R.id.BtnDevs);
        BtnProfile.setOnClickListener(viewCreate -> {
            Bundle bundleProfile = new Bundle();
            Navigation.findNavController(view).navigate(R.id.action_settingsFragment_to_profileFragment, bundleProfile);
        });
        BtnDevs.setOnClickListener(viewCreate -> {
            Bundle bundleDevs = new Bundle();
            Navigation.findNavController(view).navigate(R.id.action_settingsFragment_to_devsFragment, bundleDevs);
        });
    }
}