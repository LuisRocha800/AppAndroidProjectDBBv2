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
    private Button btnIrPrueba;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdministrador = findViewById(R.id.btnAdministrador);
        btnEmpleado = findViewById(R.id.btnEmpleado);
        btnCliente = findViewById(R.id.btnCliente);
        btnIrPrueba = findViewById(R.id.btnIrPrueba);

        btnAdministrador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              irPantallaLoginAdministrador();
            }
        });

        btnEmpleado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               irPantallaLoginEmpleado();
            }
        });

        btnCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irPantallaLoginCliente();
            }
        });

        btnIrPrueba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               irPantallaPrueba();
            }
        });
    }


    private void irPantallaLoginAdministrador(){
        Intent intent = new Intent(this,LoginAdministradorActivity.class);
        startActivity(intent);
    }

    private void irPantallaLoginEmpleado(){
        Intent intent = new Intent(this,LoginEmpleadoActivity.class);
        startActivity(intent);
    }

    private void irPantallaLoginCliente(){
        Intent intent = new Intent(this,LoginClienteActivity.class);
        startActivity(intent);
    }

    private void irPantallaPrueba(){
        Intent intent = new Intent(this,Prueba.class);
        startActivity(intent);
    }
}