package com.example.summy.controlrendimiento.views;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.summy.controlrendimiento.BuildConfig;
import com.example.summy.controlrendimiento.R;
import com.example.summy.controlrendimiento.Triathlon.ServiceGenerator;
import com.example.summy.controlrendimiento.Triathlon.TriathlonDatabaseService;
import com.example.summy.controlrendimiento.adapters.CompetenciasAdapter;
import com.example.summy.controlrendimiento.model.CompetenciasRespuesta;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ObtenerCompetenciasApiActivity extends AppCompatActivity {

    //Recycler
    private RecyclerView ObtCompetenciasApiRecyclerView;

    private View rootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_obtener_competencias_api);

        //Recycler
        ObtCompetenciasApiRecyclerView = (RecyclerView) findViewById(R.id.ObtCompetenciasApiRecyclerView);
        ObtCompetenciasApiRecyclerView.setHasFixedSize(true);
        ObtCompetenciasApiRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        rootView = findViewById(R.id.rootView);
    }

    @Override
    protected void onStart() {
        super.onStart();

        cargarDatos();
    }

    private void cargarDatos() {

    }

    private void mostrarMessage(String mensaje) {
        Snackbar.make(rootView, mensaje, Snackbar.LENGTH_LONG).show();
    }
}
