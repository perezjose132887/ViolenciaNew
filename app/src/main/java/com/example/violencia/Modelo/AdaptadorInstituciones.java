package com.example.violencia.Modelo;

import static android.content.Intent.ACTION_DIAL;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.violencia.DetallePublicacionActivity;
import com.example.violencia.R;

import java.util.List;

public class AdaptadorInstituciones extends RecyclerView.Adapter<AdaptadorInstituciones.PlayerViewnHolder> {

    private Context mCtx;
    private List<ListaInstituciones> institucionesList;

    public AdaptadorInstituciones(Context mCtx, List<ListaInstituciones>institucionesList){
        this.mCtx=mCtx;
        this.institucionesList=institucionesList;
    }

    @NonNull
    @Override
    public AdaptadorInstituciones.PlayerViewnHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(mCtx);
        View view=inflater.inflate(R.layout.list_institucion,null);
        return new AdaptadorInstituciones.PlayerViewnHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull AdaptadorInstituciones.PlayerViewnHolder holder, int position) {
        ListaInstituciones listaInstituciones=institucionesList.get(position);
        holder.nombreInsti.setText(listaInstituciones.getNombreInstitucionIns());
        holder.direccionInsti.setText("■ Dirección: "+listaInstituciones.getDireccionIns()
                                        +"\n■ Telefono: "+listaInstituciones.getTelefonoIns());
        //holder.txtNombre.setText("■"+listaPublicacionOficiales.getContenido());


        //holder.txtNombre.setText(remplazar(""+listaPublicacionOficiales.getContenido()));

        /*holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent=new Intent(holder.itemView.getContext(), DetallePublicacionActivity.class);
                intent.putExtra("itemDetalle",listaInstituciones);
                holder.itemView.getContext().startActivity(intent);
                Toast.makeText(mCtx, "Hola"+listaPublicacionOficiales.getTitulo(), Toast.LENGTH_SHORT).show();
            }
        });*/
    }

    /*public static String remplazar (String str) {
        return str.replaceAll("<br />", "\n");
    }*/

    @Override
    public int getItemCount() {
        return institucionesList.size();
    }



    static class PlayerViewnHolder extends RecyclerView.ViewHolder{
        TextView nombreInsti,direccionInsti;

        public PlayerViewnHolder(@NonNull View itemView) {
            super(itemView);
            nombreInsti= itemView.findViewById(R.id.txtNombreInsti);
            direccionInsti= itemView.findViewById(R.id.txtDireccionInsti);
        }
    }

}
