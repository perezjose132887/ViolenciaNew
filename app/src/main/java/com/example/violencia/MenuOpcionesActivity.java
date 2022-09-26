package com.example.violencia;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import com.example.violencia.Modelo.ModelContactosActivity;

import java.util.ArrayList;

public class MenuOpcionesActivity extends AppCompatActivity {

    Button seleccionarContactos,irAalerta;

    AdminSQLiteOpenHelper admin= new AdminSQLiteOpenHelper(MenuOpcionesActivity.this,"administracion",null,1);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_opciones);

        seleccionarContactos=(Button) findViewById(R.id.btnSeleccionarContactos);
        irAalerta=(Button) findViewById(R.id.btnIrAalerta);
        String idUsuario=getIntent().getStringExtra("idUsuario");
        String nombres=getIntent().getStringExtra("nombres");
        String primerApellido=getIntent().getStringExtra("primerApellido");
        String numeroCI=getIntent().getStringExtra("numeroCI");
        String foto=getIntent().getStringExtra("foto");
        String correo=getIntent().getStringExtra("correo");
        guardarPreferenciasSesion(idUsuario,nombres,primerApellido,numeroCI,correo);

        /*//Permiso para realizar llamadas
        int permissionCheck = ContextCompat.checkSelfPermission(
                this, Manifest.permission.CALL_PHONE);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 225);
        } else {
        }*/

        seleccionarContactos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MenuOpcionesActivity.this,ModuloContactoActivity.class);
                intent.putExtra("idUsuario",idUsuario);
                intent.putExtra("nombres",nombres);
                intent.putExtra("primerApellido",primerApellido);
                intent.putExtra("numeroCI",numeroCI);
                intent.putExtra("foto",foto);
                intent.putExtra("correo",correo);
                startActivity(intent);
                finish();
            }
        });


        irAalerta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase BaseDeDatos=admin.getWritableDatabase();
                //select * from contactos
                Cursor cursor= BaseDeDatos.rawQuery("SELECT * FROM contactos",null);
                if(cursor.getCount() != 0){
                    Intent intent=new Intent(MenuOpcionesActivity.this,NavigationDrawer.class);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(MenuOpcionesActivity.this, "Seleccione algunos contactos de confianza", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }






    @Override
    public void onBackPressed(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("¿Desea salir de la App?")
                .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        builder.show();
    }



    public void guardarPreferenciasSesion(String idUsuario,String nombres,String primerApellido,String numeroCI,String correo){
        SharedPreferences preferencesSesion=getSharedPreferences("sesion", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferencesSesion.edit();
        editor.putString("idUsuario",idUsuario);
        editor.putString("nombres",nombres);
        editor.putString("primerApellido",primerApellido);
        editor.putString("numeroCI",numeroCI);
        editor.putString("correo",correo);
        editor.commit();
    }


}