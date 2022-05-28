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
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Autobuses extends AppCompatActivity {

    private EditText txtPlacas;
    private EditText txtNumero_Asientos;
    private EditText txtStatusAutobus;

    private TextView textView36;

    private Button btnInsertarAutobus;
    private Button btnActAutobus;
    private Button btnBuscarPlacas;
    private Button btnEliminarAutobus;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.autobuses);
        AndroidNetworking.initialize(getApplicationContext());

        txtPlacas = findViewById(R.id.txtPlacas);
        txtNumero_Asientos = findViewById(R.id.txtNumero_Asientos);
        txtStatusAutobus = findViewById(R.id.txtStatusAutobus);


        btnInsertarAutobus = findViewById(R.id.btnInsertarAutobus);
        btnActAutobus = findViewById(R.id.btnActAutobus);
        btnBuscarPlacas = findViewById(R.id.btnBuscarPlacas);
        btnEliminarAutobus = findViewById(R.id.btnEliminarAutobus);


        btnInsertarAutobus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertarAutobus();
            }
        });

        btnActAutobus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actualizarAutobus();
            }
        });

        btnBuscarPlacas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buscarPlacas();
            }
        });

        btnEliminarAutobus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eliminarAutobus();
            }
        });

       /* listarRutas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              irVentanVentasFecha();
            }
        });*/
    }

    private void insertarAutobus() {
         if(validarCampos()) {
             //OBTENER DATOS DE LOS COMPONENTES DE LA VISTA
             String placas = txtPlacas.getText().toString();
             String status = txtStatusAutobus.getText().toString();
             String asientos = txtNumero_Asientos.getText().toString();


             Map<String, String> datos = new HashMap<>();
             datos.put("placas", placas);
             datos.put("status", status);
             datos.put("numero_asientos", asientos);
             JSONObject jsonData = new JSONObject(datos);

             AndroidNetworking.post(Constantes.URL_INSERTAR_AUTOBUS)

                     .addJSONObjectBody(jsonData)
                     .setPriority(Priority.MEDIUM)
                     .build()
                     .getAsJSONObject(new JSONObjectRequestListener() {
                         @Override
                         public void onResponse(JSONObject response) {
                             try {
                                 String estado = response.getString("estado");
                                 String error = response.getString("error");
                                 Toast.makeText(Autobuses.this, estado, Toast.LENGTH_SHORT).show();


                             } catch (JSONException e) {
                                 Toast.makeText(Autobuses.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                             }
                         }

                         @Override
                         public void onError(ANError anError) {
                             Toast.makeText(Autobuses.this, "Error: " + anError.getErrorDetail(), Toast.LENGTH_SHORT).show();
                         }
                     });
         }else{
             Toast.makeText(this, "No pueden haber campos vacios", Toast.LENGTH_SHORT).show();
         }
    }

    private void actualizarAutobus(){
        if(validarCampos()) {
            // TOMA LOS DATOS DE LOS EDIT TEXT
            String placas = txtPlacas.getText().toString();
            String status = txtStatusAutobus.getText().toString();
            String asientos = txtNumero_Asientos.getText().toString();


            // LOS PREPARA EN UN MAP
            Map<String, String> datos = new HashMap<>();
            datos.put("id_ruta", placas);
            datos.put("descripcion", asientos);
            datos.put("duracion_aprox", status);

            // METE O PREPARA LOS DATOS EN UN JSON
            JSONObject jsonData = new JSONObject(datos);

            // ESPECIFICAR LA URL PARA QUE SE CARGUE EN JSON EN EL .php
            AndroidNetworking.post(Constantes.URL_ACTUALIZAR_AUTOBUS)
                    .addJSONObjectBody(jsonData)
                    .setPriority(Priority.MEDIUM)
                    .build()
                    .getAsJSONObject(new JSONObjectRequestListener() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                String estado = response.getString("estado");
                                String error = response.getString("error");
                                Toast.makeText(Autobuses.this, estado, Toast.LENGTH_SHORT).show();
                                //Toast.makeText(GuardarProductoActivity.this, "Error: "+error, Toast.LENGTH_SHORT).show();
                            } catch (JSONException e) {
                                Toast.makeText(Autobuses.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onError(ANError anError) {
                            Toast.makeText(Autobuses.this, "Error: " + anError.getErrorDetail(), Toast.LENGTH_SHORT).show();

                        }
                    });
        }else {
            Toast.makeText(this, "No pueden haber campos vacios", Toast.LENGTH_SHORT).show();
        }
    }

    private void eliminarAutobus(){
           String id = txtPlacas.getText().toString();
           Map<String, String> datos = new HashMap<>();
           datos.put("placas", id);

           AndroidNetworking.post(Constantes.URL_ELIMINAR_AUTOBUS)
                   .addJSONObjectBody(new JSONObject(datos))
                   .setPriority(Priority.MEDIUM)
                   .build()
                   .getAsJSONObject(new JSONObjectRequestListener() {
                       @Override
                       public void onResponse(JSONObject response) {

                           try {
                               String estado = response.getString("estado");
                               String error = response.getString("error");
                               Toast.makeText(Autobuses.this, estado, Toast.LENGTH_SHORT).show();
                               //Toast.makeText(GuardarProductoActivity.this, "Error: "+error, Toast.LENGTH_SHORT).show();
                           } catch (JSONException e) {
                               Toast.makeText(Autobuses.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                           }

                       }

                       @Override
                       public void onError(ANError anError) {
                           Toast.makeText(Autobuses.this, "Error: " + anError.getErrorDetail(), Toast.LENGTH_SHORT).show();
                       }
                   });

    }

    private void buscarPlacas(){

            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            JSONObject object = new JSONObject();
            String id = txtPlacas.getText().toString().trim();
            String url = Constantes.URL_BUSCAR_PLACAS + "?placas=" + id;

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, object, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        JSONObject datos = response.getJSONObject("data");

                        String asientos = datos.getString("numero_asientos");
                        String status = datos.getString("status");

                        txtStatusAutobus.setText(asientos);
                        txtNumero_Asientos.setText(status);

                    } catch (JSONException e) {
                        Toast.makeText(Autobuses.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    txtStatusAutobus.setText(error.toString());
                    txtNumero_Asientos.setText(error.toString());
                }
            });
            requestQueue.add(jsonObjectRequest);

    }

    private boolean validarCampos(){
        return !txtPlacas.getText().toString().trim().isEmpty() &&
                !txtStatusAutobus.getText().toString().isEmpty() &&
                !txtNumero_Asientos.getText().toString().isEmpty();
    }

    private void irVentanVentasFecha(){
        Intent intent = new Intent(this,ListaVentas.class);
        startActivity(intent);
    }
}
