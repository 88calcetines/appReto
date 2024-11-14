package com.dam2.appretoandroid.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.FragmentManager;

import com.dam2.appretoandroid.R;
import com.dam2.appretoandroid.dialog.ImagenDialogFragment;
import com.dam2.appretoandroid.modelo.ImagenGaleria;
import com.dam2.appretoandroid.ui.galeria.GaleriaFragment;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class GaleriaAdapter extends BaseAdapter {

    private ArrayList<ImagenGaleria> imagenesArray;
    private Context mContext;
    private GaleriaFragment galeriaFragment;
    private FragmentManager fragmentManager;

    public GaleriaAdapter(Context mContext, ArrayList<ImagenGaleria> imagenesArray, FragmentManager fragmentManager) {
        this.mContext = mContext;
        this.imagenesArray=imagenesArray;
        this.fragmentManager=fragmentManager;
    }

    @Override
    public int getCount() {
        return imagenesArray.size();
    }

    @Override
    public Object getItem(int position) {
        return imagenesArray.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.grd_galeria_item,parent,false);
        ImageView ivFoto = view.findViewById(R.id.ivFoto);
        Picasso.get().load(imagenesArray.get(position).getFoto()).resize(800,600)
                .centerCrop().into(ivFoto);
        ivFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("Imagen",imagenesArray.get(position).getFoto());
                ImagenDialogFragment imagenDialogFragment = new ImagenDialogFragment();

                imagenDialogFragment.setArguments(bundle);
                imagenDialogFragment.show(fragmentManager, "Imagen");


            }
        });
        return view;
    }
    public void setImagenesArray(List<ImagenGaleria> imagenesArray){this.imagenesArray = new ArrayList<>(imagenesArray);}
}
