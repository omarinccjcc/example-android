package pe.edu.upeu.movil.unionperuana;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import java.util.List;
import java.util.logging.Handler;

import pe.edu.upeu.movil.unionperuana.bean.Institution;
import pe.edu.upeu.movil.unionperuana.service.UnionService;

public class NearToMeActivity extends AppCompatActivity implements LocationListener {

    private SectionsPagerAdapter mSectionsPagerAdapter;

    private ViewPager mViewPager;
    private double latitud;
    private double longitud;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_near_to_me);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        latitudLongitu();
        List<Institution> listInstitution = findInstitutions();

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), listInstitution);

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//habilita el boton en ActionBar
    }







    private LocationManager locationManager;
    private Location location;
    private final int REQUEST_LOCATION = 200;

    @Override
    public void onLocationChanged(Location location) {
        //latitudePosition.setText(String.valueOf(location.getLatitude()));
        //longitudePosition.setText(String.valueOf(location.getLongitude()));
        //getAddressFromLocation(location, getApplicationContext(), new GeoCoderHandler());
    }
    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }
    @Override
    public void onProviderEnabled(String provider) {
    }
    @Override
    public void onProviderDisabled(String provider) {
        if (provider.equals(LocationManager.GPS_PROVIDER)) {
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == REQUEST_LOCATION) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            }
        }
    }


    public void latitudLongitu() {
        Bundle params = getIntent().getExtras();
        if ("near".equals(params.getString("typeSearch"))) {
            //LocationManager locManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            try {
                locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION, android.Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
                } else {
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 100, 2, this);
                    if (locationManager != null) {
                        location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                    }
                }
                if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                    if (location != null) {
                        latitud = location.getLatitude();
                        longitud = location.getLongitude();
                    }
                } else {
                    latitud = -12.0553011;
                    longitud = -77.0802424;
                }
                //if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                //    return;
                //}
                //Location loc = locManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                //latitud = loc.getLatitude();
                //longitud = loc.getLatitude();
            }catch (Exception e) {
                latitud = -12.0553011;
                longitud = -77.0802424;
            }
        }else{
            latitud = Double.parseDouble(params.getString("latitud"));
            longitud = Double.parseDouble(params.getString("longitud"));
        }
    }

    public List<Institution> findInstitutions(){
        Bundle params = getIntent().getExtras();
        UnionService unionService = new UnionService();
        if("near".equals(params.getString("typeSearch"))){

            return unionService.findInstitutionByBaseTyIdCityIdChurch(
                    null,null,null,
                    latitud+"",
                    longitud+"",
                    params.getString("typeSearch"));
        }else{
            return unionService.findInstitutionByBaseTyIdCityIdChurch(
                    params.getString("baseTypeId"),
                    params.getInt("cityId")+"",
                    params.getString("church"),null,null,
                    params.getString("typeSearch"));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_near_to_me, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }else {
            onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }


    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        List<Institution> listInstitution;

        public SectionsPagerAdapter(FragmentManager fm, List<Institution> listInstitution) {
            super(fm);
            this.listInstitution = listInstitution;
        }

        @Override
        public Fragment getItem(int position) {

            switch (position){
                case 0:
                    NearToMeChurchListActivity tab1 = new NearToMeChurchListActivity(listInstitution);
                    return tab1;
                case 1:
                    NearToMeChurchMapActivity tab2 = new NearToMeChurchMapActivity(listInstitution,latitud,longitud);
                    return tab2;
                default:
                    return null;
            }
            //return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Lista";
                case 1:
                    return "Mapa";
            }
            return null;
        }
    }
}
