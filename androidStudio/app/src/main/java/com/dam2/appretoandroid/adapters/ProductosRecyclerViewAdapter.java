package com.dam2.appretoandroid.adapters;

import android.content.Context;
import android.content.res.AssetManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dam2.appretoandroid.R;
import com.dam2.appretoandroid.modelo.Producto;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ProductosRecyclerViewAdapter extends RecyclerView.Adapter<ProductosRecyclerViewAdapter.RecyclerViewHolder>
{
    private ArrayList<Producto> productos;
    private Context mContext;
    private View.OnClickListener onClickListener;
    private FragmentManager fragmentManager;
    private static final String localPath = "androidStudio/app/src/main";
    private AssetManager assetManager;

    public ProductosRecyclerViewAdapter(Context mContext, FragmentManager fragmentManager, ArrayList<Producto> productos) {
        this.mContext = mContext;
        this.fragmentManager = fragmentManager;
        this.productos = productos;
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder
    {
        private final ImageView holderIv;
        private final TextView holderTv;


        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            holderIv=itemView.findViewById(R.id.ivProducto);
            holderTv= itemView.findViewById(R.id.tvProducto);
        }
        public ImageView getHolderIv(){return holderIv;}
        public TextView getHolderTv(){return holderTv;}
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(mContext);
        View vista = inflater.inflate(R.layout.producto_item_layout,
                parent, false);
        assetManager= mContext.getAssets();

        return new RecyclerViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {


        Producto producto=productos.get(position);
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


        holder.holderTv.setText(producto.getNombre());
    }

    @Override
    public int getItemCount() {
        return productos.size();
    }

    public void setProductos(List<Producto> productos)
    {
        this.productos=new ArrayList<>(productos);
    }


}
