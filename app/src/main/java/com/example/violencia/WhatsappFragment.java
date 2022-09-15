package com.example.violencia;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WhatsappFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WhatsappFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    View vista;
    Button cochabamba,laPaz,oruro,potosi,tarija,chuquisaca,santaCruz,beni,pando;

    public WhatsappFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment WhatsappFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static WhatsappFragment newInstance(String param1, String param2) {
        WhatsappFragment fragment = new WhatsappFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        vista=inflater.inflate(R.layout.fragment_whatsapp, container, false);
        cochabamba=(Button) vista.findViewById(R.id.btnCochabamba);
        laPaz=(Button) vista.findViewById(R.id.btnLaPaz);
        oruro=(Button) vista.findViewById(R.id.btnOruro);
        potosi=(Button) vista.findViewById(R.id.btnPotosi);
        tarija=(Button) vista.findViewById(R.id.btnTarija);
        chuquisaca=(Button) vista.findViewById(R.id.btnChuquisaca);
        santaCruz=(Button) vista.findViewById(R.id.btnSantaCruz);
        beni=(Button) vista.findViewById(R.id.btnBeni);
        pando=(Button) vista.findViewById(R.id.btnPando);



        cochabamba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String telefono="60707069";
                enviarWhatsapp(telefono);
            }
        });
        laPaz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String telefono="61000523";
                enviarWhatsapp(telefono);
            }
        });
        oruro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String telefono="62805818";
                enviarWhatsapp(telefono);
            }
        });
        potosi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String telefono="72042570";
                enviarWhatsapp(telefono);
            }
        });
        tarija.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String telefono="68241690";
                enviarWhatsapp(telefono);
            }
        });
        chuquisaca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String telefono="72042698";
                enviarWhatsapp(telefono);
            }
        });
        santaCruz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String telefono="72042264";
                enviarWhatsapp(telefono);
            }
        });
        beni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String telefono="72042109";
                enviarWhatsapp(telefono);
            }
        });
        pando.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String telefono="72042740";
                enviarWhatsapp(telefono);
            }
        });





        return vista;
    }



    public void enviarWhatsapp(String telefono){
        Intent sendIntent= new Intent();
        sendIntent.setAction(Intent.ACTION_VIEW);
        String uri="whatsapp://send?phone=591"+telefono+"&text="+"";
        sendIntent.setData(Uri.parse(uri));
        startActivity(sendIntent);
    }
}