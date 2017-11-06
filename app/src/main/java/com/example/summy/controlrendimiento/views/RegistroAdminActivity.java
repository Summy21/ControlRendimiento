package com.example.summy.controlrendimiento.views;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.example.summy.controlrendimiento.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

@RequiresApi(api = Build.VERSION_CODES.N)
public class RegistroAdminActivity extends AppCompatActivity {

    private TextView detalleAdminTextView;
    private TextView cerrarSesionButton;

    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;

    private static final String TAG = "RegistroAdminActivity";

    //herramienta para crer el toolbar
    Toolbar toolbar;

    //Recycler
    private RecyclerView competenciasRecyclerView;
    private View rootView;

    //Fecha
    Calendar dateTime = Calendar.getInstance();
    private EditText campoFecha;
    private Button botonSeleccionFechaNal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_admin);

        //Vilculamos nuestro toolbar con el XML
        toolbar = (Toolbar) findViewById(R.id.tvMenu);
        toolbar.setTitle("Control Rendiminto");
        setSupportActionBar(toolbar);

        detalleAdminTextView = (TextView)findViewById(R.id.detalleAdminTextView);
        cerrarSesionButton = (Button) findViewById(R.id.cerrarSesionButton);

        //Recycler
        competenciasRecyclerView = (RecyclerView) findViewById(R.id.competenciasRecyclerView);
        competenciasRecyclerView.setHasFixedSize(true);
        competenciasRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        rootView = findViewById(R.id.rootView);

        inicialize();

        cerrarSesionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cerrarSesion();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_competencias_api,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.item2:
                startActivity(new Intent(getApplicationContext(),ObtenerCompetenciasApiActivity.class));
                break;
        }
        switch (item.getItemId()){
            case R.id.item3:
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(RegistroAdminActivity.this);
                View mView = getLayoutInflater().inflate(R.layout.activity_dialog_competencia_nacional, null);

                campoFecha = (EditText)mView.findViewById(R.id.campoFecha);
                botonSeleccionFechaNal = (Button) mView.findViewById(R.id.botonSeleccionFechaNal);

                botonSeleccionFechaNal.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        actualizarFecha();
                    }
                });
                mBuilder.setView(mView);
                final AlertDialog dialog = mBuilder.create();
                dialog.show();

        }
        return super.onOptionsItemSelected(item);
    }

    private void inicialize() {
        firebaseAuth = FirebaseAuth.getInstance();
        authStateListener = new FirebaseAuth.AuthStateListener() {          //<--authlistener es donde detecta que hubo cambios en la sesion
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();  //FirebaseUser agarramos todos los gatos del usuario de la autenticacion una ves que ocurrio
                if (firebaseUser != null){
                    detalleAdminTextView.setText("ID User: " + firebaseUser.getUid() + " email: " + firebaseUser.getEmail());
                }else {
                    Log.w(TAG, "onAuthStateChanged - cerro sesion");
                }
            }
        };
    }
    private void cerrarSesion() {
        firebaseAuth.signOut();
    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(authStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        firebaseAuth.removeAuthStateListener(authStateListener);
    }

    //Selector de fecha
    private void actualizarFecha(){
        new DatePickerDialog(this, d, dateTime.get(Calendar.YEAR),
                dateTime.get(Calendar.MONTH),
                dateTime.get(Calendar.DAY_OF_MONTH))
                .show();
    }

    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            dateTime.set(Calendar.YEAR, year);
            dateTime.set(Calendar.MONTH, monthOfYear);
            dateTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            campoFecha.setText(dayOfMonth+"/"+(monthOfYear+1)+"/"+year);
        }
    };

}
