package com.sara.smartlocator;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class MainActivity extends AppCompatActivity {

    Button btnStart;
    LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStart = findViewById(R.id.btnStart);

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        btnStart.setOnClickListener(v -> {

            // Vérifier permission
            if (ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);

                return;
            }

            // Activer GPS
            locationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER,
                    5000,
                    5,
                    new LocationListener() {
                        @Override
                        public void onLocationChanged(Location location) {
                            double lat = location.getLatitude();
                            double lon = location.getLongitude();

                            Toast.makeText(MainActivity.this,
                                    "Lat: " + lat + " Lon: " + lon,
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
            );

            // Aller vers la map
            startActivity(new Intent(MainActivity.this, GoogleMapActivity.class));
        });
    }
}