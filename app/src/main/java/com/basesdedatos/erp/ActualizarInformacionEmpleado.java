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

public class ActualizarInformacionEmpleado extends AppCompatActivity {

    private TextView txtRFCEmpleado;
    private EditText txtNombreE;
    private EditText txtApellidoPE;
    private EditText txtApellidoME;
    private EditText txtTelefonoE;
    private EditText txtemailE;
    private EditText txtCPE;
    private EditText txtEntidadE;
    private EditText txtLocalidadE;
    private EditText txtColoniaE;
    private EditText txtCalleE;
    private EditText txtNumExtE;
    private EditText txtNumIntE;
    private EditText txtCedCastralE;

    private TextView txtidDireccionE;

    private Button btnActDatosE;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actualizar_informacion_empleado);

        txtRFCEmpleado = findViewById(R.id.txtRFCEmpleado);
        txtNombreE = findViewById(R.id.txtNombreE);
        txtApellidoPE = findViewById(R.id.txtApellidoPE);
        txtApellidoME = findViewById(R.id.txtApellidoME);
        txtTelefonoE = findViewById(R.id.txtTelefonoE);
        txtemailE = findViewById(R.id.txtemailE);
        txtCPE = findViewById(R.id.txtCPE);
        txtEntidadE = findViewById(R.id.txtEntidadE);
        txtLocalidadE = findViewById(R.id.txtLocalidadE);
        txtColoniaE = findViewById(R.id.txtColoniaE);
        txtCalleE = findViewById(R.id.txtCalleE);
        txtNumExtE = findViewById(R.id.txtNumExtE);
        txtNumIntE = findViewById(R.id.txtNumIntE);
        txtCedCastralE = findViewById(R.id.txtCedCastralE);
        txtidDireccionE = findViewById(R.id.txtidDireccionE);

        btnActDatosE = findViewById(R.id.btnActDatosE);

        String datoRFCC = getIntent().getStringExtra("RFCEmpleado");
        txtRFCEmpleado.setText(datoRFCC);

        mostrarDatosE();

        btnActDatosE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actualizarInfoDireccionE();
                actualizarInfoPersonaEmpleado();
            }
        });
    }

    private void mostrarDatosE(){

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JSONObject object = new JSONObject();
        String RFC = txtRFCEmpleado.getText().toString().trim();
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
                    txtNombreE.setText(nombre);
                    txtApellidoPE.setText(apellidop);
                    txtApellidoME.setText(apellidom);
                    txtTelefonoE.setText(telefono);
                    txtemailE.setText(email);
                    txtCPE.setText(cp);
                    txtEntidadE.setText(entidad_federativa);
                    txtLocalidadE.setText(localidad);
                    txtColoniaE.setText(colonia);
                    txtCalleE.setText(calle);
                    txtNumExtE.setText(numero_ext);
                    txtNumIntE.setText(numero_int);
                    txtCedCastralE.setText(cedulaCas);

                    txtidDireccionE.setText(idDireccion);


                }catch(JSONException e){
                    Toast.makeText(ActualizarInformacionEmpleado.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                txtNombreE.setText(error.toString());
                txtApellidoPE.setText(error.toString());
                txtApellidoME.setText(error.toString());
                txtTelefonoE.setText(error.toString());
                txtemailE.setText(error.toString());
                txtCPE.setText(error.toString());
                txtEntidadE.setText(error.toString());
                txtColoniaE.setText(error.toString());
                txtCalleE.setText(error.toString());
                txtNumExtE.setText(error.toString());
                txtNumIntE.setText(error.toString());
                txtCedCastralE.setText(error.toString());
                txtidDireccionE.setText(error.toString());

            }
        });
        requestQueue.add(jsonObjectRequest);

    }

    private void actualizarInfoPersonaEmpleado(){
        if (validarCamposE()) { //SE ACTUALIZA EL PRODUCTO

            // TOMA LOS DATOS DE LOS EDIT TEXT
            String nombre = txtNombreE.getText().toString();
            String apellidoP = txtApellidoPE.getText().toString();
            String apellidoM = txtApellidoME.getText().toString();
            String telefono = txtTelefonoE.getText().toString();
            String email = txtemailE.getText().toString();
            String rfc = txtRFCEmpleado.getText().toString();

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
                                Toast.makeText(ActualizarInformacionEmpleado.this, estado, Toast.LENGTH_SHORT).show();
                                //Toast.makeText(GuardarProductoActivity.this, "Error: "+error, Toast.LENGTH_SHORT).show();
                            } catch (JSONException e) {
                                Toast.makeText(ActualizarInformacionEmpleado.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onError(ANError anError) {
                            Toast.makeText(ActualizarInformacionEmpleado.this, "Error: " + anError.getErrorDetail(), Toast.LENGTH_SHORT).show();

                        }
                    });
        } else {
            Toast.makeText(this, "Existen campos vacios, no se puede actualizar", Toast.LENGTH_SHORT).show();
        }

    }

    private void actualizarInfoDireccionE(){
        if (validarCamposE()) { //SE ACTUALIZA EL PRODUCTO

            // TOMA LOS DATOS DE LOS EDIT TEXT
            String cp = txtCPE.getText().toString();
            String entidad = txtEntidadE.getText().toString();
            String localidad = txtLocalidadE.getText().toString();
            String colonia = txtColoniaE.getText().toString();
            String calle = txtCalleE.getText().toString();
            String numeroExt = txtNumExtE.getText().toString();
            String numeroInt = txtNumIntE.getText().toString();
            String cedulaCatastral = txtCedCastralE.getText().toString();
            String idDireccion = txtidDireccionE.getText().toString();

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
                                Toast.makeText(ActualizarInformacionEmpleado.this, estado, Toast.LENGTH_SHORT).show();
                                //Toast.makeText(GuardarProductoActivity.this, "Error: "+error, Toast.LENGTH_SHORT).show();
                            } catch (JSONException e) {
                                Toast.makeText(ActualizarInformacionEmpleado.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onError(ANError anError) {
                            Toast.makeText(ActualizarInformacionEmpleado.this, "Error: " + anError.getErrorDetail(), Toast.LENGTH_SHORT).show();

                        }
                    });
        } else {
            Toast.makeText(this, "Existen campos vacios, no se puede actualizar", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean validarCamposE(){
        return !txtNombreE.getText().toString().trim().isEmpty() &&
                !txtApellidoPE.getText().toString().trim().isEmpty() &&
                !txtApellidoME.getText().toString().trim().isEmpty() &&
                !txtTelefonoE.getText().toString().trim().isEmpty() &&
                !txtemailE.getText().toString().trim().isEmpty() &&
                !txtCPE.getText().toString().trim().isEmpty() &&
                !txtEntidadE.getText().toString().trim().isEmpty() &&
                !txtLocalidadE.getText().toString().trim().isEmpty() &&
                !txtColoniaE.getText().toString().trim().isEmpty() &&
                !txtCalleE.getText().toString().trim().isEmpty() &&
                !txtNumExtE.getText().toString().trim().isEmpty() &&
                !txtNumIntE.getText().toString().trim().isEmpty() &&
                !txtCedCastralE.getText().toString().trim().isEmpty();
    }
}
