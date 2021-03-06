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

    private View rootView;

    ////////////////////
    private Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEmail = (EditText) findViewById(R.id.emailEditText);
        mPassword = (EditText) findViewById(R.id.passwordEditText);
        btnSignIn = (Button) findViewById(R.id.signInButton);

        rootView = findViewById(R.id.rootViewLogin);

        btnSignUp = (Button) findViewById(R.id.signUpButton);

        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
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
                String email = mEmail.getText().toString();
                String pass = mPassword.getText().toString();
                if (!email.equals("") && !pass.equals("")) {
                    signUp(email, pass);
                } else {
                    mostrarMessage("Datos incompletos");
                }
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
                                mostrarMessage("Autenticacion exitosa como Administrador");
                                Intent intent = new Intent(MainActivity.this, RegistroAdminActivity.class);
                                startActivity(intent);
                                finish();
                            }else{
                                mostrarMessage("Autenticacion exitosa");
                                Intent intent = new Intent(MainActivity.this, DiariaActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        } else {
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                        }
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "signInWithEmail:failed", task.getException());
                            mostrarMessage("Autenticacion fallida");
                        }
                    }
                });
    }

    private void signUp(String email, String pass) {

        mAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {
                    FirebaseUser User = mAuth.getCurrentUser();
                    String idUser = User.getUid();

                    Intent intent = new Intent(MainActivity.this, RegistroActivity.class);
                    intent.putExtra("idUser", idUser);
                    mostrarMessage("Creacion de cuenta exitosa");
                //    Toast.makeText(MainActivity.this, idUser, Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                    finish();
                } else {
                    mostrarMessage("La cuenta con este correo ya existe");
//                    Toast.makeText(MainActivity.this, "La cuenta con este correo ya existe.", Toast.LENGTH_SHORT).show();
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