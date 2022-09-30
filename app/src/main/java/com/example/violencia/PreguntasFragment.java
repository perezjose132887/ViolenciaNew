package com.example.violencia;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PreguntasFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PreguntasFragment extends Fragment implements View.OnClickListener{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    Button descarga;
    View vista;

    TextView p1,p2,p3,p4,p5,p6,p7,p8,p9,p10;

    Button b1,b2,b3,b4,b5,b6,b7,b8,b9,b10;

    public PreguntasFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PreguntasFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PreguntasFragment newInstance(String param1, String param2) {
        PreguntasFragment fragment = new PreguntasFragment();
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
        vista=inflater.inflate(R.layout.fragment_preguntas, container, false);
        descarga=(Button) vista.findViewById(R.id.btnDescargar);

        p1=(TextView) vista.findViewById(R.id.p1);
        p2=(TextView) vista.findViewById(R.id.p2);
        p3=(TextView) vista.findViewById(R.id.p3);
        p4=(TextView) vista.findViewById(R.id.p4);
        p5=(TextView) vista.findViewById(R.id.p5);
        p6=(TextView) vista.findViewById(R.id.p6);
        p7=(TextView) vista.findViewById(R.id.p7);
        p8=(TextView) vista.findViewById(R.id.p8);
        p9=(TextView) vista.findViewById(R.id.p9);
        p10=(TextView) vista.findViewById(R.id.p10);

        b1=(Button) vista.findViewById(R.id.b1);
        b2=(Button) vista.findViewById(R.id.b2);
        b3=(Button) vista.findViewById(R.id.b3);
        b4=(Button) vista.findViewById(R.id.b4);
        b5=(Button) vista.findViewById(R.id.b5);
        b6=(Button) vista.findViewById(R.id.b6);
        b7=(Button) vista.findViewById(R.id.b7);
        b8=(Button) vista.findViewById(R.id.b8);
        b9=(Button) vista.findViewById(R.id.b9);
        b10=(Button) vista.findViewById(R.id.b10);





        vista.findViewById(R.id.b1).setOnClickListener((View.OnClickListener)this);
        vista.findViewById(R.id.b2).setOnClickListener((View.OnClickListener)this);
        vista.findViewById(R.id.b3).setOnClickListener((View.OnClickListener)this);
        vista.findViewById(R.id.b4).setOnClickListener((View.OnClickListener)this);
        vista.findViewById(R.id.b5).setOnClickListener((View.OnClickListener)this);
        vista.findViewById(R.id.b6).setOnClickListener((View.OnClickListener)this);
        vista.findViewById(R.id.b7).setOnClickListener((View.OnClickListener)this);
        vista.findViewById(R.id.b8).setOnClickListener((View.OnClickListener)this);
        vista.findViewById(R.id.b9).setOnClickListener((View.OnClickListener)this);
        vista.findViewById(R.id.b10).setOnClickListener((View.OnClickListener)this);


        descarga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.google.com/url?sa=t&source=web&rct=j&url=https://www.defensoria.gob.bo/uploads/files/cartilla-ley-348-en-43-preguntas-y-respuestas.pdf&ved=2ahUKEwio9I6cqrr6AhVhjJUCHcp4ByoQFnoECA8QAQ&usg=AOvVaw2dCUc-XFnutG83mIOYQAjU"));
                startActivity(intent);
            }
        });

        return vista;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.b1:
                if(p1.getVisibility()==View.GONE){
                    p1.setVisibility(View.VISIBLE);
                    b1.setText("Ver menos");
                }else{
                    p1.setVisibility(View.GONE);
                    b1.setText("Ver más");
                }
                break;
            case R.id.b2:
                if(p2.getVisibility()==View.GONE){
                    p2.setVisibility(View.VISIBLE);
                    b2.setText("Ver menos");
                }else{
                    p2.setVisibility(View.GONE);
                    b2.setText("Ver más");
                }
                break;
            case R.id.b3:
                if(p3.getVisibility()==View.GONE){
                    p3.setVisibility(View.VISIBLE);
                    b3.setText("Ver menos");
                }else{
                    p3.setVisibility(View.GONE);
                    b3.setText("Ver más");
                }
                break;
            case R.id.b4:
                if(p4.getVisibility()==View.GONE){
                    p4.setVisibility(View.VISIBLE);
                    b4.setText("Ver menos");
                }else{
                    p4.setVisibility(View.GONE);
                    b4.setText("Ver más");
                }
                break;
            case R.id.b5:
                if(p5.getVisibility()==View.GONE){
                    p5.setVisibility(View.VISIBLE);
                    b5.setText("Ver menos");
                }else{
                    p5.setVisibility(View.GONE);
                    b5.setText("Ver más");
                }
                break;
            case R.id.b6:
                if(p6.getVisibility()==View.GONE){
                    p6.setVisibility(View.VISIBLE);
                    b6.setText("Ver menos");
                }else{
                    p6.setVisibility(View.GONE);
                    b6.setText("Ver más");
                }
                break;
            case R.id.b7:
                if(p7.getVisibility()==View.GONE){
                    p7.setVisibility(View.VISIBLE);
                    b7.setText("Ver menos");
                }else{
                    p7.setVisibility(View.GONE);
                    b7.setText("Ver más");
                }
                break;
            case R.id.b8:
                if(p8.getVisibility()==View.GONE){
                    p8.setVisibility(View.VISIBLE);
                    b8.setText("Ver menos");
                }else{
                    p8.setVisibility(View.GONE);
                    b8.setText("Ver más");
                }
                break;
            case R.id.b9:
                if(p9.getVisibility()==View.GONE){
                    p9.setVisibility(View.VISIBLE);
                    b9.setText("Ver menos");
                }else{
                    p9.setVisibility(View.GONE);
                    b9.setText("Ver más");
                }
                break;
            case R.id.b10:
                if(p10.getVisibility()==View.GONE){
                    p10.setVisibility(View.VISIBLE);
                    b10.setText("Ver menos");
                }else{
                    p10.setVisibility(View.GONE);
                    b10.setText("Ver más");
                }
                break;
        }
    }



}