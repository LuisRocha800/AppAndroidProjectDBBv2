package com.basesdedatos.erp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ManufacturaAdquiridaActivity extends AppCompatActivity {

    private TextView RFCCliente;

    private EditText txtIdManufactura;
    private EditText txtDireccionMan;
    private EditText txtCostoMan;
    private EditText txtFecAdqMan;

    private Button btnBuscarManufactura;
    private Button btnSolMantenimiento;
    private Button btnHistorialMantenimiento;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manufactura_adquirida);

        txtIdManufactura = findViewById(R.id.txtIdManufactura);
        txtDireccionMan = findViewById(R.id.txtDireccionMan);
        txtCostoMan = findViewById(R.id.txtCostoMan);
        txtFecAdqMan = findViewById(R.id.txtFecAdqMan);
        RFCCliente = findViewById(R.id.rfcCliente);

        btnBuscarManufactura = findViewById(R.id.btnBuscarManufactura);
        btnSolMantenimiento = findViewById(R.id.btnSolMantenimiento);
        btnHistorialMantenimiento = findViewById(R.id.btnHistorialMantenimiento);

        btnBuscarManufactura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buscarIdMantenimiento();
            }
        });

        btnSolMantenimiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             solicMantenimiento();
            }
        });

        btnHistorialMantenimiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                historialMantenimiento();
            }
        });

    }

    private void buscarIdMantenimiento(){

    }

    private void solicMantenimiento(){

        Intent intent = new Intent(this,SolicitudMantenimiento.class);
        startActivity(intent);
    }

    private void historialMantenimiento(){

        Intent intent = new Intent(this,HistorialMantenimientoActivity.class);
        startActivity(intent);
    }
}
