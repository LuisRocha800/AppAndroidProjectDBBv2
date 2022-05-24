package com.basesdedatos.erp;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.androidnetworking.AndroidNetworking;

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
    }
}
