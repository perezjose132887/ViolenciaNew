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

public class AdaptadorPublicacionComunidad extends RecyclerView.Adapter<AdaptadorPublicacionComunidad.PlayerViewnHolder> {
    private Context mCtx;
    private List<ListaPublicaciones> publicacionOficialesList;

    public AdaptadorPublicacionComunidad(Context mCtx, List<ListaPublicaciones>publicacionOficialesList){
        this.mCtx=mCtx;
        this.publicacionOficialesList=publicacionOficialesList;
    }

    @NonNull
    @Override
    public PlayerViewnHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(mCtx);
        View view=inflater.inflate(R.layout.list_card,null);
        return new AdaptadorPublicacionComunidad.PlayerViewnHolder(view);
    }




    @Override
    public void onBindViewHolder(@NonNull PlayerViewnHolder holder, int position) {
        ListaPublicaciones listaPublicacionOficiales=publicacionOficialesList.get(position);
        Glide.with(mCtx)
                .load(listaPublicacionOficiales.getFotoPublicacion())
                .into(holder.imgDenucia);
        holder.txtID.setText(listaPublicacionOficiales.getTitulo());
        //holder.txtNombre.setText("■"+listaPublicacionOficiales.getContenido());

        //holder.txtNombre.setText(remplazar(""+listaPublicacionOficiales.getContenido()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(holder.itemView.getContext(), DetallePublicacionActivity.class);
                intent.putExtra("itemDetalle",listaPublicacionOficiales);
                holder.itemView.getContext().startActivity(intent);
                //Toast.makeText(mCtx, "Hola"+listaPublicacionOficiales.getTitulo(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static String remplazar (String str) {
        return str.replaceAll("<br />", "\n");
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
