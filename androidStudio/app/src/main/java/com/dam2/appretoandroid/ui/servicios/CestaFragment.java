package com.dam2.appretoandroid.ui.servicios;

import static androidx.compose.ui.semantics.SemanticsPropertiesKt.dismiss;

import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.dam2.appretoandroid.R;
import com.dam2.appretoandroid.SharedViewModel;
import com.dam2.appretoandroid.adapters.CestaAdapter;
import com.dam2.appretoandroid.dialog.CompraDialog;
import com.dam2.appretoandroid.modelo.Cesta;
import com.dam2.appretoandroid.modelo.CestaProducto;
import com.dam2.appretoandroid.modelo.FragmentInterface;

import java.text.DecimalFormat;
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
    private ArrayList<CestaProducto> cestaPrueba;
    private Button btnCompra;


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
        btnCompra=view.findViewById(R.id.btnCompra);

        rvCesta=view.findViewById(R.id.rvCesta);
        rvCesta.setLayoutManager(new LinearLayoutManager(mContext));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(
                rvCesta.getContext(), DividerItemDecoration.VERTICAL);
        rvCesta.addItemDecoration(dividerItemDecoration);

        cestaAdapter=new CestaAdapter(mContext, getParentFragmentManager(), new ArrayList<>(),
                new CestaAdapter.OnRemoveFromCartClickListener() {
                    @Override
                    public void onRemoveFromCartClicked(CestaProducto producto) {
                        sharedViewModel.removeItemFromCart(producto);
                    }
                });
        rvCesta.setAdapter(cestaAdapter);

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
                DecimalFormat numberFormat = new DecimalFormat("#.00");
                tvPrecioTotal.setText(numberFormat.format(aDouble)+ "€");
            }
        });
        btnCompra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CompraDialog compraDialog  = new CompraDialog();
                compraDialog.show(getParentFragmentManager(), "Compra Dialog");


            }
        });



    }

    @Override
    public void onRemoveFromCartClicked(CestaProducto producto) {
        sharedViewModel.removeItemFromCart(producto);
        cestaAdapter.updateItems(mcestaProductos);
    }
}