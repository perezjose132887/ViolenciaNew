package com.example.violencia.Modelo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.violencia.R;

import java.util.List;

public class AdaptadorPublicacionPautasDeSeguridad extends RecyclerView.Adapter<AdaptadorPublicacionPautasDeSeguridad.PlayerViewnHolder>{
    private Context mCtx;
    private List<ListaPublicaciones> publicacionOficialesList;

    public AdaptadorPublicacionPautasDeSeguridad(Context mCtx, List<ListaPublicaciones>publicacionOficialesList){
        this.mCtx=mCtx;
        this.publicacionOficialesList=publicacionOficialesList;
    }

    @NonNull
    @Override
    public AdaptadorPublicacionPautasDeSeguridad.PlayerViewnHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(mCtx);
        View view=inflater.inflate(R.layout.list_card,null);
        return new AdaptadorPublicacionPautasDeSeguridad.PlayerViewnHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull AdaptadorPublicacionPautasDeSeguridad.PlayerViewnHolder holder, int position) {
        ListaPublicaciones listaPublicacionOficiales=publicacionOficialesList.get(position);
        Glide.with(mCtx)
                .load(listaPublicacionOficiales.getFotoPublicacion())
                .into(holder.imgDenucia);
        holder.txtID.setText(listaPublicacionOficiales.getTitulo());
        holder.txtNombre.setText("■"+listaPublicacionOficiales.getContenido());
    }

    @Override
    public int getItemCount() {
        return publicacionOficialesList.size();
    }



    static class PlayerViewnHolder extends RecyclerView.ViewHolder{
        TextView txtID,txtNombre;
        ImageView imgDenucia;

        public PlayerViewnHolder(@NonNull View itemView) {
            super(itemView);
            txtID= itemView.findViewById(R.id.txtid);
            txtNombre= itemView.findViewById(R.id.txtnombre);
            imgDenucia= itemView.findViewById(R.id.imgFotoDenuncia);
        }
    }
}
