package com.basesdedatos.erp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class OpcionesEmpleado extends AppCompatActivity {

    private Button btnIrManufactura;
    private Button btn_finanzas;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.opciones_empleado_activity);

        btnIrManufactura = findViewById(R.id.btnIrManufactura);
        btn_finanzas = findViewById(R.id.btn_finanzas_empleado);

        btnIrManufactura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              irPantallaManufacturaCActivity();
            }
        });

        btn_finanzas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irPantallaMenuFinanzas();
            }
        });
    }

    private void irPantallaManufacturaCActivity(){
        Intent intent = new Intent(this,ManufacturaCActivity.class);
        startActivity(intent);
    }

    private void irPantallaMenuFinanzas(){
        Intent intent = new Intent(this, MenuFinanzas.class);
        startActivity(intent);
    }
}
