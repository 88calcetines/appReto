package com.dam2.appretoandroid;


import android.app.SearchManager;
import android.app.SearchableInfo;
import android.app.appsearch.SearchResults;
import android.content.ComponentName;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.KeyEventDispatcher;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.dam2.appretoandroid.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private SharedViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.drawable.ic_home_black_24dp);


        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_servicios, R.id.navigation_mapa, R.id.navigation_galeria, R.id.navigation_historia)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        SearchManager searchManager= (SearchManager) getSystemService(Context.SEARCH_SERVICE);

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

}