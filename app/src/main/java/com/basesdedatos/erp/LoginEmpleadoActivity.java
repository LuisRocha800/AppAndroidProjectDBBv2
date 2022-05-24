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

public class LoginEmpleadoActivity extends AppCompatActivity {

    private EditText txtUsuarioEmpleado;
    private EditText txtPasswordEmpleado;
    private TextView viewUserEmp;
    private TextView viewPassEmp;
    private Button btnOkEmpleado;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_empleado_activity);

        txtUsuarioEmpleado = findViewById(R.id.txtUsuarioEmpleado);
        txtPasswordEmpleado = findViewById(R.id.txtPasswordEmpleado);
        viewUserEmp = findViewById(R.id.viewUserEmp);
        viewPassEmp = findViewById(R.id.viewPassEmp);
        btnOkEmpleado = findViewById(R.id.btnOkEmpleado);

        btnOkEmpleado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verificarAccesoEmpleado();
            }
        });
    }

    private void verificarAccesoEmpleado(){

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JSONObject object = new JSONObject();
        String RFC = txtUsuarioEmpleado.getText().toString().trim();
        String url = Constantes.URL_USUARIOEMPLEADO_ID_WEB_SERVICE+"?rfc="+RFC;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, object, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject datos = response.getJSONObject("data");
                    String rfc = datos.getString("rfc");
                    String password = datos.getString("contrasenia");

                    viewUserEmp.setText(rfc);
                    viewPassEmp.setText(password);

                }catch(JSONException e){

                }
            } }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                viewUserEmp.setText(error.toString());
                viewPassEmp.setText(error.toString());

            }
        });
        requestQueue.add(jsonObjectRequest);


        String user = txtUsuarioEmpleado.getText().toString();
        String password = txtPasswordEmpleado.getText().toString();
        String uss = viewUserEmp.getText().toString();
        String pss = viewPassEmp.getText().toString();

        if(verificarCamp()) {

            if (user.equals(uss) && password.equals(pss)) {
                enviarUsuarioEmpleado();
                Toast.makeText(this, "Welcome " + user, Toast.LENGTH_SHORT).show();
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

    public void enviarUsuarioEmpleado(){
        Intent intent = new Intent(this, MenuEmpleado.class);
        intent.putExtra("datoRFCEmpleado",txtUsuarioEmpleado.getText().toString());
        startActivity(intent);
    }
}

