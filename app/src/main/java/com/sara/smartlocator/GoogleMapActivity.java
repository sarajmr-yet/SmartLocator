package com.sara.smartlocator;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;

public class GoogleMapActivity extends AppCompatActivity {

    private MapView map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Configuration.getInstance().load(
                getApplicationContext(),
                getSharedPreferences("osm", MODE_PRIVATE)
        );

        setContentView(R.layout.activity_map);

        map = findViewById(R.id.map);
        map.setTileSource(TileSourceFactory.MAPNIK);
        map.setMultiTouchControls(true);

        // Position initiale (ex: Rabat )
        GeoPoint startPoint = new GeoPoint(34.020882, -6.841650);

        map.getController().setZoom(15.0);
        map.getController().setCenter(startPoint);

        // Marker
        Marker marker = new Marker(map);
        marker.setPosition(startPoint);
        marker.setTitle("Ma position ");
        map.getOverlays().add(marker);
        marker.setSubDescription("Bienvenue dans SmartLocator ");
    }
}
