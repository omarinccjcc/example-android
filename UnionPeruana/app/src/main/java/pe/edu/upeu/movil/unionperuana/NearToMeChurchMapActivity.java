package pe.edu.upeu.movil.unionperuana;

import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import pe.edu.upeu.movil.unionperuana.bean.Institution;


/**
 * Created by omar on 23/05/17.
 */

public class NearToMeChurchMapActivity extends Fragment implements OnMapReadyCallback {

    GoogleMap map;
    MapView mapView;
    View mView;
    List<Institution> listInstitution;
    double latitud;
    double longitud;



    public NearToMeChurchMapActivity(List<Institution> listInstitution,double latitud,double longitud) {
        this.listInstitution = listInstitution;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.activity_neartomechurch_map, container, false);
        return mView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mapView = (MapView) mView.findViewById(R.id.mapView);
        if (mapView != null) {
            mapView.onCreate(null);
            mapView.onResume();
            mapView.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        MapsInitializer.initialize(getContext());
        map = googleMap;
        //LatLng sydney = new LatLng(-12.0500000, -77.0500031);
        LatLng sydney = new LatLng(latitud, longitud);
        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        //map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 200));
        map.animateCamera(CameraUpdateFactory.zoomTo(12), 200, null);

        for (Institution institution: listInstitution){
            map.addMarker(new MarkerOptions().position(sydney).title(institution.getNameInstitution()).snippet(institution.getAddress())
                    .icon(BitmapDescriptorFactory.fromResource( institution.getLogoImagen() )) .anchor(0.5f, 0.5f) );
        }

        //sydney = new LatLng(-12.0333131,-76.9701174);
        //map.addMarker(new MarkerOptions().position(sydney).title("Universidad Peruana Uninón").snippet("Av. Manuel Nuñez Butron 1223"));

        //sydney = new LatLng(-12.0475306,-77.077969);
        //map.addMarker(new MarkerOptions().position(sydney).title("Clinica Good Hope").snippet("Av. Manuel Nuñez Butron 1223"));

        //sydney = new LatLng(-12.0587783, -77.0268997);
        //map.addMarker(new MarkerOptions().position(sydney).title("Iglesia").snippet("Av. Manuel Nuñez Butron 1223")
        //        .icon(BitmapDescriptorFactory.fromResource( R.drawable.gmap)) .anchor(0.5f, 0.5f) );

    }
}
