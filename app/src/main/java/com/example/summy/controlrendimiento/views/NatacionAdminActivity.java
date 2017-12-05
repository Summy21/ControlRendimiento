package com.example.summy.controlrendimiento.views;

import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.summy.controlrendimiento.R;

import java.util.Date;

@RequiresApi(api = Build.VERSION_CODES.N)
public class NatacionAdminActivity extends AppCompatActivity {

    private static final String TAG = "NatacionAdmin";

    Spinner spinnerEtapas;
    String[] itemsEtapa;
    TextView tvMicrociclo;
    TextView tvMesociclo;
    Calendar calendar = Calendar.getInstance();

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_natacion_admin);
        showToolbar("Gestion de rutinas natacion", true);

        tvMicrociclo = (TextView) findViewById(R.id.tvMicrociclo);
        tvMesociclo = (TextView) findViewById(R.id.tvMesociclo);
        spinnerEtapas = (Spinner) findViewById(R.id.spinnerEtapas);

        tvMicrociclo.setText(nroMicrociclo()+" ");
        tvMesociclo.setText(nroMesociclo());
    }

    public int nroMicrociclo(){
        Date date = new Date();
        calendar.setTime(date);
        return calendar.get(Calendar.WEEK_OF_YEAR);
    }
    public String nroMesociclo(){
        long date = System.currentTimeMillis();
        SimpleDateFormat m = new SimpleDateFormat("MM");
        return m.format(date);
    }
    public void showToolbar(String tittle, boolean upButton){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(tittle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
    }
}
