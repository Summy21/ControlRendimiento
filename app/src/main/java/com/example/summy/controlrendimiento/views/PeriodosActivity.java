package com.example.summy.controlrendimiento.views;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.summy.controlrendimiento.R;
import com.example.summy.controlrendimiento.model.Atleta;
import com.example.summy.controlrendimiento.model.Periodo;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

@RequiresApi(api = Build.VERSION_CODES.N)
public class PeriodosActivity extends AppCompatActivity {

    private View rootView;

    private DatabaseReference myRef;

    //datos registro
    private EditText etPerEtapaGeneral;
    private EditText etFechaIniEtapaGeneral;
    private EditText etFechaFinEtapaGeneral;

    private EditText etPrepEtapaEspecifica;
    private EditText etFechaIniEtapaEspecifica;
    private EditText etFechaFinEtapaEspecifica;

    private EditText etCompPerPreparatorio;
    private EditText etFechaIniPerPreparatorio;
    private EditText etFechaFinPerPreparatorio;

    private EditText etCompPerCompetencia;
    private EditText etFechaIniPerCompetencia;
    private EditText etFechaFinPerCompetencia;

    private EditText etCompPerTransicion;
    private EditText etFechaIniPerTransicion;
    private EditText etFechaFinPerTransicion;

    //Botones Fechas
    private Button btnIni1;
    private Button btnFin1;
    private Button btnIni2;
    private Button btnFin2;
    private Button btnIni3;
    private Button btnFin3;
    private Button btnIni4;
    private Button btnFin4;
    private Button btnIni5;
    private Button btnFin5;
    private Button btnGuardar;

    String idComp;

