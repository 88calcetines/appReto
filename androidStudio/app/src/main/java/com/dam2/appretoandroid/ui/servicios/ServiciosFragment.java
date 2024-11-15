package com.dam2.appretoandroid.ui.servicios;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import com.dam2.appretoandroid.R;
import com.dam2.appretoandroid.SharedViewModel;
import com.dam2.appretoandroid.adapters.ViewPagerAdapter;
import com.dam2.appretoandroid.databinding.FragmentServiciosBinding;
import com.dam2.appretoandroid.modelo.Cesta;
import com.dam2.appretoandroid.modelo.FragmentInterface;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class ServiciosFragment extends Fragment implements FragmentInterface {

    private FragmentServiciosBinding binding;
    private ViewPager2 viewPager;
    private String[] nombreTab=new String[5];
    private ViewPagerAdapter viewPagerAdapter;
    private SharedViewModel sharedViewModel;
    private Cesta cesta;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ServiciosViewModel serviciosViewModel =
                new ViewModelProvider(this).get(ServiciosViewModel.class);

        binding = FragmentServiciosBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        enableSearch(false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        nombreTab[0]="Main";
        nombreTab[1]="Videojuegos";
        nombreTab[2]="Consolas";
        nombreTab[3]="Telefonia";
        nombreTab[4]="Cesta";
        cesta=new Cesta();

        viewPager=view.findViewById(R.id.pager);
        viewPagerAdapter=new ViewPagerAdapter(this);
        viewPager.setAdapter(viewPagerAdapter);
        TabLayout tabLayout=view.findViewById(R.id.tab_layout);

        new TabLayoutMediator(tabLayout,viewPager,(tab, position) -> tab.setText(nombreTab[position])).attach();

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                onTabChanged(position);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    private void enableSearch(boolean state)
    {
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        sharedViewModel.getMenuItem().observe(getViewLifecycleOwner(), new Observer<MenuItem>() {
            @Override
            public void onChanged(MenuItem menuItem) {
                menuItem.setVisible(state);
            }
        });

    }
    private void onTabChanged(int position) {
        // Your function logic for tab change
        Log.d("TabChange", "Switched to tab: " + position);
        switch (position)
        {
            case 0:
                enableSearch(false);
                break;

            case 1:

                enableSearch(true);

                break;
            case 3:
                enableSearch(true);

                break;

            case 4:
               enableSearch(false);
               break;



        }
    }

    @Override
    public Cesta getCesta() {
        return cesta;
    }
}