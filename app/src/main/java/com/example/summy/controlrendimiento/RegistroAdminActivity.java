package com.example.summy.controlrendimiento;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegistroAdminActivity extends AppCompatActivity {

    private TextView detalleAdminTextView;
    private TextView cerrarSesionButton;

    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;

    private static final String TAG = "RegistroAdminActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_admin);

        detalleAdminTextView = (TextView)findViewById(R.id.detalleAdminTextView);
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
                    detalleAdminTextView.setText("ID User: " + firebaseUser.getUid() + " email: " + firebaseUser.getEmail());
                }else {
                    Log.w(TAG, "onAuthStateChanged - cerro sesion");
                }
            }
        };
    }
    private void cerrarSesion() {
        firebaseAuth.signOut();
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
