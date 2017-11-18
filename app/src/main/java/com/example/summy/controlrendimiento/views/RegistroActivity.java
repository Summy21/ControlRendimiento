package com.example.summy.controlrendimiento.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;
import com.example.summy.controlrendimiento.R;
import com.example.summy.controlrendimiento.model.Atleta;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by SUMMY on 28/9/2017.
 */

public class RegistroActivity extends AppCompatActivity {


    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;

    private static final String TAG = "RegistroActivity";

    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference myRef;

    //datos registro
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

    private Button btnfinalizar;

    private View rootView;

    DatabaseReference databaseAtleta;

    Bundle extras;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        myRef = FirebaseDatabase.getInstance().getReference("Atletas");
        Intent intent= getIntent();
        extras = intent.getExtras();

        Toast.makeText(RegistroActivity.this, " "+extras.getString("idUser") , Toast.LENGTH_SHORT).show();

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
        btnfinalizar = (Button)findViewById(R.id.btnFinalizarRegistro);

        showToolbar("Crear Cuenta", false);

        rootView = findViewById(R.id.rootViewRegistro);

       // databaseAtleta = FirebaseDatabase.getInstance().getReference("atleta");

        btnfinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             //   crearCuenta(etEmail.getText().toString(), etPasswordCreateaccount.getText().toString());
                adicionarAtleta(extras.getString("idUser"));
            }
        });


    }


    private void adicionarAtleta (String idUser){
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


            Atleta atleta = new Atleta(nombres,paterno,materno,estatura,genero,peso,telcelular,direccion,telfamiliar,telseguromedico);

            myRef.child(idUser).setValue(atleta);
            Toast.makeText(this,"registro adicionado",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(getApplicationContext(), DiariaActivity.class);
            this.startActivity(intent);
            finish();

        }else{
            mostrarMessage("Falta completar los datos");
            //Toast.makeText(this,"Falta completar los datos",Toast.LENGTH_LONG).show();
        }
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
