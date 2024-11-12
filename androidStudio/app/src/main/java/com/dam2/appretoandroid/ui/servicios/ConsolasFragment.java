package com.dam2.appretoandroid.ui.servicios;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.dam2.appretoandroid.R;
import com.dam2.appretoandroid.SharedViewModel;
import com.dam2.appretoandroid.adapters.ProductosRecyclerViewAdapter;
import com.dam2.appretoandroid.modelo.Producto;

import java.util.ArrayList;
import java.util.List;

public class ConsolasFragment extends Fragment {

    private ConsolasViewModel mViewModel;
    private VideojuegosViewModel sharedViewModel;
    public static final String ARG_OBJECT = "Consolas";
    private RecyclerView rvProductosMejorValorados;
    private RecyclerView rvProductosBaratos;
    private RecyclerView rvProductosRecientes;
    private Context mContext;
    private Button btnReload;

    public static ConsolasFragment newInstance() {
        return new ConsolasFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_consolas, container, false);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.mContext=context;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sharedViewModel=new ViewModelProvider(this).get(VideojuegosViewModel.class);
        sharedViewModel.setCategoria("Consolas");
        btnReload=view.findViewById(R.id.btnReload);
        sharedViewModel.cargarProductos();

        rvProductosMejorValorados=view.findViewById(R.id.rvProductosMejorValorados);
        rvProductosMejorValorados.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL,false));
        ProductosRecyclerViewAdapter adapter= new ProductosRecyclerViewAdapter(mContext,getChildFragmentManager(),new ArrayList<>());
        rvProductosMejorValorados.setAdapter(adapter);

        rvProductosBaratos=view.findViewById(R.id.rvProductosBaratos);
        rvProductosBaratos.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL,false));
        ProductosRecyclerViewAdapter adapterBaratos= new ProductosRecyclerViewAdapter(mContext,getChildFragmentManager(),new ArrayList<>());
        rvProductosBaratos.setAdapter(adapter);

        rvProductosRecientes=view.findViewById(R.id.rvProductosRecientes);
        rvProductosRecientes.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL,false));
        ProductosRecyclerViewAdapter adapterRecientes= new ProductosRecyclerViewAdapter(mContext,getChildFragmentManager(),new ArrayList<>());
        rvProductosRecientes.setAdapter(adapter);

        sharedViewModel.getmProductos().observe(getViewLifecycleOwner(), new Observer<List<Producto>>() {
            @Override
            public void onChanged(List<Producto> productos) {

                adapter.setProductos(productos);
                adapter.notifyDataSetChanged();
                view.findViewById(R.id.loadingPanel).setVisibility(View.GONE);
            }
        });
        sharedViewModel.getmProductosBaratos().observe(getViewLifecycleOwner(), new Observer<List<Producto>>() {
            @Override
            public void onChanged(List<Producto> productos) {
                if(!(productos ==null))
                {
                    adapterBaratos.setProductos(productos);
                    adapterBaratos.notifyDataSetChanged();
                }

                view.findViewById(R.id.loadingPanel).setVisibility(View.GONE);
            }
        });
        sharedViewModel.getmProductosRecientes().observe(getViewLifecycleOwner(), new Observer<List<Producto>>() {
            @Override
            public void onChanged(List<Producto> productos) {
                if(!(productos ==null))
                {
                    adapterRecientes.setProductos(productos);
                    adapterRecientes.notifyDataSetChanged();
                }

                view.findViewById(R.id.loadingPanel).setVisibility(View.GONE);
            }
        });
        sharedViewModel.getLlamadaCorrecta().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(!aBoolean)
                {
                    view.findViewById(R.id.loadingPanel).setVisibility(View.GONE);
                    view.findViewById(R.id.btnReload).setVisibility(View.VISIBLE);

                }
            }
        });
        btnReload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewModel.cargarProductos();
                view.findViewById(R.id.loadingPanel).setVisibility(View.VISIBLE);
                view.findViewById(R.id.btnReload).setVisibility(View.INVISIBLE);

            }
        });
        sharedViewModel.toastMessage.observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(mContext, s, Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
    }
}