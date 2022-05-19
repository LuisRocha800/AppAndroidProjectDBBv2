package com.basesdedatos.erp;

import android.os.Bundle;
import android.widget.ArrayAdapter;
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

public class TotalComprasActivity extends AppCompatActivity {

    private TextView mostRFC;

    private ListView listaTotalCompras;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.total_compras_activity);
        AndroidNetworking.initialize(getApplicationContext());

        listaTotalCompras = findViewById(R.id.listaTotalCompras);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);

        mostRFC = findViewById(R.id.mostRFC);

        String datoRFC = getIntent().getStringExtra("RFCCliente");
        mostRFC.setText(datoRFC);

        mostrarCompras();
    }

  private void mostrarCompras(){
      listaTotalCompras.setAdapter(adapter);

      String RFC = mostRFC.getText().toString();


      Map<String,String> datos = new HashMap<>();
      datos.put("rfc", RFC);
      JSONObject jsonData = new JSONObject(datos);
      AndroidNetworking.post(Constantes.URL_CLIENTE_TOTAL_COMPRAS)
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
                                  String desc = "DESCRIPCION: " + jsonProducto.getString("descripcion");
                                  String fecha = "FECHA: " + jsonProducto.getString("fecha");
                                  String monto = "MONTO: " + jsonProducto.getString("monto");


                                  String dataString = desc + "\n" + fecha + " \n" + monto;
                                  adapter.add(dataString);
                              }
                          }else{
                              Toast.makeText(TotalComprasActivity.this, "Aun no tiene ninguna compra",Toast.LENGTH_SHORT).show();
                          }
                      } catch (JSONException e) {
                          Toast.makeText(TotalComprasActivity.this, "Error: "+e.getMessage(),Toast.LENGTH_SHORT).show();
                      }
                  }
                  @Override
                  public void onError(ANError anError) {
                      Toast.makeText(TotalComprasActivity.this, "Error: "+anError.getErrorDetail(),Toast.LENGTH_SHORT).show();
                  }
              });
  }
  }

