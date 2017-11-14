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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
        myRef = mFirebaseDatabase.getReference();
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
                showData(dataSnapshot);

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
/*
    buttonUpdate.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String name = editTextName.getText().toString().trim();
            String genre = spinnerGenre.getSelectedItem().toString();
            if (!TextUtils.isEmpty(name)) {
                updateArtist(artistId, name, genre);
                b.dismiss();
            }
        }
    });

    private boolean updateArtist(String id, String name, String genre) {
        //getting the specified artist reference
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("artists").child(id);

        //updating artist
        Artist artist = new Artist(id, name, genre);
        dR.setValue(artist);
        Toast.makeText(getApplicationContext(), "Artist Updated", Toast.LENGTH_LONG).show();
        return true;
    }
*/
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
            mostrarMessage("Registro modificado");
            Intent intent = new Intent(getApplicationContext(), DiariaActivity.class);
            this.startActivity(intent);
            finish();

        }else{
            mostrarMessage("Falta completar los datos");
            //Toast.makeText(this,"Falta completar los datos",Toast.LENGTH_LONG).show();
        }
    }

    public void showToolbar(String tittle, boolean upButton){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(tittle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);

    }
    public void showData (DataSnapshot dataSnapshot){
        for (DataSnapshot ds : dataSnapshot.getChildren()){
            Atleta atleta = new Atleta();
            atleta.setNombres(ds.child(userId).getValue(Atleta.class).getNombres());
            atleta.setPaterno(ds.child(userId).getValue(Atleta.class).getPaterno());
            atleta.setMaterno(ds.child(userId).getValue(Atleta.class).getMaterno());
            atleta.setEstatura(ds.child(userId).getValue(Atleta.class).getEstatura());
            atleta.setGenero(ds.child(userId).getValue(Atleta.class).getGenero());
            atleta.setPeso(ds.child(userId).getValue(Atleta.class).getPeso());
            atleta.setTelCelular(ds.child(userId).getValue(Atleta.class).getTelCelular());
            atleta.setDomicilio(ds.child(userId).getValue(Atleta.class).getDomicilio());
            atleta.setTelFamiliar(ds.child(userId).getValue(Atleta.class).getTelFamiliar());
            atleta.setTelSeguroMedico(ds.child(userId).getValue(Atleta.class).getTelSeguroMedico());

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
    }

    private void mostrarMessage(String mensaje) {
        Snackbar.make(rootView, mensaje, Snackbar.LENGTH_LONG).show();
    }
}
