package com.example.summy.controlrendimiento.views;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.summy.controlrendimiento.R;
import com.example.summy.controlrendimiento.adapters.CompetidoresAdapter;
import com.example.summy.controlrendimiento.model.Atleta;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

@RequiresApi(api = Build.VERSION_CODES.N)
public class ControlCompetidoresActivity extends AppCompatActivity {




    private View rootView;
    Toolbar toolbar;
    private DatabaseReference myRef;


    private RecyclerView competidoresRecyclerView;
    List<Atleta> atletasList;
    CompetidoresAdapter competidoresAdapter;


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control_competidores);
        showToolbar("Control", true);
        rootView = findViewById(R.id.rootViewLC);

        //Recycler
        competidoresRecyclerView = (RecyclerView) findViewById(R.id.competidoresRecyclerView);
        competidoresRecyclerView.setHasFixedSize(true);
        competidoresRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Base de datos competidores
        atletasList = new ArrayList<>();

        myRef = FirebaseDatabase.getInstance().getReference("Atletas");

        competidoresAdapter = new CompetidoresAdapter(atletasList, this);
        competidoresRecyclerView.setAdapter(competidoresAdapter);

        updateList();

    }

    private void updateList() {
        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                atletasList.add(dataSnapshot.getValue(Atleta.class));
                competidoresAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void showToolbar(String tittle, boolean upButton) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(tittle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
    }

    private void mostrarMessage(String mensaje) {
        Snackbar.make(rootView, mensaje, Snackbar.LENGTH_LONG).show();
    }

}
