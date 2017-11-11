package com.example.summy.controlrendimiento.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.summy.controlrendimiento.R;


/**
 * Created by SUMMY on 28/9/2017.
 */

public class DiariaActivity extends AppCompatActivity{

    //herramienta para crer el toolbar
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diaria);

        //Vilculamos nuestro toolbar con el XML
        toolbar = (Toolbar) findViewById(R.id.tvMenu);
        toolbar.setTitle("ACTIVIDAD DE HOY");
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_diaria,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.item2:
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(DiariaActivity.this);
                View mView = getLayoutInflater().inflate(R.layout.activity_datos_frecuencia_volumen, null);

                mBuilder.setView(mView);
                final AlertDialog dialog = mBuilder.create();
                dialog.show();
                break;
        }
        switch (item.getItemId()){
            case R.id.item3:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
