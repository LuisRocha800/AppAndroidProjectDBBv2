package com.basesdedatos.erp;

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

public class ActualizarInformacionAdministrador extends AppCompatActivity {

    private TextView txtRFCAdministrador;
    private EditText txtNombreA;
    private EditText txtApellidoPA;
    private EditText txtApellidoMA;
    private EditText txtTelefonoA;
    private EditText txtemailA;
    private EditText txtCPA;
    private EditText txtEntidadA;
    private EditText txtLocalidadA;
    private EditText txtColoniaA;
    private EditText txtCalleA;
    private EditText txtNumExtA;
    private EditText txtNumIntA;
    private EditText txtCedCastralA;

    private TextView txtidDireccionA;

    private Button btnActDatosA;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actualizar_informacion_administrador);

        txtRFCAdministrador = findViewById(R.id.txtRFCAdministrador);
        txtNombreA = findViewById(R.id.txtNombreA);
        txtApellidoPA = findViewById(R.id.txtApellidoPA);
        txtApellidoMA = findViewById(R.id.txtApellidoMA);
        txtTelefonoA = findViewById(R.id.txtTelefonoA);
        txtemailA = findViewById(R.id.txtemailA);
        txtCPA = findViewById(R.id.txtCPA);
        txtEntidadA = findViewById(R.id.txtEntidadA);
        txtLocalidadA = findViewById(R.id.txtLocalidadA);
        txtColoniaA = findViewById(R.id.txtColoniaA);
        txtCalleA = findViewById(R.id.txtCalleA);
        txtNumExtA = findViewById(R.id.txtNumExtA);
        txtNumIntA = findViewById(R.id.txtNumIntA);
        txtCedCastralA = findViewById(R.id.txtCedCastralA);
        txtidDireccionA = findViewById(R.id.txtidDireccionA);

        btnActDatosA = findViewById(R.id.btnActDatosA);

        String datoRFCC = getIntent().getStringExtra("RFCAdmin");
        txtRFCAdministrador.setText(datoRFCC);

        mostrarDatosA();

        btnActDatosA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actualizarInfoDireccionA();
                actualizarInfoPersonaAdministrador();

            }
        });
    }

    private void mostrarDatosA(){

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JSONObject object = new JSONObject();
        String RFC = txtRFCAdministrador.getText().toString().trim();
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
                    String colonia = datos.getString("colonia");
                    String cedulaCas = datos.getString("cedula_catastral");
                    String idDireccion = datos.getString("id_direccion");
                    txtNombreA.setText(nombre);
                    txtApellidoPA.setText(apellidop);
                    txtApellidoMA.setText(apellidom);
                    txtTelefonoA.setText(telefono);
                    txtemailA.setText(email);
                    txtCPA.setText(cp);
                    txtEntidadA.setText(entidad_federativa);
                    txtLocalidadA.setText(localidad);
                    txtColoniaA.setText(colonia);
                    txtCalleA.setText(calle);
                    txtNumExtA.setText(numero_ext);
                    txtNumIntA.setText(numero_int);
                    txtCedCastralA.setText(cedulaCas);

                    txtidDireccionA.setText(idDireccion);


                }catch(JSONException e){
                    Toast.makeText(ActualizarInformacionAdministrador.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                txtNombreA.setText(error.toString());
                txtApellidoPA.setText(error.toString());
                txtApellidoMA.setText(error.toString());
                txtTelefonoA.setText(error.toString());
                txtemailA.setText(error.toString());
                txtCPA.setText(error.toString());
                txtEntidadA.setText(error.toString());
                txtColoniaA.setText(error.toString());
                txtCalleA.setText(error.toString());
                txtNumExtA.setText(error.toString());
                txtNumIntA.setText(error.toString());
                txtCedCastralA.setText(error.toString());
                txtidDireccionA.setText(error.toString());

            }
        });
        requestQueue.add(jsonObjectRequest);

    }

    private void actualizarInfoPersonaAdministrador(){
        if (validarCamposA()) { //SE ACTUALIZA EL PRODUCTO

            // TOMA LOS DATOS DE LOS EDIT TEXT
            String nombre = txtNombreA.getText().toString();
            String apellidoP = txtApellidoPA.getText().toString();
            String apellidoM = txtApellidoMA.getText().toString();
            String telefono = txtTelefonoA.getText().toString();
            String email = txtemailA.getText().toString();
            String rfc = txtRFCAdministrador.getText().toString();

            // LOS PREPARA EN UN MAP
            Map<String, String> datos = new HashMap<>();
            datos.put("nombre", nombre);
            datos.put("apellido1", apellidoP);
            datos.put("apellido2", apellidoM);
            datos.put("telefono", telefono);
            datos.put("email_persona", email);
            datos.put("rfc",rfc);

            // METE O PREPARA LOS DATOS EN UN JSON
            JSONObject jsonData = new JSONObject(datos);

            // ESPECIFICAR LA URL PARA QUE SE CARGUE EN JSON EN EL .php
            AndroidNetworking.post(Constantes.URL_ACTUALIZAR_PERSONA)
                    .addJSONObjectBody(jsonData)
                    .setPriority(Priority.MEDIUM)
                    .build()
                    .getAsJSONObject(new JSONObjectRequestListener() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                String estado = response.getString("estado");
                                String error = response.getString("error");
                                Toast.makeText(ActualizarInformacionAdministrador.this, estado, Toast.LENGTH_SHORT).show();
                                //Toast.makeText(GuardarProductoActivity.this, "Error: "+error, Toast.LENGTH_SHORT).show();
                            } catch (JSONException e) {
                                Toast.makeText(ActualizarInformacionAdministrador.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onError(ANError anError) {
                            Toast.makeText(ActualizarInformacionAdministrador.this, "Error: " + anError.getErrorDetail(), Toast.LENGTH_SHORT).show();

                        }
                    });
        } else {
            Toast.makeText(this, "Existen campos vacios, no se puede actualizar", Toast.LENGTH_SHORT).show();
        }

    }

    private void actualizarInfoDireccionA(){
        if (validarCamposA()) { //SE ACTUALIZA EL PRODUCTO

            // TOMA LOS DATOS DE LOS EDIT TEXT
            String cp = txtCPA.getText().toString();
            String entidad = txtEntidadA.getText().toString();
            String localidad = txtLocalidadA.getText().toString();
            String colonia = txtColoniaA.getText().toString();
            String calle = txtCalleA.getText().toString();
            String numeroExt = txtNumExtA.getText().toString();
            String numeroInt = txtNumIntA.getText().toString();
            String cedulaCatastral = txtCedCastralA.getText().toString();
            String idDireccion = txtidDireccionA.getText().toString();

            // LOS PREPARA EN UN MAP
            Map<String, String> datos = new HashMap<>();
            datos.put("cp", cp);
            datos.put("entidad_federativa", entidad);
            datos.put("localidad", localidad);
            datos.put("colonia", colonia);
            datos.put("calle", calle);
            datos.put("numero_ext",numeroExt);
            datos.put("numero_int",numeroInt);
            datos.put("cedula_catastral",cedulaCatastral);
            datos.put("id_direccion",idDireccion);

            // METE O PREPARA LOS DATOS EN UN JSON
            JSONObject jsonData = new JSONObject(datos);

            // ESPECIFICAR LA URL PARA QUE SE CARGUE EN JSON EN EL .php
            AndroidNetworking.post(Constantes.URL_ACTUALIZAR_DIRECCION)
                    .addJSONObjectBody(jsonData)
                    .setPriority(Priority.MEDIUM)
                    .build()
                    .getAsJSONObject(new JSONObjectRequestListener() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                String estado = response.getString("estado");
                                String error = response.getString("error");
                                Toast.makeText(ActualizarInformacionAdministrador.this, estado, Toast.LENGTH_SHORT).show();
                                //Toast.makeText(GuardarProductoActivity.this, "Error: "+error, Toast.LENGTH_SHORT).show();
                            } catch (JSONException e) {
                                Toast.makeText(ActualizarInformacionAdministrador.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onError(ANError anError) {
                            Toast.makeText(ActualizarInformacionAdministrador.this, "Error: " + anError.getErrorDetail(), Toast.LENGTH_SHORT).show();

                        }
                    });
        } else {
            Toast.makeText(this, "Existen campos vacios, no se puede actualizar", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean validarCamposA(){
        return !txtNombreA.getText().toString().trim().isEmpty() &&
                !txtApellidoPA.getText().toString().trim().isEmpty() &&
                !txtApellidoMA.getText().toString().trim().isEmpty() &&
                !txtTelefonoA.getText().toString().trim().isEmpty() &&
                !txtemailA.getText().toString().trim().isEmpty() &&
                !txtCPA.getText().toString().trim().isEmpty() &&
                !txtEntidadA.getText().toString().trim().isEmpty() &&
                !txtLocalidadA.getText().toString().trim().isEmpty() &&
                !txtColoniaA.getText().toString().trim().isEmpty() &&
                !txtCalleA.getText().toString().trim().isEmpty() &&
                !txtNumExtA.getText().toString().trim().isEmpty() &&
                !txtNumIntA.getText().toString().trim().isEmpty() &&
                !txtCedCastralA.getText().toString().trim().isEmpty();
    }

}
