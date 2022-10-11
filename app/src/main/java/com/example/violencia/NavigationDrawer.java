package com.example.violencia;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.application.isradeleon.notify.Notify;
import com.google.android.material.navigation.NavigationView;

public class NavigationDrawer extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    ActionBarDrawerToggle toogle;

    TextView nombreHeader,correoHeader;


    String idUsuario;
    String nombres;
    String primerApellido;
    String numeroCI;
    String foto;
    String correo;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);

        drawerLayout=findViewById(R.id.drawerLayout);
        navigationView=findViewById(R.id.nav_view);
        toolbar=findViewById(R.id.toolbar);
        nombreHeader=(TextView) findViewById(R.id.txtNombreHeader);
        correoHeader=(TextView) findViewById(R.id.txtCorreoHeader);



        /////////////////NOTIFICACION//////////////////
        int numero = (int)(Math.random()*5+1);
        mensajeNotificacion(numero);
        //////////////////////////////////////////////



         idUsuario=getIntent().getStringExtra("idUsuario");
         nombres=getIntent().getStringExtra("nombres");
         primerApellido=getIntent().getStringExtra("primerApellido");
         numeroCI=getIntent().getStringExtra("numeroCI");
         foto=getIntent().getStringExtra("foto");
         correo=getIntent().getStringExtra("correo");
        cargarPreferenciasSesionDrawer();


        //Toast.makeText(NavigationDrawer.this, ""+nombres+" "+idUsuario, Toast.LENGTH_SHORT).show();





        getSupportFragmentManager().beginTransaction().add(R.id.content,new AlertaFragment()).commit();
        setTitle("ALERTA");

        //Setup toolbar
        setSupportActionBar(toolbar);

        toogle=setUpDrawerToogle();
        drawerLayout.addDrawerListener(toogle);


        navigationView.setNavigationItemSelectedListener(this);

    }



    /*@Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==event.KEYCODE_BACK){
            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setMessage("¿Estas seguro de salir de la App?")
                    .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent= new Intent(Intent.ACTION_MAIN);
                            intent.addCategory(Intent.CATEGORY_HOME);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        }
                    })
                    .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            builder.show();
        }
        return super.onKeyDown(keyCode, event);
    }*/

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



    public void cargarPreferenciasSesionDrawer(){
        SharedPreferences preferencesSesion=getSharedPreferences("sesion", Context.MODE_PRIVATE);
        String idUsuario=preferencesSesion.getString("idUsuario","No encontrado");
        String nombres=preferencesSesion.getString("nombres","No encontrado");
        String primerApellido=preferencesSesion.getString("primerApellido","No encontrado");
        String numeroCI=preferencesSesion.getString("numeroCI","No encontrado");
        String correo=preferencesSesion.getString("correo","No encontrado");
        //Toast.makeText(this, "Preferences"+nombres+" "+primerApellido, Toast.LENGTH_SHORT).show();
    }



    public ActionBarDrawerToggle setUpDrawerToogle(){
        return new ActionBarDrawerToggle(this,
                drawerLayout,
                toolbar,
                R.string.drawer_open,
                R.string.drawer_close);
    }


    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        toogle.syncState();
    }


    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        toogle.onConfigurationChanged(newConfig);
    }



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        selectItemNav(item);
        return true;
    }





    private void selectItemNav(MenuItem item) {
        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        switch (item.getItemId()){
            case R.id.nav_alerta:
                ft.replace(R.id.content,new AlertaFragment()).commit();
                break;
            case R.id.nav_emergencia:
                ft.replace(R.id.content,new EmergenciasFragment()).commit();
                break;
            case R.id.nav_pregunta:
                ft.replace(R.id.content,new PreguntasFragment()).commit();
                break;
            case R.id.nav_denuncia:
                DenunciaFragment denunciaFragment= new DenunciaFragment();
                Bundle bundle=new Bundle();
                bundle.putString("idUsuario",idUsuario);
                bundle.putString("nombres",nombres);
                denunciaFragment.setArguments(bundle);
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();
                fragmentTransaction.add(R.id.content,denunciaFragment,null);
                fragmentTransaction.commit();
                //ft.replace(R.id.content,new DenunciaFragment()).commit();
                break;
            case R.id.nav_whatsapp:
                ft.replace(R.id.content,new WhatsappFragment()).commit();
                break;
            case R.id.nav_contactos:
                ft.replace(R.id.content,new ContactosFragment()).commit();
                break;
            case R.id.nav_test:
                ft.replace(R.id.content,new TestFragment()).commit();
                break;
            case R.id.nav_salir:
                ft.replace(R.id.content,new SalirFragment()).commit();
                break;
            case R.id.nav_misDenuncias:
                ft.replace(R.id.content,new MisDenunciasFragment()).commit();
                break;
        }
        setTitle(item.getTitle());
        drawerLayout.closeDrawers();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(toogle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }








    ////////////////////NOTIFICACION////////////////////
    private void mensajeNotificacion(int numero){
        switch (numero){
            case 1:
                /*return "Defiende tu vida, lucha por tu independencia," +
                        " busca tu felicidad y aprende a quererte.";*/
                Notify.build(getApplicationContext())
                        .setTitle("POR UNA VIDA LIBRE DE VIOLENCIAS")
                        //.setContent("Defiende tu vida, lucha por tu independencia, busca tu felicidad y aprende a quererte.")
                        .setContent("Nada bueno viene jamás de la violencia")
                        .setSmallIcon(R.drawable.corazonnoti)
                        .setColor(R.color.black)
                        .setLargeIcon(R.drawable.stop)
                        .largeCircularIcon()
                        .setPicture(R.drawable.noti5)
                        .show();
                break;
            case 2:
                /*return "Cualquier momento del día o de la noche es bueno" +
                        " para decir basta y poner fin a una etapa de tu" +
                        " vida que hubieras deseado no vivir.";*/
                Notify.build(getApplicationContext())
                        .setTitle("POR UNA VIDA LIBRE DE VIOLENCIAS")
                        //.setContent("Cualquier momento del día o de la noche es bueno para decir basta y poner fin a una etapa de tu vida que hubieras deseado no vivir.")
                        .setContent("La violencia es el problema, no la solucion")
                        .setSmallIcon(R.drawable.corazonnoti)
                        .setColor(R.color.black)
                        .setLargeIcon(R.drawable.stop)
                        .largeCircularIcon()
                        .setPicture(R.drawable.noti3)
                        .show();
                break;
            case 3:

                /*return "No quiero sentirme valiente cuando salga a la calle," +
                        " quiero sentirme libre.";*/
                Notify.build(getApplicationContext())
                        .setTitle("POR UNA VIDA LIBRE DE VIOLENCIAS")
                        //.setContent("No quiero sentirme valiente cuando salga a la calle quiero sentirme libre.")
                        .setContent("Ponle din para tener un principio ¡DENUNCIA!")
                        .setSmallIcon(R.drawable.corazonnoti)
                        .setColor(R.color.black)
                        .setLargeIcon(R.drawable.stop)
                        .largeCircularIcon()
                        .setPicture(R.drawable.noti2)
                        .show();
                break;
            case 4:
                /*return "Rompe el silencio. Cuando seas testigo de la violencia" +
                        " contra las mujeres no te quedes de brazos cruzados." +
                        " Actúa.";*/
                Notify.build(getApplicationContext())
                        .setTitle("POR UNA VIDA LIBRE DE VIOLENCIAS")
                        //.setContent("Rompe el silencio. Cuando seas testigo de la violencia contra las mujeres no te quedes de brazos cruzados. Actúa.")
                        .setContent("¡Mujer liberáte de las garras de la violencia!")
                        .setSmallIcon(R.drawable.corazonnoti)
                        .setColor(R.color.black)
                        .setLargeIcon(R.drawable.stop)
                        .largeCircularIcon()
                        .setPicture(R.drawable.noti4)
                        .show();
                break;
            case 5:
                /*return "La cantidad de ropa que uso no determina la cantidad" +
                        " de respeto que merezco.";*/
                Notify.build(getApplicationContext())
                        .setTitle("POR UNA VIDA LIBRE DE VIOLENCIAS")
                        //.setContent("La cantidad de ropa que uso no determina la cantidad de respeto que merezco.")
                        .setContent("No estas sola, ¡DENUNCIA!")
                        .setSmallIcon(R.drawable.corazonnoti)
                        .setColor(R.color.black)
                        .setLargeIcon(R.drawable.stop)
                        .largeCircularIcon()
                        .setPicture(R.drawable.noti1)
                        .show();
                break;
            default:
                break;
        }
    }

    //////////////////////////////////////////////////








}