package com.basesdedatos.erp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SolicitudMantenimiento extends AppCompatActivity {

    private EditText txtTipoDefecto;
    private EditText txtDescDefecto;
    private Button btnSolicDefecto;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.solicitud_mantenimiento_activity);

        txtDescDefecto = findViewById(R.id.txtDescDefecto);
        txtTipoDefecto = findViewById(R.id.txtTipoDefecto);

        btnSolicDefecto = findViewById(R.id.btnSolicDefecto);

        btnSolicDefecto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                solicitudDefecto();
            }
        });
    }

    private void solicitudDefecto(){

    }
}
