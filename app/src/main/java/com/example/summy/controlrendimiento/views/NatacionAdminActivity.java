package com.example.summy.controlrendimiento.views;

import android.icu.text.SimpleDateFormat;
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
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.summy.controlrendimiento.R;
import com.example.summy.controlrendimiento.model.GestionRutinas;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Date;

@RequiresApi(api = Build.VERSION_CODES.N)
public class NatacionAdminActivity extends AppCompatActivity {

    private View rootView;

    private DatabaseReference myRef;
    Spinner spinnerEtapas;
    Spinner spinnerPeriodos;
    TextView tvMicrociclo;
    TextView tvMesociclo;
    EditText etNroSesiones;
    EditText etAer;
    EditText etAel;
    EditText etAem;
    EditText etAei;
    EditText etPae;
    EditText etCla;
    EditText etPla;
    EditText etCala;
    EditText etPala;
    TextView tvVolumenT;

    Calendar calendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_natacion_admin);
        showToolbar("Gestion de rutinas natacion", true);
        rootView = findViewById(R.id.rootViewGestionN);

        tvMicrociclo = (TextView) findViewById(R.id.tvMicrociclo);
        tvMesociclo = (TextView) findViewById(R.id.tvMesociclo);
        spinnerEtapas = (Spinner) findViewById(R.id.spinnerEtapas);

        Button btnGuardar = (Button) findViewById(R.id.btnGuardar);
        final String idM = String.valueOf(nroMicrociclo());
        tvMicrociclo.setText(idM);
        tvMesociclo.setText(nroMesociclo());

        mostrarGestionNatacion(idM);

        myRef = FirebaseDatabase.getInstance().getReference("RutinasEjercicio").child("Natacion");

        tvMicrociclo = (TextView) findViewById(R.id.tvMicrociclo);
        tvMesociclo = (TextView) findViewById(R.id.tvMesociclo);
        spinnerEtapas = (Spinner) findViewById(R.id.spinnerEtapas);
        spinnerPeriodos = (Spinner) findViewById(R.id.spinnerPeriodos);

        etNroSesiones = (EditText) findViewById(R.id.etNroSesiones);
        etAer = (EditText) findViewById(R.id.etAer);
        etAel = (EditText) findViewById(R.id.etAel);
        etAem = (EditText) findViewById(R.id.etAem);
        etAei = (EditText) findViewById(R.id.etAei);
        etPae = (EditText) findViewById(R.id.etPae);
        etCla = (EditText) findViewById(R.id.etCla);
        etPla = (EditText) findViewById(R.id.etPla);
        etCala = (EditText) findViewById(R.id.etCala);
        etPala = (EditText) findViewById(R.id.etPala);
        tvVolumenT = (TextView) findViewById(R.id.tvVolumenT);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adicionarGestionNatacion(idM);
            }
        });
    }

    private void mostrarGestionNatacion(String idM) {
        myRef = FirebaseDatabase.getInstance().getReference("RutinasEjercicio").child("Natacion");
        myRef.child(idM).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                GestionRutinas gestionRutinas = dataSnapshot.getValue(GestionRutinas.class);
                etNroSesiones.setText(gestionRutinas.getNroSesiones());
                etAer.setText(gestionRutinas.getAer());
                etAel.setText(gestionRutinas.getAel());
                etAem.setText(gestionRutinas.getAem());
                etAei.setText(gestionRutinas.getAei());
                etPae.setText(gestionRutinas.getPae());
                etCla.setText(gestionRutinas.getCla());
                etPla.setText(gestionRutinas.getPla());
                etCala.setText(gestionRutinas.getCala());
                etPala.setText(gestionRutinas.getPala());
                tvVolumenT.setText(gestionRutinas.getVolumenT());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void adicionarGestionNatacion(String idM) {
        String microciclo = tvMicrociclo.getText().toString().trim();
        String mesociclo = tvMesociclo.getText().toString().trim();
        String etapa = spinnerEtapas.getSelectedItem().toString();
        String periodo = spinnerPeriodos.getSelectedItem().toString();
        String nroSesiones = etNroSesiones.getText().toString().trim();
        String aer = etAer.getText().toString().trim();
        String ael = etAel.getText().toString().trim();
        String aem = etAem.getText().toString().trim();
        String aei = etAei.getText().toString().trim();
        String pae = etPae.getText().toString().trim();
        String cla = etCla.getText().toString().trim();
        String pla = etPla.getText().toString().trim();
        String cala = etCala.getText().toString().trim();
        String pala = etPala.getText().toString().trim();

        int Aer = Integer.parseInt(aer);
        int Ael = Integer.parseInt(ael);
        int Aem = Integer.parseInt(aem);
        int Aei = Integer.parseInt(aei);
        int Pae = Integer.parseInt(pae);
        int Cla = Integer.parseInt(cla);
        int Pla = Integer.parseInt(pla);
        int Cala = Integer.parseInt(cala);
        int Pala = Integer.parseInt(pala);
        double sesion = Integer.parseInt(nroSesiones);

        double volT = (Aer+Ael+Aem+Aei+Pae+Cla+Pla+Cala+Pala);
        double vol = (Aer+Ael+Aem+Aei+Pae+Cla+Pla+Cala+Pala)/sesion;

        String volumenT = String.valueOf(volT);
        String volumen = String.valueOf(vol);
        if(!TextUtils.isEmpty(microciclo)){

            GestionRutinas gestionRutinas = new GestionRutinas(microciclo, mesociclo, etapa, periodo, nroSesiones, aer, ael,aem,aei,pae,cla,pla,cala,pala, volumenT, volumen);
            myRef.child(idM).setValue(gestionRutinas);
            tvVolumenT.setText(volumenT);
            mostrarMessage("Adicionado");
        }
    }

    public int nroMicrociclo(){
        Date date = new Date();
        calendar.setTime(date);
        return calendar.get(Calendar.WEEK_OF_YEAR);
    }
    public String nroMesociclo(){
        long date = System.currentTimeMillis();
        SimpleDateFormat m = new SimpleDateFormat("MM");
        return m.format(date);
    }
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
