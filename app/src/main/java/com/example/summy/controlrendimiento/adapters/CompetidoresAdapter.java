package com.example.summy.controlrendimiento.adapters;

import android.app.Dialog;
import android.content.Context;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.summy.controlrendimiento.R;
import com.example.summy.controlrendimiento.model.Atleta;
import com.example.summy.controlrendimiento.model.EntrenamientoRut;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Date;
import java.util.List;


@RequiresApi(api = Build.VERSION_CODES.N)
public class CompetidoresAdapter extends RecyclerView.Adapter<CompetidoresAdapter.CompetenciasViewHolder> {
    private DatabaseReference myRef;
    //
    private DatabaseReference myRefProm;
    double totalMC;
    //
    private FirebaseAuth mAuth;
    Calendar calendar = Calendar.getInstance();

    private final Context context;
    private List<Atleta> atletas;


    public CompetidoresAdapter(List<Atleta> atletas, Context context) {
        this.atletas = atletas;
        this.context = context;
    }

    @Override
    public CompetenciasViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_competidor, parent, false);
        return new CompetenciasViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CompetenciasViewHolder holder, int position) {
        final Atleta atle = atletas.get(position);

        holder.tvNombre.setText(atle.getNombres());
        holder.tvPaterno.setText(atle.getPaterno());
        holder.tvMaterno.setText(atle.getMaterno());


        holder.layoutCompetidor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DisplayMetrics metrics = context.getResources().getDisplayMetrics();

                int DeviceTotalWidth = metrics.widthPixels;
                int DeviceTotalHeight = metrics.widthPixels;

                final Dialog dialog = new Dialog(context);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.activity_control_competidor);
                dialog.getWindow().setLayout(DeviceTotalWidth, DeviceTotalHeight);
                dialog.show();

                final String idM = String.valueOf(nroMicrociclo());
                myRef = FirebaseDatabase.getInstance().getReference("EntrenamientoRut").child("Natacion").child(idM);

                /////

                totalMC = 0;
                //String idUser = "yoN3GcvfRTS8k27ACHq8G5w1Box2";
                final String idUser = atle.getIdUSer();
                myRefProm = FirebaseDatabase.getInstance().getReference("EntrenamientoRut").child("Natacion").child(idM).child(idUser);

                myRefProm.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        int contador = 0;
                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            EntrenamientoRut entrenamientoRut = ds.getValue(EntrenamientoRut.class);
                            totalMC = totalMC + Double.parseDouble(entrenamientoRut.getVolumen());
                            contador++;
                        }
                        totalMC = totalMC / contador;
                      //  Toast.makeText(CompetidoresAdapter.this, "Promedio: " + totalMC, Toast.LENGTH_LONG).show();
                        TextView tvVolumenValor = (TextView) dialog.findViewById(R.id.tvVolumenValor);
                        tvVolumenValor.setText(totalMC+"");
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });


                ///////



            }
        });
    }

    public int nroMicrociclo() {
        Date date = new Date();
        calendar.setTime(date);
        return calendar.get(Calendar.WEEK_OF_YEAR);
    }

    @Override
    public int getItemCount() {
        return atletas.size();
    }

    public static class CompetenciasViewHolder extends RecyclerView.ViewHolder {

        TextView tvNombre;
        TextView tvPaterno;
        TextView tvMaterno;
        TextView tvNombreComp;
        private LinearLayout layoutCompetidor;

        public CompetenciasViewHolder(View itemView) {
            super(itemView);

            tvNombre = (TextView) itemView.findViewById(R.id.tvNombre);
            tvPaterno = (TextView) itemView.findViewById(R.id.tvPaterno);
            tvMaterno = (TextView) itemView.findViewById(R.id.tvMaterno);
            layoutCompetidor = (LinearLayout) itemView.findViewById(R.id.layoutCompetidor);

            tvNombreComp = (TextView) itemView.findViewById(R.id.tvNombreComp);
        }

    }

}
