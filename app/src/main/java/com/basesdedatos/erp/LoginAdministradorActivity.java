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

public class LoginAdministradorActivity extends AppCompatActivity {

    private EditText txtUsuarioAdm;
    private EditText txtPasswordAdm;
    private TextView viewUserAdm;
    private TextView viewPassAdm;
    private Button btnOkAdm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_administrador_activity);

        txtUsuarioAdm = findViewById(R.id.txtUsuarioAdm);
        txtPasswordAdm = findViewById(R.id.txtPasswordAdm);
        viewUserAdm = findViewById(R.id.viewUserAdm);
        viewPassAdm = findViewById(R.id.viewPassAdm);
        btnOkAdm = findViewById(R.id.btnOkAdm);

        btnOkAdm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                verificarAcceso();
            }
        });
    }

    private void verificarAcceso(){

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JSONObject object = new JSONObject();
        String RFC = txtUsuarioAdm.getText().toString().trim();
        String url = Constantes.URL_USUARIOADMIN_ID_WEB_SERVICE+"?rfc="+RFC;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, object, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject datos = response.getJSONObject("data");
                    String rfc = datos.getString("rfc");
                    String password = datos.getString("contrasenia");

                    viewUserAdm.setText(rfc);
                    viewPassAdm.setText(password);

                }catch(JSONException e){

                }
            } }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                viewUserAdm.setText(error.toString());
                viewPassAdm.setText(error.toString());

            }
        });
        requestQueue.add(jsonObjectRequest);


        String user = txtUsuarioAdm.getText().toString();
        String password = txtPasswordAdm.getText().toString();
        String uss = viewUserAdm.getText().toString();
        String pss = viewPassAdm.getText().toString();

        if(verificar()) {

            if (user.equals(uss) && password.equals(pss)) {
                irMenuAdmin();
                Toast.makeText(this, "Welcome " + user, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "El usuario o la contraseña son incorrectos", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this, "No pueden haber campos vacios", Toast.LENGTH_SHORT).show();
        }
    }

    private void irMenuAdmin(){
        Intent intent = new Intent(this,MenuAdministrador.class);
        intent.putExtra("datoRFCAdm",txtUsuarioAdm.getText().toString());
        startActivity(intent);
    }
    private boolean verificar(){
        return !txtUsuarioAdm.getText().toString().trim().isEmpty() &&
                !txtPasswordAdm.getText().toString().trim().isEmpty();
    }
}
