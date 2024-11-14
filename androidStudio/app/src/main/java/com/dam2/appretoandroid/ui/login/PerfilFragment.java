package com.dam2.appretoandroid.ui.login;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dam2.appretoandroid.R;
import com.dam2.appretoandroid.modelo.Usuario;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;

public class PerfilFragment extends Fragment {

    private PerfilViewModel mViewModel;
    private Context context;
    private Usuario mUsuario;
    private TextView tvNombrePerfil;
    private TextView tvApellido1Perfil;
    private TextView tvApellido2Perfil;
    private TextView tvEmailPerfil;
    private TextView tvBienvenidoPerfil;
    private TextView tvUsuarioPerfil;
    private ImageView ivFotoPerfl;
    private AssetManager assetManager;

    public static PerfilFragment newInstance() {
        return new PerfilFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_perfil, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mViewModel=new ViewModelProvider(requireParentFragment()).get(PerfilViewModel.class);
        mViewModel.getPerfil();
        initializeUi(view);
        mViewModel.getUsuario().observe(getViewLifecycleOwner(), new Observer<Usuario>() {
            @Override
            public void onChanged(Usuario usuario) {
                mUsuario=usuario;
                loadUi();
            }
        });
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context=context;
        assetManager= context.getAssets();
    }

    public void initializeUi(View view )
    {
        tvNombrePerfil=view.findViewById(R.id.tvNombrePerfil);
        tvApellido1Perfil=view.findViewById(R.id.tvApellido1Perfil);
        tvApellido2Perfil=view.findViewById(R.id.tvApellido2Perfil);
        tvEmailPerfil=view.findViewById(R.id.tvEmailPerfil);
        tvBienvenidoPerfil=view.findViewById(R.id.tvBienvenidoPerfil);
        ivFotoPerfl=view.findViewById(R.id.ivFotoPerfil);
        tvUsuarioPerfil=view.findViewById(R.id.tvUsuarioPerfil);


    }
    public void loadUi()
    {
        tvNombrePerfil.setText(mUsuario.getNombre());
        tvApellido1Perfil.setText(mUsuario.getApellido1());
        tvApellido2Perfil.setText(mUsuario.getApellido2());
        tvEmailPerfil.setText(mUsuario.getEmail());
        tvBienvenidoPerfil.setText("Bienvenido: "+mUsuario.getNombre());
        tvUsuarioPerfil.setText(mUsuario.getNombre());
        try {
            String imagepath=mUsuario.getImagen().substring("assets/".length());
            if (imagepath.startsWith("/")) {
                imagepath = imagepath.substring(1);
            }
            InputStream inputStream = assetManager.open(imagepath);
            /*if(imagepath.equals("silenthill2.png")&&imagepath.equals("rdr2.png"))
            {
                continue;
            }*/

            Picasso.get().load(android.net.Uri.parse("file:///android_asset/" + imagepath)).into(ivFotoPerfl);
        } catch (IOException e) {
            return;
            //throw new RuntimeException(e);
        }


    }
}