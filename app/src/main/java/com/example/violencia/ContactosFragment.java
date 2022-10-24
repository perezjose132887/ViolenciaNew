package com.example.violencia;

import static android.app.Activity.RESULT_OK;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.example.violencia.Modelo.ModelContactosActivity;

import java.io.IOException;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ContactosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ContactosFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    View vista;
    Button eliminarLista,guardar;
    ImageButton seleccionar;
    EditText nombreContacto,telefono;
    static final int PICK_CONTACT_REQUEST=1;
    ListView listaContactos;

    AdminSQLiteOpenHelper admin= new AdminSQLiteOpenHelper(getContext(),"administracion",null,1);






    public ContactosFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ContactosFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ContactosFragment newInstance(String param1, String param2) {
        ContactosFragment fragment = new ContactosFragment();
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




        vista=inflater.inflate(R.layout.fragment_contactos, container, false);
        eliminarLista=(Button) vista.findViewById(R.id.btnEliminarLista);
        seleccionar=(ImageButton) vista.findViewById(R.id.ibtnSeleccionar);
        nombreContacto=(EditText) vista.findViewById(R.id.etNombreContacto);
        telefono=(EditText) vista.findViewById(R.id.etTelefono);
        guardar=(Button) vista.findViewById(R.id.btnGuardarContacto);
        listaContactos=(ListView) vista.findViewById(R.id.lvListaContactos);



        //Selecciona un contacto del celular
        seleccionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SeleccionarContacto();
            }
        });



        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guardarContactoLista();
            }
        });


        eliminarLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eliminarListView();
            }
        });

        listarContactos();

        return vista;
    }





    private void eliminarListView(){
        SharedPreferences preferences=getActivity().getSharedPreferences("sesion", Context.MODE_PRIVATE);
        String nana=preferences.getString("idUsuario","No encontrado");
        AdminSQLiteOpenHelper admin= new AdminSQLiteOpenHelper(getContext(),"administracion",null,1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();
        //BaseDeDatos.delete("contactos",null,null);
        BaseDeDatos.execSQL("DELETE FROM contactos WHERE usuariobd="+Integer.parseInt(nana));
        //BaseDeDatos.execSQL("create table if not exists contactos(idContacto integer primary key autoincrement, nombre text, numero int,usuariobd int)");
        Toast.makeText(getContext(),"La lista a sido limpiado",Toast.LENGTH_SHORT).show();
        BaseDeDatos.close();
        //startActivity(getActivity().getIntent());
        FragmentManager fragmentManager=getActivity().getSupportFragmentManager();
        FragmentTransaction transaction=fragmentManager.beginTransaction();
        transaction.setReorderingAllowed(true);
        transaction.replace(R.id.content,ContactosFragment.newInstance("",""));
        transaction.commit();
        //getActivity().finish();
    }







    //Metodo para seleccionar los contactos del telefono
    private void SeleccionarContacto() {
        Intent selectContactItem=new Intent(Intent.ACTION_PICK, Uri.parse("content://contacts"));
        selectContactItem.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
        startActivityForResult(selectContactItem,PICK_CONTACT_REQUEST);
    }





    //Metodo sobreescrito cuando acabemos de seleccionar el contacto del telefono
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_CONTACT_REQUEST) {
            if (resultCode == RESULT_OK) {
                Uri uri = data.getData();
                Cursor cursor = getActivity().getContentResolver().query(uri, null, null, null, null);

                if(cursor.moveToFirst()){
                    int columnaNombre=cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
                    int columnaNumero=cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                    String nombre=cursor.getString(columnaNombre);
                    String numero=cursor.getString(columnaNumero);
                    nombreContacto.setText(nombre);
                    telefono.setText(numero);

                }
            }
        }
    }




    //Metodo para guardar contacto en listview
    private void guardarContactoLista(){

        SharedPreferences preferences=getActivity().getSharedPreferences("sesion", Context.MODE_PRIVATE);
        String nana=preferences.getString("idUsuario","No encontrado");
        AdminSQLiteOpenHelper admin= new AdminSQLiteOpenHelper(getActivity(),"administracion",null,1);
        SQLiteDatabase BaseDeDatos=admin.getWritableDatabase();//Abrir modo de lectura y escritura la bdd

        String snombreContacto=nombreContacto.getText().toString();//obtenemos el valor de los edittext
        String sTelefono=telefono.getText().toString();

        if(!snombreContacto.isEmpty() && !sTelefono.isEmpty()){//condicion para no dejar vacio los campos de contactos
            ContentValues registro= new ContentValues();
            registro.put("nombre",snombreContacto);//guardamos dentro de la bdd ahora falta insertar en la tabla
            registro.put("numero",sTelefono);
            registro.put("usuariobd",Integer.parseInt(nana));

            BaseDeDatos.insert("contactos",null,registro);//registramos a la tabla
            BaseDeDatos.close();//Cerramos la base de datos;
            nombreContacto.setText("");
            telefono.setText("");
            Toast.makeText(getContext(),"Registro exitoso", Toast.LENGTH_SHORT).show();
            listarContactos();
        }else{
            Toast.makeText(getContext(),"Debes llenar todos los campos", Toast.LENGTH_SHORT).show();
        }


    }


    private void listarContactos(){

        ArrayList<ModelContactosActivity> lista= obtenerContactos();
        if(!lista.isEmpty()){
            ArrayAdapter<ModelContactosActivity> adaptador = new ArrayAdapter<ModelContactosActivity>(getContext(), android.R.layout.simple_list_item_1,lista);
            listaContactos.setAdapter(adaptador);
           }

    }



    private ArrayList<ModelContactosActivity> obtenerContactos() {
        SharedPreferences preferences=getActivity().getSharedPreferences("sesion", Context.MODE_PRIVATE);
        String nana=preferences.getString("idUsuario","No encontrado");
        AdminSQLiteOpenHelper admin= new AdminSQLiteOpenHelper(getContext(),"administracion",null,1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();
        //select * from contactos
        Cursor cursor = BaseDeDatos.rawQuery("SELECT * FROM contactos WHERE usuariobd="+Integer.parseInt(nana), null);
        ArrayList<ModelContactosActivity> lista = new ArrayList<ModelContactosActivity>();


        while (cursor.moveToNext()) {
            ModelContactosActivity cm = new ModelContactosActivity();
            cm.setId(cursor.getInt(0));
            cm.setContacto(cursor.getString(1));
            cm.setTelefono(cursor.getString(2));
            cm.setUsuariobd(cursor.getInt(3));
            lista.add(cm);
        }
        BaseDeDatos.close();
        return lista;

    }


}