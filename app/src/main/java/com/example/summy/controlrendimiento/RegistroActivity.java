package com.example.summy.controlrendimiento;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by SUMMY on 28/9/2017.
 */

public class RegistroActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
    }

    public void finalizarRegistro(View view) {
        Intent intent = new Intent(this, DiariaActivity.class);
        this.startActivity(intent);
    }
}
