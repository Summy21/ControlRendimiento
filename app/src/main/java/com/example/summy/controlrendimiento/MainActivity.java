package com.example.summy.controlrendimiento;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.summy.controlrendimiento.views.DiariaActivity;
import com.example.summy.controlrendimiento.views.EditarRegistroAtleta;
import com.example.summy.controlrendimiento.views.RegistroActivity;
import com.example.summy.controlrendimiento.views.RegistroAdminActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
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
    private Button btnSignUp;

    private View rootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEmail = (EditText) findViewById(R.id.emailEditText);
        mPassword = (EditText) findViewById(R.id.passwordEditText);
        btnSignIn = (Button) findViewById(R.id.signInButton);
        btnSignUp = (Button) findViewById(R.id.signUpButton);

        rootView = findViewById(R.id.rootViewLogin);

        mAuth = FirebaseAuth.getInstance();



        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");

                }
            }
        };

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn(mEmail.getText().toString(), mPassword.getText().toString());
            }
        });
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUp(mEmail.getText().toString(), mPassword.getText().toString());
            }
        });
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

    public void signIn(String email, String password) {
        Log.d(TAG, "signIn:" + email);
        if (!validateForm()) {
         //   Toast.makeText(MainActivity.this,"Completar campos para Iniciar Secion",Toast.LENGTH_LONG).show();
            mostrarMessage("Completar campos para crear una cuenta");
            return;
        }
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            if (user.getEmail().equalsIgnoreCase("admin@admin.com")){
                                mostrarMessage("Autenticacion exitosa como Admin");
                             //   Toast.makeText(MainActivity.this, "Autenticacion exitosa como Admin", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(MainActivity.this, RegistroAdminActivity.class);
                                startActivity(intent);
                                finish();
                            }else{
                                mostrarMessage("Autenticacion exitosa");
                            //    Toast.makeText(MainActivity.this, "Autenticacion exitosa", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(MainActivity.this, DiariaActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        } else {
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                      //      Toast.makeText(MainActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                        }
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "signInWithEmail:failed", task.getException());
                            mostrarMessage("Autenticacion fallida");
                        }
                    }
                });
    }

    public void signUp(String email, String password) {
        Log.d(TAG, "signIn:" + email);
        if (!validateForm()) {
        //    Toast.makeText(MainActivity.this,"Completar campos para crear una cuenta",Toast.LENGTH_LONG).show();
            mostrarMessage("Completar campos para crear una cuenta");
            return;
        }
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());
                            mostrarMessage("Creacion de cuenta exitosa");
                            Intent intent = new Intent(MainActivity.this, RegistroActivity.class);
                            startActivity(intent);
                            finish();
                        }
                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            mostrarMessage("Autenticacion fallida");
                        }
                    }
                });
    }

    private boolean validateForm() {
        boolean valid = true;

        String email = mEmail.getText().toString();
        if (TextUtils.isEmpty(email)) {
            valid = false;
        }

        String password = mPassword.getText().toString();
        if (TextUtils.isEmpty(password)) {
            valid = false;
        }

        return valid;
    }


    private void mostrarMessage(String mensaje) {
        Snackbar.make(rootView, mensaje, Snackbar.LENGTH_LONG).show();
    }
}