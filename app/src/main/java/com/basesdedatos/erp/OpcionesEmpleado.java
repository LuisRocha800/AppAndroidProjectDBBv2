package com.basesdedatos.erp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class OpcionesEmpleado extends AppCompatActivity {

    private Button btnIrManufactura;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.opciones_empleado_activity);

        btnIrManufactura = findViewById(R.id.btnIrManufactura);

        btnIrManufactura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              irPantallaManufacturaCActivity();
            }
        });
    }

    private void irPantallaManufacturaCActivity(){
        Intent intent = new Intent(this,ManufacturaCActivity.class);
        startActivity(intent);
    }
}
