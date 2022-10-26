package com.example.violencia;

import android.Manifest;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Address;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationRequest;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Handler;
import android.provider.Settings;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.violencia.Modelo.ModelContactosActivity;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AlertaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AlertaFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    View vista;
    ImageButton alerta;
    TextView letraAlerta;

    //To esto es para mandar coordenadas
    EditText latitud, longitud;
    String slatitud, slongitud, idUsuario, nombres, primerApellido;


    ////////////////////////////////////////////////////////////////////////////////////
    private BluetoothAdapter btAdapter = null;
    private BluetoothSocket btSocket = null;
    public static String address = null;
    private static final UUID BTMODULEUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    public boolean activar;
    Handler bluetoothIn;
    final int handlerState = 0;
    Button prender, button, apagar, desconectar;
    private ConnectedThread MyConexionBT;
    ////////////////////////////////////////////////////////////////////////////////////////////////


    public AlertaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AlertaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AlertaFragment newInstance(String param1, String param2) {
        AlertaFragment fragment = new AlertaFragment();
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
        vista = inflater.inflate(R.layout.fragment_alerta, container, false);
        alerta = (ImageButton) vista.findViewById(R.id.ibtnAlerta);
        latitud = (EditText) vista.findViewById(R.id.etLatitud);
        longitud = (EditText) vista.findViewById(R.id.etLongitud);
        letraAlerta=(TextView) vista.findViewById(R.id.tvAlerta);


        //Permiso para gps
        int permissioCheck = ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION);
        if (permissioCheck == PackageManager.PERMISSION_DENIED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity()
                    , Manifest.permission.ACCESS_FINE_LOCATION)) {

            } else {
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            }
        }



        ObtenerCoordenadas();


        ///////////////////////////////////////////////////////////////////////////////////////////
        btAdapter = BluetoothAdapter.getDefaultAdapter();
        verificarBluetooth();



        Set<BluetoothDevice> pairedDeveicesList = btAdapter.getBondedDevices();

        for(BluetoothDevice pairedDevice : pairedDeveicesList){
            if(pairedDevice.getName().equals("HC-05")){

                address = pairedDevice.getAddress();
            }
        }
        ////////////////////////////////////////////////////////////////////////////////////////////



        alerta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                try {
                    //insertarAlerta("https://codeperez.000webhostapp.com/ModuloAlertaActivity.php");
                    //insertarAlerta("https://luchacontralaviolencia.000webhostapp.com/ModuloAlertaActivity.php");
                    insertarAlerta("http://192.168.1.103/violencia/ModuloAlertaActivity.php");
                    Thread.sleep(1*1000);
                    enviarMensaje();
                    //Thread.sleep(1*1000);
                    llamar();
                    activar = true;
                    onResume();
                    MyConexionBT.write("1");
                    Thread.sleep(8*1000);
                    MyConexionBT.write("0");
                    btSocket.close();
                    //Thread.sleep(1*1000);



                    FragmentManager fragmentManager=getActivity().getSupportFragmentManager();
                    FragmentTransaction transaction=fragmentManager.beginTransaction();
                    transaction.setReorderingAllowed(true);
                    transaction.replace(R.id.content,AlertaFragment.newInstance("",""));
                    transaction.commit();



                    ///////////////////////////////////////////////////////////////////////
                    ////////////////////////////////////////////////////////////////////////////////////
                } catch (InterruptedException | IOException e) {
                    e.printStackTrace();
                }








            }
        });


        letraAlerta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        return vista;
    }


    public void insertarAlerta(String URL) {
        slatitud = latitud.getText().toString().trim();
        slongitud = longitud.getText().toString().trim();
        SharedPreferences preferences = getActivity().getSharedPreferences("sesion", Context.MODE_PRIVATE);
        idUsuario = preferences.getString("idUsuario", "No encontrado");


        if (slatitud.isEmpty() && slongitud.isEmpty()) {
            Toast.makeText(getContext(), "LLene todo los campos", Toast.LENGTH_SHORT).show();
        } else {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Toast.makeText(getContext(), "Registro Alerta", Toast.LENGTH_SHORT).show();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getContext(), error.toString(), Toast.LENGTH_SHORT).show();
                }
            }) {
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {

                    Map<String, String> parametros = new Hashtable<String, String>();
                    parametros.put("idUsuario", idUsuario);
                    parametros.put("latitud", slatitud);
                    parametros.put("longitud", slongitud);
                    return parametros;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
            requestQueue.add(stringRequest);
        }

    }


    private Boolean verificarGPSOn() {
        String provider = Settings.Secure.getString(getContext().getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
        System.out.println("Provider contains=> " + provider);
        if (provider.contains("gps") || provider.contains("network")) {
            return true;
        } else {
            startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
            Toast.makeText(getContext(), "El gps debe estar encendido", Toast.LENGTH_SHORT).show();
            return false;
        }


    }


    public void ObtenerCoordenadas() {
        verificarGPSOn();
        LocationManager locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        LocationListener locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                //Toast.makeText(getActivity(), ""+location.getLatitude()+" "+location.getLongitude(), Toast.LENGTH_SHORT).show();
                latitud.setText("" + location.getLatitude());
                longitud.setText("" + location.getLongitude());
            }

            public void onStatusChanged(String provider, int status, Bundle extras) {
            }

            public void onProviderEnabled(String provider) {
            }

            public void onProviderDisabled(String provider) {
            }
        };
        int permissioCheck = ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);

    }


    //obtener coordenadas
    /*public void ObtenerCoordenadas(){
        LocationManager locationManager=(LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        LocationListener locationListener=new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                //Toast.makeText(getActivity(), ""+location.getLatitude()+" "+location.getLongitude(), Toast.LENGTH_SHORT).show();
                latitud.setText(""+location.getLatitude());
                longitud.setText(""+location.getLongitude());
            }
            public void onStatusChanged(String provider,int status,Bundle extras){}
            public void onProviderEnabled(String provider){}
            public void onProviderDisabled(String provider){}
        };
        int permissioCheck=ContextCompat.checkSelfPermission(getContext(),Manifest.permission.ACCESS_FINE_LOCATION);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,0,0,locationListener);

    }*/


    //Enviar mensje a los contactos seleccionados
    public void enviarMensaje() {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(getContext(), "administracion", null, 1);
        SharedPreferences preferences = getActivity().getSharedPreferences("sesion", Context.MODE_PRIVATE);
        nombres = preferences.getString("nombres", "No encontrado");
        primerApellido = preferences.getString("primerApellido", "No encontrado");
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();
        Cursor cursor = BaseDeDatos.rawQuery("SELECT * FROM contactos", null);
        ArrayList<ModelContactosActivity> lista = new ArrayList<ModelContactosActivity>();
        while (cursor.moveToNext()) {
            ModelContactosActivity cm = new ModelContactosActivity();
            cm.setId(cursor.getInt(0));
            cm.setContacto(cursor.getString(1));
            cm.setTelefono(cursor.getString(2));
            lista.add(cm);
        }
        String mensajeAyuda = "Hola soy " + nombres + " necesito tu ayuda ¡URGENTE!, mi ubicacion es: \n https://maps.google.com/?q="
                + latitud.getText().toString().trim() + "," + longitud.getText().toString().trim()
                + "\n App Violencia Contra la Mujer";

        for (int i = 0; i < lista.size(); i++) {
            //Envia mensajes a todos los contactos seleccionados
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(lista.get(i).getTelefono(), null, mensajeAyuda, null, null);
        }
        Toast.makeText(getContext(), "SmsEnviado", Toast.LENGTH_SHORT).show();
        Toast.makeText(getContext(), mensajeAyuda, Toast.LENGTH_SHORT).show();
    }


    //Realizar la llamada al 911
    public void llamar() {
        String phone = "tel:800140348";
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse(phone));
        startActivity(intent);
    }


    /////////////////////////////////////////////////////////////////////////////////////
    private BluetoothSocket createBluetoothSocket(BluetoothDevice device) throws IOException {

        return device.createRfcommSocketToServiceRecord(BTMODULEUUID);
    }

    private void verificarBluetooth() {
        if (btAdapter.isEnabled()) {


        } else {

            Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(intent, 1);
        }

    }


    public void onResume() {
        super.onResume();


        if (activar) {
            BluetoothDevice device = btAdapter.getRemoteDevice(address);

            try {
                btSocket = createBluetoothSocket(device);

            } catch (IOException e) {
                Toast.makeText(getActivity(), "La creacción del Socket fallo", Toast.LENGTH_LONG).show();
            }
            // Establece la conexión con el socket Bluetooth.
            try {

                btSocket.connect();
            } catch (IOException e) {
                try {
                    btSocket.close();
                } catch (IOException e2) {

                }
            }
            MyConexionBT = new ConnectedThread(btSocket);
            MyConexionBT.start();
        }

    }







    private class ConnectedThread extends Thread {
        private final InputStream mmInStream;
        private final OutputStream mmOutStream;

        public ConnectedThread(BluetoothSocket socket) {

            InputStream tmpIn = null;
            OutputStream tmpOut = null;
            try {
                tmpIn = socket.getInputStream();
                tmpOut = socket.getOutputStream();
            } catch (IOException e) {

            }
            mmInStream = tmpIn;
            mmOutStream = tmpOut;
        }

        public void run() {
            byte[] buffer = new byte[256];
            int bytes;

            // Se mantiene en modo escucha para determinar el ingreso de datos
            while (true) {
                try {

                    bytes = mmInStream.read(buffer);
                    String readMessage = new String(buffer, 0, bytes);
                    // Envia los datos obtenidos hacia el evento via handler
                    bluetoothIn.obtainMessage(handlerState, bytes, -1, readMessage).sendToTarget();
                } catch (IOException e) {
                    break;
                }
            }
        }

        //Envio de trama
        public void write(String input) {
            try {
                mmOutStream.write(input.getBytes());
            } catch (IOException e) {
                //si no es posible enviar datos se cierra la conexión
                Toast.makeText(getContext(), "La Conexión fallo", Toast.LENGTH_LONG).show();

                //getActivity().finish();
            }
        }

    }
    ////////////////////////////////////////////////////////////////////////////////////////////////







}



