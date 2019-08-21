package com.example.reborn30.maptecs;

import android.Manifest;
import android.content.Context;
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
   LatLngBounds bounds;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tecmap);
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            checkUserLocationPermission();
        }

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }
    private Context context;

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

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        CameraPosition cameraPosition = new CameraPosition.Builder().target(new LatLng(32.519913, -116.993464))
                .zoom(17.5f)
                .bearing(25).tilt(10).build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(this,R.raw.style_json));
        final LatLngBounds ADELAIDE = new LatLngBounds(
                new LatLng(32.5292812,-116.9899659), new LatLng(32.530833,-116.9867787));

// Constrain the camera target to the Adelaide bounds.
        mMap.setLatLngBoundsForCameraTarget(ADELAIDE);
        mMap.setMinZoomPreference(mMap.getCameraPosition().zoom);

        IUni U;
        U = new TEC();
        String university = "university",coffe="coffee",parking="parking";
        //Marker tres,est,cafe,labred,labinds,labioQ,labmat,labele,edi500,metalmec,labcomp;

        _setmap(googleMap,EDI600,"Edificio 600","Salones 601-620 \n AudioVisual 2 y 3 \n Banos",U.ed600(),university);
        _setmap(googleMap,EDI200,"Edificio 200","Salones 201-210 \n Banos",U.Quint(),university);
        _setmap(googleMap,Q,"Q","Salones Q101-Q103 \n Messas de ping pong",U.Q(),university);
        _setmap(googleMap,CAFE,"Cafeteria","Mesas Comida","explanada",coffe);
        _setmap(googleMap,EDIF500,"Edificio 500","Salones 500-510","na",university);
        _setmap(googleMap,METALMEC,"Metal Mecanica","Coordinacion de Metal-Mecanica","na",university);
        _setmap(googleMap,LABCOMP,"Laboratorio de Computos","Laboratorios de computadoras","na",university);
        _setmap(googleMap,LABELE,"Laboratorio de electromecanica","Laboratorios de electromecanica","NA",university);
        _setmap(googleMap,LABMAT,"Labotarios de matematicas","Investigacion de matematicas","na",university);
          _setmap(googleMap,LABBQI,"Laboratorio de bioquimica","Laboratorio 1","na",university);
          _setmap(googleMap,LABINDS,"Coordinacion de Industrial","Laboratiorio y salones","na",university);
          _setmap(googleMap,LABREDES,"Laboratorio de redes","Lab E y redes","na",university);
          _setmap(googleMap,EstaMes,"Estacionamienti `1", "Espacio de 34","na",parking);
          _setmap(googleMap,ed300,"Edificio 300","Salones 300-310","na",university);

//_ediseis.setInfoWindowAnchor(0,12);
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

                    // Getting reference to the TextView to set latitude

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
        //





        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            buildGoogleApiClient();
        mMap.setMyLocationEnabled(true);
          mMap.getMyLocation();
        }

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
