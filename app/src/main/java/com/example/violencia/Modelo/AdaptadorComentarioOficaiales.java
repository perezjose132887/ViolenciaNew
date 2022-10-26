package com.example.violencia.Modelo;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.violencia.DetallePublicacionActivity;
import com.example.violencia.R;

import java.util.List;

public class AdaptadorComentarioOficaiales extends RecyclerView.Adapter<AdaptadorComentarioOficaiales.PlayerViewnHolder>{

    private Context mCtx;
    private List<ListaComentarios> comentarioOficialesList;

    public AdaptadorComentarioOficaiales(Context mCtx, List<ListaComentarios>comentarioOficialesList){
        this.mCtx=mCtx;
        this.comentarioOficialesList=comentarioOficialesList;
    }

    @NonNull
    @Override
    public AdaptadorComentarioOficaiales.PlayerViewnHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(mCtx);
        View view=inflater.inflate(R.layout.list_comentario,null);
        return new AdaptadorComentarioOficaiales.PlayerViewnHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull AdaptadorComentarioOficaiales.PlayerViewnHolder holder, int position) {
        ListaComentarios listaComentarioOficiales=comentarioOficialesList.get(position);

        holder.tituloComentario.setText(listaComentarioOficiales.getCorreoco()+"\n"+listaComentarioOficiales.getFechaRegistro());
        holder.contenidoComentario.setText(listaComentarioOficiales.getComentarioco());

    }

    public static String remplazar (String str) {
        return str.replaceAll("<br />", "\n");
    }

    @Override
    public int getItemCount() {
        return comentarioOficialesList.size();
    }



    static class PlayerViewnHolder extends RecyclerView.ViewHolder{
        TextView tituloComentario,contenidoComentario;

        public PlayerViewnHolder(@NonNull View itemView) {
            super(itemView);
            tituloComentario= itemView.findViewById(R.id.txtTituloComentario);
            contenidoComentario= itemView.findViewById(R.id.txtContenidoComentario);
        }
    }


}
