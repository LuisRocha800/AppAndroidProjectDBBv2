package com.basesdedatos.erp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity{

    private Button btnAdministrador;
    private Button btnEmpleado;
    private Button btnCliente;
    private Button btnIniciarSesionGlobal;
    private Button btn_ventas;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnIniciarSesionGlobal = findViewById(R.id.btnIniciarSesionGlobal);

        btnIniciarSesionGlobal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irPantallaLoginGlobal();
            }
        });

    }

    private void irPantallaLoginGlobal(){
        Intent intent = new Intent(this,LoginGlobal.class);
        startActivity(intent);
    }

}