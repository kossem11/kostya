package com.example.kostya.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.kostya.R;

public class MenuFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_menu, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ImageButton BtnHome = view.findViewById(R.id.BtnHome);
        ImageButton BtnMenu = view.findViewById(R.id.BtnMenu);
        ImageButton BtnSettings = view.findViewById(R.id.BtnSettings);
        BtnHome.setOnClickListener(viewCreate -> {
            Bundle bundleHome = new Bundle();
            Navigation.findNavController(view).navigate(R.id.action_menuFragment_to_mainFragment, bundleHome);
        });
        BtnSettings.setOnClickListener(viewCreate -> {
            Bundle bundleSettings = new Bundle();
            Navigation.findNavController(view).navigate(R.id.action_menuFragment_to_settingsFragment, bundleSettings);
        });
        BtnMenu.setOnClickListener(viewCreate -> {
            Bundle bundleMenu = new Bundle();
            Navigation.findNavController(view).navigate(R.id.action_menuFragment_self, bundleMenu);
        });
    }
}