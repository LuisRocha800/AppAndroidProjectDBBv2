package com.basesdedatos.erp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MenuFinanzas extends AppCompatActivity{
    private Button btn_activos;
    private Button btn_pasivos;
    private Button btn_facturas;
    private Button btn_consulta_personalizada;

    private ListView list_activos_pasivos;
    ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_finanzas);
        AndroidNetworking.initialize(getApplicationContext());

        btn_activos = findViewById(R.id.btn_activos);
        btn_pasivos = findViewById(R.id.btn_pasivos);
        btn_facturas = findViewById(R.id.btn_facturas);
        btn_consulta_personalizada = findViewById(R.id.btn_consulta_personalizada);
        list_activos_pasivos = findViewById(R.id.lista_activos_pasivos);
        adapter = new ArrayAdapter<String >(this,android.R.layout.simple_list_item_1);

        btn_activos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                consultaActivos();
            }
        });

        btn_pasivos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                consultaPasivos();
            }
        });

        btn_facturas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irAFacturas();
            }
        });

        btn_consulta_personalizada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public void consultaActivos(){
        list_activos_pasivos.setAdapter(adapter);

        Map<String,String> datos = new HashMap<>();
        //datos.put("fecha1", fecuno);
        JSONObject jsonData = new JSONObject(datos);

        AndroidNetworking.post(Constantes.URL_CONSULTA_VENTA)
                .addJSONObjectBody(jsonData)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String respuesta = response.getString("respuesta");
                            if (respuesta.equals("200")){
                                JSONArray arrayProductos = response.getJSONArray("data");
                                for(int i=0;i<arrayProductos.length();i++){
                                    JSONObject jsonProducto = arrayProductos.getJSONObject(i);
                                    String id = "ID: " + jsonProducto.getString("id");
                                    String descripcion = "DESCRIPCION: " + jsonProducto.getString("descripcion");
                                    String fecha = "FECHA: " + jsonProducto.getString("fecha");
                                    String monto = "MONTO: " + jsonProducto.getDouble("monto") + " pesos MXN";
                                    String nombrecliente = "NOMBRE CLIENTE: " + jsonProducto.getString("nombrecliente");
                                    String apellidocliente = "APELLIDO CLIENTE: " + jsonProducto.getString("apellidocliente");

                                    String dataString = id + "\n" + descripcion + " \n" + fecha + " \n" + monto+ " \n" + nombrecliente+ " \n" + apellidocliente;
                                    adapter.add(dataString);
                                }
                            }else{
                                Toast.makeText(MenuFinanzas.this, "No puede haber campos vacios ",Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            Toast.makeText(MenuFinanzas.this, "Error: "+e.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(MenuFinanzas.this, "Error: "+anError.getErrorDetail(),Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void consultaPasivos(){

    }

    public void irAFacturas(){

    }

    public void consultaPersonalizada(){
        Intent intent = new Intent(this, FacturasActivity.class);
        startActivity(intent);
    }

}
