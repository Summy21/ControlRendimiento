package com.example.summy.controlrendimiento.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.summy.controlrendimiento.R;
import com.example.summy.controlrendimiento.model.Atleta;
import com.example.summy.controlrendimiento.model.CompNacional;
import com.example.summy.controlrendimiento.model.Entrenamiento;
import com.example.summy.controlrendimiento.model.EntrenamientoRut;
import com.example.summy.controlrendimiento.views.ControlCompetidoresActivity;
import com.example.summy.controlrendimiento.views.DiariaActivity;

import java.util.List;


public class CompetidoresAdapter extends RecyclerView.Adapter<CompetidoresAdapter.CompetenciasViewHolder> {

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
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
                alertDialog.setTitle(Html.fromHtml("<font color='#388E3C'>"+ atle.getNombres()+" "+ atle.getPaterno()+" "+atle.getMaterno()+ "</font> <font color='#388E3C'>"));
                alertDialog.setMessage("your message ");
//                alertDialog.setPositiveButton("CANCEL", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.cancel();
//                    }
//                });
//                alertDialog.setNegativeButton("YES", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//                        // DO SOMETHING HERE
//
//                    }
//                });

                AlertDialog dialog = alertDialog.create();
                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return atletas.size();
    }

    public static class CompetenciasViewHolder extends RecyclerView.ViewHolder {

        TextView tvNombre;
        TextView tvPaterno;
        TextView tvMaterno;
        private LinearLayout layoutCompetidor;

        public CompetenciasViewHolder(View itemView) {
            super(itemView);

            tvNombre  = (TextView) itemView.findViewById(R.id.tvNombre);
            tvPaterno  = (TextView) itemView.findViewById(R.id.tvPaterno);
            tvMaterno  = (TextView) itemView.findViewById(R.id.tvMaterno);
            layoutCompetidor = (LinearLayout) itemView.findViewById(R.id.layoutCompetidor);
        }

    }

}
