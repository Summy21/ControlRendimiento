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

public class EntrenamientoCiclismoAdminActivity extends AppCompatActivity {

    private EditText etcalentamientoC;
    private EditText etFasePrinc1C;
    private EditText etFasePrinc2C;
    private EditText etFaseFundC;
    private EditText etVueltaCalmaC;

    private View rootView;

    private DatabaseReference myRef;
    boolean firstTime = true;
    String id = "entrCiclismo1";

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrenamiento_ciclismo_admin);
        rootView = findViewById(R.id.rootViewEntrenamientoC);
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

        myRef = FirebaseDatabase.getInstance().getReference("Entrenamientos").child("Ciclismo");

        etcalentamientoC = (EditText) findViewById(R.id.etcalentamientoC);
        etFasePrinc1C = (EditText) findViewById(R.id.etFasePrinc1C);
        etFasePrinc2C = (EditText) findViewById(R.id.etFasePrinc2C);
        etFaseFundC = (EditText) findViewById(R.id.etFaseFundC);
        etVueltaCalmaC = (EditText) findViewById(R.id.etVueltaCalmaC);

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
        String calentamiento = etcalentamientoC.getText().toString().trim();
        String fasePrinc1 = etFasePrinc1C.getText().toString().trim();
        String fasePrinc2 = etFasePrinc2C.getText().toString().trim();
        String faseFund = etFaseFundC.getText().toString().trim();
        String vueltaCalma = etVueltaCalmaC.getText().toString().trim();

        if(!TextUtils.isEmpty(calentamiento)){
            //     id = myRef.push().getKey();

            Entrenamiento entrenamientoCiclismo = new Entrenamiento(id, calentamiento,fasePrinc1,fasePrinc2,faseFund,vueltaCalma);
            myRef.child(id).setValue(entrenamientoCiclismo);
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
                Entrenamiento entrenamientoCiclismo = dataSnapshot.getValue(Entrenamiento.class);

                etcalentamientoC.setText(entrenamientoCiclismo.getCalentamiento());
                etFasePrinc1C.setText(entrenamientoCiclismo.getFasePrinc1());
                etFasePrinc2C.setText(entrenamientoCiclismo.getFasePrinc2());
                etFaseFundC.setText(entrenamientoCiclismo.getFaseFund());
                etVueltaCalmaC.setText(entrenamientoCiclismo.getVueltaCalma());
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
