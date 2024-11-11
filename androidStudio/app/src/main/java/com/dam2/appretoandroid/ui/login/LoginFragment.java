package com.dam2.appretoandroid.ui.login;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.dam2.appretoandroid.R;
import com.dam2.appretoandroid.SharedViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class LoginFragment extends Fragment {

    private LoginViewModel mViewModel;
    private LinearLayout lnNombre;
    private LinearLayout lnApellido;
    private EditText etNombre;
    private EditText etApellido;
    private EditText etEmail;
    private EditText etPassword;
    private Button btnLogin;
    private Button btnRegistro;

    private Context mContext;
    private NavController navController;
    private BottomNavigationView bottomNavigationView;



    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel=new ViewModelProvider(requireParentFragment()).get(LoginViewModel.class);
        navController= NavHostFragment.findNavController(this);
        bottomNavigationView=getActivity().findViewById(R.id.nav_view);
        enableSearch(false);
        initializeUi(view);

        mViewModel.getLoginCorrecto().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(aBoolean){
                    Toast.makeText(mContext, "loginCOrrecto", Toast.LENGTH_SHORT).show();
                    navController.navigate(R.id.action_fragmentlogin_to_fragmentservicio);
                    bottomNavigationView.getMenu().findItem(R.id.navigation_login).setVisible(false);
                    if(bottomNavigationView.getMenu().findItem(R.id.navigation_perfil) == null){
                        bottomNavigationView.getMenu().add(Menu.NONE, R.id.navigation_perfil, Menu.NONE, "Perfil")
                                .setIcon(R.drawable.ic_dashboard_black_24dp)
                                .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
                    }

                }else{
                    Toast.makeText(mContext, "login inCOrrecto", Toast.LENGTH_SHORT).show();
                }


            }
        });



    }

    private void enableSearch(boolean state)
    {
        SharedViewModel sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        sharedViewModel.getMenuItem().observe(getViewLifecycleOwner(), new Observer<MenuItem>() {
            @Override
            public void onChanged(MenuItem menuItem) {
                menuItem.setVisible(state);
            }
        });

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.mContext=context;
    }

    public void hideUi(View view)
    {
        etApellido.setText("");
        etNombre.setText("");
        lnNombre.setVisibility(View.INVISIBLE);
        lnApellido.setVisibility(View.INVISIBLE);
    }
    public void showUi(View view)
    {
        lnNombre.setVisibility(View.VISIBLE);
        lnApellido.setVisibility(View.VISIBLE);
    }
    private void initializeUi(View view)
    {
        lnApellido=view.findViewById(R.id.lnApellido);
        lnNombre=view.findViewById(R.id.lnNombre);
        etNombre=view.findViewById(R.id.etNombre);
        etApellido=view.findViewById(R.id.etApellido);
        etEmail=view.findViewById(R.id.etEmail);
        etPassword=view.findViewById(R.id.etContrasena);
        btnLogin=view.findViewById(R.id.btnLogin);
        btnRegistro=view.findViewById(R.id.btnRegister);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideUi(v);
                if(!etEmail.getText().toString().isEmpty() && !etPassword.getText().toString().isEmpty() )
                {
                    String username = etEmail.getText().toString();
                    String password = etPassword.getText().toString();
                    mViewModel.login(getViewLifecycleOwner(),mContext,username,password);

                }
            }
        });
        btnRegistro.setOnClickListener(v -> showUi(view));
    }
}