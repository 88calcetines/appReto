package com.dam2.appretoandroid.ui.servicios;

import androidx.lifecycle.MutableLiveData;
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

public class DevicesFragment extends Fragment implements ProductosRecyclerViewAdapter.OnAddToCartClickListener {

    private DevicesViewModel mViewModel;
    public static final String ARG_OBJECT = "Devices";
    private RecyclerView rvProductos;
    private Context mContext;
    private Button btnReload;
    private VideojuegosViewModel sharedViewModel;
    private SharedViewModel cestaViewModel;

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
        sharedViewModel=new ViewModelProvider(this).get(VideojuegosViewModel.class);
        cestaViewModel =new ViewModelProvider(this).get(SharedViewModel.class);
        sharedViewModel.setCategoria("Telefonia");

        sharedViewModel.cargarProductos();
        btnReload=view.findViewById(R.id.btnReload);


        rvProductos=view.findViewById(R.id.rvProductos);
        rvProductos.setLayoutManager(new GridLayoutManager(mContext,3));
        ProductosRecyclerViewAdapter adapter= new ProductosRecyclerViewAdapter(mContext,getChildFragmentManager(),new ArrayList<>(),
                new ProductosRecyclerViewAdapter.OnAddToCartClickListener() {
                    @Override
                    public void onAddToCartClickListener(CestaProducto producto) {
                        cestaViewModel.addItemToCart(producto);
                    }
                });
        rvProductos.setAdapter(adapter);

        sharedViewModel.getmProductos().observe(getViewLifecycleOwner(), new Observer<List<Producto>>() {
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