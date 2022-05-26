package com.basesdedatos.erp;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class HistorialMantenimientoActivity extends AppCompatActivity {

    private TextView txtIdManufacturaHistorial;
    private EditText txtFecIniHistorial;
    private EditText txtFecFinHistorial;

    private ListView listaDeMantenimiento;
    private ArrayAdapter<String> adapter;

    private Button btnFiltrarFecHistorial;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.historial_mantenimiento_activity);

        txtIdManufacturaHistorial = findViewById(R.id.txtIdManufacturaHistorial);
        txtFecIniHistorial = findViewById(R.id.txtFecIniHistorial);
        txtFecFinHistorial = findViewById(R.id.txtFecFinHistorial);

        listaDeMantenimiento = findViewById(R.id.listaDeMantenimiento);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);

        btnFiltrarFecHistorial = findViewById(R.id.btnFiltrarFecHistorial);

        btnFiltrarFecHistorial.setOnClickListener(new View.OnClickListener() {
            @Override
             public void onClick(View v) {
              mostrarHistorialMantenimiento();
            }
        });
    }

    private void mostrarHistorialMantenimiento(){

    }
}
