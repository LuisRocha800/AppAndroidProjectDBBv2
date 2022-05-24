package com.basesdedatos.erp;

import android.os.Bundle;
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

public class MenuAdministrador extends AppCompatActivity {

    public TextView mostNombreAdm;
    public TextView mostApellidoAdm;
    public TextView mostRFCAdm;
    public TextView mostEmailAdm;
    public TextView mostDireccionAdm;
    public TextView mostTelefonoAdm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_administrador);
        AndroidNetworking.initialize(getApplicationContext());

        mostNombreAdm = findViewById(R.id.mostNombreAdmin);
        mostApellidoAdm = findViewById(R.id.mostApellidoAdmin);
        mostRFCAdm = findViewById(R.id.mostRFCAdmin);
        mostEmailAdm = findViewById(R.id.mostEmailAdmin);
        mostDireccionAdm = findViewById(R.id.mostDireccionAdmin);
        mostTelefonoAdm = findViewById(R.id.mostTelefonoAdmin);

        String datoRFC = getIntent().getStringExtra("datoRFCAdm");
        mostRFCAdm.setText(datoRFC);

        mostrarDatosAdmin();
    }

    public void mostrarDatosAdmin(){
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JSONObject object = new JSONObject();
        String RFC = mostRFCAdm.getText().toString().trim();
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
                    mostNombreAdm.setText(nombre);
                    mostApellidoAdm.setText(apellidop+" "+apellidom);
                    mostTelefonoAdm.setText(telefono);
                    mostEmailAdm.setText(email);
                    mostDireccionAdm.setText(calle+", "+localidad+", "+entidad_federativa+", "+cp);
                }catch(JSONException e){
                    Toast.makeText(MenuAdministrador.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                mostNombreAdm.setText(error.toString());
                mostApellidoAdm.setText(error.toString());
                mostTelefonoAdm.setText(error.toString());
                mostEmailAdm.setText(error.toString());
                mostDireccionAdm.setText(error.toString());
            }
        });
        requestQueue.add(jsonObjectRequest);
    }
}
