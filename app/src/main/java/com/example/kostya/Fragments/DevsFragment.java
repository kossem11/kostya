package com.example.kostya.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;


import com.example.kostya.R;

public class DevsFragment extends Fragment {

    WebView webView;

    public DevsFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_devs, container, false);
        webView=view.findViewById(R.id.webview);
        webView.loadUrl("https://github.com/kossem11");

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageButton BtnHome = view.findViewById(R.id.BtnHome);
        ImageButton BtnSettings = view.findViewById(R.id.BtnSettings);
        ImageButton BtnMenu = view.findViewById(R.id.BtnMenu);
        BtnHome.setOnClickListener(viewCreate -> {
            Bundle bundleHome = new Bundle();
            Navigation.findNavController(view).navigate(R.id.action_devsFragment_to_mainFragment, bundleHome);
        });
        BtnMenu.setOnClickListener(viewCreate -> {
            Bundle bundleHome = new Bundle();
            Navigation.findNavController(view).navigate(R.id.action_devsFragment_to_menuFragment, bundleHome);
        });
        BtnSettings.setOnClickListener(viewCreate -> {
            Bundle bundleSettings = new Bundle();
            Navigation.findNavController(view).navigate(R.id.action_devsFragment_to_settingsFragment, bundleSettings);
        });
    }
}