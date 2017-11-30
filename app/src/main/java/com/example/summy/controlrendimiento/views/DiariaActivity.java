package com.example.summy.controlrendimiento.views;

import android.content.Intent;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.example.summy.controlrendimiento.MainActivity;
import com.example.summy.controlrendimiento.R;
import com.example.summy.controlrendimiento.model.EntrenamientoNatacion;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Date;

/**
 * Created by SUMMY on 28/9/2017.
 */

@RequiresApi(api = Build.VERSION_CODES.N)
public class DiariaActivity extends AppCompatActivity{

    //herramienta para crer el toolbar
    Toolbar toolbar;

    //// Cerrando secion
    private FirebaseAuth mAuth;

    //fechas
    private TextView tvIniciarN;
    private TextView tvFinalizarN;

    private TextView tvIniciarC;
    private TextView tvFinalizarC;

    private TextView tvIniciarCa;
    private TextView tvFinalizarCa;

    boolean sw1, sw2, sw3, sw4, sw5, sw6 = false;

    DateFormat formatDateTime = DateFormat.getDateTimeInstance();
    Calendar dateTime = Calendar.getInstance();

    private DatabaseReference myRef;
    String id = "entrNatacion1";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diaria);

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
                startActivity(new Intent(getApplicationContext(),ListCompetenciasUserActivity.class));
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
                        EntrenamientoNatacion entrenamientoNatacion = dataSnapshot.getValue(EntrenamientoNatacion.class);

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

                myRef.child(id).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        EntrenamientoNatacion entrenamientoNatacion = dataSnapshot.getValue(EntrenamientoNatacion.class);

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

                mBuilder.setView(mView);
                final AlertDialog dialog = mBuilder.create();
                dialog.show();
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

                mBuilder.setView(mView);
                final AlertDialog dialog = mBuilder.create();
                dialog.show();
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

    public void btnStartNatacion(View view) {
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(DiariaActivity.this);
        View mView = getLayoutInflater().inflate(R.layout.activity_natacion_user, null);

        tvIniciarN            = (TextView) mView.findViewById(R.id.tvIniciarN);
        tvFinalizarN          = (TextView) mView.findViewById(R.id.tvFinalizarN);
        Button btnIniciarN   = (Button) mView.findViewById(R.id.btnIniciarN);
        Button btnFinalizarN = (Button) mView.findViewById(R.id.btnFinalizarN);
        Button btnGuardarN = (Button) mView.findViewById(R.id.btnGuardarN);

        mBuilder.setView(mView);
        final AlertDialog dialog = mBuilder.create();
        dialog.show();
        btnIniciarN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sw1 = true;
                actualizarTiempo();
            }
        });
        btnFinalizarN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sw2 = true;
                actualizarTiempo();
            }
        });
        btnGuardarN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    public void btnStartCiclismo(View view) {
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(DiariaActivity.this);
        View mView = getLayoutInflater().inflate(R.layout.activity_ciclismo_user, null);

        tvIniciarC            = (TextView) mView.findViewById(R.id.tvIniciarC);
        tvFinalizarC          = (TextView) mView.findViewById(R.id.tvFinalizarC);
        Button btnIniciarC = (Button) mView.findViewById(R.id.btnIniciarC);
        Button btnFinalizarC = (Button) mView.findViewById(R.id.btnFinalizarC);
        Button btnGuardarC = (Button) mView.findViewById(R.id.btnGuardarC);

        mBuilder.setView(mView);
        final AlertDialog dialog = mBuilder.create();

        btnIniciarC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sw3 = true;
                actualizarTiempo();
            }
        });
        btnFinalizarC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sw4 = true;
                actualizarTiempo();
            }
        });
        btnGuardarC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public void btnStartCarrera(View view) {
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(DiariaActivity.this);
        View mView = getLayoutInflater().inflate(R.layout.activity_carrera_user, null);

        tvIniciarCa            = (TextView) mView.findViewById(R.id.tvIniciarCa);
        tvFinalizarCa          = (TextView) mView.findViewById(R.id.tvFinalizarCa);
        Button btnIniciarCa = (Button) mView.findViewById(R.id.btnIniciarCa);
        Button btnFinalizarCa = (Button) mView.findViewById(R.id.btnFinalizarCa);
        Button btnGuardarCa = (Button) mView.findViewById(R.id.btnGuardarCa);

        mBuilder.setView(mView);
        final AlertDialog dialog = mBuilder.create();

        btnIniciarCa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sw5 = true;
                actualizarTiempo();
            }
        });
        btnFinalizarCa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sw6 = true;
                actualizarTiempo();
            }
        });
        btnGuardarCa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
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
            sw1 = false;
        }
        if(sw4){
            tvFinalizarC.setText(dateFormat.format(new Date()));
            sw2 = false;
        }if(sw5){
            tvIniciarCa.setText(dateFormat.format(new Date()));
            sw5 = false;
        }
        if(sw6){
            tvFinalizarCa.setText(dateFormat.format(new Date()));
            sw6 = false;
        }

    }

}
