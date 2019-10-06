package com.example.reborn30.maptecs;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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

public class TECMapActivity extends FragmentActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        com.google.android.gms.location.LocationListener {

    private GoogleMap mMap;
    private GoogleApiClient googleApiClient;
    private LocationRequest locationRequest;
    private Location lastLocation;
    private Marker currentUserLocationMarker;
    private static final  int Request_User_location_code=99;
    TextView informacion;
    ImageView pre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tecmap);
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            checkUserLocationPermission();
        }
        informacion = findViewById(R.id.txtinfo);
        pre = findViewById(R.id.foto);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }
    static final LatLng ed300 = new LatLng(32.530269, -116.988077);
    static  final  LatLng EstaMes = new LatLng(   32.528844, -116.988736);
    static  final  LatLng CAFE = new LatLng(   32.528954, -116.988263);
    static  final  LatLng LABREDES = new LatLng(   32.529267, -116.988568);
    static  final  LatLng LABINDS = new LatLng(   32.529452, -116.988538);
    static  final  LatLng LABBQI = new LatLng(   32.529141, -116.987756);
    static  final  LatLng LABMAT = new LatLng(   32.529222, -116.988009);
    static  final  LatLng LABELE = new LatLng(   32.530602, -116.986852);
    static  final  LatLng EDIF500 = new LatLng(   32.530387, -116.987206);
    static  final  LatLng METALMEC = new LatLng(   32.530402, -116.986365);
    static  final  LatLng LABCOMP = new LatLng(   32.529734, -116.986889);
    static  final  LatLng EDI200 = new LatLng(   32.529700, -116.988352);
    static  final  LatLng Q = new LatLng(   32.529872, -116.987461);
    static  final LatLng EDI600 = new LatLng(32.531153, -116.986074);
    static  final LatLng coorbioq = new LatLng(32.530933, -116.985960);
    static  final LatLng biblioteca = new LatLng(32.530722, -116.987242);
     static  final LatLng teatro = new LatLng(32.530652, -116.987800);
     static final LatLng estaAlum = new LatLng(32.531239, -116.987254);
     static  final LatLng estaAlum2 = new LatLng(32.529784, -116.986364);
    Marker lastOpenned = null;

    private Marker estaAlumno,estaAlumn01, teatr,salonesq,salones_500,LaboratioC,electromecanica,salones_200,salones_300,salones_600,cafeteria,estacionamiento,coormetalmec,coordinacionind,coorbio,biblio;
    Button centrar;
    @Override
    public void onMapReady(GoogleMap googleMap) {
        final String modulo = getIntent().getStringExtra("Nombre");
        mMap = googleMap;
        mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(this,R.raw.style_json));

        switch (modulo){
            case "Tomas":
                ubicarmapa(32.528821, -116.986893,16.9f,25,10,32.526946, -116.986892,32.531263, -116.985820);
                ubicacionesTomas(mMap);

                //  mMap.getUiSettings().setScrollGesturesEnabled(false);
                break;
            case "Otay":
              //  ubicarmapa(32.5349911,-116.9281903,17.5f,25,10,32.5349911,-116.9281903,32.5355519,-116.9272247);
                CameraPosition ubicacion = new CameraPosition.Builder().target(new LatLng(32.529735, -116.987606))
                        .zoom(18.5f)
                        .bearing(25).tilt(10).build();
                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(ubicacion));

                break;
        }

        mMap.setMinZoomPreference(mMap.getCameraPosition().zoom);

