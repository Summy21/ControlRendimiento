package com.example.summy.controlrendimiento.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.summy.controlrendimiento.R;
import com.example.summy.controlrendimiento.adapters.CompetenciasUserAdapter;
import com.example.summy.controlrendimiento.model.CompNacional;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class ListCompetenciasUserActivity extends AppCompatActivity {

    private RecyclerView competenciasRecyclerView;
    List<CompNacional> compNacionalList;
    CompetenciasUserAdapter competenciasUserAdapter;

    private DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_competencias_user);

        showToolbar("Vista de competencias", true);

        competenciasRecyclerView = (RecyclerView) findViewById(R.id.competenciasRecyclerView);
        competenciasRecyclerView.setHasFixedSize(true);
        competenciasRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        compNacionalList = new ArrayList<>();
        myRef = FirebaseDatabase.getInstance().getReference("Nacionales");

        competenciasUserAdapter = new CompetenciasUserAdapter(compNacionalList);
        competenciasRecyclerView.setAdapter(competenciasUserAdapter);

        updateList();
    }

    private void updateList(){
        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                compNacionalList.add(dataSnapshot.getValue(CompNacional.class));
                competenciasUserAdapter.notifyDataSetChanged();
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

    public void showToolbar(String tittle, boolean upButton){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(tittle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);

    }
}
