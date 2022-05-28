package com.basesdedatos.erp;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class HistorialMantenimientoActivity extends AppCompatActivity {

    private TextView txtIdManufacturaHistorial;

    private ListView listaDeMantenimiento;
    private ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.historial_mantenimiento_activity);
        AndroidNetworking.initialize(getApplicationContext());

        txtIdManufacturaHistorial = findViewById(R.id.txtIdManufacturaHistorial);

        listaDeMantenimiento = findViewById(R.id.listaDeMantenimiento);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);

        String datoRFC = getIntent().getStringExtra("datoIdInmueble");
        txtIdManufacturaHistorial.setText(datoRFC);

        mostrarHistorialMantenimiento();
    }

    private void mostrarHistorialMantenimiento(){

        listaDeMantenimiento.setAdapter(adapter);

        String id = txtIdManufacturaHistorial.getText().toString();

        Map<String,String> datos = new HashMap<>();
        datos.put("id_manufac", id);
        JSONObject jsonData = new JSONObject(datos);

        AndroidNetworking.post(Constantes.URL_HISTORIAL_DEFECTOS2)
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
                                    String folioManu = "FOLIO INMUEBLE: " + jsonProducto.getString("id_manufac");
                                    String folioDefecto = "FOLIO DEFECTO: " + jsonProducto.getString("id_defecto");
                                    String tipo = "TIPO DEFECTO: " + jsonProducto.getString("tipo");
                                    String descripcion = "DESCRIPCION: " + jsonProducto.getString("descripcion");


                                    String dataString = folioManu + "\n" + folioDefecto+ "\n" + tipo + "\n" + descripcion;
                                    adapter.add(dataString);
                                }
                            }else{
                                Toast.makeText(HistorialMantenimientoActivity.this, "No ha especificado el id de su inmueble",Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            Toast.makeText(HistorialMantenimientoActivity.this, "Error: "+e.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(HistorialMantenimientoActivity.this, "Error: "+anError.getErrorDetail(),Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
