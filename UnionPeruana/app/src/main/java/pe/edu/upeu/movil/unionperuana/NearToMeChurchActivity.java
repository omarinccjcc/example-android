package pe.edu.upeu.movil.unionperuana;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by omar on 21/05/17.
 */

public class NearToMeChurchActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_near_to_me_church);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager() .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-12.0500000, -77.0500031);
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 200));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(12), 200, null);
        //mMap.setMyLocationEnabled(true);
        sydney = new LatLng(-12.0333131,-76.9701174);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Universidad Peruana Uninón").snippet("Av. Manuel Nuñez Butron 1223"));

        sydney = new LatLng(-12.0475306,-77.077969);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Clinica Good Hope").snippet("Av. Manuel Nuñez Butron 1223"));

        sydney = new LatLng(-12.0587783, -77.0268997);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Iglesia").snippet("Av. Manuel Nuñez Butron 1223")
                .icon(BitmapDescriptorFactory.fromResource( R.drawable.ic_launcher))
                .anchor(0.5f, 0.5f)
        );

        /*
        mMap.addMarker(new MarkerOptions().position(sydney).title("Universidad Peruana Uninón"));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(13), 200, null);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

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
        mMap.setMyLocationEnabled(true);
    */
    }
}
