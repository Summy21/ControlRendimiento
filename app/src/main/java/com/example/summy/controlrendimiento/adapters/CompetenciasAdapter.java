package com.example.summy.controlrendimiento.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.summy.controlrendimiento.ObtenerCompetenciasApiActivity;
import com.example.summy.controlrendimiento.R;
import com.example.summy.controlrendimiento.model.Competencia;


import java.util.ArrayList;
import java.util.List;


public class CompetenciasAdapter extends RecyclerView.Adapter<CompetenciasAdapter.DeviceViewHolder> {

    private Context context;
    private List<Competencia> dataset;
    private OnCompetenciaSelectedListener onCompetenciaSelectedListener;

    public interface OnCompetenciaSelectedListener {
        void onCompetenciaSelected(Competencia departamento);
    }

    public CompetenciasAdapter(List<Competencia> dataset, ObtenerCompetenciasApiActivity context) {
        this.dataset = dataset;
        this.context = context;
//        this.onPeliculaSelectedListener = (OnPeliculaSelectedListener) context;
    }

    @Override
    public DeviceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_competencia_api, parent, false);
        return new DeviceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DeviceViewHolder holder, int position) {
        Competencia c = dataset.get(position);

        holder.competenciaTextView.setText(c.getEvent_title());
        holder.lugarTextView.setText(c.getEvent_country());
        holder.fechaIniTextView.setText(c.getEvent_date());
        holder.fechaFinTextView.setText(c.getEvent_finish_date());

 //       String url = "http://image.tmdb.org/t/p/w185" + p.getImagen();

  //      Glide.with(context).load(url).into(holder.fotoImageView);

//        holder.setOnPeliculaItemClick(p, onPeliculaItemClickListener);
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public static class DeviceViewHolder extends RecyclerView.ViewHolder {

        TextView competenciaTextView;
        TextView lugarTextView;
        TextView fechaIniTextView;
        TextView fechaFinTextView;

   //     ImageView fotoImageView;

        public DeviceViewHolder(View itemView) {
            super(itemView);

            competenciaTextView = (TextView) itemView.findViewById(R.id.competenciaTextView);
            lugarTextView = (TextView) itemView.findViewById(R.id.lugarTextView);
            fechaIniTextView = (TextView) itemView.findViewById(R.id.fechaIniTextView);
            fechaFinTextView = (TextView) itemView.findViewById(R.id.fechaFinTextView);
       //     fotoImageView = (ImageView) itemView.findViewById(R.id.fotoImageView);
        }

        public void setDeviceSelectedListener(final Competencia competencia, final OnCompetenciaSelectedListener onCompetenciaSelectedListener) {
//            cardView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    onPeliculaSelectedListener.onPeliculaSelected(departamento);
//                }
//            });
        }
    }

    public void add(Competencia Competencia) {
        dataset.add(Competencia);
        notifyDataSetChanged();
    }

    public void setDataset(List<Competencia> unidadesEducativas) {
        if (unidadesEducativas == null) {
            dataset = new ArrayList<>();
        } else {
            dataset = unidadesEducativas;
        }
        notifyDataSetChanged();
    }

    public void clear() {
        dataset.clear();
        notifyDataSetChanged();
    }

}
