package com.dam2.appretoandroid.ui.login;

import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.dam2.appretoandroid.R;
import com.dam2.appretoandroid.SharedViewModel;
import com.dam2.appretoandroid.api.SessionManager;
import com.dam2.appretoandroid.modelo.Usuario;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class LoginFragment extends Fragment {

    private LoginViewModel mViewModel;
    private LinearLayout lnNombre;
    private LinearLayout lnApellido;
    private LinearLayout lnApellido2;
    private EditText etNombre;
    private EditText etApellido;
    private EditText etApellido2;
    private EditText etEmail;
    private EditText etPassword;
    private Button btnLogin;
    private Button btnRegistro;
    private String username;
    private String password;

    private Button btnPerfil;
    private Context mContext;
    private NavController navController;
    private BottomNavigationView bottomNavigationView;
    private SessionManager sessionManager;

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


        mContext = requireContext();
        mViewModel = new ViewModelProvider(requireParentFragment()).get(LoginViewModel.class);
        navController = NavHostFragment.findNavController(this);
        bottomNavigationView = requireActivity().findViewById(R.id.nav_view);
        sessionManager=new SessionManager(mContext);

        enableSearch(false);
        initializeUi(view);

        mViewModel.getLoginCorrecto().observe(getViewLifecycleOwner(), isLoginCorrect -> {
            if (isLoginCorrect) {
                Toast.makeText(mContext, "Login correcto", Toast.LENGTH_SHORT).show();
                btnPerfil.setVisibility(View.VISIBLE);


            } else {
                Toast.makeText(mContext, "Login incorrecto", Toast.LENGTH_SHORT).show();
                btnPerfil.setVisibility(View.INVISIBLE);
            }
        });

    }

    private void enableSearch(boolean state) {
        SharedViewModel sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        sharedViewModel.getMenuItem().observe(getViewLifecycleOwner(), menuItem -> menuItem.setVisible(state));
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    private void hideUi() {
        etApellido.setText("");
        etNombre.setText("");
        etApellido2.setText("");
        lnNombre.setVisibility(View.INVISIBLE);
        lnApellido.setVisibility(View.INVISIBLE);
        lnApellido2.setVisibility(View.INVISIBLE);
    }

    private void showUi() {
        lnNombre.setVisibility(View.VISIBLE);
        lnApellido.setVisibility(View.VISIBLE);
        lnApellido2.setVisibility(View.VISIBLE);
    }

    private void initializeUi(View view) {
        lnApellido = view.findViewById(R.id.lnApellido1);
        lnApellido2=view.findViewById(R.id.lnApellido2);
        lnNombre = view.findViewById(R.id.lnNombre);
        etNombre = view.findViewById(R.id.etNombre);
        etApellido = view.findViewById(R.id.etApellido1);
        etApellido2=view.findViewById(R.id.etApellido2);
        etEmail = view.findViewById(R.id.etEmail);
        etPassword = view.findViewById(R.id.etContrasena);
        btnLogin = view.findViewById(R.id.btnLogin);
        btnRegistro = view.findViewById(R.id.btnRegister);
        btnPerfil = view.findViewById(R.id.btnPerfil);

        btnLogin.setOnClickListener(v -> {
            hideUi();
            if (!etEmail.getText().toString().isEmpty() && !etPassword.getText().toString().isEmpty()) {
                username = etEmail.getText().toString();
                password = etPassword.getText().toString();
                mViewModel.login(getViewLifecycleOwner(), mContext, username, password);
            } else {
                Toast.makeText(mContext, "Introduce email y contraseña", Toast.LENGTH_SHORT).show();
            }
        });
        btnPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("user", username);
                bundle.putSerializable("password", password);

                NavOptions navOptions = new NavOptions.Builder().build();
                navController.navigate(R.id.action_fragmentlogin_to_fragmentperfil, bundle, navOptions);
            }
        });

        btnRegistro.setOnClickListener(v -> {
            showUi();
            if (!etEmail.getText().toString().isEmpty() && !etPassword.getText().toString().isEmpty()
            && !etNombre.getText().toString().isEmpty() && !etApellido.getText().toString().isEmpty()
            && !etApellido2.getText().toString().isEmpty()) {
                Usuario usuario = new Usuario();
                usuario.setContrasena(etPassword.getText().toString());
                usuario.setEmail(etEmail.getText().toString());
                usuario.setApellido1(etApellido.getText().toString());
                usuario.setNombre(etNombre.getText().toString());
                usuario.setApellido2(etApellido2.getText().toString());
                usuario.setImagen("/assets\\/images\\/default-avatar.png");
                mViewModel.registro(getViewLifecycleOwner(), mContext, usuario);
            } else {
                Toast.makeText(mContext, "Introduce email y contraseña", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void onResume() {
        super.onResume();

    }

}
