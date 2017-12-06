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

public class EntrenamientoNatacionAdminActivity extends AppCompatActivity {

    private EditText etcalentamientoN;
    private EditText etFasePrinc1N;
    private EditText etFasePrinc2N;
    private EditText etFaseFundN;
    private EditText etVueltaCalmaN;

    private View rootView;

    private DatabaseReference myRef;
    boolean firstTime = true;
    String id = "entrNatacion1";

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrenamiento_natacion_admin);
        rootView = findViewById(R.id.rootViewEntrenamientoN);
        showToolbar("Entrenamiento Diario", true);
        //DIA
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

        myRef = FirebaseDatabase.getInstance().getReference("Entrenamientos").child("Natacion");

        etcalentamientoN = (EditText) findViewById(R.id.etcalentamientoN);
        etFasePrinc1N = (EditText) findViewById(R.id.etFasePrinc1N);
        etFasePrinc2N = (EditText) findViewById(R.id.etFasePrinc2N);
        etFaseFundN = (EditText) findViewById(R.id.etFaseFundN);
        etVueltaCalmaN = (EditText) findViewById(R.id.etVueltaCalmaN);

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
        String calentamiento = etcalentamientoN.getText().toString().trim();
        String fasePrinc1 = etFasePrinc1N.getText().toString().trim();
        String fasePrinc2 = etFasePrinc2N.getText().toString().trim();
        String faseFund = etFaseFundN.getText().toString().trim();
        String vueltaCalma = etVueltaCalmaN.getText().toString().trim();

        if(!TextUtils.isEmpty(calentamiento)){
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
                Entrenamiento entrenamientoNatacion = dataSnapshot.getValue(Entrenamiento.class);

                etcalentamientoN.setText(entrenamientoNatacion.getCalentamiento());
                etFasePrinc1N.setText(entrenamientoNatacion.getFasePrinc1());
                etFasePrinc2N.setText(entrenamientoNatacion.getFasePrinc2());
                etFaseFundN.setText(entrenamientoNatacion.getFaseFund());
                etVueltaCalmaN.setText(entrenamientoNatacion.getVueltaCalma());
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
