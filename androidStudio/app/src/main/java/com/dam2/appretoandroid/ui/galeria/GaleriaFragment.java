package com.dam2.appretoandroid.ui.galeria;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import com.dam2.appretoandroid.R;
import com.dam2.appretoandroid.adapters.GaleriaTabAdapter;
import com.dam2.appretoandroid.databinding.FragmentGaleriaBinding;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class GaleriaFragment extends Fragment {

    private FragmentGaleriaBinding binding;
    private String[] nombreTab=new String[2];
    private ViewPager2 viewPager;
    private GaleriaTabAdapter galeriaTabAdapter;

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
        nombreTab[0]="Interior";
        nombreTab[1]="Exterior";

        viewPager=view.findViewById(R.id.pager_galeria);
        galeriaTabAdapter=new GaleriaTabAdapter(this);
        viewPager.setAdapter(galeriaTabAdapter);
        TabLayout tabLayout= view.findViewById(R.id.tab_layout_galeria);

        new TabLayoutMediator(tabLayout,viewPager,(tab, position) -> tab.setText(nombreTab[position])).attach();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}