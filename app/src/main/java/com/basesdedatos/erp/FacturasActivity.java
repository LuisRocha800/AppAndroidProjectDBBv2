package com.basesdedatos.erp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

public class FacturasActivity extends AppCompatActivity {

    private Button btn_generar_factura;
    private Button btn_consultar_factura;
    private Button btn_eliminar_factura;
    private Button btn_modificar_factura;

    private TextView txt_id_venta;
    private TextView txt_fecha_emision;
    private TextView txt_lugar_emision;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidNetworking.initialize(getApplicationContext());

        btn_consultar_factura = findViewById(R.id.btn_consultar_factura);
        btn_generar_factura = findViewById(R.id.btn_generar_factura);
        btn_eliminar_factura = findViewById(R.id.btn_eliminar_factura);
        btn_modificar_factura = findViewById(R.id.btn_modificar_factura);
        txt_id_venta = findViewById(R.id.txt_id_venta);
        txt_fecha_emision = findViewById(R.id.txt_fecha_emision);
        txt_lugar_emision = findViewById(R.id.txt_lugar_emision);

        btn_generar_factura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generar_factura();
            }
        });

        btn_modificar_factura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modificar_factura();
            }
        });

        btn_consultar_factura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                consultar_factura();
            }
        });

        btn_eliminar_factura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eliminar_factura();
            }
        });
    }

    private void generar_factura(){
        if(isValidarCampos()){

            String id_venta = txt_id_venta.getText().toString();
            String fecha_emision = txt_fecha_emision.getText().toString();
            String lugar_emision = txt_lugar_emision.getText().toString();

            Map<String,String> datos = new HashMap<>();
            datos.put("venta_id", id_venta);
            datos.put("fecha_emision", fecha_emision);
            datos.put("lugar_emision", lugar_emision);
            JSONObject jsonData = new JSONObject(datos);

            AndroidNetworking.post(Constantes.URL_CONSULTA_FECHAS)
                    .addJSONObjectBody(jsonData)
                    .setPriority(Priority.MEDIUM)
                    .build()
                    .getAsJSONObject(new JSONObjectRequestListener() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                String estado = response.getString("estado");
                                String error = response.getString("error");
                                Toast.makeText(FacturasActivity.this, estado, Toast.LENGTH_SHORT).show();

                            }catch (JSONException e){
                                Toast.makeText(FacturasActivity.this, "Error: "+e.getMessage(),Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onError(ANError anError) {
                            Toast.makeText(FacturasActivity.this, "Error: "+anError.getErrorDetail(), Toast.LENGTH_SHORT).show();
                        }
                    });

        }else{
            Toast.makeText(this, "No se puede generar la factura si existen campos vacios",Toast.LENGTH_SHORT).show();
        }
    }

    private void eliminar_factura(){
        if(isValidarCampos()){

            String id = txt_id_venta.getText().toString();
            Map<String,String> datos = new HashMap<>();
            datos.put("venta_id",id);

            AndroidNetworking.post(Constantes.URL_CONSULTA_FECHAS)
                    .addJSONObjectBody(new JSONObject(datos))
                    .setPriority(Priority.MEDIUM)
                    .build()
                    .getAsJSONObject(new JSONObjectRequestListener() {
                        @Override
                        public void onResponse(JSONObject response) {

                            try {
                                String estado = response.getString("estado");
                                String error = response.getString("error");
                                Toast.makeText(FacturasActivity.this, estado, Toast.LENGTH_SHORT).show();
                                //Toast.makeText(GuardarProductoActivity.this, "Error: "+error, Toast.LENGTH_SHORT).show();
                            } catch (JSONException e) {
                                Toast.makeText(FacturasActivity.this, "Error: "+e.getMessage(), Toast.LENGTH_SHORT).show();
                            }

                        }

                        @Override
                        public void onError(ANError anError) {
                            Toast.makeText(FacturasActivity.this, "Error: "+anError.getErrorDetail(), Toast.LENGTH_SHORT).show();
                        }
                    });
        }else{
            Toast.makeText(this,"Los campos no pueden estar vacios",Toast.LENGTH_SHORT).show();
        }
    }

    private void modificar_factura(){
        if (isValidarCampos()) { //SE ACTUALIZA EL PRODUCTO

            // TOMA LOS DATOS DE LOS EDIT TEXT
            String id_venta = txt_id_venta.getText().toString();
            String fecha_emision = txt_fecha_emision.getText().toString();
            String lugar_emision = txt_lugar_emision.getText().toString();


            // LOS PREPARA EN UN MAP
            Map<String, String> datos = new HashMap<>();
            datos.put("id_venta", id_venta);
            datos.put("fecha_emision", fecha_emision);
            datos.put("lugar_emision", lugar_emision);

            // METE O PREPARA LOS DATOS EN UN JSON
            JSONObject jsonData = new JSONObject(datos);

            // ESPECIFICAR LA URL PARA QUE SE CARGUE EN JSON EN EL .php
            AndroidNetworking.post(Constantes.URL_CONSULTA_FECHAS)
                    .addJSONObjectBody(jsonData)
                    .setPriority(Priority.MEDIUM)
                    .build()
                    .getAsJSONObject(new JSONObjectRequestListener() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                String estado = response.getString("estado");
                                String error = response.getString("error");
                                Toast.makeText(FacturasActivity.this, estado, Toast.LENGTH_SHORT).show();
                                //Toast.makeText(GuardarProductoActivity.this, "Error: "+error, Toast.LENGTH_SHORT).show();
                            } catch (JSONException e) {
                                Toast.makeText(FacturasActivity.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onError(ANError anError) {
                            Toast.makeText(FacturasActivity.this, "Error: " + anError.getErrorDetail(), Toast.LENGTH_SHORT).show();

                        }
                    });
        } else {
            Toast.makeText(this, "Existen campos vacios, no se puede actualizar", Toast.LENGTH_SHORT).show();
        }
    }

    private void consultar_factura(){
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JSONObject object = new JSONObject();
        String id = txt_id_venta.getText().toString().trim();
        String url = Constantes.URL_CLIENTE_X_ID+"?id="+id;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, object, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject datos = response.getJSONObject("data");
                    String id_venta = datos.getString("venta_id");
                    String fecha_emision = datos.getString("fecha_emision");
                    String lugar_emision = datos.getString("lugar_emision");
                    txt_id_venta.setText(id_venta);
                    txt_fecha_emision.setText(fecha_emision);
                    txt_lugar_emision.setText(lugar_emision);
                }catch(JSONException e){
                    Toast.makeText(FacturasActivity.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                txt_id_venta.setText(error.toString());
                txt_fecha_emision.setText(error.toString());
                txt_lugar_emision.setText(error.toString());
            }
        });
        requestQueue.add(jsonObjectRequest);

    }

    private boolean isValidarCampos(){
        return !txt_id_venta.getText().toString().trim().isEmpty() &&
                !txt_fecha_emision.getText().toString().trim().isEmpty() &&
                !txt_lugar_emision.getText().toString().trim().isEmpty();
    }
}
