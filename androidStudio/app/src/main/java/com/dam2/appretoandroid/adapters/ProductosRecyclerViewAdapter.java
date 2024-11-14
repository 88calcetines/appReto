package com.dam2.appretoandroid.adapters;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;
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

public class ProductosRecyclerViewAdapter extends RecyclerView.Adapter<ProductosRecyclerViewAdapter.RecyclerViewHolder>
{
    private ArrayList<Producto> productos;
    private Context mContext;
    private View.OnClickListener onClickListener;
    private FragmentManager fragmentManager;
    private static final String localPath = "androidStudio/app/src/main";
    private AssetManager assetManager;
    private OnAddToCartClickListener onAddToCartClickListener;


    public ProductosRecyclerViewAdapter(Context mContext, FragmentManager fragmentManager, ArrayList<Producto> productos, OnAddToCartClickListener listener) {
        this.mContext = mContext;
        this.fragmentManager = fragmentManager;
        this.productos = productos;
        this.onAddToCartClickListener=listener;
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder
    {
        private final ImageView holderIv;
        private final TextView holderTv;
        private final TextView holderTvValoracion;
        private final ImageButton holderIbCesta;


        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            holderIv=itemView.findViewById(R.id.ivProducto);
            holderTv= itemView.findViewById(R.id.tvProducto);
            holderTvValoracion=itemView.findViewById(R.id.tvValoracion);
            holderIbCesta=itemView.findViewById(R.id.ibBotonProducto);
        }
        public ImageView getHolderIv(){return holderIv;}
        public TextView getHolderTv(){return holderTv;}
        public TextView getHolderTvValoracion(){return holderTvValoracion;}
        public ImageButton getHolderIbCesta(){return holderIbCesta;}
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
         CestaProducto cestaProducto=new CestaProducto(producto.getNombre(),producto.getPrecio(),
                producto.getStock(),producto.getDescripcion(),producto.getImagen(),producto.getValoracion());
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
        holder.holderTvValoracion.setText(producto.getValoracion()+"");
        holder.holderIbCesta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("cesta Producto", cestaProducto+"");
                onAddToCartClickListener.onAddToCartClickListener(cestaProducto);
            }
        });
    }

    @Override
    public int getItemCount() {
        return productos.size();
    }

    public void setProductos(List<Producto> productos)
    {
        this.productos=new ArrayList<>(productos);
    }
    public interface OnAddToCartClickListener {
        void onAddToCartClickListener(CestaProducto producto);
    }


}