    //Fechas
    Calendar dateTime = Calendar.getInstance();
    boolean sw1, sw2, sw3, sw4, sw5, sw6, sw7, sw8, sw9, sw10 = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_periodos);
        showToolbar("Gestionar Periodos", true);
        rootView = findViewById(R.id.rootViewPeriodos);

        myRef = FirebaseDatabase.getInstance().getReference("Periodos");

        etPerEtapaGeneral      = (EditText) findViewById(R.id.etPerEtapaGeneral);
        etFechaIniEtapaGeneral = (EditText) findViewById(R.id.etFechaIniEtapaGeneral);
        etFechaFinEtapaGeneral = (EditText) findViewById(R.id.etFechaFinEtapaGeneral);
        btnIni1                = (Button) findViewById(R.id.btnIni1);
        btnFin1                = (Button) findViewById(R.id.btnFin1);

        etPrepEtapaEspecifica     = (EditText) findViewById(R.id.etPrepEtapaEspecifica);
        etFechaIniEtapaEspecifica = (EditText) findViewById(R.id.etFechaIniEtapaEspecifica);
        etFechaFinEtapaEspecifica = (EditText) findViewById(R.id.etFechaFinEtapaEspecifica);
        btnIni2                   = (Button) findViewById(R.id.btnIni2);
        btnFin2                   = (Button) findViewById(R.id.btnFin2);


        etCompPerPreparatorio     = (EditText) findViewById(R.id.etCompPerPreparatorio);
        etFechaIniPerPreparatorio = (EditText) findViewById(R.id.etFechaIniPerPreparatorio);
        etFechaFinPerPreparatorio = (EditText) findViewById(R.id.etFechaFinPerPreparatorio);
        btnIni3                   = (Button) findViewById(R.id.btnIni3);
        btnFin3                   = (Button) findViewById(R.id.btnFin3);

        etCompPerCompetencia     = (EditText) findViewById(R.id.etCompPerCompetencia);
        etFechaIniPerCompetencia = (EditText) findViewById(R.id.etFechaIniPerCompetencia);
        etFechaFinPerCompetencia = (EditText) findViewById(R.id.etFechaFinPerCompetencia);
        btnIni4                  = (Button) findViewById(R.id.btnIni4);
        btnFin4                  = (Button) findViewById(R.id.btnFin4);

        etCompPerTransicion     = (EditText) findViewById(R.id.etCompPerTransicion);
        etFechaIniPerTransicion = (EditText) findViewById(R.id.etFechaIniPerTransicion);
        etFechaFinPerTransicion = (EditText) findViewById(R.id.etFechaFinPerTransicion);
        btnIni5                 = (Button) findViewById(R.id.btnIni5);
        btnFin5                 = (Button) findViewById(R.id.btnFin5);

        btnGuardar              = (Button) findViewById(R.id.btnGuardar);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarPeriodo();
            }
        });
        btnIni1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sw1 = true;
                actualizarFecha();
            }
        });
        btnFin1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sw2 = true;
                actualizarFecha();
            }
        });
        btnIni2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sw3 = true;
                actualizarFecha();
            }
        });
        btnFin2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sw4 = true;
                actualizarFecha();
            }
        });
        btnIni3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sw5 = true;
                actualizarFecha();
            }
        });
        btnFin3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sw6 = true;
                actualizarFecha();
            }
        });
        btnIni4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sw7 = true;
                actualizarFecha();
            }
        });
        btnFin4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sw8 = true;
                actualizarFecha();
            }
        });
        btnIni5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sw9 = true;
                actualizarFecha();
            }
        });
        btnFin5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sw10 = true;
                actualizarFecha();
            }
        });

    }


    private void guardarPeriodo() {
        String PerEtapaGeneral      = etPerEtapaGeneral.getText().toString().trim();
        String FechaIniEtapaGeneral = etFechaIniEtapaGeneral.getText().toString().trim();
        String FechaFinEtapaGeneral = etFechaFinEtapaGeneral.getText().toString().trim();

        String PrepEtapaEspecifica     = etPrepEtapaEspecifica.getText().toString().trim();
        String FechaIniEtapaEspecifica = etFechaIniEtapaEspecifica.getText().toString().trim();
        String FechaFinEtapaEspecifica = etFechaFinEtapaEspecifica.getText().toString().trim();

        String CompPerPreparatorio     = etCompPerPreparatorio.getText().toString().trim();
        String FechaIniPerPreparatorio = etFechaIniPerPreparatorio.getText().toString().trim();
        String FechaFinPerPreparatorio = etFechaFinPerPreparatorio.getText().toString().trim();

        String CompPerCompetencia     = etCompPerCompetencia.getText().toString().trim();
        String FechaIniPerCompetencia = etFechaIniPerCompetencia.getText().toString().trim();
        String FechaFinPerCompetencia = etFechaFinPerCompetencia.getText().toString().trim();

        String CompPerTransicion     = etCompPerTransicion.getText().toString().trim();
        String FechaIniPerTransicion = etFechaIniPerTransicion.getText().toString().trim();
        String FechaFinPerTransicion = etFechaFinPerTransicion.getText().toString().trim();

        if (!TextUtils.isEmpty(PerEtapaGeneral)){


            Periodo periodo = new Periodo(PerEtapaGeneral,
                    FechaIniEtapaGeneral,
                    FechaFinEtapaGeneral,
                    PrepEtapaEspecifica,
                    FechaIniEtapaEspecifica,
                    FechaFinEtapaEspecifica,
                    CompPerPreparatorio,
                    FechaIniPerPreparatorio,
                    FechaFinPerPreparatorio,
                    CompPerCompetencia,
                    FechaIniPerCompetencia,
                    FechaFinPerCompetencia,
                    CompPerTransicion,
                    FechaIniPerTransicion,
                    FechaFinPerTransicion);

            myRef.child("-KzS9LwdwLG3VFOuEdk7").setValue(periodo);

            Toast.makeText(this,"Periodo adicionado",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(getApplicationContext(), RegistroActivity.class);
            this.startActivity(intent);
            finish();

        }else{
            mostrarMessage("Falta completar los datos");
        }
    }

    //Selector de fecha
    private void actualizarFecha(){
        new DatePickerDialog(this, d, dateTime.get(Calendar.YEAR),
                dateTime.get(Calendar.MONTH),
                dateTime.get(Calendar.DAY_OF_MONTH))
                .show();
    }
    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            dateTime.set(Calendar.YEAR, year);
            dateTime.set(Calendar.MONTH, monthOfYear);
            dateTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);

            if(sw1 == true){
                etFechaIniEtapaGeneral.setText(dayOfMonth+"/"+(monthOfYear+1)+"/"+year);
                sw1 = false;
            }
            if(sw2 == true){
                etFechaFinEtapaGeneral.setText(dayOfMonth+"/"+(monthOfYear+1)+"/"+year);
                sw2 = false;
            }if(sw3 == true){
                etFechaIniEtapaEspecifica.setText(dayOfMonth+"/"+(monthOfYear+1)+"/"+year);
                sw3 = false;
            }if(sw4 == true){
                etFechaFinEtapaEspecifica.setText(dayOfMonth+"/"+(monthOfYear+1)+"/"+year);
                sw4 = false;
            }if(sw5 == true){
                etFechaIniPerPreparatorio.setText(dayOfMonth+"/"+(monthOfYear+1)+"/"+year);
                sw5 = false;
            }if(sw6 == true){
                etFechaFinPerPreparatorio.setText(dayOfMonth+"/"+(monthOfYear+1)+"/"+year);
                sw6 = false;
            }if(sw7 == true){
                etFechaIniPerCompetencia.setText(dayOfMonth+"/"+(monthOfYear+1)+"/"+year);
                sw7 = false;
            }if(sw8 == true){
                etFechaFinPerCompetencia.setText(dayOfMonth+"/"+(monthOfYear+1)+"/"+year);
                sw8 = false;
            }if(sw9 == true){
                etFechaIniPerTransicion.setText(dayOfMonth+"/"+(monthOfYear+1)+"/"+year);
                sw9 = false;
            }if(sw10 == true){
                etFechaFinPerTransicion.setText(dayOfMonth+"/"+(monthOfYear+1)+"/"+year);
                sw10 = false;
            }

        }
    };


    public void showToolbar(String tittle, boolean upButton){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(tittle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
    }

    private void mostrarMessage(String mensaje) {
        Snackbar.make(rootView, mensaje, Snackbar.LENGTH_LONG).show();
    }
}
