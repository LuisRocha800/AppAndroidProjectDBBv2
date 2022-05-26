package com.basesdedatos.erp;

import android.os.Bundle;
import android.text.Editable;
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

public class ActualizarInformacionCliente extends AppCompatActivity {

    private TextView txtRFCCliente;
    private EditText txtNombreC;
    private EditText txtApellidoPC;
    private EditText txtApellidoMC;
    private EditText txtTelefonoC;
    private EditText txtemailC;
    private EditText txtCPC;
    private EditText txtEntidadC;
    private EditText txtLocalidadC;
    private EditText txtColoniaC;
    private EditText txtCalleC;
    private EditText txtNumExtC;
    private EditText txtNumIntC;
    private EditText txtCedCastralC;

    private TextView txtidDireccion;

    private Button btnActDatosC;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actualizar_informacion_cliente);

        txtRFCCliente = findViewById(R.id.txtRFCCliente);
        txtNombreC = findViewById(R.id.txtNombreC);
        txtApellidoPC = findViewById(R.id.txtApellidoPC);
        txtApellidoMC = findViewById(R.id.txtApellidoMC);
        txtTelefonoC = findViewById(R.id.txtTelefonoC);
        txtemailC = findViewById(R.id.txtemailC);
        txtCPC = findViewById(R.id.txtCPC);
        txtEntidadC = findViewById(R.id.txtEntidadC);
        txtLocalidadC = findViewById(R.id.txtLocalidadC);
        txtColoniaC = findViewById(R.id.txtColoniaC);
        txtCalleC = findViewById(R.id.txtCalleC);
        txtNumExtC = findViewById(R.id.txtNumExtC);
        txtNumIntC = findViewById(R.id.txtNumIntC);
        txtCedCastralC = findViewById(R.id.txtCedCastralC);
        txtidDireccion = findViewById(R.id.txtidDireccion);

        btnActDatosC = findViewById(R.id.btnActDatosC);

        String datoRFCC = getIntent().getStringExtra("RFCClienteA");
        txtRFCCliente.setText(datoRFCC);

        mostrarDatosAct();

        btnActDatosC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actualizarInfoDireccionCliente();
                actualizarInfoPersonaCliente();
            }
        });
    }

    private void mostrarDatosAct(){

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JSONObject object = new JSONObject();
        String RFC = txtRFCCliente.getText().toString().trim();
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
                    txtNombreC.setText(nombre);
                    txtApellidoPC.setText(apellidop);
                    txtApellidoMC.setText(apellidom);
                    txtTelefonoC.setText(telefono);
                    txtemailC.setText(email);
                    txtCPC.setText(cp);
                    txtEntidadC.setText(entidad_federativa);
                    txtLocalidadC.setText(localidad);
                    txtColoniaC.setText(colonia);
                    txtCalleC.setText(calle);
                    txtNumExtC.setText(numero_ext);
                    txtNumIntC.setText(numero_int);
                    txtCedCastralC.setText(cedulaCas);

                    txtidDireccion.setText(idDireccion);


                }catch(JSONException e){
                    Toast.makeText(ActualizarInformacionCliente.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                txtNombreC.setText(error.toString());
                txtApellidoPC.setText(error.toString());
                txtApellidoMC.setText(error.toString());
                txtTelefonoC.setText(error.toString());
                txtemailC.setText(error.toString());
                txtCPC.setText(error.toString());
                txtEntidadC.setText(error.toString());
                txtColoniaC.setText(error.toString());
                txtCalleC.setText(error.toString());
                txtNumExtC.setText(error.toString());
                txtNumIntC.setText(error.toString());
                txtCedCastralC.setText(error.toString());
                txtidDireccion.setText(error.toString());

            }
        });
        requestQueue.add(jsonObjectRequest);

    }

    private void actualizarInfoPersonaCliente(){
        if (validarCampos()) { //SE ACTUALIZA EL PRODUCTO

            // TOMA LOS DATOS DE LOS EDIT TEXT
            String nombre = txtNombreC.getText().toString();
            String apellidoP = txtApellidoPC.getText().toString();
            String apellidoM = txtApellidoMC.getText().toString();
            String telefono = txtTelefonoC.getText().toString();
            String email = txtemailC.getText().toString();
            String rfc = txtRFCCliente.getText().toString();

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
                                Toast.makeText(ActualizarInformacionCliente.this, estado, Toast.LENGTH_SHORT).show();
                                //Toast.makeText(GuardarProductoActivity.this, "Error: "+error, Toast.LENGTH_SHORT).show();
                            } catch (JSONException e) {
                                Toast.makeText(ActualizarInformacionCliente.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onError(ANError anError) {
                            Toast.makeText(ActualizarInformacionCliente.this, "Error: " + anError.getErrorDetail(), Toast.LENGTH_SHORT).show();

                        }
                    });
        } else {
            Toast.makeText(this, "Existen campos vacios, no se puede actualizar", Toast.LENGTH_SHORT).show();
        }
    }

    private void actualizarInfoDireccionCliente(){
        if (validarCampos()) { //SE ACTUALIZA EL PRODUCTO

            // TOMA LOS DATOS DE LOS EDIT TEXT
            String cp = txtCPC.getText().toString();
            String entidad = txtEntidadC.getText().toString();
            String localidad = txtLocalidadC.getText().toString();
            String colonia = txtColoniaC.getText().toString();
            String calle = txtCalleC.getText().toString();
            String numeroExt = txtNumExtC.getText().toString();
            String numeroInt = txtNumIntC.getText().toString();
            String cedulaCatastral = txtCedCastralC.getText().toString();
            String idDireccion = txtidDireccion.getText().toString();

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
                                Toast.makeText(ActualizarInformacionCliente.this, estado, Toast.LENGTH_SHORT).show();
                                //Toast.makeText(GuardarProductoActivity.this, "Error: "+error, Toast.LENGTH_SHORT).show();
                            } catch (JSONException e) {
                                Toast.makeText(ActualizarInformacionCliente.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onError(ANError anError) {
                            Toast.makeText(ActualizarInformacionCliente.this, "Error: " + anError.getErrorDetail(), Toast.LENGTH_SHORT).show();

                        }
                    });
        } else {
            Toast.makeText(this, "Existen campos vacios, no se puede actualizar", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean validarCampos(){
         return !txtNombreC.getText().toString().trim().isEmpty() &&
                !txtApellidoPC.getText().toString().trim().isEmpty() &&
                !txtApellidoMC.getText().toString().trim().isEmpty() &&
                !txtTelefonoC.getText().toString().trim().isEmpty() &&
                !txtemailC.getText().toString().trim().isEmpty() &&
                 !txtCPC.getText().toString().trim().isEmpty() &&
                 !txtEntidadC.getText().toString().trim().isEmpty() &&
                 !txtLocalidadC.getText().toString().trim().isEmpty() &&
                 !txtColoniaC.getText().toString().trim().isEmpty() &&
                 !txtCalleC.getText().toString().trim().isEmpty() &&
                 !txtNumExtC.getText().toString().trim().isEmpty() &&
                 !txtNumIntC.getText().toString().trim().isEmpty() &&
                 !txtCedCastralC.getText().toString().trim().isEmpty();
    }
}
