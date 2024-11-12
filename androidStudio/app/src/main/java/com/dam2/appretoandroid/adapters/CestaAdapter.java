package com.dam2.appretoandroid.adapters;

import android.content.Context;
import android.content.res.AssetManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dam2.appretoandroid.R;
import com.dam2.appretoandroid.modelo.CestaProducto;
import com.dam2.appretoandroid.modelo.Producto;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class CestaAdapter extends RecyclerView.Adapter<CestaAdapter.RecyclerViewHolder>
{
    private ArrayList<CestaProducto> cesta;
    private Context mContext;
    private FragmentManager fragmentManager;
    private AssetManager assetManager;
    private OnRemoveFromCartClickListener onRemoveFromCartClickListener;


    public CestaAdapter(Context mContext, FragmentManager fragmentManager, ArrayList<CestaProducto> productos) {
        this.mContext = mContext;
        this.fragmentManager = fragmentManager;
        this.cesta = productos;
    }
    public static class RecyclerViewHolder extends RecyclerView.ViewHolder
    {
        private final ImageView holderIv;
        private final TextView holderTv;
        private final ImageButton holderIb;



        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            holderIv=itemView.findViewById(R.id.ivProductoCesta);
            holderTv=itemView.findViewById(R.id.tvNombreProductoCesta);
            holderIb=itemView.findViewById(R.id.ibBotonProductoCesta);

        }
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(mContext);
        View vista = inflater.inflate(R.layout.cesta_list_item,
                parent, false);
        assetManager= mContext.getAssets();


        return new RecyclerViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {

        CestaProducto producto = cesta.get(position);
        holder.holderTv.setText(producto.getNombre());
        try {
            String imagepath=producto.getImagen().substring("assets/".length());
            if (imagepath.startsWith("/")) {
                imagepath = imagepath.substring(1);
            }
            InputStream inputStream = assetManager.open(imagepath);
            /*if(imagepath.equals("silenthill2.png")&&imagepath.equals("rdr2.png"))
            {
                continue;
            }*/

            Picasso.get().load(android.net.Uri.parse("file:///android_asset/" + imagepath)).into(holder.holderIv);
        } catch (IOException e) {
            return;
            //throw new RuntimeException(e);
        }
        holder.holderIb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRemoveFromCartClickListener.onRemoveFromCartClicked(producto);

            }
        });
    }

    @Override
    public int getItemCount() {
        return cesta.size();
    }
    public void updateItems(List<CestaProducto> productos) {
        setCesta(productos);
        notifyDataSetChanged(); // Notify the adapter that the data has changed
    }

    public void setCesta(List<CestaProducto> productos)
    {
        this.cesta=new ArrayList<>(productos);
    }

    public interface OnRemoveFromCartClickListener {
        void onRemoveFromCartClicked(CestaProducto producto);
    }



}
