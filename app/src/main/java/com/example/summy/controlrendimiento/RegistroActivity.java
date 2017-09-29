package com.example.summy.controlrendimiento;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by SUMMY on 28/9/2017.
 */

public class RegistroActivity extends AppCompatActivity {

    private TextView detalleUserTextView;
    private Button cerrarSesionButton;

    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;

    private static final String TAG = "RegistroActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        detalleUserTextView = (TextView)findViewById(R.id.detalleUserTextView);
        cerrarSesionButton = (Button) findViewById(R.id.cerrarSesionButton);

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

    public void finalizarRegistro(View view) {
        Intent intent = new Intent(this, DiariaActivity.class);
        this.startActivity(intent);
    }


}
