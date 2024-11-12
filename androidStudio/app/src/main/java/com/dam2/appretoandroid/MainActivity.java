package com.dam2.appretoandroid;


import android.app.SearchManager;
import android.app.SearchableInfo;
import android.app.appsearch.SearchResults;
import android.content.ComponentName;
import android.content.Context;
import android.os.Bundle;
import android.se.omapi.Session;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.dam2.appretoandroid.api.MyApiAdapter;
import com.dam2.appretoandroid.api.SessionManager;
import com.dam2.appretoandroid.modelo.Producto;
import com.dam2.appretoandroid.ui.UserDialogFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        sessionManager = new SessionManager(this);



        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.

         appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_servicios, R.id.navigation_mapa, R.id.navigation_galeria, R.id.navigation_historia,
                R.id.navigation_login)
                .build();



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

        SearchableInfo searchableInfo=searchManager.getSearchableInfo(component);
        searchView.setSearchableInfo(searchableInfo);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Log.d("item",item.getItemId()+"");
        if(item.getItemId()==R.id.profile)
        {
            Log.d("d","hahiah");
            UserDialogFragment userDialogFragment=new UserDialogFragment();

            userDialogFragment.show(getSupportFragmentManager(),"User");
        }
        return super.onOptionsItemSelected(item);
    }
}