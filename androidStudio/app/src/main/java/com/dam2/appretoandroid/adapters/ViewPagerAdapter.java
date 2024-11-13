package com.dam2.appretoandroid.adapters;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.dam2.appretoandroid.ui.servicios.AllServicioFragment;
import com.dam2.appretoandroid.ui.servicios.CestaFragment;
import com.dam2.appretoandroid.ui.servicios.ConsolasFragment;
import com.dam2.appretoandroid.ui.servicios.DevicesFragment;
import com.dam2.appretoandroid.ui.servicios.ServiciosFragment;
import com.dam2.appretoandroid.ui.servicios.VideojuegosFragment;

public class ViewPagerAdapter extends FragmentStateAdapter {

    public ViewPagerAdapter(@NonNull ServiciosFragment fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment = null;
        switch (position)
        {
            case 0:
                fragment = new AllServicioFragment();
                Bundle bundleAllServicios= new Bundle();
                bundleAllServicios.putInt(VideojuegosFragment.ARG_OBJECT, position+1);
                bundleAllServicios.putString("nombreTag", "AllServicios");
                fragment.setArguments(bundleAllServicios);

                break;

            case 1:
                fragment = new VideojuegosFragment();
                Bundle bundleVideojuegos= new Bundle();
                bundleVideojuegos.putInt(VideojuegosFragment.ARG_OBJECT, position+1);
                bundleVideojuegos.putString("nombreTag", "Videojuegos");
                fragment.setArguments(bundleVideojuegos);

                break;
            case 2:
                fragment = new ConsolasFragment();
                Bundle bundleConsolas= new Bundle();
                bundleConsolas.putInt(ConsolasFragment.ARG_OBJECT, position+1);
                bundleConsolas.putString("nombreTag", "Consolas");
                fragment.setArguments(bundleConsolas);
                break;
            case 3:
                fragment = new DevicesFragment();
                Bundle bundleDevices= new Bundle();
                bundleDevices.putInt(DevicesFragment.ARG_OBJECT, position+1);
                bundleDevices.putString("nombreTag", "Devices");
                fragment.setArguments(bundleDevices);
                break;
            case 4:
                fragment = new CestaFragment();
                Bundle bundleCesta= new Bundle();
                bundleCesta.putInt(CestaFragment.ARG_OBJECT, position+1);
                bundleCesta.putString("nombreTag", "Cesta");
                fragment.setArguments(bundleCesta);
                break;



        }

        return fragment;
    }

    @Override
    public int getItemCount() {
        return 5;
    }
}
