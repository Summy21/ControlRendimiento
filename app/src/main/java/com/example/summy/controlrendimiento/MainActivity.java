package com.example.summy.controlrendimiento;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.summy.controlrendimiento.views.DiariaActivity;
import com.example.summy.controlrendimiento.views.RegistroActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";


    //https://www.youtube.com/watch?v=FTmdSGBlhWA&list=PLgCYzUzKIBE_cyEsXgIcwC3P8ipvlSFd_&index=2
    //ayuda de video
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;


    private EditText mEmail,mPassword;
    private Button btnSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEmail = (EditText) findViewById(R.id.emailEditText);
        mPassword = (EditText) findViewById(R.id.passwordEditText);
        btnSignIn = (Button) findViewById(R.id.signInButton);

        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {

                    Intent intent = new Intent(MainActivity.this, DiariaActivity.class);
                    //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);

                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                    Toast.makeText(MainActivity.this,"Iniciando Secion",Toast.LENGTH_LONG).show();
                    finish();


                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");

                }
                // ...
            }
        };
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = mEmail.getText().toString();
                String pass= mPassword.getText().toString();
                if (!email.equals("") && !pass.equals("")){
                    mAuth.signInWithEmailAndPassword(email, pass);
                }else {
                    Toast.makeText(MainActivity.this,"Completar campos para Iniciar Secion",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void irAcrearCuenta(View view){
        Intent intent = new Intent(MainActivity.this, RegistroActivity.class);
        startActivity(intent);
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
}
