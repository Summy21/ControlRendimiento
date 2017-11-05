package com.example.summy.controlrendimiento;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;


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
        toolbar = (Toolbar) findViewById(R.id.tbMenu);
        toolbar.setTitle("Control Rendiminto");
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.item2:
                startActivity(new Intent(getApplicationContext(),DatosFrecuenciaVolumenActivity.class));
                break;
        }
        switch (item.getItemId()){
            case R.id.item3:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
