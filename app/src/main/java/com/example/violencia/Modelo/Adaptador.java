package com.example.violencia.Modelo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.violencia.MisDenunciasFragment;
import com.example.violencia.R;

import java.util.List;

public class Adaptador extends ArrayAdapter<DenunciaUsuarios> {


    Context context;
    List<DenunciaUsuarios> arrayalistaUsers;

    public Adaptador(@NonNull MisDenunciasFragment context, List<DenunciaUsuarios>arrayalistaUsers) {
        super(context.getContext(), R.layout.list_usuarios, arrayalistaUsers);
        this.context=getContext();
        this.arrayalistaUsers=arrayalistaUsers;
    }



    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_usuarios,null,true);

        TextView txtID= view.findViewById(R.id.txtid);
        TextView txtNombre= view.findViewById(R.id.txtnombre);


        txtID.setText(arrayalistaUsers.get(position).getFechaRegistro());
        txtNombre.setText("■Denunciante:     "+arrayalistaUsers.get(position).getNombres()+"\n■Tipo de Denuncia:  "+
                arrayalistaUsers.get(position).getDescripcionCategoria()+"\n■Declaración:      "+
                arrayalistaUsers.get(position).getDeclaracion()+"\n■Estado:               "+
                arrayalistaUsers.get(position).getEstado());


        return view;
    }
}
