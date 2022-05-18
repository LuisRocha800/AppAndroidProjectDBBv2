package com.basesdedatos.erp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LoginClienteActivity extends AppCompatActivity {

    private EditText txtUsuarioCliente;
    private EditText txtPasswordCliente;

    private Button btnOkCliente;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_cliente_activity);

        txtUsuarioCliente = findViewById(R.id.txtUsuarioCliente);
        txtPasswordCliente = findViewById(R.id.txtPasswordCliente);

        btnOkCliente = findViewById(R.id.btnOkCliente);

        btnOkCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              verficarAccesoCliente();
            }
        });
    }

    private void verficarAccesoCliente(){
        if(verficarcampos()) {
            String uss = "postgres";
            String pss = "123";

            String user = txtUsuarioCliente.getText().toString();
            String password = txtPasswordCliente.getText().toString();

            if (user.equals(uss) && password.equals(pss)) {
                Toast.makeText(this, "Welcome " + user, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, MenuCliente.class);
                startActivity(intent);
            } else {
                Toast.makeText(this, "El usuario o la contraseña son incorrectos", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this, "No pueden haber campos vacios", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean verficarcampos(){
       return !txtUsuarioCliente.getText().toString().trim().isEmpty()&&
              !txtPasswordCliente.getText().toString().trim().isEmpty();
    }
}
