package com.dam2.appretoandroid.ui.galeria;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class GaleriaViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public GaleriaViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is notifications fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}