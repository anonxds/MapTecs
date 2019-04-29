package com.example.reborn30.maptecs;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

//public class TECMapActivity extends FragmentActivity implements OnMapReadyCallback {
//
//    private GoogleMap mMap;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_tecmap);
//        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
//        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
//                .findFragmentById(R.id.map);
//        mapFragment.getMapAsync(this);
//
//    }
//
//
//    /**
//     * Manipulates the map once available.
//     * This callback is triggered when the map is ready to be used.
//     * This is where we can add markers or lines, add listeners or move the camera. In this case,
//     * we just add a marker near Sydney, Australia.
//     * If Google Play services is not installed on the device, the user will be prompted to install
//     * it inside the SupportMapFragment. This method will only be triggered once the user has
//     * installed Google Play services and returned to the app.
//     */
//    @Override
//    public void onMapReady(GoogleMap googleMap) {
//        mMap = googleMap;
//
//        // Add a marker in Sydney and move the camera
////        LatLng sydney = new LatLng(-34, 151);
////        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
////        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
////         LatLngBounds AUSTRALIA = new LatLngBounds(
////                new LatLng(-20, 113), new LatLng(-10, 154));
////
////// Set the camera to the greatest possible zoom level that includes the
////// bounds
////        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(AUSTRALIA.getCenter(), 10));
//        mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(this,R.raw.style_json));
//
//        LatLngBounds ADELAIDE = new LatLngBounds(
//                new LatLng(32.5296806,-116.9877132), new LatLng(32.5296806,-116.9877132));
//// Constrain the camera target to the Adelaide bounds.
//        mMap.setLatLngBoundsForCameraTarget(ADELAIDE);
//
//        LatLng TEC = new LatLng(32.5296806,-116.9877132);
//        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(TEC,17));
//        CameraPosition cameraPosition = new CameraPosition.Builder().target(TEC)
//                .zoom(17.6f)
//
//                .bearing(27).tilt(30).build();
//
//        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
//        mMap.getUiSettings(). setZoomGesturesEnabled(false);
////        BitmapDescriptor map =  BitmapDescriptorFactory.fromResource(R.drawable.niebla2);
////        GroundOverlayOptions groundOverlay = new GroundOverlayOptions()
////                .image(map)
////                .position(TEC, 17.6f)
////                .transparency(0.1f)
////                .anchor(0.5f, 0.5f);
////        mMap.addGroundOverlay(groundOverlay);
//        LatLng officina = new LatLng(32.529169, -116.987342);
//Marker off =  mMap.addMarker(new MarkerOptions().position(officina).title("Officina").icon(BitmapDescriptorFactory.fromResource(R.drawable.zacatecas)).draggable(false));
//off.showInfoWindow();
//        LatLng trecientos = new LatLng(32.529961, -116.988182);
//     Marker dosientos=   mMap.addMarker(new MarkerOptions().position(trecientos).title("Salones 200").icon(BitmapDescriptorFactory.fromResource(R.drawable.mexico)));
//dosientos.showInfoWindow();
//       LatLng verdaderotreciento = new LatLng(32.530241, -116.988070);
//        Marker treciento =  mMap.addMarker(new MarkerOptions().position(verdaderotreciento).title("Salones 300").icon(BitmapDescriptorFactory.fromResource(R.drawable.valle)));
//treciento.showInfoWindow();
//        LatLng salonesQ = new LatLng(32.529815, -116.987481);
//      Marker salones = mMap.addMarker(new MarkerOptions().position(salonesQ).title("Salones Q").icon(BitmapDescriptorFactory.fromResource(R.drawable.uno)));
//  //      salones.showInfoWindow();
//    }
//}

public class TECMapActivity extends FragmentActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        com.google.android.gms.location.LocationListener {

    private GoogleMap mMap;
    private GoogleApiClient googleApiClient;
    private LocationRequest locationRequest;
    private Location lastLocation;
    private Marker currentUserLocationMarker;
    private static final  int Request_User_location_code=99;
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


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
//
//        LatLng mexico = new LatLng(19.4326077, -99.1332079999997);
//        mMap.addMarker(new MarkerOptions().position(mexico).title("Mexico").snippet("Mexico solicita personal").icon(BitmapDescriptorFactory.fromResource(R.drawable.mexico)).draggable(true));
//        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mexico,7));
//
//        LatLng valle = new LatLng(19.1950960, -100.13267250000001);
//        mMap.addMarker(new MarkerOptions().position(valle).title("Valle del bravo").snippet("valle precioso").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)));
//
//        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(valle,7));
//
//        CameraPosition cameraPosition = new CameraPosition.Builder().target(new LatLng(19.4326077, -99.1332079999997))
//                .zoom(15.5f)
//                .bearing(0).tilt(25).build();
//
//        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            buildGoogleApiClient();

            mMap.setMyLocationEnabled(true);

        }

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case Request_User_location_code: if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED){

                        buildGoogleApiClient();

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
        mMap.animateCamera(CameraUpdateFactory.zoomBy(12));

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
