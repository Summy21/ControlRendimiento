package com.example.summy.controlrendimiento.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.summy.controlrendimiento.model.CompNacional;
import com.example.summy.controlrendimiento.views.ObtenerCompetenciasApiActivity;
import com.example.summy.controlrendimiento.R;
import com.example.summy.controlrendimiento.model.Competencia;


import java.util.ArrayList;
import java.util.List;


public class CompetenciasAdapter extends RecyclerView.Adapter<CompetenciasAdapter.CompetenciasViewHolder> {

    private List<CompNacional> compNacional;

    public CompetenciasAdapter(List<CompNacional> compNacional) {
        this.compNacional = compNacional;
    }

    @Override
    public CompetenciasViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_competencia, parent, false);
        return new CompetenciasViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CompetenciasViewHolder holder, int position) {
        CompNacional comp = compNacional.get(position);

        holder.competenciaTextView.setText(comp.getTituloComp());
        holder.lugarTextView.setText(comp.getLugarComp());
        holder.fechaIniTextView.setText(comp.getFechaIni());
        holder.fechaFinTextView.setText(comp.getFechaFin());

        holder.itemView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
                menu.add(holder.getAdapterPosition(), 0, 0, "Eliminar");
                menu.add(holder.getAdapterPosition(), 1, 0, "Editar");
            //    menu.add(holder.getAdapterPosition(), 2, 0, "Elegir como principal");
            }
        });
    }

    @Override
    public int getItemCount() {
        return compNacional.size();
    }

    public static class CompetenciasViewHolder extends RecyclerView.ViewHolder {

        TextView competenciaTextView;
        TextView lugarTextView;
        TextView fechaIniTextView;
        TextView fechaFinTextView;

   //   ImageView fotoImageView;

        public CompetenciasViewHolder(View itemView) {
            super(itemView);

            competenciaTextView  = (TextView) itemView.findViewById(R.id.competenciaTextView);
            lugarTextView        = (TextView) itemView.findViewById(R.id.lugarTextView);
            fechaIniTextView     = (TextView) itemView.findViewById(R.id.fechaIniTextView);
            fechaFinTextView     = (TextView) itemView.findViewById(R.id.fechaFinTextView);
       //   fotoImageView = (ImageView) itemView.findViewById(R.id.fotoImageView);
        }

    }

}
