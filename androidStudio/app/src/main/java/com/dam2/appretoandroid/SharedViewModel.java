package com.dam2.appretoandroid;

import android.view.MenuItem;
import android.view.View;


import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SharedViewModel extends ViewModel {

    private final MutableLiveData<MenuItem> liveView = new MutableLiveData<>();

    public void setMenuItem(MenuItem searchView)
    {
        liveView.setValue(searchView);
    }

    public LiveData<MenuItem> getMenuItem()
    {
        return liveView;
    }
}
