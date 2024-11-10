package com.dam2.appretoandroid.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dam2.appretoandroid.R;
import com.dam2.appretoandroid.modelo.Producto;

import java.util.ArrayList;
import java.util.List;

public class CestaAdapter extends RecyclerView.Adapter<CestaAdapter.RecyclerViewHolder>
{
    private ArrayList<Producto> cesta;
    private Context mContext;
    private FragmentManager fragmentManager;

    public CestaAdapter(Context mContext, FragmentManager fragmentManager, ArrayList<Producto> productos) {
        this.mContext = mContext;
        this.fragmentManager = fragmentManager;
        this.cesta = productos;
    }
    public static class RecyclerViewHolder extends RecyclerView.ViewHolder
    {

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(mContext);
        View vista = inflater.inflate(R.layout.cesta_list_item,
                parent, false);

        return new RecyclerViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return cesta.size();
    }

    public void setCesta(List<Producto> productos)
    {
        this.cesta=new ArrayList<>(productos);
    }



}
