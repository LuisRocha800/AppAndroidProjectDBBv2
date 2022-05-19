package com.basesdedatos.erp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

public class MenuCliente extends AppCompatActivity {

    public TextView mostNombreCliente;
    public TextView mostApellidoCliente;
    public TextView mostRFCCliente;
    public TextView mostEmailCliente;
    public TextView mostDireccionCliente;
    public TextView mostTelefonoCliente;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_cliente);

        mostNombreCliente = findViewById(R.id.mostNombreCliente);
        mostApellidoCliente = findViewById(R.id.mostApellidoCliente);
        mostRFCCliente = findViewById(R.id.mostRFCCliente);
        mostEmailCliente = findViewById(R.id.mostEmailCliente);
        mostDireccionCliente = findViewById(R.id.mostDireccionCliente);
        mostTelefonoCliente = findViewById(R.id.mostTelefonoCliente);

        String datoRFC = getIntent().getStringExtra("datoRFCCliente");
        mostRFCCliente.setText(datoRFC);

        mostrarDatos();

        }

    public void mostrarDatos(){

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JSONObject object = new JSONObject();
        String RFC = mostRFCCliente.getText().toString().trim();
        String url = Constantes.URL_CLIENTE_X_ID+"?rfc="+RFC;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, object, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject datos = response.getJSONObject("data");
                    String nombre = datos.getString("nombre");
                    String apellidop = datos.getString("apellidopaterno");
                    String apellidom = datos.getString("apellidomaterno");
                    String telefono = datos.getString("telefono");
                    String email = datos.getString("email");
                    String direccion = datos.getString("direccion");
                    mostNombreCliente.setText(nombre);
                    mostApellidoCliente.setText(apellidop+" "+apellidom);
                    mostTelefonoCliente.setText(telefono);
                    mostEmailCliente.setText(email);
                    mostDireccionCliente.setText(direccion);
                }catch(JSONException e){
                    Toast.makeText(MenuCliente.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                mostNombreCliente.setText(error.toString());
                mostApellidoCliente.setText(error.toString());
                mostTelefonoCliente.setText(error.toString());
                mostEmailCliente.setText(error.toString());
                mostDireccionCliente.setText(error.toString());
            }
        });
        requestQueue.add(jsonObjectRequest);
    }
    }


