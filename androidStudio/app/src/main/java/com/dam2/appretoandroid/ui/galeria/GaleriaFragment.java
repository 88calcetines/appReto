package com.dam2.appretoandroid.ui.galeria;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.dam2.appretoandroid.R;

import com.dam2.appretoandroid.databinding.FragmentGaleriaBinding;


public class GaleriaFragment extends Fragment {

    private FragmentGaleriaBinding binding;
    private GridView grd_galeria;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        GaleriaViewModel galeriaViewModel =
                new ViewModelProvider(this).get(GaleriaViewModel.class);

        binding = FragmentGaleriaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();



        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);






    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}