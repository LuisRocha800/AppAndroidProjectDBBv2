package com.basesdedatos.erp;

import android.content.Intent;
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

import org.json.JSONException;
import org.json.JSONObject;

public class LoginClienteActivity extends AppCompatActivity {

    private EditText txtUsuarioCliente;
    private EditText txtPasswordCliente;
    private TextView viewUser;
    private TextView viewPass;
    private Button btnOkCliente;

    String pass;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_cliente_activity);

        txtUsuarioCliente = findViewById(R.id.txtUsuarioCliente);
        txtPasswordCliente = findViewById(R.id.txtPasswordCliente);
        viewUser = findViewById(R.id.viewUser);
        viewPass = findViewById(R.id.viewPass);

        btnOkCliente = findViewById(R.id.btnOkCliente);


        btnOkCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                verficarAccesoCliente();
            }
        });
    }

    private void verficarAccesoCliente(){

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JSONObject object = new JSONObject();
        String RFC = txtUsuarioCliente.getText().toString().trim();
        String url = Constantes.URL_CLIENTE_X_ID+"?rfc="+RFC;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, object, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject datos = response.getJSONObject("data");
                    String rfc = datos.getString("rfc");
                    String password = datos.getString("contraseña");

                    viewUser.setText(rfc);
                    viewPass.setText(password);

                }catch(JSONException e){

                }
            } }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                viewUser.setText(error.toString());
                viewPass.setText(error.toString());

            }
        });
        requestQueue.add(jsonObjectRequest);


        String user = txtUsuarioCliente.getText().toString();
        String password = txtPasswordCliente.getText().toString();
        String uss = viewUser.getText().toString();
        String pss = viewPass.getText().toString();

        if(verficarcampos()) {

                if (user.equals(uss) && password.equals(pss)) {
                    enviarUsuarioCliente();
                    Toast.makeText(this, "Welcome " + user, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "El usuario o la contraseña son incorrectos", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(this, "No pueden haber campos vacios", Toast.LENGTH_SHORT).show();
            }
    }

    private boolean verficarcampos(){
       return !txtUsuarioCliente.getText().toString().trim().isEmpty()&&
              !txtPasswordCliente.getText().toString().trim().isEmpty();
    }

    public void enviarUsuarioCliente(){
        Intent intent = new Intent(this, MenuCliente.class);
        intent.putExtra("datoRFCCliente",txtUsuarioCliente.getText().toString());
        startActivity(intent);
    }

}
