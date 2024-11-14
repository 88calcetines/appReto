package com.dam2.appretoandroid;


import android.Manifest;
import android.app.SearchManager;
import android.app.SearchableInfo;
import android.app.appsearch.SearchResults;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.se.omapi.Session;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.dam2.appretoandroid.api.MyApiAdapter;
import com.dam2.appretoandroid.api.SessionManager;
import com.dam2.appretoandroid.modelo.Producto;
import com.dam2.appretoandroid.ui.UserDialogFragment;
import com.dam2.appretoandroid.ui.login.PerfilFragment;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationServices;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.dam2.appretoandroid.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity  {

    private ActivityMainBinding binding;
    private SharedViewModel viewModel;
    private Menu menuG;
    private SessionManager sessionManager;
    private AppBarConfiguration appBarConfiguration;
    private static final int REQUEST_CODE_LOCATION_PERMISSION = 100;
    private boolean isPermisos = false;
    private FusedLocationProviderClient fusedLocationClient;
    private LocationCallback locationCallback;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        sessionManager = new SessionManager(this);
        fusedLocationClient= LocationServices.getFusedLocationProviderClient(this);



        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.

         appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_servicios, R.id.navigation_mapa, R.id.navigation_galeria, R.id.navigation_historia,
                R.id.navigation_login)
                .build();



         getCurrentLocation();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);


        /*Call<Producto> call = MyApiAdapter.getApiService().getProductosNombre("Fifa 24");

        call.enqueue(new Callback<Producto>() {
            @Override
            public void onResponse(Call<Producto> call, Response<Producto> response) {
                Producto foundProducto=response.body();
                Log.d("producto", foundProducto+"");
            }

            @Override
            public void onFailure(Call<Producto> call, Throwable throwable) {

            }
        });*/





    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.options_menu, menu);
        SearchManager searchManager= (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        menuG=menu;


        SearchView searchView =(SearchView) menu.findItem(R.id.search).getActionView();
        viewModel = new ViewModelProvider(this).get(SharedViewModel.class);
        //viewModel.setSearchView(searchView);
        MenuItem menuItem=menu.findItem(R.id.search);
        viewModel.setMenuItem(menuItem);

        ComponentName component= new ComponentName(this, SearchResults.class);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        /*Log.d("item",item.getItemId()+"");
        if(item.getItemId()==R.id.profile)
        {
            Log.d("d","hahiah");
            UserDialogFragment userDialogFragment=new UserDialogFragment();

            userDialogFragment.show(getSupportFragmentManager(),"User");
        }*/
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);

        // If the NavController has a fragment in the back stack, pop it
        if (!navController.popBackStack()) {
            // If the back stack is empty, call the default onBackPressed
            super.onBackPressed();
        }
    }
    private  void getCurrentLocation()
    {
        if(ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
        )
        {
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION
                    },
                    REQUEST_CODE_LOCATION_PERMISSION

            );
            return;
        }
        fusedLocationClient.getLastLocation().addOnSuccessListener(this, location -> {
            if(location !=null)
            {

                //enviar locacion
                Location coordenadas=location;
                viewModel.cargarCordenadas(coordenadas);

            }else
            {

            }
        });
    }
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == REQUEST_CODE_LOCATION_PERMISSION && grantResults.length> 0)
        {
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                getCurrentLocation();
            }else
            {

            }
        }
    }

}