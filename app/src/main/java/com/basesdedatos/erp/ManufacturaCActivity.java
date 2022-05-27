package com.basesdedatos.erp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ManufacturaCActivity extends AppCompatActivity {

    private EditText txtFolioManufacturaE;
    private EditText txtFolioOutsorcingC;
    private EditText txtFolioDefectoE;
    private EditText txtDescripcionDefectoE;
    private EditText txtCostoOutsorcingE;
    private EditText txtContratoOutsorcingE;
    private EditText txtTipoDefectoE;
    private EditText txtFolioDireccionE;
    private EditText txtFechaInicioManE;
    private EditText txtStatusManE;
    private EditText txtCostoMantenimientoE;
    private EditText txtEmpresaManE;
    private EditText txtFechaInicioMantenimientoE;
    private EditText txtCostoInicialE;
    private EditText tipoManufacturaE;
    private EditText txtFechaFinalManE;
    private EditText txtCostoFinalE;
    private EditText txtFecTerminoMantenimientoE;
    private EditText txtStatusMantenimientoE;
    private EditText txtFolioMantenimientoE;

    private Button btnBuscarDatosId;
    private Button btnIngresarManE;
    private Button btnActualizarManE;
    private Button btnSolicitudesManE;
    private Button btnBorrarManE;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manufactura_c_activity);

        txtFolioManufacturaE = findViewById(R.id.txtFolioManufacturaE);
        txtFolioOutsorcingC = findViewById(R.id.txtFolioOutsorcingC);
        txtFolioDefectoE = findViewById(R.id.txtFolioDefectoE);
        txtDescripcionDefectoE = findViewById(R.id.txtDescripcionDefectoE);
        txtCostoOutsorcingE = findViewById(R.id.txtCostoOutsorcingE);
        txtContratoOutsorcingE = findViewById(R.id.txtContratoOutsorcingE);
        txtTipoDefectoE = findViewById(R.id.txtTipoDefectoE);
        txtFolioDireccionE = findViewById(R.id.txtFolioDireccionE);
        txtFechaInicioManE = findViewById(R.id.txtFechaInicioManE);
        txtStatusManE = findViewById(R.id.txtStatusManE);
        txtCostoMantenimientoE = findViewById(R.id.txtCostoMantenimientoE);
        txtEmpresaManE = findViewById(R.id.txtEmpresaManE);
        txtFechaInicioMantenimientoE = findViewById(R.id.txtFechaInicioMantenimientoE);
        txtCostoInicialE = findViewById(R.id.txtCostoInicialE);
        tipoManufacturaE = findViewById(R.id.tipoManufacturaE);
        txtFechaFinalManE = findViewById(R.id.txtFechaFinalManE);
        txtCostoFinalE = findViewById(R.id.txtCostoFinalE);
        txtFecTerminoMantenimientoE = findViewById(R.id.txtFecTerminoMantenimientoE);
        txtStatusMantenimientoE = findViewById(R.id.txtStatusMantenimientoE);
        txtFolioMantenimientoE = findViewById(R.id.txtFolioMantenimientoE);

        btnBuscarDatosId = findViewById(R.id.btnBuscarDatosId);
        btnIngresarManE = findViewById(R.id.btnIngresarManE);
        btnActualizarManE = findViewById(R.id.btnActualizarManE);
        btnSolicitudesManE = findViewById(R.id.btnSolicitudesManE);
        btnBorrarManE = findViewById(R.id.btnBorrarManE);

        btnBuscarDatosId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnIngresarManE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnActualizarManE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnSolicitudesManE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnBorrarManE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}
