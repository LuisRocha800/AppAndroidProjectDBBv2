package com.basesdedatos.erp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
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

public class MenuCliente extends AppCompatActivity {

    public TextView mostNombreCliente;
    public TextView mostApellidoCliente;
    public TextView mostRFCCliente;
    public TextView mostEmailCliente;
    public TextView mostDireccionCliente;
    public TextView mostTelefonoCliente;

    private Button btnMostCompras;
    private Button btnInfoCliente;
    private Button btnMostrarOpcionesC;

    private ListView verUltimaCompra;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_cliente);
        AndroidNetworking.initialize(getApplicationContext());

        mostNombreCliente = findViewById(R.id.mostNombreCliente);
        mostApellidoCliente = findViewById(R.id.mostApellidoCliente);
        mostRFCCliente = findViewById(R.id.mostRFCCliente);
        mostEmailCliente = findViewById(R.id.mostEmailCliente);
        mostDireccionCliente = findViewById(R.id.mostDireccionCliente);
        mostTelefonoCliente = findViewById(R.id.mostTelefonoCliente);

        verUltimaCompra = findViewById(R.id.verUltimaCompra);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);

        btnMostCompras = findViewById(R.id.btnMostCompras);
        btnInfoCliente = findViewById(R.id.btnInfoCliente);

        btnMostrarOpcionesC = findViewById(R.id.btnMostrarOpcionesC);

        String datoRFC = getIntent().getStringExtra("datoUsuarioG");
        mostRFCCliente.setText(datoRFC);

        mostrarDatos();
        //mostrarUltCompra();

        btnMostCompras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                totalCompras();
            }
        });

        btnInfoCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               irActualizarInformacionP();
            }
        });

        btnMostrarOpcionesC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irPanelOpcionesC();
            }
        });
        }

    public void mostrarDatos(){

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JSONObject object = new JSONObject();
        String RFC = mostRFCCliente.getText().toString().trim();
        String url = Constantes.URL_PERSONA_ID_WEB_SERVICE+"?rfc="+RFC;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, object, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject datos = response.getJSONObject("data");
                    String nombre = datos.getString("nombre");
                    String apellidop = datos.getString("apellido1");
                    String apellidom = datos.getString("apellido2");
                    String telefono = datos.getString("telefono");
                    String email = datos.getString("email_persona");
                    String calle = datos.getString("calle");
                    String numero_ext = datos.getString("numero_ext");
                    String numero_int = datos.getString("numero_int");
                    String localidad = datos.getString("localidad");
                    String entidad_federativa = datos.getString("entidad_federativa");
                    String cp = datos.getString("cp");
                    mostNombreCliente.setText(nombre);
                    mostApellidoCliente.setText(apellidop+" "+apellidom);
                    mostTelefonoCliente.setText(telefono);
                    mostEmailCliente.setText(email);
                    mostDireccionCliente.setText(calle+", "+localidad+", "+entidad_federativa+", "+cp);
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

 /*   public void mostrarUltCompra(){
        verUltimaCompra.setAdapter(adapter);

        String RFC = mostRFCCliente.getText().toString();


        Map<String,String> datos = new HashMap<>();
        datos.put("rfc", RFC);
        JSONObject jsonData = new JSONObject(datos);
        AndroidNetworking.post(Constantes.URL_CLIENTE_X_ID)
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
                                Toast.makeText(MenuCliente.this, "Aun no tiene ninguna compra",Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            Toast.makeText(MenuCliente.this, "Error: "+e.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(MenuCliente.this, "Error: "+anError.getErrorDetail(),Toast.LENGTH_SHORT).show();
                    }
                });
    }**/

    private void irActualizarInformacionP(){
        Intent intent = new Intent(this,ActualizarInformacionCliente.class);
        intent.putExtra("RFCClienteA",mostRFCCliente.getText().toString());
        startActivity(intent);
    }
    public void totalCompras(){
        Intent intent  = new Intent(this,TotalComprasActivity.class);
        intent.putExtra("RFCCliente",mostRFCCliente.getText().toString());
        startActivity(intent);
    }

    private void irPanelOpcionesC(){
        Intent intent = new Intent(this,OpcionesCliente.class);
        startActivity(intent);
    }
}


