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
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dam2.appretoandroid.R;
import com.dam2.appretoandroid.SharedViewModel;
import com.dam2.appretoandroid.adapters.CestaAdapter;
import com.dam2.appretoandroid.modelo.Cesta;
import com.dam2.appretoandroid.modelo.CestaProducto;
import com.dam2.appretoandroid.modelo.FragmentInterface;

import java.util.ArrayList;
import java.util.List;

public class CestaFragment extends Fragment implements CestaAdapter.OnRemoveFromCartClickListener {

    private CestaViewModel mViewModel;

    private SharedViewModel sharedViewModel;
    public static final String ARG_OBJECT = "Cesta";
    private Cesta cesta;
    private FragmentInterface fragmentInterface;
    private RecyclerView rvCesta;
    private CestaAdapter cestaAdapter;
    private Context mContext;
    private TextView tvPrecioTotal;
    private List<CestaProducto> mcestaProductos;

    public static CestaFragment newInstance() {
        return new CestaFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_cesta, container, false);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        fragmentInterface= (FragmentInterface) getParentFragment();
        this.mContext=context;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        cesta= fragmentInterface.getCesta();
        sharedViewModel=new ViewModelProvider(this).get(SharedViewModel.class);
        tvPrecioTotal=view.findViewById(R.id.tvPrecioTotal);

        rvCesta=view.findViewById(R.id.rvCesta);
        rvCesta.setLayoutManager(new LinearLayoutManager(getContext()));
        cestaAdapter=new CestaAdapter(mContext, getParentFragmentManager(), new ArrayList<>());

        sharedViewModel.getCartItems().observe(getViewLifecycleOwner(), new Observer<List<CestaProducto>>() {
            @Override
            public void onChanged(List<CestaProducto> cestaProductos) {
                mcestaProductos=cestaProductos;
                cestaAdapter.updateItems(mcestaProductos);
            }
        });
        sharedViewModel.getTotalPrice().observe(getViewLifecycleOwner(), new Observer<Double>() {
            @Override
            public void onChanged(Double aDouble) {
                tvPrecioTotal.setText(aDouble + "");
            }
        });



    }

    @Override
    public void onRemoveFromCartClicked(CestaProducto producto) {
        sharedViewModel.removeItemFromCart(producto);
        cestaAdapter.updateItems(mcestaProductos);
    }
}