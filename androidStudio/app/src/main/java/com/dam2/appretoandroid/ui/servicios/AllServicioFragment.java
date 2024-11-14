package com.dam2.appretoandroid.ui.servicios;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.dam2.appretoandroid.R;
import com.dam2.appretoandroid.SharedViewModel;
import com.dam2.appretoandroid.adapters.ProductosRecyclerViewAdapter;
import com.dam2.appretoandroid.modelo.CestaProducto;
import com.dam2.appretoandroid.modelo.Producto;

import java.util.ArrayList;
import java.util.List;

public class AllServicioFragment extends Fragment implements ProductosRecyclerViewAdapter.OnAddToCartClickListener {

    private AllServicioViewModel mViewModel;
    public static final String ARG_OBJECT = "Videojuegos";
    private Context mContext;
    private SharedViewModel cestaViewModel;
    private VideojuegosViewModel sharedViewModel;
    private RecyclerView rvProductosMejorValorados;
    private RecyclerView rvProductosRecientes;
    private Button btnReload;


    public static AllServicioFragment newInstance() {
        return new AllServicioFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_all_servicio, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel=new ViewModelProvider(this).get(AllServicioViewModel.class);
        sharedViewModel =new ViewModelProvider(this).get(VideojuegosViewModel.class);
        cestaViewModel =new ViewModelProvider(this).get(SharedViewModel.class);
        sharedViewModel.cargarProductos();

        rvProductosMejorValorados=view.findViewById(R.id.rvProductosMejorValorados);
        rvProductosMejorValorados.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL,false));
        ProductosRecyclerViewAdapter adapter= new ProductosRecyclerViewAdapter(mContext,getChildFragmentManager(),new ArrayList<>(),
                new ProductosRecyclerViewAdapter.OnAddToCartClickListener() {
                    @Override
                    public void onAddToCartClickListener(CestaProducto producto) {
                        cestaViewModel.addItemToCart(producto);
                    }
                });
        rvProductosMejorValorados.setAdapter(adapter);


        rvProductosRecientes=view.findViewById(R.id.rvProductosRecientes);
        rvProductosRecientes.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL,false));
        ProductosRecyclerViewAdapter adapterRecientes= new ProductosRecyclerViewAdapter(mContext,getChildFragmentManager(),new ArrayList<>(),
                new ProductosRecyclerViewAdapter.OnAddToCartClickListener() {
                    @Override
                    public void onAddToCartClickListener(CestaProducto producto) {

                    }
                });
        rvProductosRecientes.setAdapter(adapter);
        btnReload=view.findViewById(R.id.btnReload);

        sharedViewModel.getmProductosMejorValorados().observe(getViewLifecycleOwner(), new Observer<List<Producto>>() {
            @Override
            public void onChanged(List<Producto> productos) {


                if(!(productos ==null))
                {
                    adapter.setProductos(productos);
                    adapter.notifyDataSetChanged();
                }

                view.findViewById(R.id.loadingPanel).setVisibility(View.GONE);
                view.findViewById(R.id.btnReload).setVisibility(View.INVISIBLE);
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
                view.findViewById(R.id.btnReload).setVisibility(View.INVISIBLE);
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
                sharedViewModel.cargarProductos();
                view.findViewById(R.id.loadingPanel).setVisibility(View.VISIBLE);
                view.findViewById(R.id.btnReload).setVisibility(View.INVISIBLE);

            }
        });



    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.mContext=context;
    }

    @Override
    public void onAddToCartClickListener(CestaProducto producto) {

    }
}