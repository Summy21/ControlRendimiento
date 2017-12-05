package com.example.summy.controlrendimiento.views;

import android.icu.text.SimpleDateFormat;
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
import android.widget.TextView;

import com.example.summy.controlrendimiento.R;
import com.example.summy.controlrendimiento.model.Entrenamiento;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EntrenamientoCarreraAdminActivity extends AppCompatActivity {

    private EditText etcalentamientoCa;
    private EditText etFasePrinc1Ca;
    private EditText etFasePrinc2Ca;
    private EditText etFaseFundCa;
    private EditText etVueltaCalmaCa;

    private View rootView;

    private DatabaseReference myRef;
    boolean firstTime = true;
    String id = "entrPedestrismo1";

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrenamiento_carrera_admin);

        rootView = findViewById(R.id.rootViewEntrenamientoCa);
        showToolbar("Entrenamiento Diario", true);

        TextView tvDia = (TextView) findViewById(R.id.tvDia);
        TextView tvMes = (TextView) findViewById(R.id.tvMes);
        TextView tvAnio = (TextView) findViewById(R.id.tvAnio);
        long date = System.currentTimeMillis();
        SimpleDateFormat d = new SimpleDateFormat("dd");
        SimpleDateFormat m = new SimpleDateFormat("MMMM");
        SimpleDateFormat a = new SimpleDateFormat("yyyy");
        String diaString = d.format(date);
        String mesString = m.format(date);
        String anioString = a.format(date);
        tvDia.setText(diaString + " / ");
        tvMes.setText(mesString + " / ");
        tvAnio.setText(anioString);

        myRef = FirebaseDatabase.getInstance().getReference("Entrenamientos").child("Pedestrismo");

        etcalentamientoCa = (EditText) findViewById(R.id.etcalentamientoCa);
        etFasePrinc1Ca = (EditText) findViewById(R.id.etFasePrinc1Ca);
        etFasePrinc2Ca = (EditText) findViewById(R.id.etFasePrinc2Ca);
        etFaseFundCa = (EditText) findViewById(R.id.etFaseFundCa);
        etVueltaCalmaCa = (EditText) findViewById(R.id.etVueltaCalmaCa);

        Button btnGuardar = (Button) findViewById(R.id.btnGuardar);

        if(firstTime){
            mostrarEntrenamiento(id);
        }

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adicionarEntrenamiento(id);
                finish();
            }
        });
    }

    private void adicionarEntrenamiento(String id) {
        String calentamiento = etcalentamientoCa.getText().toString().trim();
        String fasePrinc1 = etFasePrinc1Ca.getText().toString().trim();
        String fasePrinc2 = etFasePrinc2Ca.getText().toString().trim();
        String faseFund = etFaseFundCa.getText().toString().trim();
        String vueltaCalma = etVueltaCalmaCa.getText().toString().trim();

        if(!TextUtils.isEmpty(calentamiento)){
            //     id = myRef.push().getKey();

            Entrenamiento entrenamientoNatacion = new Entrenamiento(id, calentamiento,fasePrinc1,fasePrinc2,faseFund,vueltaCalma);
            myRef.child(id).setValue(entrenamientoNatacion);
            mostrarMessage("Entrenamiento adicionado");
            firstTime = true;

        }else{
            mostrarMessage("Falta completar los datos");
        }

    }

    private void mostrarEntrenamiento(String id){

        myRef.child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Entrenamiento entrenamientoCarrera = dataSnapshot.getValue(Entrenamiento.class);

                etcalentamientoCa.setText(entrenamientoCarrera.getCalentamiento());
                etFasePrinc1Ca.setText(entrenamientoCarrera.getFasePrinc1());
                etFasePrinc2Ca.setText(entrenamientoCarrera.getFasePrinc2());
                etFaseFundCa.setText(entrenamientoCarrera.getFaseFund());
                etVueltaCalmaCa.setText(entrenamientoCarrera.getVueltaCalma());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void mostrarMessage(String mensaje) {
        Snackbar.make(rootView, mensaje, Snackbar.LENGTH_LONG).show();
    }
    public void showToolbar(String tittle, boolean upButton){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(tittle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);

    }
}


