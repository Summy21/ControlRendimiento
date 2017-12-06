package com.example.summy.controlrendimiento.views;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.summy.controlrendimiento.R;
import com.example.summy.controlrendimiento.model.Atleta;
import com.example.summy.controlrendimiento.model.GestionRutinas;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.example.summy.controlrendimiento.R.id.tvVolumenT;

/**
 * Created by Nancy on 10/11/17.
 */

public class EditarRegistroAtleta extends AppCompatActivity {

    Toolbar toolbar;
    private View rootView;

    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference myRef;
    private String userId;

    private TextView etnombres;
    private TextView etpaterno;
    private TextView etmaterno;
    private TextView etestatura;
    private TextView etgenero;
    private TextView etpeso;
    private TextView ettelefonocelular;
    private TextView etdomicilio;
    private TextView ettelefonofamiliar;
    private TextView ettelefonoseguromedico;

    private Button btnFinalizarEdicionAtleta;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_registro_atleta);
        //usamos el toolbar reutilisable en el layout
        showToolbar(getResources().getString(R.string.toolbar_tittle_editar_registro), true);

        rootView = findViewById(R.id.rootViewEditarRegistro);

        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = FirebaseDatabase.getInstance().getReference("Atletas");
        FirebaseUser user = mAuth.getCurrentUser();
        userId = user.getUid();

        etnombres = (TextView) findViewById(R.id.etNombre);
        etpaterno = (TextView) findViewById(R.id.etPaterno);
        etmaterno = (TextView) findViewById(R.id.etMaterno);
        etestatura = (TextView) findViewById(R.id.etEstatura);
        etgenero = (TextView) findViewById(R.id.etGenero);
        etpeso = (TextView) findViewById(R.id.etPeso);
        ettelefonocelular = (TextView) findViewById(R.id.etTelefonoCelular);
        etdomicilio = (TextView) findViewById(R.id.etDomicilio);
        ettelefonofamiliar = (TextView) findViewById(R.id.etTelFamiliar);
        ettelefonoseguromedico = (TextView) findViewById(R.id.etTelSeguroMedico);

        btnFinalizarEdicionAtleta = (Button) findViewById(R.id.btnFinalizarEdicion);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                showData(userId);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        btnFinalizarEdicionAtleta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modificar(userId);
            }
        });


    }

    private void modificar(String userId) {
        String nombres = etnombres.getText().toString().trim();
        String paterno = etpaterno.getText().toString().trim();
        String materno = etmaterno.getText().toString().trim();
        String estatura = etestatura.getText().toString().trim();
        String genero = etgenero.getText().toString().trim();
        String peso = etpeso.getText().toString().trim();
        String telcelular= ettelefonocelular.getText().toString().trim();
        String direccion= etdomicilio.getText().toString().trim();
        String telfamiliar= ettelefonofamiliar.getText().toString().trim();
        String telseguromedico= ettelefonoseguromedico.getText().toString().trim();

        if (!TextUtils.isEmpty(nombres)){
            DatabaseReference dR = FirebaseDatabase.getInstance().getReference("Atletas").child(userId);
            Atleta atleta = new Atleta(nombres,paterno,materno,estatura,genero,peso,telcelular,direccion,telfamiliar,telseguromedico);
            dR.setValue(atleta);
            Toast.makeText(this,"Registro modificado",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(getApplicationContext(), DiariaActivity.class);
            this.startActivity(intent);
            finish();

        }else{
            mostrarMessage("Falta completar los datos");
            Toast.makeText(this,"Falta completar los datos",Toast.LENGTH_LONG).show();
        }
    }

    public void showData (String user){
        myRef = FirebaseDatabase.getInstance().getReference("Atletas");
        myRef.child(user).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Atleta atleta = dataSnapshot.getValue(Atleta.class);
                etnombres.setText(atleta.getNombres());
                etpaterno.setText(atleta.getPaterno());
                etmaterno.setText(atleta.getMaterno());
                etestatura.setText(atleta.getEstatura());
                etgenero.setText(atleta.getGenero());
                etpeso.setText(atleta.getPeso());
                ettelefonocelular.setText(atleta.getTelCelular());
                etdomicilio.setText(atleta.getDomicilio());
                ettelefonofamiliar.setText(atleta.getTelFamiliar());
                ettelefonoseguromedico.setText(atleta.getTelSeguroMedico());
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
