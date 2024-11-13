package com.dam2.appretoandroid.ui.galeria;

import android.content.Context;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.dam2.appretoandroid.bbdd.AppDatabase;
import com.dam2.appretoandroid.bbdd.AppExecutors;
import com.dam2.appretoandroid.modelo.ImagenGaleria;

import java.util.List;

public class GaleriaViewModel extends ViewModel {

    private MutableLiveData<List<ImagenGaleria>> mImagenesGaleria;
    private MutableLiveData<Boolean> imagenesExistentes;

    public GaleriaViewModel() {
        mImagenesGaleria= new MutableLiveData<>();
        imagenesExistentes=new MutableLiveData<>();

    }
    public void setmImagenesGaleria(MutableLiveData<List<ImagenGaleria>> imagenesGaleria)
    {
        this.mImagenesGaleria=imagenesGaleria;
    }
    public void cargarImagenes(LifecycleOwner owner, Context context)
    {
        AppDatabase mDb = AppDatabase.getInstance(context);

        mDb.imagenesGaleriaDao().loadImagenes().observe(owner, new Observer<List<ImagenGaleria>>() {
            @Override
            public void onChanged(List<ImagenGaleria> imagenGalerias) {
                if(imagenGalerias.isEmpty())
                {
                    imagenesExistentes.postValue(false);
                }else{
                    mImagenesGaleria.postValue(imagenGalerias);
                }

            }
        });
    }



    public void rellenarImagenesExterior(Context context)
    {


        AppDatabase mDb= AppDatabase.getInstance(context);
        AppExecutors.getInstance().getDiskIp().execute(new Runnable() {
            @Override
            public void run() {
                mDb.imagenesGaleriaDao().insertImagen(new ImagenGaleria("https://drive.google.com/uc?export=download&id=1uc8RhWDwbfA0s6gSz9BhiVqqSCDjiw4u"));
                mDb.imagenesGaleriaDao().insertImagen(new ImagenGaleria("https://drive.google.com/uc?export=download&id=1mcuk911NLJxiPz59mS_QRKiQCbsfi-WM"));
                mDb.imagenesGaleriaDao().insertImagen(new ImagenGaleria("https://drive.google.com/uc?export=download&id=1NJZLovDVj9FJvKqQp3U3rWgNl5Dkg-A9"));
                mDb.imagenesGaleriaDao().insertImagen(new ImagenGaleria("https://drive.google.com/uc?export=download&id=1m0t5lyvtYigtruyk25C8r6wOcLelAJaE"));
                mDb.imagenesGaleriaDao().insertImagen(new ImagenGaleria("https://drive.google.com/uc?export=download&id=1GvwACGFA5efFbtllNXePlOk9_Y6oT1d6"));
                mDb.imagenesGaleriaDao().insertImagen(new ImagenGaleria("https://drive.google.com/uc?export=download&id=1j5nDwQ4hDcL1HumvZuVfaBzaJDK4yoMf"));
                mDb.imagenesGaleriaDao().insertImagen(new ImagenGaleria("https://drive.google.com/uc?export=download&id=1-LVxlctpfE3C5k8Bqycq3QgJGN2wJgzX"));
                mDb.imagenesGaleriaDao().insertImagen(new ImagenGaleria("https://drive.google.com/uc?export=download&id=1eGvxIlYjdWTY-t5wS1Fd35BvAJek4pKw"));
                mDb.imagenesGaleriaDao().insertImagen(new ImagenGaleria("https://drive.google.com/uc?export=download&id=1rbBfWqiZbyiZk2Uk48MRaKM5RiOIsIVF"));
                mDb.imagenesGaleriaDao().insertImagen(new ImagenGaleria("https://drive.google.com/uc?export=download&id=1JE5on7EJ9uPaDYoXp9yXtgVyffkr9nL2"));
            }
        });


    }

    public LiveData<List<ImagenGaleria>> getImagenes(){return mImagenesGaleria;}

    public MutableLiveData<Boolean> getImagenesExistentes() {
        return imagenesExistentes;
    }
}