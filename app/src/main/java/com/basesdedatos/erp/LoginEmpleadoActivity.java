package com.basesdedatos.erp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LoginEmpleadoActivity extends AppCompatActivity {

    private EditText txtUsuarioEmpleado;
    private EditText txtPasswordEmpleado;

    private Button btnOkEmpleado;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_empleado_activity);

        txtUsuarioEmpleado = findViewById(R.id.txtUsuarioEmpleado);
        txtPasswordEmpleado = findViewById(R.id.txtPasswordEmpleado);

        btnOkEmpleado = findViewById(R.id.btnOkEmpleado);

        btnOkEmpleado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verificarAccesoEmpleado();
            }
        });
    }

    private void verificarAccesoEmpleado(){
        if(verificarCamp()) {
            String uss = "postgres";
            String pss = "123";

            String usser = txtUsuarioEmpleado.getText().toString();
            String passw = txtPasswordEmpleado.getText().toString();

            if (usser.equals(uss) && passw.equals(pss)) {
                Toast.makeText(this, "Welcome " + usser, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, MenuEmpleado.class);
                startActivity(intent);

            } else {
                Toast.makeText(this, "El usuario o la contraseña son incorrectos", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this, "No pueden haber campos vacios", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean verificarCamp(){
        return !txtUsuarioEmpleado.getText().toString().trim().isEmpty()&&
               !txtPasswordEmpleado.getText().toString().trim().isEmpty();
    }
}

