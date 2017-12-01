package com.example.summy.controlrendimiento.views;

import android.content.Intent;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.example.summy.controlrendimiento.MainActivity;
import com.example.summy.controlrendimiento.R;
import com.example.summy.controlrendimiento.model.DiarioDisciplina;
import com.example.summy.controlrendimiento.model.DiarioEntrenamiento;
import com.example.summy.controlrendimiento.model.Entrenamiento;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.util.Date;

/**
 * Created by SUMMY on 28/9/2017.
 */

@RequiresApi(api = Build.VERSION_CODES.N)
public class DiariaActivity extends AppCompatActivity{

    private static final String TAG = "DiariaActivity";
    private View rootView;
    //herramienta para crer el toolbar
    Toolbar toolbar;

    //// Cerrando secion
    private FirebaseAuth mAuth;

    //fechas
    private TextView tvIniciarN;
    private TextView tvFinalizarN;
    private TextView tvTiempoT;

    private TextView tvTiempoTC;
    private EditText etFcMaxN;
    private EditText etFcMaxC;
    private EditText etFcMaxCa;

    private TextView tvIniciarC;
    private TextView tvFinalizarC;

    private TextView tvIniciarCa;
    private TextView tvFinalizarCa;
    private TextView tvTiempoTCa;

    boolean sw1, sw2, sw3, sw4, sw5, sw6 = false;

