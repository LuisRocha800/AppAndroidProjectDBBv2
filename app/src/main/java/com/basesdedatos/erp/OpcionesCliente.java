package com.basesdedatos.erp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class OpcionesCliente extends AppCompatActivity {

    private Button btnIrManAdq;
    private Button btn_finanzas;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.opciones_cliente_activity);

        btnIrManAdq = findViewById(R.id.btnIrManAdq);
        btn_finanzas = findViewById(R.id.btn_finanzas_cliente);

        btnIrManAdq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              irManAdquirida();
            }
        });

        btn_finanzas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irPantallaMenuFinanzas();
            }
        });
    }

    private void irManAdquirida(){

        Intent intent = new Intent(this,ManufacturaAdquiridaActivity.class);
        startActivity(intent);
    }

    private void irPantallaMenuFinanzas(){
        Intent intent = new Intent(this, MenuFinanzas.class);
        startActivity(intent);
    }
}
