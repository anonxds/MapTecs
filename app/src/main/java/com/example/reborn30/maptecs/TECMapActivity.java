package com.example.reborn30.maptecs;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.reborn30.maptecs.ListaDeEscuelas.IUni;
import com.example.reborn30.maptecs.ListaDeEscuelas.TEC;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class TECMapActivity extends FragmentActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        com.google.android.gms.location.LocationListener,BottomFilter.IbottomFilter {


    private GoogleMap mMap;
    private GoogleApiClient googleApiClient;
    private LocationRequest locationRequest;
    private Location lastLocation;
    private Marker currentUserLocationMarker;
    private static final  int Request_User_location_code=99;

    static  final  LatLng EstaMes = new LatLng(   32.528844, -116.988736);
    static final LatLng estaAlum = new LatLng(32.531239, -116.987254);
    static  final LatLng estaAlum2 = new LatLng(32.529784, -116.986364);


    static  final  LatLng _labredes = new LatLng(32.529155, -116.988737);
    static  final  LatLng CAFE = new LatLng(32.528960, -116.988229);
    static  final  LatLng _editorial = new LatLng( 32.529522, -116.987973);
    static  final  LatLng _labmatematicas = new LatLng(32.529178, -116.987991);

    static final LatLng _edi100 = new LatLng(32.529707, -116.988343);
    static  final  LatLng _edi200 = new LatLng(   32.529960, -116.988263);
    static final LatLng _edi300 = new LatLng(32.530247, -116.988129);
    static final LatLng _edi400 = new LatLng(32.530240, -116.986764);
    static  final  LatLng _edi500 = new LatLng(   32.530365, -116.987170);
    static  final LatLng _edi600 = new LatLng(32.531195, -116.986014);
    static  final  LatLng _edifQ = new LatLng(   32.529786, -116.987486);

    static  final  LatLng _sistemasC = new LatLng(32.529786, -116.986840);
    static  final  LatLng _metalmecanicaC = new LatLng(32.530460, -116.986357);
    static  final  LatLng _industrialC = new LatLng(32.529460, -116.988534);
    static  final  LatLng _bioquimicaC = new LatLng(32.530950, -116.985959);


    static  final  LatLng _labmetalmecanica = new LatLng(32.530589, -116.986858);
    static  final  LatLng _labioqumica = new LatLng(   32.530869, -116.986618);

    static  final  LatLng _calafornix = new LatLng(32.530603, -116.987788);
    static  final  LatLng _biblioteca = new LatLng(32.530727, -116.987241);
    static  final  LatLng _audiovisual = new LatLng(32.530277, -116.987532);

     static  final LatLng _biosalones = new LatLng(32.530736, -116.986319);

    private Marker
            estaAlumno,
            estaAlumn01,
            estacionamiento,
            calafornix,
            biblioteca,
            salones_100,
            salones_200,
            salones_300,
            salones_400,
            salones_500,
            salones_600,
            salones_q,
            salones_bio,
            cafeteria,
            papeleria,
            audiovisual,
            metalmecanicaC,
            industrialC,
            sistemasC,
            bioquimicaC,
            labmatematica,
            labredes,
            labelectromecanica,
            labbioquimica,
            lastOpenned = null;
    TextView _idfiltrar;
    Button btnfiltrar,_btnbuscar;
    ListView _filtrar;
    String data = "";
    Bundle bundle = new Bundle();
    private static int temporizador = 4000;
     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tecmap);
         final LoadMenu bts = new LoadMenu();
         bts.show(getSupportFragmentManager(),"LoadMenu");
         Handler handler = new Handler();
         
         Runnable runnable = new Runnable() {
             @Override
             public void run() {
                 //Second fragment after 5 seconds appears
              bts.dismiss();
                 getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

             }
         };

         handler.postDelayed(runnable, 10000);
         _filtrar = findViewById(R.id._filtracion);
        _idfiltrar = findViewById(R.id.idfiltro);
        btnfiltrar = findViewById(R.id.btnfiltros);
        _btnbuscar = findViewById(R.id.btnbuscar);
         if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            checkUserLocationPermission();
        }
         // Obtain the SupportMapFragment and get notified when the map is ready to be used.
         SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                 .findFragmentById(R.id.map);
         mapFragment.getMapAsync(this);
         btnfiltrar.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 BottomFilter bt = new BottomFilter();
                 bt.show(getSupportFragmentManager(),"BottomFilter");
             }
         });
         _btnbuscar.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent i = new Intent(getBaseContext(),LegendActivity.class);
                 startActivity(i);
             }
         });

    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        final String modulo = getIntent().getStringExtra("Nombre");
        mMap = googleMap;
        mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(this,R.raw.style_json));

        switch (modulo){
            case "Tomas":
                ubicarmapa(32.529213, -116.987736,16.9f,25,10,32.526946, -116.986892,32.531263, -116.985820);
                LatLngBounds AUSTRALIA = new LatLngBounds(new LatLng(32.528598, -116.988322), new LatLng( 32.531176, -116.984685));
                mMap.setLatLngBoundsForCameraTarget(AUSTRALIA);
                ubicacionesTomas(mMap);
                //  mMap.getUiSettings().setScrollGesturesEnabled(false);
                break;
            case "Otay":

                ubicarmapa( 32.535236, -116.926399,16.9f,25,10,32.526946, -116.986892,32.531263, -116.985820);
                LatLngBounds JAPON = new LatLngBounds(new LatLng(32.531835, -116.929663), new LatLng( 32.538346, -116.922500));
                mMap.setLatLngBoundsForCameraTarget(JAPON);

                break;
        }
        Log.d("Mensage",String.valueOf(_idfiltrar));
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            public boolean onMarkerClick(Marker marker) {
                // Check if there is an open info window
                if (lastOpenned != null) {
                    // Close the info window
                    lastOpenned.hideInfoWindow();
                    showInfo(marker);
                    // Is the marker the same marker that was already open
                    if (lastOpenned.equals(marker)) {
                        // Nullify the lastOpenned object
                        lastOpenned = null;
                        // Return so that the info window isn't openned again
                        return true;
                    }
                }
                // Open the info window for the marker
                marker.showInfoWindow();
                // Re-assign the last openned such that we can close it later
                lastOpenned = marker;

                // Event was handled by our code do not launch default behaviour.
                return true;
            }
        });
      _idfiltrar.addTextChangedListener(new TextWatcher() {
          @Override
          public void beforeTextChanged(CharSequence s, int start, int count, int after) {

          }

          @Override
          public void onTextChanged(CharSequence s, int start, int before, int count) {
              filtrarPor(s);
          }
          @Override
          public void afterTextChanged(Editable s) {

          }
      });
        mMap.setMinZoomPreference(mMap.getCameraPosition().zoom);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            buildGoogleApiClient();
            mMap.setMyLocationEnabled(true);
            mMap.getMyLocation();
        }
    }
    //
   void cargar(){

   }
    //
    void showInfo(Marker marker){

         if(marker.equals(salones_100)){
            bundle.putString("id", "100");
        }
        if (marker.equals(salones_200)){
            bundle.putString("id", "200");
        }
        if (marker.equals(salones_300)){
             bundle.putString("id", "300");
        }
        if (marker.equals(salones_400)){
            bundle.putString("id", "400");
        }
        if(marker.equals(salones_500)){
             bundle.putString("id", "500");
        }
        if(marker.equals(salones_600)){
            bundle.putString("id", "600");
        }
        if (marker.equals(salones_q)){
         bundle.putString("id", "Q");
        }

        if(marker.equals(sistemasC)){
            bundle.putString("id","sc");
        }
        if(marker.equals(metalmecanicaC)){
            bundle.putString("id","mc");
        }
        if (marker.equals(bioquimicaC)){
           bundle.putString("id", "bc");
        }
        if (marker.equals(industrialC)){
            bundle.putString("id", "ic");
        }
        if (marker.equals(labelectromecanica)){
            bundle.putString("id","metal");
        }
        if (marker.equals(labredes)){
               bundle.putString("id", "redes");
        }
        if(marker.equals(labmatematica)){
              bundle.putString("id", "mate");
        }
        if(marker.equals(labbioquimica)){
            bundle.putString("id","bio");
        }
        if (marker.equals(papeleria)){
           bundle.putString("id", "papel");

        }
        if (marker.equals(audiovisual)){
             bundle.putString("id", "audio");
        }

        if(marker.equals(cafeteria)){
            bundle.putString("id","cafeteria");
        }
        if(marker.equals(calafornix)){
            bundle.putString("id","teatro");
        }
        if(marker.equals(biblioteca)){
            bundle.putString("id","bioblioteca");

        }

        InfopanelActivity bts = new InfopanelActivity();
        bts.setArguments(bundle);
        bts.show(getSupportFragmentManager(),"InfopanelActivity");
    }

    void filtrarPor(CharSequence s){
         String Data = String.valueOf(s);
         switch (Data){
             case "Todo":
                 estaAlumno.setVisible(true);
                 estaAlumn01.setVisible(true);
                 calafornix.setVisible(true);
                 salones_q.setVisible(true);
                 salones_500.setVisible(true);
                 sistemasC.setVisible(true);
                 labelectromecanica.setVisible(true);
                 salones_200.setVisible(true);
                 salones_300.setVisible(true);
                 salones_600.setVisible(true);
                 cafeteria.setVisible(true);
                 estacionamiento.setVisible(true);
                 metalmecanicaC.setVisible(true);
                 industrialC.setVisible(true);
                 bioquimicaC.setVisible(true);
                 labmatematica.setVisible(true);
                 labredes.setVisible(true);
                 biblioteca.setVisible(true);
                 break;
             case "Salones":
                 salones_q.setVisible(true);
                 salones_100.setVisible(true);
                 salones_500.setVisible(true);
                 salones_200.setVisible(true);
                 salones_300.setVisible(true);
                 salones_400.setVisible(true);
                 salones_600.setVisible(true);
                 estaAlumno.setVisible(false);
                 estaAlumn01.setVisible(false);
                 calafornix.setVisible(false);
                 sistemasC.setVisible(false);
                 labelectromecanica.setVisible(false);
                 cafeteria.setVisible(false);
                 estacionamiento.setVisible(false);
                 metalmecanicaC.setVisible(false);
                 industrialC.setVisible(false);
                 bioquimicaC.setVisible(false);
                 labmatematica.setVisible(false);
                 labredes.setVisible(false);
                 biblioteca.setVisible(false);
                 break;
             case "Coordinaciones":
                 estaAlumno.setVisible(false);
                 estaAlumn01.setVisible(false);
                 calafornix.setVisible(false);
                 salones_q.setVisible(false);
                 salones_500.setVisible(false);
                 sistemasC.setVisible(true);
                 labelectromecanica.setVisible(false);
                 salones_200.setVisible(false);
                 salones_300.setVisible(false);
                 salones_600.setVisible(false);
                 cafeteria.setVisible(false);
                 estacionamiento.setVisible(false);
                 metalmecanicaC.setVisible(true);
                 industrialC.setVisible(true);
                 bioquimicaC.setVisible(true);
                 labmatematica.setVisible(false);
                 labredes.setVisible(false);
                 biblioteca.setVisible(false);
                 break;
             case "Oficina":
                 break;
         }
    }

    void ubicarmapa(double x1,double x2,float zoom,int orientacion,int inclinacion,double restriccion1,double restriccion2,double restriccion3,double restriccion4){
        CameraPosition ubicacion = new CameraPosition.Builder().target(new LatLng(x1, x2))
                .zoom(zoom)
                .bearing(orientacion).tilt(inclinacion).build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(ubicacion));
    }
    void ubicacionesTomas(GoogleMap googleMap){
      //  googleMap.setOnMarkerClickListener(this);
        salones_100= googleMap.addMarker(new MarkerOptions().position(_edi100).title("Edificio 100").snippet("Salones").icon(BitmapDescriptorFactory.fromResource(R.drawable.enterprise)));
        salones_200= googleMap.addMarker(new MarkerOptions().position(_edi200).title("Edificio 200").snippet("Salones/Aulamagna").icon(BitmapDescriptorFactory.fromResource(R.drawable.enterprise)));
        salones_300= googleMap.addMarker(new MarkerOptions().position(_edi300).title("Edificio 500").snippet("Salones").icon(BitmapDescriptorFactory.fromResource(R.drawable.enterprise)));
        salones_400= googleMap.addMarker(new MarkerOptions().position(_edi400).title("Edificio 400").snippet("Salones").icon(BitmapDescriptorFactory.fromResource(R.drawable.enterprise)));
        salones_500= googleMap.addMarker(new MarkerOptions().position(_edi500).title("Edificio 500").snippet("Salones").icon(BitmapDescriptorFactory.fromResource(R.drawable.enterprise)));
        salones_600= googleMap.addMarker(new MarkerOptions().position(_edi600).title("Edificio 600").snippet("Salones").icon(BitmapDescriptorFactory.fromResource(R.drawable.enterprise)));
        salones_q= googleMap.addMarker(new MarkerOptions().position(_edifQ).title("Edificio Q").snippet("Salones").icon(BitmapDescriptorFactory.fromResource(R.drawable.enterprise)));
        salones_bio= googleMap.addMarker(new MarkerOptions().position(_biosalones).title("Bioquimica").snippet("Salones").icon(BitmapDescriptorFactory.fromResource(R.drawable.enterprise)));

        labredes= googleMap.addMarker(new MarkerOptions().position(_labredes).title("Laboratorio redes").snippet("Laboratiorio.Oficinas").icon(BitmapDescriptorFactory.fromResource(R.drawable.enterprise)));
        cafeteria= googleMap.addMarker(new MarkerOptions().position(CAFE).title("Cafeteria").snippet("Cafeteria/Oficinas").icon(BitmapDescriptorFactory.fromResource(R.drawable.cafeteria)));
        labmatematica= googleMap.addMarker(new MarkerOptions().position(_labmatematicas).title("Oficinas").snippet("Ofinica/Laboratorio").icon(BitmapDescriptorFactory.fromResource(R.drawable.enterprise)));
        papeleria= googleMap.addMarker(new MarkerOptions().position(_editorial).title("Papeleria").snippet("Editorial/Enfermeria/Oficinas").icon(BitmapDescriptorFactory.fromResource(R.drawable.enterprise)));
        audiovisual= googleMap.addMarker(new MarkerOptions().position(_audiovisual).title("Audiovisual").snippet("Audiovisual/Oficinas").icon(BitmapDescriptorFactory.fromResource(R.drawable.enterprise)));

        calafornix= googleMap.addMarker(new MarkerOptions().position(_calafornix).title("Calafornix").snippet("Teatro").icon(BitmapDescriptorFactory.fromResource(R.drawable.masks)));
        biblioteca= googleMap.addMarker(new MarkerOptions().position(_biblioteca).title("Biblioteca").snippet(" ").icon(BitmapDescriptorFactory.fromResource(R.drawable.book)));

       labelectromecanica = googleMap.addMarker(new MarkerOptions().position(_labmetalmecanica).title("Laboratorio Electromecanica").snippet("Mecanica").icon(BitmapDescriptorFactory.fromResource(R.drawable.gears)));
       labbioquimica = googleMap.addMarker(new MarkerOptions().position(_labioqumica).title("Laboratiorio Bioquimica").snippet("Micrologia").icon(BitmapDescriptorFactory.fromResource(R.drawable.icobio)));

        sistemasC= googleMap.addMarker(new MarkerOptions().position(_sistemasC).title("Coordinacion Sistemas computacionales").snippet("Laboratiorio/Salones/Oficinas").icon(BitmapDescriptorFactory.fromResource(R.drawable.coor)));
        industrialC= googleMap.addMarker(new MarkerOptions().position(_industrialC).title("Coordinacion Industrial").snippet("Laboratiorios/salones/Oficinas").icon(BitmapDescriptorFactory.fromResource(R.drawable.coor)));
        bioquimicaC= googleMap.addMarker(new MarkerOptions().position(_bioquimicaC).title("Coordinacion bioquimica").snippet("Oficinas").icon(BitmapDescriptorFactory.fromResource(R.drawable.coor)));
        metalmecanicaC= googleMap.addMarker(new MarkerOptions().position(_metalmecanicaC).title("Coordinacion Metalmecanica").snippet("Oficinas/Salones").icon(BitmapDescriptorFactory.fromResource(R.drawable.coor)));


        estacionamiento = googleMap.addMarker(new MarkerOptions().position(EstaMes).title("Estacionamiento de Maestros").icon(BitmapDescriptorFactory.fromResource(R.drawable.park)));
        estaAlumno = googleMap.addMarker(new MarkerOptions().position(estaAlum).title("Estacionamiento de Alumno").icon(BitmapDescriptorFactory.fromResource(R.drawable.park)));
        estaAlumn01 = googleMap.addMarker(new MarkerOptions().position(estaAlum2).title("Estacionamiento de Alumno").icon(BitmapDescriptorFactory.fromResource(R.drawable.park)));

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case Request_User_location_code: if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED){

                        buildGoogleApiClient();
                    mMap.animateCamera(CameraUpdateFactory.zoomBy(16f));

                    mMap.setMyLocationEnabled(true);
                }
            }
            else {
                Toast.makeText(this,"denied",Toast.LENGTH_SHORT).show();
            }
        }
    }

    public boolean checkUserLocationPermission(){
        if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.ACCESS_FINE_LOCATION)){
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},Request_User_location_code);
            }
            else {
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},Request_User_location_code);
            }
            return false;
        }
        else
        {
            return true;
        }

    }
    protected synchronized void buildGoogleApiClient(){
        googleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        googleApiClient.connect();
    }

    @Override
    public void onLocationChanged(Location location) {
        lastLocation = location;
        if(currentUserLocationMarker != null){
            currentUserLocationMarker.remove();
        }
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title("Mi ubicacion");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));

        currentUserLocationMarker = mMap.addMarker(markerOptions);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        CameraPosition cameraPosition = new CameraPosition.Builder().target(new LatLng(32.5299798,-116.9874806))
                .zoom(17.5f)
                .bearing(25).tilt(10).build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));



        if(googleApiClient != null){
            LocationServices.FusedLocationApi.removeLocationUpdates(googleApiClient, this);
        }
    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {
        locationRequest = new LocationRequest();
        locationRequest.setInterval(1100);
        locationRequest.setFastestInterval(1100);
        locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, this);
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }


    @Override
    public void onClickList(String item) {
        _idfiltrar.setText(item);
        Log.d("mensage",data);
    }
}
