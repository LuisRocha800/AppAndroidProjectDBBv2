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

public class Prueba extends AppCompatActivity {

    private EditText txtIdProveedor;
    private EditText txtInfoContactoProveedor;
    private EditText txtNacionalidadProveedor;
    private EditText txtNombreProveedor;

    private TextView textView36;

    private Button btnInsertarProv;
    private Button btnActProv;
    private Button btnBuscarIdProv;
    private Button btnEliminarProv;
    private Button listarProveedores;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prueba);
        AndroidNetworking.initialize(getApplicationContext());

        txtIdProveedor = findViewById(R.id.txtIdProveedor);
        txtInfoContactoProveedor = findViewById(R.id.txtInfoContactoProveedor);
        txtNacionalidadProveedor = findViewById(R.id.txtNacionalidadProveedor);
        txtNombreProveedor = findViewById(R.id.txtNombreProveedor);

        btnInsertarProv = findViewById(R.id.btnInsertarProv);
        btnActProv = findViewById(R.id.btnActProv);
        btnBuscarIdProv = findViewById(R.id.btnBuscarIdProv);
        btnEliminarProv = findViewById(R.id.btnEliminarProv);
        listarProveedores = findViewById(R.id.listarProveedores);

        btnInsertarProv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertarProveedor();
            }
        });

        btnActProv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actualizarProveedor();
            }
        });

        btnBuscarIdProv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buscarProveedorId();
            }
        });

        btnEliminarProv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eliminarProveedor();
            }
        });

        listarProveedores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              irVentanVentasFecha();
            }
        });
    }

    private void insertarProveedor() {
         if(validarCampos()) {
             //OBTENER DATOS DE LOS COMPONENTES DE LA VISTA
             String idProv = txtIdProveedor.getText().toString();
             String nombreProv = txtNombreProveedor.getText().toString();
             String nacProv = txtNacionalidadProveedor.getText().toString();
             String infoContactoProv = txtInfoContactoProveedor.getText().toString();


             Map<String, String> datos = new HashMap<>();
             datos.put("id_proveedor", idProv);
             datos.put("nombre", nombreProv);
             datos.put("nacionalidad", nacProv);
             datos.put("info_contacto", infoContactoProv);
             JSONObject jsonData = new JSONObject(datos);

             AndroidNetworking.post(Constantes.URL_INSERTAR_PROVEEDOR)
                     .addJSONObjectBody(jsonData)
                     .setPriority(Priority.MEDIUM)
                     .build()
                     .getAsJSONObject(new JSONObjectRequestListener() {
                         @Override
                         public void onResponse(JSONObject response) {
                             try {
                                 String estado = response.getString("estado");
                                 String error = response.getString("error");
                                 Toast.makeText(Prueba.this, estado, Toast.LENGTH_SHORT).show();


                             } catch (JSONException e) {
                                 Toast.makeText(Prueba.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                             }
                         }

                         @Override
                         public void onError(ANError anError) {
                             Toast.makeText(Prueba.this, "Error: " + anError.getErrorDetail(), Toast.LENGTH_SHORT).show();
                         }
                     });
         }else{
             Toast.makeText(this, "No pueden haber campos vacios", Toast.LENGTH_SHORT).show();
         }
    }

    private void actualizarProveedor(){
        if(validarCampos()) {
            // TOMA LOS DATOS DE LOS EDIT TEXT
            String id = txtIdProveedor.getText().toString();
            String nombre = txtNombreProveedor.getText().toString();
            String nacionalidad = txtNacionalidadProveedor.getText().toString();
            String contacto = txtInfoContactoProveedor.getText().toString();


            // LOS PREPARA EN UN MAP
            Map<String, String> datos = new HashMap<>();
            datos.put("id_proveedor", id);
            datos.put("nombre", nombre);
            datos.put("nacionalidad", nacionalidad);
            datos.put("info_contacto", contacto);

            // METE O PREPARA LOS DATOS EN UN JSON
            JSONObject jsonData = new JSONObject(datos);

            // ESPECIFICAR LA URL PARA QUE SE CARGUE EN JSON EN EL .php
            AndroidNetworking.post(Constantes.URL_ACTUALIZAR_PROVEEDOR)
                    .addJSONObjectBody(jsonData)
                    .setPriority(Priority.MEDIUM)
                    .build()
                    .getAsJSONObject(new JSONObjectRequestListener() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                String estado = response.getString("estado");
                                String error = response.getString("error");
                                Toast.makeText(Prueba.this, estado, Toast.LENGTH_SHORT).show();
                                //Toast.makeText(GuardarProductoActivity.this, "Error: "+error, Toast.LENGTH_SHORT).show();
                            } catch (JSONException e) {
                                Toast.makeText(Prueba.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onError(ANError anError) {
                            Toast.makeText(Prueba.this, "Error: " + anError.getErrorDetail(), Toast.LENGTH_SHORT).show();

                        }
                    });
        }else {
            Toast.makeText(this, "No pueden haber campos vacios", Toast.LENGTH_SHORT).show();
        }
    }

    private void eliminarProveedor(){
           String id = txtIdProveedor.getText().toString();
           Map<String, String> datos = new HashMap<>();
           datos.put("id_proveedor", id);

           AndroidNetworking.post(Constantes.URL_ELIMINAR_PROVEEDOR)
                   .addJSONObjectBody(new JSONObject(datos))
                   .setPriority(Priority.MEDIUM)
                   .build()
                   .getAsJSONObject(new JSONObjectRequestListener() {
                       @Override
                       public void onResponse(JSONObject response) {

                           try {
                               String estado = response.getString("estado");
                               String error = response.getString("error");
                               Toast.makeText(Prueba.this, estado, Toast.LENGTH_SHORT).show();
                               //Toast.makeText(GuardarProductoActivity.this, "Error: "+error, Toast.LENGTH_SHORT).show();
                           } catch (JSONException e) {
                               Toast.makeText(Prueba.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                           }

                       }

                       @Override
                       public void onError(ANError anError) {
                           Toast.makeText(Prueba.this, "Error: " + anError.getErrorDetail(), Toast.LENGTH_SHORT).show();
                       }
                   });

    }
    private void buscarProveedorId(){

            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            JSONObject object = new JSONObject();
            String id = txtIdProveedor.getText().toString().trim();
            String url = Constantes.URL_BUSCAR_PROVEEDOR_ID + "?id_proveedor=" + id;

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, object, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        JSONObject datos = response.getJSONObject("data");
                        String nombre = datos.getString("nombre");
                        String nacionalidad = datos.getString("nacionalidad");
                        String contacto = datos.getString("info_contacto");
                        txtNombreProveedor.setText(nombre);
                        txtNacionalidadProveedor.setText(nacionalidad);
                        txtInfoContactoProveedor.setText(contacto);

                    } catch (JSONException e) {
                        Toast.makeText(Prueba.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    txtNombreProveedor.setText(error.toString());
                    txtNacionalidadProveedor.setText(error.toString());
                    txtInfoContactoProveedor.setText(error.toString());
                }
            });
            requestQueue.add(jsonObjectRequest);

    }

    private boolean validarCampos(){
        return !txtIdProveedor.getText().toString().trim().isEmpty() &&
                !txtNombreProveedor.getText().toString().trim().isEmpty() &&
                !txtNacionalidadProveedor.getText().toString().isEmpty() &&
                !txtInfoContactoProveedor.getText().toString().isEmpty();
    }

    private void irVentanVentasFecha(){
        Intent intent = new Intent(this,ListaVentas.class);
        startActivity(intent);
    }
}
