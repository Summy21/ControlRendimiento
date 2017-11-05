package com.example.summy.controlrendimiento;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
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

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;

    private TextView tvCrearCuenta;
    private Button iniciarSesionButton;

    private EditText emailEditText;
    private EditText passwordEditText;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //blindiar
        tvCrearCuenta = (TextView) findViewById(R.id.tvCrearCuenta);
        iniciarSesionButton = (Button)findViewById(R.id.iniciarSesionButton);

        emailEditText = (EditText)findViewById(R.id.emailEditText);
        passwordEditText = (EditText)findViewById(R.id.passwordEditText);

        inicialize(); //inicializara los objetos de la parte superior

        iniciarSesionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                iniciarSesion(emailEditText.getText().toString(), passwordEditText.getText().toString());
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
                    Log.w(TAG, "onAuthStateChanged - logeado" + firebaseUser.getUid());              //onAuthStateChanged<--proviene de que el estado cambio y si es != null entonces trago un usuario y esta logeado
                    Log.w(TAG, "onAuthStateChanged - logeado" + firebaseUser.getEmail());
                }else {
                    Log.w(TAG, "onAuthStateChanged - cerro sesion");
                }
            }
        };
    }

    public void irAcrearCuenta(View view){
        Intent intent = new Intent(MainActivity.this, RegistroActivity.class);
        startActivity(intent);
    }


    private void iniciarSesion(String email, String pass) {

        firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                    if (firebaseUser.getEmail().equalsIgnoreCase("admin@admin.com")){
                        Toast.makeText(MainActivity.this, "Autenticacion exitosa como Admin", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, RegistroAdminActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish();
                    }else{
                    Toast.makeText(MainActivity.this, "Autenticacion exitosa", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, DiariaActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Autenticacion no exitosa", Toast.LENGTH_SHORT).show();
                }
            }
        });
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


}
