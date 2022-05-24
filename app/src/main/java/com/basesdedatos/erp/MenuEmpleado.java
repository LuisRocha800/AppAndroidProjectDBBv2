package com.basesdedatos.erp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.androidnetworking.AndroidNetworking;

public class MenuEmpleado extends AppCompatActivity {

    public TextView mostNombreEmpleado;
    public TextView mostApellidoEmpleado;
    public TextView mostRFCEmpleado;
    public TextView mostEmailEmpleado;
    public TextView mostDireccionEmpleado;
    public TextView mostTelefonoEmpleado;

    private Button btnMostVentas;
    private Button btnInfoEmpleado;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_empleado);
        AndroidNetworking.initialize(getApplicationContext());

        mostNombreEmpleado = findViewById(R.id.mostNombreEmpleado);
        mostApellidoEmpleado = findViewById(R.id.mostApellidoEmpleado);
        mostRFCEmpleado = findViewById(R.id.mostRFCEmpleado);
        mostEmailEmpleado = findViewById(R.id.mostEmailEmpleado);
        mostDireccionEmpleado = findViewById(R.id.mostDireccionEmpleado);
        mostTelefonoEmpleado = findViewById(R.id.mostTelefonoEmpleado);

        String datoRFC = getIntent().getStringExtra("datoRFCEmpleado");
        mostRFCEmpleado.setText(datoRFC);
    }
}
