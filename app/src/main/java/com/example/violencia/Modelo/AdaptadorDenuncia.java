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

public class AdaptadorDenuncia extends RecyclerView.Adapter<AdaptadorDenuncia.PlayerViewnHolder> {


    private Context mCtx;
    private List<ListaDenuncias> denunciasList;

    public AdaptadorDenuncia(Context mCtx, List<ListaDenuncias>denunciasList){
        this.mCtx=mCtx;
        this.denunciasList=denunciasList;
    }
    @NonNull
    @Override
    public PlayerViewnHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(mCtx);
        View view=inflater.inflate(R.layout.list_card,null);
        return new PlayerViewnHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayerViewnHolder holder, int position) {
        ListaDenuncias listaDenuncias=denunciasList.get(position);
        Glide.with(mCtx)
                .load(listaDenuncias.getFoto())
                .into(holder.imgDenucia);
        holder.txtID.setText(listaDenuncias.getFechaRegistro());
        holder.txtNombre.setText("■Denunciante:    "+listaDenuncias.getNombres()+"\n■Declaración:     "+
                listaDenuncias.getDeclaracion()+"\n■Estado:              "+
                listaDenuncias.getEstado());
    }

    @Override
    public int getItemCount() {
        return denunciasList.size();
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
