package com.dam2.appretoandroid.ui.historia;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.dam2.appretoandroid.MainActivity;
import com.dam2.appretoandroid.R;
import com.dam2.appretoandroid.SharedViewModel;

public class HistoriaFragment extends Fragment {

    private HistoriaViewModel mViewModel;
    private SharedViewModel sharedViewModel;

    public static HistoriaFragment newInstance() {
        return new HistoriaFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {


        enableSearch(false);
        return inflater.inflate(R.layout.fragment_historia, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

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
}