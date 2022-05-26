package com.basesdedatos.erp;

import android.os.Bundle;
import android.text.Editable;
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

import org.json.JSONException;
import org.json.JSONObject;

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
}
