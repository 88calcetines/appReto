package com.dam2.appretoandroid.ui.galeria;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dam2.appretoandroid.R;

public class GaleriaInteriorFragment extends Fragment {

    private GaleriaInteriorViewModel mViewModel;
    public static final String ARG_OBJECT = "Interior";

    public static GaleriaInteriorFragment newInstance() {
        return new GaleriaInteriorFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_galeria_interior, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}