//        centrar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                switch (modulo){
//                    case "Tomas":
//                        ubicarmapa(32.519913, -116.993464,17.5f,25,10,32.5292812,-116.9899659,32.530833,-116.9867787);
//                        ubicacionesTomas(mMap);
//                        break;
//                    case "Otay":
//
//                        break;
//                }
//            }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            buildGoogleApiClient();
        mMap.setMyLocationEnabled(true);
          mMap.getMyLocation();
        }
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            public boolean onMarkerClick(Marker marker) {
                // Check if there is an open info window
                if (lastOpenned != null) {
                    // Close the info window
                    lastOpenned.hideInfoWindow();

                    if(marker.equals(salonesq)){
                        informacion.setText("Salones Q101-Q103 \n Messas de ping pong");
                        pre.setImageResource(R.drawable.salonesq);
                    }
                    if(marker.equals(salonesq)){
                        informacion.setText("Salones Q101-Q103 \n Messas de ping pong");
                        pre.setImageResource(R.drawable.salonesq1);
                    }
                    if(marker.equals(salones_500)){
                        informacion.setText("Salones 500-510");
                        pre.setImageResource(R.drawable.quint);
                    }
                    if(marker.equals(LaboratioC)){
                        Intent i = new Intent(getBaseContext(),LugaresGrandes.class);
                        startActivity(i);
                    }
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

    }
    void ubicarmapa(double x1,double x2,float zoom,int orientacion,int inclinacion,double restriccion1,double restriccion2,double restriccion3,double restriccion4){
        CameraPosition ubicacion = new CameraPosition.Builder().target(new LatLng(x1, x2))
                .zoom(zoom)
                .bearing(orientacion).tilt(inclinacion).build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(ubicacion));
    }


    void ubicacionesTomas(GoogleMap googleMap){
        IUni U;
        U = new TEC();
      //  googleMap.setOnMarkerClickListener(this);
        String university = "university",coffe="coffee",parking="parking";
        salonesq = googleMap.addMarker(new MarkerOptions().position(Q).title("Salones Q").icon(BitmapDescriptorFactory.fromResource(R.drawable.enterprise)));
        salones_500= googleMap.addMarker(new MarkerOptions().position(EDIF500).title("Edificio 500").snippet("spofssr").icon(BitmapDescriptorFactory.fromResource(R.drawable.enterprise)));
        LaboratioC = googleMap.addMarker(new MarkerOptions().position(LABCOMP).title("Laboratorio de computo").snippet("hey").icon(BitmapDescriptorFactory.fromResource(R.drawable.sisycomp1)));
        electromecanica = googleMap.addMarker(new MarkerOptions().position(LABELE).title("Laboratorio de Electromecanica/Mecanica").snippet("Toca de nuevo para mas info").icon(BitmapDescriptorFactory.fromResource(R.drawable.gear)));
        salones_200 = googleMap.addMarker(new MarkerOptions().position(EDI200).title("Edificio 200").icon(BitmapDescriptorFactory.fromResource(R.drawable.enterprise)));
        salones_300 = googleMap.addMarker(new MarkerOptions().position(ed300).title("Edificio 300").icon(BitmapDescriptorFactory.fromResource(R.drawable.enterprise)));
        salones_600 = googleMap.addMarker(new MarkerOptions().position(EDI600).title("Edificio 600").icon(BitmapDescriptorFactory.fromResource(R.drawable.enterprise)));
        cafeteria = googleMap.addMarker(new MarkerOptions().position(CAFE).title("Cafeteria").icon(BitmapDescriptorFactory.fromResource(R.drawable.cafe)));
        estacionamiento = googleMap.addMarker(new MarkerOptions().position(EstaMes).title("Estacionamiento de Maestros").icon(BitmapDescriptorFactory.fromResource(R.drawable.park)));
        coormetalmec = googleMap.addMarker(new MarkerOptions().position(METALMEC).title("Coordinacion Metal-Mecanica").icon(BitmapDescriptorFactory.fromResource(R.drawable.metalmec)));
        coordinacionind = googleMap.addMarker(new MarkerOptions().position(LABINDS).title("Coordinacion Industrial").icon(BitmapDescriptorFactory.fromResource(R.drawable.industrial)));
        coorbio =  googleMap.addMarker(new MarkerOptions().position(coorbioq).title("Coordinacion Bioquimica").icon(BitmapDescriptorFactory.fromResource(R.drawable.bioquni)));
        biblio =googleMap.addMarker(new MarkerOptions().position(biblioteca).title("Biblioteca").icon(BitmapDescriptorFactory.fromResource(R.drawable.book)));
         teatr = googleMap.addMarker(new MarkerOptions().position(teatro).title("Teatro calafonix").icon(BitmapDescriptorFactory.fromResource(R.drawable.masks)));
          estaAlumno = googleMap.addMarker(new MarkerOptions().position(estaAlum).title("Estacionamiento de Alumno").icon(BitmapDescriptorFactory.fromResource(R.drawable.park)));
        estaAlumn01 = googleMap.addMarker(new MarkerOptions().position(estaAlum2).title("Estacionamiento de Alumno").icon(BitmapDescriptorFactory.fromResource(R.drawable.park)));


        _setmap(googleMap,LABMAT,"Labotarios de matematicas","Investigacion de matematicas","na",university);
        _setmap(googleMap,LABREDES,"Laboratorio de redes","Lab E y redes","na",university);
    }
    public void _setmap(GoogleMap googleMap,LatLng posion,String titulo,String info,String mini,String icon){
    int id = getResources().getIdentifier(icon, "drawable", getPackageName());
        Marker dos =   googleMap.addMarker(new MarkerOptions()
            .position(posion)
            .title(titulo)
            .snippet(info)
            .icon(BitmapDescriptorFactory.fromResource(id)));
    dos.setTag(mini);
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
        markerOptions.title("current");
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



}
