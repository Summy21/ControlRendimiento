package com.example.summy.controlrendimiento.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.summy.controlrendimiento.MainActivity;
import com.example.summy.controlrendimiento.R;
import com.google.firebase.auth.FirebaseAuth;


/**
 * Created by SUMMY on 28/9/2017.
 */

public class DiariaActivity extends AppCompatActivity{

    //herramienta para crer el toolbar
    Toolbar toolbar;

    //// Cerrando secion
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diaria);

        //Vilculamos nuestro toolbar con el XML
        toolbar = (Toolbar) findViewById(R.id.tbMenu);
        toolbar.setTitle("Control Rendiminto");
        setSupportActionBar(toolbar);

        ///// secion instancia
        mAuth = FirebaseAuth.getInstance();



        //para obtener el id del usuario
        //FirebaseUser user = mAuth.getCurrentUser();
        //String id = user.getUid();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.item1:
                startActivity(new Intent(getApplicationContext(),DatosFrecuenciaVolumenActivity.class));
                break;
        }
        switch (item.getItemId()){
            case R.id.item2:
                startActivity(new Intent(getApplicationContext(),DatosFrecuenciaVolumenActivity.class));
                break;
        }
        switch (item.getItemId()){
            case R.id.item3:

                mAuth.signOut();
                Toast.makeText(DiariaActivity.this,"'Cerrando Secion'",Toast.LENGTH_LONG).show();
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();

                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
