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
        com.google.android.gms.location.LocationListener, GoogleMap.OnMarkerClickListener {

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
    static  final  LatLng LABINDS = new LatLng(   32.529433, -116.988479);
    static  final  LatLng LABBQI = new LatLng(   32.529141, -116.987756);
    static  final  LatLng LABMAT = new LatLng(   32.529222, -116.988009);
    static  final  LatLng LABELE = new LatLng(   32.530602, -116.986852);
    static  final  LatLng EDIF500 = new LatLng(   32.530387, -116.987206);
    static  final  LatLng METALMEC = new LatLng(   32.530466, -116.986322);
    static  final  LatLng LABCOMP = new LatLng(   32.529862, -116.986923);
    static  final  LatLng EDI200 = new LatLng(   32.529700, -116.988352);
    static  final  LatLng Q = new LatLng(   32.529872, -116.987461);
    static  final LatLng EDI600 = new LatLng(32.531153, -116.986074);

    private Marker salonesq,salones_500,LaboratioC;
    Button centrar;
    @Override
    public void onMapReady(GoogleMap googleMap) {
        final String modulo = getIntent().getStringExtra("Nombre");
        mMap = googleMap;
        switch (modulo){
            case "Tomas":
                ubicarmapa(32.52928,-116.9882325,17.9f,25,10,32.526946, -116.986892,32.531263, -116.985820);
                ubicacionesTomas(mMap);

                break;
            case "Otay":
                ubicarmapa(32.5349911,-116.9281903,17.5f,25,10,32.5349911,-116.9281903,32.5355519,-116.9272247);
                break;
        }
        mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(this,R.raw.style_json));
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
//        });
        googleMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
            // Use default InfoWindow frame
            @Override
            public View getInfoWindow(Marker arg0) {
                return null;
            }
            // Defines the contents of the InfoWindow
            @Override
            public View getInfoContents(Marker arg0) {
                View v = null;
                try {
                    // Getting view from the layout file info_window_layout
                    v = getLayoutInflater().inflate(R.layout.custom_info_window, null);
                    TextView Nombre = (TextView) v.findViewById(R.id.nameTxt);
                    TextView Info = (TextView) v.findViewById(R.id.addressTxt);
                    ImageView img = (ImageView) v.findViewById(R.id.clientPic);
                    int imageId = getResources().getIdentifier(String.valueOf(arg0.getTag()),
                            "drawable", getPackageName());
                    img.setImageResource(imageId);
                    Nombre.setText(arg0.getTitle());
                    Info.setText(arg0.getSnippet());
                } catch (Exception ev) {
                    System.out.print(ev.getMessage());
                }
                return v;
            }
        });
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            buildGoogleApiClient();
        mMap.setMyLocationEnabled(true);
          mMap.getMyLocation();
        }
    }
    void ubicarmapa(double x1,double x2,float zoom,int orientacion,int inclinacion,double restriccion1,double restriccion2,double restriccion3,double restriccion4){
        CameraPosition ubicacion = new CameraPosition.Builder().target(new LatLng(x1, x2))
                .zoom(zoom)
                .bearing(orientacion).tilt(inclinacion).build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(ubicacion));
        final LatLngBounds ADELAIDE = new LatLngBounds(
                new LatLng(restriccion1,restriccion2), new LatLng(restriccion3,restriccion4));
        mMap.setLatLngBoundsForCameraTarget(ADELAIDE);
        mMap.setMinZoomPreference(3.0f);
    }
    void ubicacionesTomas(GoogleMap googleMap){
        IUni U;
        U = new TEC();
        googleMap.setOnMarkerClickListener(this);
        String university = "university",coffe="coffee",parking="parking";
        salonesq = googleMap.addMarker(new MarkerOptions().position(Q).title("Salones Q").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
        salones_500= googleMap.addMarker(new MarkerOptions().position(EDIF500).title("5000").snippet("spofssr").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
        LaboratioC = googleMap.addMarker(new MarkerOptions().position(LABCOMP).title("Laboratorio de computo").snippet("hey").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
        _setmap(googleMap,EDI600,"Edificio 600","Salones 601-620 \n AudioVisual 2 y 3 \n Banos",U.ed600(),university);
        _setmap(googleMap,EDI200,"Edificio 200","Salones 201-210 \n Banos",U.Quint(),university);
     //_setmap(googleMap,Q,"Salones Q","Salones Q101-Q103 \n Messas de ping pong",U.Q(),university);
        _setmap(googleMap,CAFE,"Cafeteria","Mesas Comida","explanada",coffe);
     //_setmap(googleMap,EDIF500,"Edificio 500","Salones 500-510","na",university);
        _setmap(googleMap,METALMEC,"Metal Mecanica","Coordinacion de Metal-Mecanica","na",university);
      //  _setmap(googleMap,LABCOMP,"Laboratorio de Computos","Laboratorios de computadoras","na",university);
        _setmap(googleMap,LABELE,"Laboratorio de electromecanica","Laboratorios de electromecanica","_electro",university);
        _setmap(googleMap,LABMAT,"Labotarios de matematicas","Investigacion de matematicas","na",university);
        _setmap(googleMap,LABBQI,"Laboratorio de bioquimica","Laboratorio 1","na",university);
        _setmap(googleMap,LABINDS,"Coordinacion de Industrial","Laboratiorio y salones","na",university);
        _setmap(googleMap,LABREDES,"Laboratorio de redes","Lab E y redes","na",university);
        _setmap(googleMap,EstaMes,"Estacionamiento `1", "Espacio de 34","na",parking);
        _setmap(googleMap,ed300,"Edificio 300","Salones 300-310","na",university);
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

    @Override
    public boolean onMarkerClick(final Marker marker) {

        if(marker.equals(salonesq)){
             informacion.setText("Salones Q101-Q103 \n Messas de ping pong");
             pre.setImageResource(R.drawable.salonesq);
        }
        if(marker.equals(salones_500)){
            informacion.setText("Salones 500-510");
            pre.setImageResource(R.drawable.quint);
        }
        if(marker.equals(LaboratioC)){
            Intent i = new Intent(getBaseContext(),LugaresGrandes.class);
            startActivity(i);
        }
        return false;
    }
}
