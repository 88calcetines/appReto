package com.dam2.appretoandroid.adapters;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.dam2.appretoandroid.ui.galeria.GaleriaExteriorFragment;
import com.dam2.appretoandroid.ui.galeria.GaleriaInteriorFragment;


public class GaleriaTabAdapter extends FragmentStateAdapter {

    public GaleriaTabAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment = null;
        switch (position)
        {
            case 0:
                fragment = new GaleriaInteriorFragment();
                Bundle bundleInterior= new Bundle();
                bundleInterior.putInt(GaleriaInteriorFragment.ARG_OBJECT, position+1);
                bundleInterior.putString("nombreTag", "Interior");
                fragment.setArguments(bundleInterior);

                break;
            case 1:
                fragment = new GaleriaExteriorFragment();
                Bundle bundleExterior= new Bundle();
                bundleExterior.putInt(GaleriaExteriorFragment.ARG_OBJECT, position+1);
                bundleExterior.putString("nombreTag", "Exterior");
                fragment.setArguments(bundleExterior);
                break;




        }

        return fragment;
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
