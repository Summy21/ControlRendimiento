package com.example.summy.controlrendimiento;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by SUMMY on 28/9/2017.
 */

public class RegistroActivity extends AppCompatActivity {

    private TextView detalleUserTextView;
    private Button cerrarSesionButton;

    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;

    private static final String TAG = "RegistroActivity";

    private EditText etEmail;
    private EditText etPasswordCreateaccount;

    //datos registro
    private TextView etnombres;
    private TextView etpaterno;
    private TextView etmaterno;
    private TextView etestatura;
    private TextView etgenero;
    private TextView etpeso;
    private TextView ettelefonocelular;
    private TextView etdireccion;
    private TextView ettelefonofamiliar;
    private TextView ettelefonoseguromedico;
    private Button btnfinalizar;
    DatabaseReference databaseAtleta;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        detalleUserTextView = (TextView)findViewById(R.id.detalleUserTextView);
        cerrarSesionButton = (Button) findViewById(R.id.cerrarSesionButton);

        etEmail = (EditText)findViewById(R.id.etEmail);
        etPasswordCreateaccount = (EditText)findViewById(R.id.etPasswordCreateaccount);
     //
        etnombres = (TextView) findViewById(R.id.etNombre);
        etpaterno = (TextView) findViewById(R.id.etPaterno);
        etmaterno = (TextView) findViewById(R.id.etMaterno);
        etestatura = (TextView) findViewById(R.id.etEstatura);
        etgenero = (TextView) findViewById(R.id.etGenero);
        etpeso = (TextView) findViewById(R.id.etPeso);
        ettelefonocelular = (TextView) findViewById(R.id.etTelefonoCelular);
        etdireccion = (TextView) findViewById(R.id.etDireccion);
        ettelefonofamiliar = (TextView) findViewById(R.id.etTelFamiliar);
        ettelefonoseguromedico = (TextView) findViewById(R.id.etTelSeguroMedico);
        btnfinalizar = (Button)findViewById(R.id.btnFinalizarRegistro);
        databaseAtleta = FirebaseDatabase.getInstance().getReference("atleta");

        btnfinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                crearCuenta(etEmail.getText().toString(), etPasswordCreateaccount.getText().toString());
                adicionarAtleta();

            }
        });

        inicialize();

        cerrarSesionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cerrarSesion();
            }
        });
    }

    private void inicialize() {
        firebaseAuth = FirebaseAuth.getInstance();
        authStateListener = new FirebaseAuth.AuthStateListener() {          //<--authlistener es donde detecta que hubo cambios en la sesion
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();  //FirebaseUser agarramos todos los gatos del usuario de la autenticacion una ves que ocurrio
                if (firebaseUser != null){
                    detalleUserTextView.setText("ID User: " + firebaseUser.getUid() + " email: " + firebaseUser.getEmail());
                }else {
                    Log.w(TAG, "onAuthStateChanged - cerro sesion");
                }
            }
        };
    }

    private void crearCuenta(String email, String pass) {

        firebaseAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                if (task.isSuccessful()) {
                    Toast.makeText(RegistroActivity.this, "Creacion de cuenta exitosa", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegistroActivity.this, DiariaActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(RegistroActivity.this, "Creacion de cuenta no exitosa", Toast.LENGTH_SHORT).show();
                }
            }
        });

      /*  Intent intent = new Intent(this, RegistroActivity.class);
        this.startActivity(intent);*/
    }
    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(authStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        firebaseAuth.removeAuthStateListener(authStateListener);
    }

    private void cerrarSesion() {
        firebaseAuth.signOut();
    }

    //public void finalizarRegistro(View view) {
    //    Intent intent = new Intent(this, DiariaActivity.class);
    //    this.startActivity(intent);
   // }

    private void adicionarAtleta (){
        String nombres = etnombres.getText().toString();
        String paterno = etpaterno.getText().toString();
        String materno = etmaterno.getText().toString();
        String estatura = etestatura.getText().toString();
        String genero = etgenero.getText().toString();
        String peso = etpeso.getText().toString();
        String telcelular= ettelefonocelular.getText().toString();
        String direccion= etdireccion.getText().toString();
        String telfamiliar= ettelefonofamiliar.getText().toString();
        String telseguromedico= ettelefonoseguromedico.getText().toString();

        if (!TextUtils.isEmpty(nombres)){

            String id = databaseAtleta.push().getKey();
            Atleta atleta = new Atleta(nombres,paterno,materno,estatura,genero,peso,telcelular,direccion,telfamiliar,telseguromedico);
            databaseAtleta.child(id).setValue(atleta);
            Toast.makeText(this,"registro adicionado",Toast.LENGTH_LONG).show();



            Intent intent = new Intent(this, DiariaActivity.class);
            this.startActivity(intent);

        }else{
            Toast.makeText(this,"Falta completar los datos",Toast.LENGTH_LONG).show();
        }
    }
}
