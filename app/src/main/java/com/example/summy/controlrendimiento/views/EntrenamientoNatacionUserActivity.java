package com.example.summy.controlrendimiento.views;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.summy.controlrendimiento.R;
import com.example.summy.controlrendimiento.model.EntrenamientoRut;
import com.example.summy.controlrendimiento.model.GestionRutinas;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Date;

/**
 * Created by SUMMY on 12/6/2017.
 */

@RequiresApi(api = Build.VERSION_CODES.N)
public class EntrenamientoNatacionUserActivity extends AppCompatActivity{

    private View rootView;
    private FirebaseAuth mAuth;

    private DatabaseReference myRef;

    EditText etAer;
    EditText etAel;
    EditText etAem;
    EditText etAei;
    EditText etPae;
    EditText etCla;
    EditText etPla;
    EditText etCala;
    EditText etPala;

    TextView tvAer;
    TextView tvAel;
    TextView tvAem;
    TextView tvAei;
    TextView tvPae;
    TextView tvCla;
    TextView tvPla;
    TextView tvCala;
    TextView tvPala;
    Calendar calendar = Calendar.getInstance();

    private static final String TAG = "entrenamiento natacion";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrenamiento_natacion_user);
        showToolbar("Entrenamiento Natacion", true);
        rootView = findViewById(R.id.rootViewENU);
        mAuth = FirebaseAuth.getInstance();

        Button btnGuardar = (Button) findViewById(R.id.btnGuardar);

        final String idM = String.valueOf(nroMicrociclo());

        tvAer = (TextView) findViewById(R.id.tvAer);
        tvAel = (TextView) findViewById(R.id.tvAel);
        tvAem = (TextView) findViewById(R.id.tvAem);
        tvAei = (TextView) findViewById(R.id.tvAei);
        tvPae = (TextView) findViewById(R.id.tvPae);
        tvCla = (TextView) findViewById(R.id.tvCla);
        tvPla = (TextView) findViewById(R.id.tvPla);
        tvCala = (TextView) findViewById(R.id.tvCala);
        tvPala = (TextView) findViewById(R.id.tvPala);

        etAer = (EditText) findViewById(R.id.etAer);
        etAel = (EditText) findViewById(R.id.etAel);
        etAem = (EditText) findViewById(R.id.etAem);
        etAei = (EditText) findViewById(R.id.etAei);
        etPae = (EditText) findViewById(R.id.etPae);
        etCla = (EditText) findViewById(R.id.etCla);
        etPla = (EditText) findViewById(R.id.etPla);
        etCala = (EditText) findViewById(R.id.etCala);
        etPala = (EditText) findViewById(R.id.etPala);

        buscaMicroCiclo(idM);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adicionarEntrenamientoNatacion(idM);
                startActivity(new Intent(getApplicationContext(),DiariaActivity.class));
                finish();
            }
        });
    }

    public void buscaMicroCiclo(final String idM){
        myRef = FirebaseDatabase.getInstance().getReference("RutinasEjercicio").child("Natacion");
        Query query = myRef
                .orderByKey()
                .equalTo(idM)
                .limitToFirst(1);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(!dataSnapshot.exists()){
                    Log.w(TAG, "no encontrado");
                }
                for (DataSnapshot singleSnapshot: dataSnapshot.getChildren())
                {
                    if(singleSnapshot.exists()){
                        Log.w(TAG, "encontrado");
                        mostrarGestionNatacion(idM);
                    }

                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    private void mostrarGestionNatacion(String idM) {
        myRef = FirebaseDatabase.getInstance().getReference("RutinasEjercicio").child("Natacion");
        myRef.child(idM).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                GestionRutinas gestionRutinas = dataSnapshot.getValue(GestionRutinas.class);
                tvAer.setText(gestionRutinas.getAer());
                tvAel.setText(gestionRutinas.getAel());
                tvAem.setText(gestionRutinas.getAem());
                tvAei.setText(gestionRutinas.getAei());
                tvPae.setText(gestionRutinas.getPae());
                tvCla.setText(gestionRutinas.getCla());
                tvPla.setText(gestionRutinas.getPla());
                tvCala.setText(gestionRutinas.getCala());
                tvPala.setText(gestionRutinas.getPala());
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void adicionarEntrenamientoNatacion(String idM) {
        String idUser = mAuth.getCurrentUser().getUid();
        String idFecha = fechaActual();
        myRef = FirebaseDatabase.getInstance().getReference("EntrenamientoRut").child("Natacion").child(idM).child(idUser).child(idFecha);

        String aer = etAer.getText().toString().trim();
        String ael = etAel.getText().toString().trim();
        String aem = etAem.getText().toString().trim();
        String aei = etAei.getText().toString().trim();
        String pae = etPae.getText().toString().trim();
        String cla = etCla.getText().toString().trim();
        String pla = etPla.getText().toString().trim();
        String cala = etCala.getText().toString().trim();
        String pala = etPala.getText().toString().trim();

        double Aer = Double.parseDouble(aer);
        double Ael = Double.parseDouble(ael);
        double Aem = Double.parseDouble(aem);
        double Aei = Double.parseDouble(aei);
        double Pae = Double.parseDouble(pae);
        double Cla = Double.parseDouble(cla);
        double Pla = Double.parseDouble(pla);
        double Cala = Double.parseDouble(cala);
        double Pala = Double.parseDouble(pala);

        double volT = (Aer+Ael+Aem+Aei+Pae+Cla+Pla+Cala+Pala);
        double vol = (Aer+Ael+Aem+Aei+Pae+Cla+Pla+Cala+Pala)/10;

        double carg = Aer*1+Ael*2+Aem*3+Aei*6+Pae*10+Cla*14+Pla*12+Cala*8+Pala*6;

        String volumenT = String.valueOf(volT);
        String volumen = String.valueOf(vol);
        String carga = String.valueOf(carg);

        if(!TextUtils.isEmpty(aer)){

            EntrenamientoRut entrenamientoRut = new EntrenamientoRut(aer, ael,aem,aei,pae,cla,pla,cala,pala, volumenT, volumen, carga, idM);
            myRef.setValue(entrenamientoRut);
            mostrarMessage("Adicionado");
        }
    }
    private void mostrarEntrenamientoNatacion(String idM) {
        String idUser = mAuth.getCurrentUser().getUid();
        myRef = FirebaseDatabase.getInstance().getReference("EntrenamientoRut").child("Natacion").child(idM);
        myRef.child(idUser).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                GestionRutinas gestionRutinas = dataSnapshot.getValue(GestionRutinas.class);
                etAer.setText(gestionRutinas.getAer());
                etAel.setText(gestionRutinas.getAel());
                etAem.setText(gestionRutinas.getAem());
                etAei.setText(gestionRutinas.getAei());
                etPae.setText(gestionRutinas.getPae());
                etCla.setText(gestionRutinas.getCla());
                etPla.setText(gestionRutinas.getPla());
                etCala.setText(gestionRutinas.getCala());
                etPala.setText(gestionRutinas.getPala());
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    public int nroMicrociclo(){
        Date date = new Date();
        calendar.setTime(date);
        return calendar.get(Calendar.WEEK_OF_YEAR);
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
    public String fechaActual(){
        Calendar f = Calendar.getInstance();
        Date fechacompleta = f.getTime();
        Log.w(TAG, String.valueOf(fechacompleta));
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        String formatoFecha= df.format(f.getTime());
        Log.w(TAG, formatoFecha);
        return  formatoFecha;
    }
}
