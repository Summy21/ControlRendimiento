package com.example.summy.controlrendimiento.views;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.summy.controlrendimiento.R;
import com.example.summy.controlrendimiento.model.CompNacional;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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
    private Button btnFechaIni;
    private Button btnFechaFin;

    //Base de datos Competencias
    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference myRef;

    private TextView tvNombreCompetencia;
    private TextView tvLugarCompetencia;
    private EditText etFechaIni;
    private EditText etFechaFin;
    private Button btnGuardar;



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

        //Base de datos competencias
        myRef = FirebaseDatabase.getInstance().getReference("Nacionales");

        tvNombreCompetencia = (TextView) findViewById(R.id.tvNombreCompetencia);
        tvLugarCompetencia  = (TextView) findViewById(R.id.tvLugarCompetencia);

        btnGuardar          = (Button)findViewById(R.id.btnGuardar);


    }

    private void guardar() {
        String tituloComp = tvNombreCompetencia.getText().toString().trim();
        String lugarComp = tvLugarCompetencia.getText().toString().trim();
        String fechaIni = etFechaIni.getText().toString().trim();
        String fechaFin = etFechaFin.getText().toString().trim();
        if (!TextUtils.isEmpty(tituloComp)){

            CompNacional compNacional = new CompNacional(tituloComp, lugarComp, fechaIni, fechaFin);
            myRef.setValue(compNacional);
            Toast.makeText(this,"registro adicionado",Toast.LENGTH_LONG).show();
        }else{
            mostrarMessage("Falta completar los datos");
            //Toast.makeText(this,"Falta completar los datos",Toast.LENGTH_LONG).show();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_competencias_api,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.item1:
                startActivity(new Intent(getApplicationContext(),ObtenerCompetenciasApiActivity.class));
                break;
        }
        switch (item.getItemId()){
            case R.id.item2:
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(RegistroAdminActivity.this);
                View mView = getLayoutInflater().inflate(R.layout.activity_dialog_competencia_nacional, null);

                etFechaIni          = (EditText) findViewById(R.id.etFechaIni);
                etFechaFin          = (EditText) findViewById(R.id.etFechaFin);
          //      btnGuardar          = (Button)mView.findViewById(R.id.btnGuardar);
                btnFechaIni = (Button) mView.findViewById(R.id.btnFechaIni);
          //      btnFechaFin = (Button) mView.findViewById(R.id.btnFechaFin);


                btnFechaIni.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        actualizarFecha();
                    }
                });
         /*       btnFechaFin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        actualizarFecha();
                    }
                });*/
         /*       btnGuardar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        guardar();
                    }
                });*/
                mBuilder.setView(mView);
                final AlertDialog dialog = mBuilder.create();
                dialog.show();

        }
        switch (item.getItemId()){
            case R.id.item3:
                startActivity(new Intent(getApplicationContext(),PeriodosActivity.class));
                break;
        }
        switch (item.getItemId()){
            case R.id.item4:
                startActivity(new Intent(getApplicationContext(),NatacionAdminActivity.class));
                break;
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
            etFechaIni.setText(dayOfMonth+"/"+(monthOfYear+1)+"/"+year);
        }
    };
    private void mostrarMessage(String mensaje) {
        Snackbar.make(rootView, mensaje, Snackbar.LENGTH_LONG).show();
    }

}
