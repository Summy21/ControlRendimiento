package com.example.summy.controlrendimiento;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void crearCuenta(View view) {
        Intent intent = new Intent(this, RegistroActivity.class);
        this.startActivity(intent);
    }

    public void iniciarSesion(View view) {
        Intent intent = new Intent(this, DiariaActivity.class);
        this.startActivity(intent);
    }
}
