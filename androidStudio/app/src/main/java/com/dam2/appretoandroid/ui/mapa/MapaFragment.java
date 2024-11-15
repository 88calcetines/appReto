package com.dam2.appretoandroid.ui.mapa;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.dam2.appretoandroid.R;
import com.dam2.appretoandroid.SharedViewModel;
import com.dam2.appretoandroid.api.SessionManager;
import com.dam2.appretoandroid.databinding.FragmentMapaBinding;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapaFragment extends Fragment implements OnMapReadyCallback {

    private FragmentMapaBinding binding;
    private Context mContext;
    private GoogleMap gMap;
    private SharedViewModel sharedViewModel;
    private Location mLocation;
    private SessionManager sessionManager;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        MapaViewModel mapaViewModel =
                new ViewModelProvider(this).get(MapaViewModel.class);
        sharedViewModel=new ViewModelProvider(this).get(SharedViewModel.class);
        sessionManager=new SessionManager(mContext);


        binding = FragmentMapaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        enableSearch(false);


        sharedViewModel.getLocationLiveData().observe(getViewLifecycleOwner(), new Observer<Location>() {
            @Override
            public void onChanged(Location location) {
                mLocation=location;
            }
        });
        return root;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment= (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);
    }




    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        this.gMap=googleMap;
        // Add a marker in Sydney, Australia,
        // and move the map's camera to the same location.
        LatLng almi = new LatLng(43.26407363220316, -2.9233381905269074);

        String token =sessionManager.fetchAuthToken();
        if(mLocation!=null && token!=null)
        {
            almi =new LatLng(mLocation.getLatitude(), mLocation.getLongitude());
        }
        gMap.addMarker(new MarkerOptions()
                .position(almi)
                .title("Almi"));
        gMap.moveCamera(CameraUpdateFactory.newLatLng(almi));
        gMap.animateCamera(CameraUpdateFactory.newLatLngZoom(almi,18));
    }

    private void enableSearch(boolean state)
    {
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        sharedViewModel.getMenuItem().observe(getViewLifecycleOwner(), new Observer<MenuItem>() {
            @Override
            public void onChanged(MenuItem menuItem) {
                menuItem.setVisible(state);
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        onMapReady(gMap);
    }
}