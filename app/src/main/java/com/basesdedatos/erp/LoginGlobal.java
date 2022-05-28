package com.basesdedatos.erp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginGlobal extends AppCompatActivity {

    private EditText txtUsuarioGlobal;
    private EditText txtPasswordGlobal;
    private TextView viewUser;
    private TextView viewPass;
    private TextView viewCargo;

    private Button btnOkGlobal;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginglobal);

        txtUsuarioGlobal = findViewById(R.id.txtUsuarioGlobal);
        txtPasswordGlobal = findViewById(R.id.txtPasswordGlobal);
        viewUser = findViewById(R.id.viewUser);
        viewPass = findViewById(R.id.viewPass);
        viewCargo = findViewById(R.id.viewCargo);


        btnOkGlobal = findViewById(R.id.btnOkGlobal);

        btnOkGlobal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              verificarLogin();
            }
        });
    }

    private void verificarLogin(){

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JSONObject object = new JSONObject();
        String RFC = txtUsuarioGlobal.getText().toString().trim();
        String url = Constantes.URL_USUARIOGLOBAL_ID_WEB_SERVICE+"?rfc="+RFC;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, object, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject datos = response.getJSONObject("data");
                    String rfc = datos.getString("rfc");
                    String password = datos.getString("contrasenia");
                    String cargo = datos.getString("rol");

                    viewUser.setText(rfc);
                    viewPass.setText(password);
                    viewPass.setText(cargo);

                }catch(JSONException e){

                }
            } }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                viewUser.setText(error.toString());
                viewPass.setText(error.toString());
                viewCargo.setText(error.toString());

            }
        });
        requestQueue.add(jsonObjectRequest);


        String user = txtUsuarioGlobal.getText().toString();
        String password = txtPasswordGlobal.getText().toString();
        String uss = viewUser.getText().toString();
        String pss = viewPass.getText().toString();
        String rol = viewCargo.getText().toString();

        String veriRol1 = "administrador";
        String veriRol2 = "empleado";
        String veriRol3 = "cliente";

        if(verificarcampos()) {

            if (user.equals(uss) && password.equals(pss) && rol.equals(veriRol1)) {
                 irPantallaAdministrador();
                Toast.makeText(this, "Welcome " + user, Toast.LENGTH_SHORT).show();
            } else if(user.equals(uss) && password.equals(pss) && rol.equals(veriRol2)) {
                 irPantallaEmpleado();
                Toast.makeText(this, "Welcome " + user, Toast.LENGTH_SHORT).show();
            } else if (user.equals(uss) && password.equals(pss) && rol.equals(veriRol3)){
                  irPantallaCliente();
                Toast.makeText(this, "Welcome " + user, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Usuario y/o Contraseña Incorrectos", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this, "No pueden haber campos vacios", Toast.LENGTH_SHORT).show();
        }
    }

    private void irPantallaAdministrador(){
        Intent intent = new Intent(this,MenuAdministrador.class);
        startActivity(intent);
    }

    private void irPantallaEmpleado(){
        Intent intent = new Intent(this,MenuEmpleado.class);
        startActivity(intent);
    }

    private void irPantallaCliente(){
        Intent intent = new Intent(this,MenuCliente.class);
        startActivity(intent);
    }

    private boolean verificarcampos(){
        return !txtUsuarioGlobal.getText().toString().trim().isEmpty()&&
                !txtPasswordGlobal.getText().toString().trim().isEmpty();
    }
}
