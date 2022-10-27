package com.example.violencia;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InstitucionesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InstitucionesFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    View vista;
    Button cochabambaIns,laPazIns,oruroIns,potosiIns,tarijaIns,chuquisacaIns,santaCruzIns,beniIns,pandoIns;

    public InstitucionesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InstitucionesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static InstitucionesFragment newInstance(String param1, String param2) {
        InstitucionesFragment fragment = new InstitucionesFragment();
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
        vista=inflater.inflate(R.layout.fragment_instituciones, container, false);
        cochabambaIns=(Button) vista.findViewById(R.id.btnCochabambaIns);
        laPazIns=(Button) vista.findViewById(R.id.btnLaPazIns);
        oruroIns=(Button) vista.findViewById(R.id.btnOruroIns);
        potosiIns=(Button) vista.findViewById(R.id.btnPotosiIns);
        tarijaIns=(Button) vista.findViewById(R.id.btnTarijaIns);
        chuquisacaIns=(Button) vista.findViewById(R.id.btnChuquisacaIns);
        santaCruzIns=(Button) vista.findViewById(R.id.btnSantaCruzIns);
        beniIns=(Button) vista.findViewById(R.id.btnBeniIns);
        pandoIns=(Button) vista.findViewById(R.id.btnPandoIns);



        cochabambaIns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), InstitucionesCochabambaActivity.class);
                startActivity(intent);
            }
        });
        laPazIns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), InstitucionesLaPazActivity.class);
                startActivity(intent);
            }
        });
        oruroIns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), InstitucionesOruroActivity.class);
                startActivity(intent);
            }
        });
        potosiIns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), InstitucionesPotosiActivity.class);
                startActivity(intent);
            }
        });
        tarijaIns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), InstitucionesTarijaActivity.class);
                startActivity(intent);
            }
        });
        chuquisacaIns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), InstitucionesChuquisacaActivity.class);
                startActivity(intent);
            }
        });
        santaCruzIns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), InstitucionesSantaCruzActivity.class);
                startActivity(intent);
            }
        });
        beniIns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), InstitucionesBeniActivity.class);
                startActivity(intent);
            }
        });
        pandoIns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), InstitucionesPandoActivity.class);
                startActivity(intent);
            }
        });
        return vista;
    }


}