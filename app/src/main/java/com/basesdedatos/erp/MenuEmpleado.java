package com.basesdedatos.erp;

import android.os.Bundle;
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

import org.json.JSONException;
import org.json.JSONObject;

public class MenuEmpleado extends AppCompatActivity {

    public TextView mostNombreEmpleado;
    public TextView mostApellidoEmpleado;
    public TextView mostRFCEmpleado;
    public TextView mostEmailEmpleado;
    public TextView mostDireccionEmpleado;
    public TextView mostTelefonoEmpleado;

    private Button btnMostVentas;
    private Button btnInfoEmpleado;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_empleado);
        AndroidNetworking.initialize(getApplicationContext());

        mostNombreEmpleado = findViewById(R.id.mostNombreEmpleado);
        mostApellidoEmpleado = findViewById(R.id.mostApellidoEmpleado);
        mostRFCEmpleado = findViewById(R.id.mostRFCEmpleado);
        mostEmailEmpleado = findViewById(R.id.mostEmailEmpleado);
        mostDireccionEmpleado = findViewById(R.id.mostDireccionEmpleado);
        mostTelefonoEmpleado = findViewById(R.id.mostTelefonoEmpleado);

        String datoRFC = getIntent().getStringExtra("datoRFCEmpleado");
        mostRFCEmpleado.setText(datoRFC);

        mostrarDatosEmpleado();
    }

    public void mostrarDatosEmpleado(){
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JSONObject object = new JSONObject();
        String RFC = mostRFCEmpleado.getText().toString().trim();
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
                    mostNombreEmpleado.setText(nombre);
                    mostApellidoEmpleado.setText(apellidop+" "+apellidom);
                    mostTelefonoEmpleado.setText(telefono);
                    mostEmailEmpleado.setText(email);
                    mostDireccionEmpleado.setText(calle+", "+localidad+", "+entidad_federativa+", "+cp);
                }catch(JSONException e){
                    Toast.makeText(MenuEmpleado.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                mostNombreEmpleado.setText(error.toString());
                mostApellidoEmpleado.setText(error.toString());
                mostTelefonoEmpleado.setText(error.toString());
                mostEmailEmpleado.setText(error.toString());
                mostDireccionEmpleado.setText(error.toString());
            }
        });
        requestQueue.add(jsonObjectRequest);
    }
}
