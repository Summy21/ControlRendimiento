package com.example.summy.controlrendimiento;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by SUMMY on 28/9/2017.
 */

public class RegistroActivity extends AppCompatActivity {

    private TextView detalleUserTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        detalleUserTextView = (TextView)findViewById(R.id.detalleUserTextView);
    }

    public void finalizarRegistro(View view) {
        Intent intent = new Intent(this, DiariaActivity.class);
        this.startActivity(intent);
    }
}
