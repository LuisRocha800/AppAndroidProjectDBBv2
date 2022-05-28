package com.basesdedatos.erp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SolicitudMantenimiento extends AppCompatActivity {

    private EditText txtTipoDefecto;
    private EditText txtDescDefecto;
    private EditText txtFolioDefecto;
    private TextView txtFolioInmueble;
    private Button btnSolicDefecto;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.solicitud_mantenimiento_activity);

        txtFolioInmueble = findViewById(R.id.idManufactura);
        txtDescDefecto = findViewById(R.id.txtDescDefecto);
        txtTipoDefecto = findViewById(R.id.txtTipoDefecto);
        txtFolioDefecto = findViewById(R.id.txtFolioDefecto);

        btnSolicDefecto = findViewById(R.id.btnSolicDefecto);

        String datoRFC = getIntent().getStringExtra("datoIdInmueble");
        txtFolioInmueble.setText(datoRFC);

        generarFolios();

        btnSolicDefecto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                solicitudDefecto();
            }
        });
    }

    private void solicitudDefecto(){


        String id = txtFolioDefecto.getText().toString();
        String tipo = txtTipoDefecto.getText().toString();
        String descripcion = txtDescDefecto.getText().toString();
        String inmueble = txtFolioInmueble.getText().toString();

        Map<String,String> datos = new HashMap<>();
        datos.put("id_defecto", id);
        datos.put("tipo", tipo);
        datos.put("descripcion", descripcion);
        datos.put("id_manufac", inmueble);
        JSONObject jsonData = new JSONObject(datos);

        AndroidNetworking.post(Constantes.URL_INSERTAR_DEFECTO)
                .addJSONObjectBody(jsonData)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String estado = response.getString("estado");
                            String error = response.getString("error");
                            Toast.makeText(SolicitudMantenimiento.this, estado, Toast.LENGTH_SHORT).show();

                        }catch (JSONException e){
                            Toast.makeText(SolicitudMantenimiento.this, "Error: "+e.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(SolicitudMantenimiento.this, "Error: "+anError.getErrorDetail(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void generarFolios(){
        int numero = (int)(Math.random()*10000000+1);
        String numCadena1= Integer.toString(numero);
        txtFolioDefecto.setText(numCadena1);


    }
}
