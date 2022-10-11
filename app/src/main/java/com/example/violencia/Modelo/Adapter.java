package com.example.violencia.Modelo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.violencia.R;

import java.util.List;

public class Adapter extends ArrayAdapter<MisDenuncias> {

    Context context;
    List<MisDenuncias> arraymisdenuncias;
    public Adapter(@NonNull Context context, List<MisDenuncias>arraymisdenuncias) {
        super(context, R.layout.list_misdenuncias,arraymisdenuncias);
        this.context=context;
        this.arraymisdenuncias=arraymisdenuncias;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_misdenuncias,null,true);
        TextView autoIncrement=view.findViewById(R.id.txtAutoincrement);
        TextView nombresResult=view.findViewById(R.id.txtNombresResult);

        autoIncrement.setText(arraymisdenuncias.get(position).getAutoIncrement());
        nombresResult.setText(arraymisdenuncias.get(position).DenuncianteInfo());
        return view;
    }
}
