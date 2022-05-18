package com.basesdedatos.erp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LoginAdministradorActivity extends AppCompatActivity {

    private EditText txtUsuarioAdm;
    private EditText txtPasswordAdm;

    private Button btnOkAdm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_administrador_activity);

        txtUsuarioAdm = findViewById(R.id.txtUsuarioAdm);
        txtPasswordAdm = findViewById(R.id.txtPasswordAdm);
        btnOkAdm = findViewById(R.id.btnOkAdm);

        btnOkAdm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verificarAcceso();
            }
        });
    }

    private void verificarAcceso(){
        if(verificar()) {
            String us = "postgres";
            String ps = "123";

            String usu = txtUsuarioAdm.getText().toString();
            String pss = txtPasswordAdm.getText().toString();

            if (usu.equals(us) && ps.equals(ps)) {
                Toast.makeText(this, "Welcome " + usu, Toast.LENGTH_SHORT).show();
                irMenuAdmin();
            } else {
                Toast.makeText(this, "Credenciales de acceso incorrectos", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this, "No puede haber campos vacios", Toast.LENGTH_SHORT).show();
        }

    }

    private void irMenuAdmin(){
        Intent intent = new Intent(this,MenuAdministrador.class);
        startActivity(intent);
    }
    private boolean verificar(){
        return !txtUsuarioAdm.getText().toString().trim().isEmpty() &&
                !txtPasswordAdm.getText().toString().trim().isEmpty();
    }
}
