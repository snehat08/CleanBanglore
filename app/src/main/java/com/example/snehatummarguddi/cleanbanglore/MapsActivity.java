package com.example.snehatummarguddi.cleanbanglore;

import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.identity.intents.Address;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {

    GoogleMap googleMap;
    private GoogleApiClient mLocationClient;


    private static final int ERROR_DIALOG_REQUEST = 9001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_activity);
        mLocationClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
        mLocationClient.connect();


//        if (servicesOk()) {
//            setContentView(R.layout.map_activity);
//            initMap();
//
//
//        } else {
//            setContentView(R.layout.test_activity);
//        }

        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


    }


//    public boolean servicesOk() {
//
//        int isAvailable = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
//        if (isAvailable == ConnectionResult.SUCCESS) {
//            return true;
//        } else if (GooglePlayServicesUtil.isUserRecoverableError(isAvailable)) {
//            Dialog dialog = GooglePlayServicesUtil.getErrorDialog(isAvailable, this, ERROR_DIALOG_REQUEST);
//            dialog.show();
//        } else {
//            Toast.makeText(this, "can't connect to mapping service", Toast.LENGTH_SHORT).show();
//        }
//        return false;
//    }
//
//    private boolean initMap() {
//        if (mMap == null) {
//            SupportMapFragment mapFragment =
//                    (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
//            mapFragment.getMapAsync(this);
//        }
//        return (mMap != null);
//    }


    @Override
    public void onMapReady(GoogleMap googleMap) {

        LatLng latLng = new LatLng(12.9038, 77.5618);
        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(latLng, 15);
        googleMap.moveCamera(update);


    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Toast.makeText(this, "Ready to map", Toast.LENGTH_SHORT).show();


    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    public void buttononclick(View view) {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location currentLocation = LocationServices.FusedLocationApi
                .getLastLocation(mLocationClient);
        if (currentLocation == null) {
            Toast.makeText(MapsActivity.this, "can't connect t", Toast.LENGTH_SHORT).show();

        } else {

            LatLng latLng = new LatLng(
                    currentLocation.getLatitude(),
                    currentLocation.getLongitude()
            );

            CameraUpdate update = CameraUpdateFactory.newLatLngZoom(latLng, 21);
            googleMap.animateCamera(update);


        }
    }

//    public void showCurrentLocation(View view) {
////        Toast toast = Toast.makeText(this, "Hold and drag marker to get accurate location.", Toast.LENGTH_LONG);
////        toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
////        toast.show();
////
////        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
////            // TODO: Consider calling
////            //    ActivityCompat#requestPermissions
////            // here to request the missing permissions, and then overriding
////            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
////            //                                          int[] grantResults)
////            // to handle the case where the user grants the permission. See the documentation
////            // for ActivityCompat#requestPermissions for more details.
////            return;
////        }
////        Location currentLocation = LocationServices.FusedLocationApi
////                .getLastLocation(mLocationClient);
////        SupportMapFragment mapFragment;
////        mapFragment =  (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
////        mapFragment.getMapAsync(this);
////
////        googleMap.clear();
////        googleMap.setMyLocationEnabled(true);
////        Button btn = (Button) findViewById(R.id.button3);
////        btn.setEnabled(true);
////
////        if (currentLocation == null) {
////        } else {
////            LatLng latLng = new LatLng(
////                    currentLocation.getLatitude(),
////                    currentLocation.getLongitude()
////            );
////            double lat = currentLocation.getLatitude();
////            double lng = currentLocation.getLongitude();
////            Geocoder gc = new Geocoder(this);
////            List<Address> list = null;
//////            try {
//////                list = gc.getFromLocation(currentLocation.getLatitude(),currentLocation.getLongitude(),1);
//////
//////                EditText tv;
//////                if(list.size()> 0 ) {
//////                    Address  add = list.get(0);
//////                    String locality = add.getSubLocality();
//////                    tv.setText(locality);
//////                } else {
//////                    tv.setText("Not found, Enter manually");
//////                }
//////
//////            } catch (IOException e) {
//////                Log.d(TAG,"SomeShit "+e.getMessage());
//////                e.printStackTrace();
//////            }
////
////
////
////            CameraUpdate update = CameraUpdateFactory.newLatLngZoom(
////                    latLng, 18
////            );
////            googleMap.animateCamera(update);
////            Marker marker = googleMap.addMarker(new MarkerOptions()
////                    .position(latLng)
////                    .title("Current Location")
////                    .draggable(true)
////                    .snippet("Proceed further by adding a landmark."));
////        }
////    }
//
//
//        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
//
//
//        Criteria criteria = new Criteria();
//        String bestProvider = locationManager.getBestProvider(criteria, true);
//        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            // TODO: Consider calling
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
//            return;
//        }
//        Location location = locationManager.getLastKnownLocation(bestProvider);
//
//        if (location == null) {
//            Toast.makeText(getApplicationContext(), "GPS signal not found", Toast.LENGTH_SHORT).show();
//        }
//        if (location != null) {
//            Log.e("locatin", "location--" + location);
//
//            Log.e("latitude at beginning",
//                    "@@@@@@@@@@@@@@@" + location.getLatitude());
//
//        }
//    }
//
//
//}


}





