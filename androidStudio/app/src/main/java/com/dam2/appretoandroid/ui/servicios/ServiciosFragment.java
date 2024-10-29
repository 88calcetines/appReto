package com.dam2.appretoandroid.ui.servicios;

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
import com.dam2.appretoandroid.adapters.ViewPagerAdapter;
import com.dam2.appretoandroid.databinding.FragmentServiciosBinding;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class ServiciosFragment extends Fragment {

    private FragmentServiciosBinding binding;
    private ViewPager2 viewPager;
    private String[] nombreTab=new String[4];
    private ViewPagerAdapter viewPagerAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ServiciosViewModel serviciosViewModel =
                new ViewModelProvider(this).get(ServiciosViewModel.class);

        binding = FragmentServiciosBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        nombreTab[0]="VideoJuegos";
        nombreTab[1]="Consoloas";
        nombreTab[2]="Smartphones Tablets";
        nombreTab[3]="Cesta";

        viewPager=view.findViewById(R.id.pager);
        viewPagerAdapter=new ViewPagerAdapter(this);
        TabLayout tabLayout=view.findViewById(R.id.tab_layout);

        new TabLayoutMediator(tabLayout,viewPager,(tab, position) -> tab.setText(nombreTab[position])).attach();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}