    private DatabaseReference myRef;
    String id = "entrNatacion1";
    String idC = "entrCiclismo1";
    String idCa = "entrPedestrismo1";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diaria);
        rootView = findViewById(R.id.rootViewDiaria);

        //Vilculamos nuestro toolbar con el XML
        toolbar = (Toolbar) findViewById(R.id.tvMenu);
        toolbar.setTitle("ACTIVIDAD DE HOY");
        setSupportActionBar(toolbar);
        ///// secion instancia
        mAuth = FirebaseAuth.getInstance();
        //DIA
        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @RequiresApi(api = Build.VERSION_CODES.N)
                            @Override
                            public void run() {
                                TextView dia = (TextView) findViewById(R.id.dia);
                                TextView mes = (TextView) findViewById(R.id.mes);
                                long date = System.currentTimeMillis();
                                SimpleDateFormat d = new SimpleDateFormat("dd");
                                SimpleDateFormat m = new SimpleDateFormat("MMMM");
                                String diaString = d.format(date);
                                String mesString = m.format(date);
                                dia.setText(diaString);
                                mes.setText(mesString);
                            }
                        });
                    }
                } catch (InterruptedException e) {
                }
            }
        };
        t.start();


        LinearLayout listCompUser = (LinearLayout) findViewById(R.id.listCompUser);

        listCompUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),RegistroAdminActivity.class));
            }
        });

        LinearLayout listEntrenamientoN = (LinearLayout) findViewById(R.id.listEntrenamientoN);

        listEntrenamientoN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(DiariaActivity.this);
                View mView = getLayoutInflater().inflate(R.layout.activity_dialog_entrenamiento_natacion, null);

                final TextView tvCalentamientoN = (TextView) mView.findViewById(R.id.tvCalentamientoN);
                final TextView tvFase1          = (TextView) mView.findViewById(R.id.tvFase1N);
                final TextView tvFase2          = (TextView) mView.findViewById(R.id.tvFase2N);
                final TextView tvFaseFundN      = (TextView) mView.findViewById(R.id.tvFaseFundN);
                final TextView tvCalmaN         = (TextView) mView.findViewById(R.id.tvCalmaN);
                final Button btnListo         = (Button) mView.findViewById(R.id.btnListo);

                mBuilder.setView(mView);
                final AlertDialog dialog = mBuilder.create();
                dialog.show();

                myRef = FirebaseDatabase.getInstance().getReference("Entrenamientos").child("Natacion");

                myRef.child(id).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Entrenamiento entrenamientoNatacion = dataSnapshot.getValue(Entrenamiento.class);

                        tvCalentamientoN.setText(entrenamientoNatacion.getCalentamiento());
                        tvFase1.setText(entrenamientoNatacion.getFasePrinc1());
                        tvFase2.setText(entrenamientoNatacion.getFasePrinc2());
                        tvFaseFundN.setText(entrenamientoNatacion.getFaseFund());
                        tvCalmaN.setText(entrenamientoNatacion.getVueltaCalma());
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                btnListo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

            }

        });

        LinearLayout listEntrenamientoC = (LinearLayout) findViewById(R.id.listEntrenamientoC);

        listEntrenamientoC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(DiariaActivity.this);
                View mView = getLayoutInflater().inflate(R.layout.activity_dialog_entrenamiento_ciclismo, null);

                final TextView tvCalentamientoC = (TextView) mView.findViewById(R.id.tvCalentamientoC);
                final TextView tvFase1C          = (TextView) mView.findViewById(R.id.tvFase1C);
                final TextView tvFase2C         = (TextView) mView.findViewById(R.id.tvFase2C);
                final TextView tvFaseFundC      = (TextView) mView.findViewById(R.id.tvFaseFundC);
                final TextView tvCalmaC         = (TextView) mView.findViewById(R.id.tvCalmaC);
                final Button btnListo         = (Button) mView.findViewById(R.id.btnListo);

                mBuilder.setView(mView);
                final AlertDialog dialog = mBuilder.create();
                dialog.show();

                myRef = FirebaseDatabase.getInstance().getReference("Entrenamientos").child("Ciclismo");

                myRef.child(idC).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Entrenamiento entrenamientoNatacion = dataSnapshot.getValue(Entrenamiento.class);

                        tvCalentamientoC.setText(entrenamientoNatacion.getCalentamiento());
                        tvFase1C.setText(entrenamientoNatacion.getFasePrinc1());
                        tvFase2C.setText(entrenamientoNatacion.getFasePrinc2());
                        tvFaseFundC.setText(entrenamientoNatacion.getFaseFund());
                        tvCalmaC.setText(entrenamientoNatacion.getVueltaCalma());
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                btnListo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

            }
        });
        LinearLayout listEntrenamientoCa = (LinearLayout) findViewById(R.id.listEntrenamientoCa);

        listEntrenamientoCa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(DiariaActivity.this);
                View mView = getLayoutInflater().inflate(R.layout.activity_dialog_entrenamiento_carrera, null);

                final TextView tvCalentamientoCa = (TextView) mView.findViewById(R.id.tvCalentamientoCa);
                final TextView tvFase1Ca          = (TextView) mView.findViewById(R.id.tvFase1Ca);
                final TextView tvFase2Ca         = (TextView) mView.findViewById(R.id.tvFase2Ca);
                final TextView tvFaseFundCa      = (TextView) mView.findViewById(R.id.tvFaseFundCa);
                final TextView tvCalmaCa         = (TextView) mView.findViewById(R.id.tvCalmaCa);
                final Button btnListo         = (Button) mView.findViewById(R.id.btnListo);

                mBuilder.setView(mView);
                final AlertDialog dialog = mBuilder.create();
                dialog.show();

                myRef = FirebaseDatabase.getInstance().getReference("Entrenamientos").child("Pedestrismo");

                myRef.child(idCa).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Entrenamiento entrenamientoNatacion = dataSnapshot.getValue(Entrenamiento.class);

                        tvCalentamientoCa.setText(entrenamientoNatacion.getCalentamiento());
                        tvFase1Ca.setText(entrenamientoNatacion.getFasePrinc1());
                        tvFase2Ca.setText(entrenamientoNatacion.getFasePrinc2());
                        tvFaseFundCa.setText(entrenamientoNatacion.getFaseFund());
                        tvCalmaCa.setText(entrenamientoNatacion.getVueltaCalma());
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                btnListo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_diaria,menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.item1:
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(DiariaActivity.this);
                View mView = getLayoutInflater().inflate(R.layout.activity_datos_frecuencia_volumen, null);

                final EditText etFrecDespertar = (EditText) mView.findViewById(R.id.etFrecDespertar);
                final EditText etVolGral = (EditText) mView.findViewById(R.id.etVolGral);
                Button btnGuardar = (Button) mView.findViewById(R.id.btnGuardar);

                mBuilder.setView(mView);
                final AlertDialog dialog = mBuilder.create();
                dialog.show();

                String idFecha = fechaActual();

                String idUser = mAuth.getCurrentUser().getUid();

                myRef = FirebaseDatabase.getInstance().getReference("Fechas").child(idFecha).child(idUser).child("DiarioEntrenamiento");

                btnGuardar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String frecDespertar = etFrecDespertar.getText().toString().trim();
                        String volGral = etVolGral.getText().toString().trim();

                        if(!TextUtils.isEmpty(frecDespertar)){
                            DiarioEntrenamiento diarioE = new DiarioEntrenamiento(frecDespertar,volGral);
                            myRef.setValue(diarioE);

                        }else{
                            mostrarMessage("Falta completar los datos");
                        }
                        dialog.dismiss();
                    }
                });

                break;
        }
        switch (item.getItemId()){
            case R.id.item2:
                startActivity(new Intent(getApplicationContext(),EditarRegistroAtleta.class));
                break;
        }
        switch (item.getItemId()){
            case R.id.item3:
                mAuth.signOut();
                Toast.makeText(DiariaActivity.this,"'Cerrando Sesion'",Toast.LENGTH_LONG).show();
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();

                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void promeCardiaco(){

    }

    public String fechaActual(){
        Calendar f = Calendar.getInstance();
        Date fechacompleta = f.getTime();
        Log.w(TAG, String.valueOf(fechacompleta));
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        String formatoFecha= df.format(f.getTime());
        Log.w(TAG, formatoFecha);
        return  formatoFecha;
    }
    public String horaActual(){
        Calendar f = Calendar.getInstance();
        Date fechacompleta = f.getTime();
        Log.w(TAG, String.valueOf(fechacompleta));
        SimpleDateFormat hf = new SimpleDateFormat("HH:mm:ss");
        String formatoHora= hf.format(f.getTime());
        Log.w(TAG, formatoHora);
        return  formatoHora;
    }
    public String controlTiempo(String tInicial, String tFinal){
        Calendar f = Calendar.getInstance();
        SimpleDateFormat hf = new SimpleDateFormat("HH:mm:ss");
        Date tIni=null;
        Date tFin=null;
        try {
            tIni = hf.parse(tInicial);
            tFin = hf.parse(tFinal);

        } catch (ParseException e) {
            Log.e(TAG, "Funcion diferenciaFechas: Error Parse " + e);
        } catch (Exception e){
            Log.e(TAG, "Funcion diferenciaFechas: Error " + e);
        }
        Calendar calendarInicio = Calendar.getInstance();
        Calendar calendarFinal = Calendar.getInstance();
        calendarInicio.setTime(tIni);
        calendarFinal.setTime(tFin);

        long milis1 = calendarInicio.getTimeInMillis();
        long milis2 = calendarFinal.getTimeInMillis();
        long diferenciaMilisegundos = milis1 - milis2;

        long diffSegundos =  Math.abs (diferenciaMilisegundos / 1000);
        long dihMinutos =  Math.abs (diferenciaMilisegundos / (60 * 1000));
        long reshMinutos = dihMinutos%60;
        long diffHoras =   (diferenciaMilisegundos / (60 * 60 * 1000));

        Log.w(TAG, "Minutos >>"+String.valueOf(reshMinutos));
        Log.w(TAG, "Segundos >>"+String.valueOf(diffSegundos));
        Log.w(TAG, "todo >>"+String.valueOf(reshMinutos) +":"+String.valueOf(diffSegundos));

        return String.valueOf(diffHoras) +":"+String.valueOf(reshMinutos) +":"+String.valueOf(diffSegundos);
    }

    public void btnStartNatacion(View view) {
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(DiariaActivity.this);
        View mView = getLayoutInflater().inflate(R.layout.activity_natacion_user, null);

        tvIniciarN            = (TextView) mView.findViewById(R.id.tvIniciarN);
        tvFinalizarN          = (TextView) mView.findViewById(R.id.tvFinalizarN);
        tvTiempoT             = (TextView) mView.findViewById(R.id.tvTiempoT);
        etFcMaxN             = (EditText) mView.findViewById(R.id.etFcMaxN);
        final Button btnIniciarN   = (Button) mView.findViewById(R.id.btnIniciarN);
        final Button btnFinalizarN = (Button) mView.findViewById(R.id.btnFinalizarN);
        Button btnGuardarN = (Button) mView.findViewById(R.id.btnGuardarN);

        String idFecha = fechaActual();
        String idUser = mAuth.getCurrentUser().getUid();
        myRef = FirebaseDatabase.getInstance().getReference("Natacion").child(idFecha).child(idUser);
        mBuilder.setView(mView);
        final AlertDialog dialog = mBuilder.create();
        dialog.show();
        btnIniciarN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sw1 = true;
                actualizarTiempo();
                btnIniciarN.setEnabled(false);
            }
        });
        btnFinalizarN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sw2 = true;
                actualizarTiempo();
                btnFinalizarN.setEnabled(false);
                String tInicial = tvIniciarN.getText().toString().trim();
                String tFinal = tvFinalizarN.getText().toString().trim();
                String total = controlTiempo(tInicial, tFinal);
                tvTiempoT.setText(total);
            }
        });

        btnGuardarN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tiempoTrabajo = tvTiempoT.getText().toString().trim();
                String fcMax = etFcMaxN.getText().toString().trim();

                if(!TextUtils.isEmpty(tiempoTrabajo)){
                    DiarioDisciplina diarioD = new DiarioDisciplina(tiempoTrabajo,fcMax);
                    myRef.setValue(diarioD);

                }else{
                    mostrarMessage("Falta completar los datos");
                }
                dialog.dismiss();
            }
        });
    }

    public void btnStartCiclismo(View view) {
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(DiariaActivity.this);
        View mView = getLayoutInflater().inflate(R.layout.activity_ciclismo_user, null);

        tvIniciarC            = (TextView) mView.findViewById(R.id.tvIniciarC);
        tvFinalizarC          = (TextView) mView.findViewById(R.id.tvFinalizarC);
        tvTiempoTC             = (TextView) mView.findViewById(R.id.tvTiempoTC);
        etFcMaxC             = (EditText) mView.findViewById(R.id.etFcMaxC);
        final Button btnIniciarC = (Button) mView.findViewById(R.id.btnIniciarC);
        final Button btnFinalizarC = (Button) mView.findViewById(R.id.btnFinalizarC);
        Button btnGuardarC = (Button) mView.findViewById(R.id.btnGuardarC);

        String idFecha = fechaActual();
        String idUser = mAuth.getCurrentUser().getUid();
        myRef = FirebaseDatabase.getInstance().getReference("Ciclismo").child(idFecha).child(idUser);
        mBuilder.setView(mView);
        final AlertDialog dialog = mBuilder.create();
        dialog.show();

        btnIniciarC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sw3 = true;
                actualizarTiempo();
                btnIniciarC.setEnabled(false);
            }
        });
        btnFinalizarC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sw4 = true;
                actualizarTiempo();
                btnFinalizarC.setEnabled(false);
                String tInicial = tvIniciarC.getText().toString().trim();
                String tFinal = tvFinalizarC.getText().toString().trim();
                Log.w(TAG, "tiempos  >>" + tInicial +" , "+tFinal);
                String total = controlTiempo(tInicial, tFinal);
                tvTiempoTC.setText(total);
            }
        });
        btnGuardarC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tiempoTrabajo = tvTiempoTC.getText().toString().trim();
                String fcMax = etFcMaxC.getText().toString().trim();

                if(!TextUtils.isEmpty(tiempoTrabajo)){
                    DiarioDisciplina diarioD = new DiarioDisciplina(tiempoTrabajo,fcMax);
                    myRef.setValue(diarioD);

                }else{
                    mostrarMessage("Falta completar los datos");
                }
                dialog.dismiss();
            }
        });
    }

    public void btnStartCarrera(View view) {
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(DiariaActivity.this);
        View mView = getLayoutInflater().inflate(R.layout.activity_carrera_user, null);

        tvIniciarCa            = (TextView) mView.findViewById(R.id.tvIniciarCa);
        tvFinalizarCa          = (TextView) mView.findViewById(R.id.tvFinalizarCa);
        tvTiempoTCa             = (TextView) mView.findViewById(R.id.tvTiempoTCa);
        final Button btnIniciarCa = (Button) mView.findViewById(R.id.btnIniciarCa);
        final Button btnFinalizarCa = (Button) mView.findViewById(R.id.btnFinalizarCa);
        Button btnGuardarCa = (Button) mView.findViewById(R.id.btnGuardarCa);
        etFcMaxCa             = (EditText) mView.findViewById(R.id.etFcMaxCa);

        String idFecha = fechaActual();
        String idUser = mAuth.getCurrentUser().getUid();
        myRef = FirebaseDatabase.getInstance().getReference("Pedestrismo").child(idFecha).child(idUser);
        mBuilder.setView(mView);
        final AlertDialog dialog = mBuilder.create();
        dialog.show();

        btnIniciarCa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sw5 = true;
                actualizarTiempo();
                btnIniciarCa.setEnabled(false);
            }
        });
        btnFinalizarCa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sw6 = true;
                actualizarTiempo();
                btnFinalizarCa.setEnabled(false);
                String tInicial = tvIniciarCa.getText().toString().trim();
                String tFinal = tvFinalizarCa.getText().toString().trim();
                String total = controlTiempo(tInicial, tFinal);
                tvTiempoTCa.setText(total);
            }
        });
        btnGuardarCa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tiempoTrabajo = tvTiempoTCa.getText().toString().trim();
                String fcMax = etFcMaxCa.getText().toString().trim();

                if(!TextUtils.isEmpty(tiempoTrabajo)){
                    DiarioDisciplina diarioD = new DiarioDisciplina(tiempoTrabajo,fcMax);
                    myRef.setValue(diarioD);

                }else{
                    mostrarMessage("Falta completar los datos");
                }
                dialog.dismiss();
            }
        });
    }
    private void actualizarTiempo() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        if(sw1){
            tvIniciarN.setText(dateFormat.format(new Date()));
            sw1 = false;
        }
        if(sw2){
            tvFinalizarN.setText(dateFormat.format(new Date()));
            sw2 = false;
        }
        if(sw3){
            tvIniciarC.setText(dateFormat.format(new Date()));
            sw3 = false;
        }
        if(sw4){
            tvFinalizarC.setText(dateFormat.format(new Date()));
            sw4 = false;
        }if(sw5){
            tvIniciarCa.setText(dateFormat.format(new Date()));
            sw5 = false;
        }
        if(sw6){
            tvFinalizarCa.setText(dateFormat.format(new Date()));
            sw6 = false;
        }

    }
    private void mostrarMessage(String mensaje) {
        Snackbar.make(rootView, mensaje, Snackbar.LENGTH_LONG).show();
    }

}
