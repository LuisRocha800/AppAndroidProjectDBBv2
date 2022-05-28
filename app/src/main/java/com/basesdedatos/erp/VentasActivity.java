package com.basesdedatos.erp;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
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

public class VentasActivity extends AppCompatActivity {

    private Button btn_consultar_venta;
    private Button btn_registrar_venta;
    private TextView txt_id_cliente;
    private TextView txt_id_manufactura;
    private TextView txt_id_empleado;
    private TextView txt_fecha_venta;

    private ListView list_ventas;
    private ArrayAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ventas_activity);
        AndroidNetworking.initialize(getApplicationContext());

        btn_consultar_venta = findViewById(R.id.btn_consultar_venta);
        btn_registrar_venta = findViewById(R.id.btn_registrar_venta);
        txt_id_cliente = findViewById(R.id.txt_id_cliente);
        txt_id_empleado = findViewById(R.id.txt_id_empleado);
        txt_id_manufactura = findViewById(R.id.txt_id_manufactura);
        txt_fecha_venta = findViewById(R.id.txt_fecha_venta);

        list_ventas = findViewById(R.id.lista_ventas);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);

        btn_consultar_venta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                consultarVenta();
            }
        });
    }

    private void consultarVenta(){
        list_ventas.setAdapter(adapter);

        String id_venta = txt_id_cliente.getText().toString();

        Map<String,String> datos = new HashMap<>();
        datos.put("id", id_venta);
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
                                Toast.makeText(VentasActivity.this, "No puede haber campos vacios ",Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            Toast.makeText(VentasActivity.this, "Error: "+e.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(VentasActivity.this, "Error: "+anError.getErrorDetail(),Toast.LENGTH_SHORT).show();
                    }
                });

    }

    private boolean isValidarCampos(){
        return !txt_id_cliente.getText().toString().trim().isEmpty() &&
                !txt_id_empleado.getText().toString().trim().isEmpty() &&
                !txt_id_manufactura.getText().toString().trim().isEmpty() &&
                !txt_fecha_venta.getText().toString().trim().isEmpty();
    }
}
