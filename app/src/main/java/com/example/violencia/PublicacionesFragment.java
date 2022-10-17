package com.example.violencia;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PublicacionesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PublicacionesFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    View vista;
    Button publicacionesOficiales,publicacionesComunidad,publicacionesEducativa,publicacionesPautas,publicacionesIgualitaria;

    public PublicacionesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PublicacionesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PublicacionesFragment newInstance(String param1, String param2) {
        PublicacionesFragment fragment = new PublicacionesFragment();
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
        vista=inflater.inflate(R.layout.fragment_publicaciones, container, false);
        publicacionesOficiales=(Button) vista.findViewById(R.id.btnPublicacionesOficiales);
        publicacionesComunidad=(Button) vista.findViewById(R.id.btnPublicacionesComunidad);
        publicacionesEducativa=(Button) vista.findViewById(R.id.btnPublicacionesEducativa);
        publicacionesPautas=(Button) vista.findViewById(R.id.btnPublicacionesPautasDeSeguridad);
        publicacionesIgualitaria=(Button) vista.findViewById(R.id.btnPublicacionesActitudesIgualitarias);



        publicacionesOficiales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),PublicacionesOficialesActivity.class);
                startActivity(intent);
            }
        });

        publicacionesComunidad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),PublicacionesComunidadActivity.class);
                startActivity(intent);
            }
        });

        publicacionesEducativa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),PublicacionesEducativaActivity.class);
                startActivity(intent);
            }
        });

        publicacionesPautas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),PublicacionesPautasDeSeguridadActivity.class);
                startActivity(intent);
            }
        });

        publicacionesIgualitaria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),PublicacionesActitudesIgualitariasActivity.class);
                startActivity(intent);
            }
        });


        return vista;
    }
}