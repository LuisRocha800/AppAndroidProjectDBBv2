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

public class ManufacturaAdquiridaActivity extends AppCompatActivity {

    private TextView RFCCliente;

    private EditText txtIdManufactura;
    private EditText txtDireccionMan;
    private EditText txtCostoMan;
    private EditText txtFecAdqMan;

    private Button btnBuscarManufactura;
    private Button btnSolMantenimiento;
    private Button btnHistorialMantenimiento;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manufactura_adquirida);

        txtIdManufactura = findViewById(R.id.txtIdManufactura);
        txtDireccionMan = findViewById(R.id.txtDireccionMan);
        txtCostoMan = findViewById(R.id.txtCostoMan);
        txtFecAdqMan = findViewById(R.id.txtFecAdqMan);
        RFCCliente = findViewById(R.id.rfcCliente);

        btnBuscarManufactura = findViewById(R.id.btnBuscarManufactura);
        btnSolMantenimiento = findViewById(R.id.btnSolMantenimiento);
        btnHistorialMantenimiento = findViewById(R.id.btnHistorialMantenimiento);

        btnBuscarManufactura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buscarIdMantenimiento();
            }
        });

        btnSolMantenimiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             solicMantenimiento();
            }
        });

        btnHistorialMantenimiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                historialMantenimiento();
            }
        });

    }

    private void buscarIdMantenimiento(){

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JSONObject object = new JSONObject();
        String id = txtIdManufactura.getText().toString().trim();
        String url = Constantes.URL_MANUFACTURA_ID+"?id_manufactura="+id;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, object, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject datos = response.getJSONObject("data");
                    String calle = datos.getString("calle");
                    String colonia = datos.getString("colonia");
                    String localidad = datos.getString("localidad");
                    String entidad = datos.getString("entidad_federativa");
                    String cp = datos.getString("cp");
                    String costoT = datos.getString("costo_total");
                    String fecAdq = datos.getString("fecha_termino");

                    txtDireccionMan.setText(calle+","+colonia+","+localidad+","+entidad+","+cp);
                    txtCostoMan.setText(costoT);
                    txtFecAdqMan.setText(fecAdq);
                }catch(JSONException e){
                    Toast.makeText(ManufacturaAdquiridaActivity.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                txtDireccionMan.setText(error.toString());
                txtCostoMan.setText(error.toString());
                txtFecAdqMan.setText(error.toString());
            }
        });
        requestQueue.add(jsonObjectRequest);

    }

    private void solicMantenimiento(){

        Intent intent = new Intent(this,SolicitudMantenimiento.class);
        intent.putExtra("datoIdInmueble",txtIdManufactura.getText().toString());
        startActivity(intent);
    }

    private void historialMantenimiento(){

        Intent intent = new Intent(this,HistorialMantenimientoActivity.class);
        intent.putExtra("datoIdInmueble",txtIdManufactura.getText().toString());
        startActivity(intent);
    }
}
