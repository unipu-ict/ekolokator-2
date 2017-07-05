package com.example.adrian.ekolokator;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import static java.security.AccessController.getContext;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    SupportMapFragment mapFragment;
    private LocationListener gpsListener;
    public static Marker mar;
    public static LatLng trenutna_lokacija = new LatLng(44.8009215, 16.4005538);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Button odabrano = (Button) findViewById(R.id.odabrana);
        odabrano.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NextActivity(trenutna_lokacija);
            }
        });

        Button trenutno = (Button) findViewById(R.id.trenutna);
        trenutno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                trenutnoOdabrano();
                NextActivity(trenutna_lokacija);
            }
        });
    }



    public void trenutnoOdabrano()
    {
        gpsListener = new GPSListener();
        String provider = Context.LOCATION_SERVICE;
        LocationManager locationManager = (LocationManager) getSystemService(provider);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, gpsListener);
        Location location = locationManager.getLastKnownLocation(provider);
        updateWithNewLocation(location);
        if (location != null)
            trenutna_lokacija = new LatLng(location.getLatitude(), location.getLongitude());

    }

    //updatenje nove lokacije
    private void updateWithNewLocation(Location location){
        if(location!=null){
            double lat = location.getLatitude();
            double lng = location.getLongitude();
            gotolocation(lat,lng,17);
        }
    }

    void NextActivity(LatLng latLng)
    {
        Intent intent = new Intent(this, ReportActivity.class);
        intent.putExtra("Latlng", latLng);
        startActivity(intent);
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
        //postavljanje mape
        mMap = googleMap;
        //tip mape
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                trenutna_lokacija = latLng;
                gotolocation(latLng.latitude, latLng.longitude,17);
            }
        });

        // ako je trenutna lokacija null onda postavi pregled Hrvatske
        if(trenutna_lokacija.latitude != 0.0){
            removeMarker();
            gotolocation(trenutna_lokacija.latitude, trenutna_lokacija.longitude, 16); }
        else {
            //pregled hrvatske
            gotolocationNoMarker(44.8009215, 16.4005538, 6);
        }
    }





    // klasa GPSListener za dobivanje LocationListenera
    private class GPSListener implements LocationListener {

        public void onLocationChanged(Location location){
            //kada se promjeni lokacija pokreni sljedeću funkciju
            updateWithNewLocation(location);
        }
        public void onProviderDisabled(String provider){
            //TODO autogeneratedmethod stub
        }
        public void onProviderEnabled(String provider){
            //TODO
        }
        public void onStatusChanged(String provider, int status, Bundle extras){
            //TODO
        }

    }


    //metoda koja prebacuje cameru na novu lokaciju i postavlja marker
    public void gotolocation(double lat, double lang, float zoom){
        Toast.makeText(this, "Location selected: " + new LatLng(lat, lang).toString(), Toast.LENGTH_LONG).show();
        //micu se markeri pozivom funkcije remove marker
        if(mar!=null) {
            removeMarker(); }
        LatLng latLng = new LatLng(lat, lang);
        MarkerOptions marker = new MarkerOptions().position(latLng).title("Vaša lokacija");
        mar = mMap.addMarker(marker);
        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(latLng, zoom);
        mMap.moveCamera(update);
    }

    // za postavljanje pogleda hrvatske, odnosno bez markera
    public void gotolocationNoMarker(double lat, double lang, float zoom){
        LatLng latLng = new LatLng(lat, lang);
        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(latLng, zoom);
        mMap.moveCamera(update);
    }

    public void removeMarker(){
        if(mar!=null) {
            if (mar.isVisible()) {
                mar.remove();
            }
        }
    }






}
