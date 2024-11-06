package com.dam2.appretoandroid.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.dam2.appretoandroid.R;
import com.squareup.picasso.Picasso;

public class ImagenDialogFragment extends DialogFragment
{
    private String imagen;
    private ImageView ivImagen;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_imagen, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle=getArguments();
        imagen=bundle.getString("Imagen");
        ivImagen=view.findViewById(R.id.ivImagenDialog);
        Picasso.get().load(imagen).into(ivImagen);
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                dismiss();
                return true;
            }
        });
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Dialog dialog=super.onCreateDialog(savedInstanceState);

        dialog.setTitle("Imagen");
        return dialog;
    }
}
