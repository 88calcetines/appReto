package com.dam2.appretoandroid.ui.servicios;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dam2.appretoandroid.R;
import com.dam2.appretoandroid.adapters.ProductosRecyclerViewAdapter;
import com.dam2.appretoandroid.modelo.Producto;

import java.util.ArrayList;
import java.util.List;

public class DevicesFragment extends Fragment {

    private DevicesViewModel mViewModel;
    public static final String ARG_OBJECT = "Devices";
    private RecyclerView rvProductos;
    private Context mContext;

    public static DevicesFragment newInstance() {
        return new DevicesFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_devices, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel=new ViewModelProvider(this).get(DevicesViewModel.class);
        mViewModel.cargarProductos();
        rvProductos=view.findViewById(R.id.rvProductos);
        GridLayoutManager layoutManager=new GridLayoutManager(mContext,2);
        rvProductos.setLayoutManager(layoutManager);
        ProductosRecyclerViewAdapter adapter= new ProductosRecyclerViewAdapter(mContext,getChildFragmentManager(),new ArrayList<>());
        rvProductos.setAdapter(adapter);
        mViewModel.getmProductos().observe(getViewLifecycleOwner(), new Observer<List<Producto>>() {
            @Override
            public void onChanged(List<Producto> productos) {
                Log.d("Productos", productos.get(1).getDescripcion());
                adapter.setProductos(productos);
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.mContext=context;
    }
}