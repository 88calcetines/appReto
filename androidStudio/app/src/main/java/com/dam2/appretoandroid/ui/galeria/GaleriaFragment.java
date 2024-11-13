package com.dam2.appretoandroid.ui.galeria;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.dam2.appretoandroid.R;

import com.dam2.appretoandroid.SharedViewModel;
import com.dam2.appretoandroid.adapters.GaleriaAdapter;
import com.dam2.appretoandroid.databinding.FragmentGaleriaBinding;
import com.dam2.appretoandroid.modelo.ImagenGaleria;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class GaleriaFragment extends Fragment {

    private FragmentGaleriaBinding binding;
    private GridView grd_galeria;
    private Context mContext;
    private GaleriaViewModel galeriaViewModel;
    private SharedViewModel sharedViewModel;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        GaleriaViewModel galeriaViewModel =
                new ViewModelProvider(this).get(GaleriaViewModel.class);

        binding = FragmentGaleriaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();



        return root;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.mContext=context;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        enableSearch(false);
        galeriaViewModel=new ViewModelProvider(this).get(GaleriaViewModel.class);
        galeriaViewModel.rellenarImagenesExterior(mContext);
        grd_galeria=view.findViewById(R.id.grd_galeria);
        GaleriaAdapter galeriaAdapter = new GaleriaAdapter(mContext, new ArrayList<ImagenGaleria>(), getChildFragmentManager());
        grd_galeria.setAdapter(galeriaAdapter);
        galeriaViewModel.getImagenes().observe(getViewLifecycleOwner(), new Observer<List<ImagenGaleria>>() {
            @Override
            public void onChanged(List<ImagenGaleria> imagenGalerias) {
                galeriaAdapter.setImagenesArray(imagenGalerias);
                galeriaAdapter.notifyDataSetChanged();
            }
        });
        galeriaViewModel.cargarImagenes(getViewLifecycleOwner(),mContext);








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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}