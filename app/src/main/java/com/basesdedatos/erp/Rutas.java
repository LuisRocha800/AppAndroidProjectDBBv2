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

public class Rutas extends AppCompatActivity {

    private EditText txtIdRuta;
    private EditText txtDescripcionRuta;
    private EditText txtDuracionAprox;
    private EditText txtNombreRuta;

    private TextView textView36;

    private Button btnInsertarRuta;
    private Button btnActRuta;
    private Button btnBuscarIdRuta;
    private Button btnEliminarRuta;
    private Button listarRutas;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rutas);
        AndroidNetworking.initialize(getApplicationContext());

        txtIdRuta = findViewById(R.id.txtIdRuta);
        txtDescripcionRuta = findViewById(R.id.txtDescripcionRuta);
        txtDuracionAprox = findViewById(R.id.txtDuracionAprox);
        txtNombreRuta = findViewById(R.id.txtNombreRuta);

        btnInsertarRuta = findViewById(R.id.btnInsertarRuta);
        btnActRuta = findViewById(R.id.btnActRuta);
        btnBuscarIdRuta = findViewById(R.id.btnBuscarIdRuta);
        btnEliminarRuta = findViewById(R.id.btnEliminarRuta);
        //listarRutas = findViewById(R.id.listarRutas);

        btnInsertarRuta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertarRuta();
            }
        });

        btnActRuta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actualizarRuta();
            }
        });

        btnBuscarIdRuta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buscarRutaId();
            }
        });

        btnEliminarRuta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eliminarRuta();
            }
        });

       /* listarRutas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              irVentanVentasFecha();
            }
        });*/
    }

    private void insertarRuta() {
         if(validarCampos()) {
             //OBTENER DATOS DE LOS COMPONENTES DE LA VISTA
             String idProv = txtIdRuta.getText().toString();
             String nombreProv = txtNombreRuta.getText().toString();
             String nacProv = txtDuracionAprox.getText().toString();
             String infoContactoProv = txtDescripcionRuta.getText().toString();


             Map<String, String> datos = new HashMap<>();
             datos.put("id_ruta", idProv);
             datos.put("nombre", nombreProv);
             datos.put("descripcion", nacProv);
             datos.put("duracion_aprox", infoContactoProv);
             JSONObject jsonData = new JSONObject(datos);

             AndroidNetworking.post(Constantes.URL_INSERTAR_RUTA)

                     .addJSONObjectBody(jsonData)
                     .setPriority(Priority.MEDIUM)
                     .build()
                     .getAsJSONObject(new JSONObjectRequestListener() {
                         @Override
                         public void onResponse(JSONObject response) {
                             try {
                                 String estado = response.getString("estado");
                                 String error = response.getString("error");
                                 Toast.makeText(Rutas.this, estado, Toast.LENGTH_SHORT).show();


                             } catch (JSONException e) {
                                 Toast.makeText(Rutas.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                             }
                         }

                         @Override
                         public void onError(ANError anError) {
                             Toast.makeText(Rutas.this, "Error: " + anError.getErrorDetail(), Toast.LENGTH_SHORT).show();
                         }
                     });
         }else{
             Toast.makeText(this, "No pueden haber campos vacios", Toast.LENGTH_SHORT).show();
         }
    }

    private void actualizarRuta(){
        if(validarCampos()) {
            // TOMA LOS DATOS DE LOS EDIT TEXT
            String id = txtIdRuta.getText().toString();
            String nombre = txtNombreRuta.getText().toString();
            String duracion = txtDuracionAprox.getText().toString();
            String descripcion = txtDescripcionRuta.getText().toString();


            // LOS PREPARA EN UN MAP
            Map<String, String> datos = new HashMap<>();
            datos.put("id_ruta", id);
            datos.put("nombre", nombre);
            datos.put("descripcion", descripcion);
            datos.put("duracion_aprox", duracion);

            // METE O PREPARA LOS DATOS EN UN JSON
            JSONObject jsonData = new JSONObject(datos);

            // ESPECIFICAR LA URL PARA QUE SE CARGUE EN JSON EN EL .php
            AndroidNetworking.post(Constantes.URL_ACTUALIZAR_RUTA)
                    .addJSONObjectBody(jsonData)
                    .setPriority(Priority.MEDIUM)
                    .build()
                    .getAsJSONObject(new JSONObjectRequestListener() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                String estado = response.getString("estado");
                                String error = response.getString("error");
                                Toast.makeText(Rutas.this, estado, Toast.LENGTH_SHORT).show();
                                //Toast.makeText(GuardarProductoActivity.this, "Error: "+error, Toast.LENGTH_SHORT).show();
                            } catch (JSONException e) {
                                Toast.makeText(Rutas.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onError(ANError anError) {
                            Toast.makeText(Rutas.this, "Error: " + anError.getErrorDetail(), Toast.LENGTH_SHORT).show();

                        }
                    });
        }else {
            Toast.makeText(this, "No pueden haber campos vacios", Toast.LENGTH_SHORT).show();
        }
    }

    private void eliminarRuta(){
           String id = txtIdRuta.getText().toString();
           Map<String, String> datos = new HashMap<>();
           datos.put("id_ruta", id);

           AndroidNetworking.post(Constantes.URL_ELIMINAR_RUTA)
                   .addJSONObjectBody(new JSONObject(datos))
                   .setPriority(Priority.MEDIUM)
                   .build()
                   .getAsJSONObject(new JSONObjectRequestListener() {
                       @Override
                       public void onResponse(JSONObject response) {

                           try {
                               String estado = response.getString("estado");
                               String error = response.getString("error");
                               Toast.makeText(Rutas.this, estado, Toast.LENGTH_SHORT).show();
                               //Toast.makeText(GuardarProductoActivity.this, "Error: "+error, Toast.LENGTH_SHORT).show();
                           } catch (JSONException e) {
                               Toast.makeText(Rutas.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                           }

                       }

                       @Override
                       public void onError(ANError anError) {
                           Toast.makeText(Rutas.this, "Error: " + anError.getErrorDetail(), Toast.LENGTH_SHORT).show();
                       }
                   });

    }

    private void buscarRutaId(){

            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            JSONObject object = new JSONObject();
            String id = txtIdRuta.getText().toString().trim();
            String url = Constantes.URL_BUSCAR_RUTA_ID + "?id_ruta=" + id;

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, object, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        JSONObject datos = response.getJSONObject("data");
                        String nombre = datos.getString("nombre");
                        String descripcion = datos.getString("descripcion");
                        String duracion = datos.getString("duracion_aprox");
                        txtNombreRuta.setText(nombre);
                        txtDuracionAprox.setText(descripcion);
                        txtDescripcionRuta.setText(duracion);

                    } catch (JSONException e) {
                        Toast.makeText(Rutas.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    txtNombreRuta.setText(error.toString());
                    txtDuracionAprox.setText(error.toString());
                    txtDescripcionRuta.setText(error.toString());
                }
            });
            requestQueue.add(jsonObjectRequest);

    }

    private boolean validarCampos(){
        return !txtIdRuta.getText().toString().trim().isEmpty() &&
                !txtNombreRuta.getText().toString().trim().isEmpty() &&
                !txtDuracionAprox.getText().toString().isEmpty() &&
                !txtDescripcionRuta.getText().toString().isEmpty();
    }

    private void irVentanVentasFecha(){
        Intent intent = new Intent(this,ListaVentas.class);
        startActivity(intent);
    }
}
