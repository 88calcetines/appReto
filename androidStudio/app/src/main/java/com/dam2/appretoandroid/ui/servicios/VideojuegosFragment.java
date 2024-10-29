package com.dam2.appretoandroid.ui.servicios;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dam2.appretoandroid.R;

public class VideojuegosFragment extends Fragment {

    private VideojuegosViewModel mViewModel;
    public static final String ARG_OBJECT = "Videojuegos";

    public static VideojuegosFragment newInstance() {
        return new VideojuegosFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_videojuegos, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(VideojuegosViewModel.class);
        // TODO: Use the ViewModel
    }

}