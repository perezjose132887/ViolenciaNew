package com.example.violencia;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

public class NavigationDrawer extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    ActionBarDrawerToggle toogle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);

        drawerLayout=findViewById(R.id.drawerLayout);
        navigationView=findViewById(R.id.nav_view);
        toolbar=findViewById(R.id.toolbar);

        getSupportFragmentManager().beginTransaction().add(R.id.content,new AlertaFragment()).commit();
        setTitle("ALERTA");

        //Setup toolbar
        setSupportActionBar(toolbar);

        toogle=setUpDrawerToogle();
        drawerLayout.addDrawerListener(toogle);


        navigationView.setNavigationItemSelectedListener(this);

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
                ft.replace(R.id.content,new DenunciaFragment()).commit();
                break;
            case R.id.nav_whatsapp:
                ft.replace(R.id.content,new WhatsappFragment()).commit();
                break;
            case R.id.nav_notificacion:
                ft.replace(R.id.content,new NotificacionesFragment()).commit();
                break;
            case R.id.nav_contactos:
                ft.replace(R.id.content,new ContactosFragment()).commit();
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
}