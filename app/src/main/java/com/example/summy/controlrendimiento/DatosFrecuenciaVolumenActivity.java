package com.example.summy.controlrendimiento;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

/**
 * Created by Nancy on 28/09/17.
 */

public class DatosFrecuenciaVolumenActivity extends AppCompatActivity {

    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_frecuencia_volumen);

        //1 llamamos el toolbar de nuestro layout
        toolbar = (Toolbar) findViewById(R.id.tbMenu);

        //2 Enlazamos el toolbar con el XML
        setSupportActionBar(toolbar);

        //3 habilitamos el titulo del toolbar
        getSupportActionBar().setDisplayShowTitleEnabled(true);

        //4 Se crea el boton para volver a la actividad padre (Previamente instanciar los atributos en el Manifest)
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);


    }
}
