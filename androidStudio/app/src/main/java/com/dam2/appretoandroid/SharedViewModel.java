package com.dam2.appretoandroid;

import android.app.Application;
import android.location.Location;
import android.view.MenuItem;
import android.view.View;


import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.dam2.appretoandroid.modelo.CestaProducto;

import java.util.List;

public class SharedViewModel extends AndroidViewModel {

    private CestaItemRepository cestaItemRepository;
    private LiveData<List<CestaProducto>> cartItems;
    private LiveData<Double> totalPrice;
    private MutableLiveData<Location> locationLiveData;
    private final MutableLiveData<MenuItem> liveView = new MutableLiveData<>();
    public SharedViewModel(Application application)
    {
        super(application);
        cestaItemRepository = new CestaItemRepository(application);
        cartItems= cestaItemRepository.getAllItems();
        totalPrice= cestaItemRepository.getPrecioTotal();
        locationLiveData=new MutableLiveData<Location>();

    }

    public LiveData<List<CestaProducto>> getCartItems() {
        return cartItems;
    }

    public LiveData<Double> getTotalPrice() {
        return totalPrice;
    }

    public MutableLiveData<MenuItem> getLiveView() {
        return liveView;
    }
    public void addItemToCart(CestaProducto producto)
    {
        cestaItemRepository.insertItem(producto);
    }

    public void removeItemFromCart(CestaProducto producto) {
        cestaItemRepository.deleteItem(producto);
    }

    public void setMenuItem(MenuItem searchView)
    {
        liveView.setValue(searchView);
    }

    public LiveData<MenuItem> getMenuItem()
    {
        return liveView;
    }

    public MutableLiveData<Location> getLocationLiveData() {
        return locationLiveData;
    }

    public void setLocationLiveData(MutableLiveData<Location> locationLiveData) {
        this.locationLiveData = locationLiveData;
    }
    public void cargarCordenadas(Location location)
    {
        locationLiveData.postValue(location);
    }
}
