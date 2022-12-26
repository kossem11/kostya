package com.example.kostya.Fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.kostya.R;


public class MainFragment extends Fragment   {

    ImageButton imagebutton;
    EditText editText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ImageButton BtnHome = view.findViewById(R.id.BtnHome);
        ImageButton BtnSettings = view.findViewById(R.id.BtnSettings);
        ImageButton BtnMenu = view.findViewById(R.id.BtnMenu);
        BtnHome.setOnClickListener(viewCreate -> {
            Bundle bundleHome = new Bundle();
            Navigation.findNavController(view).navigate(R.id.action_mainFragment_self, bundleHome);
        });
        BtnSettings.setOnClickListener(viewCreate -> {
            Bundle bundleSettings = new Bundle();
            Navigation.findNavController(view).navigate(R.id.action_mainFragment_to_settingsFragment, bundleSettings);
        });
        BtnMenu.setOnClickListener(viewCreate -> {
            Bundle bundleMenu = new Bundle();
            Navigation.findNavController(view).navigate(R.id.action_mainFragment_to_menuFragment, bundleMenu);
        });
    }
}