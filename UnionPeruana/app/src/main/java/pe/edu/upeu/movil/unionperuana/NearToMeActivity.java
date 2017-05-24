package pe.edu.upeu.movil.unionperuana;

import android.support.design.widget.TabLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import pe.edu.upeu.movil.unionperuana.bean.Institution;
import pe.edu.upeu.movil.unionperuana.service.UnionService;

/**
 * Created by omar on 21/05/17.
 */

public class NearToMeActivity extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;

    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_near_to_me);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        List<Institution> listInstitution=findInstitutions();

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(),listInstitution);

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//habilita el boton en ActionBar
    }

    public List<Institution> findInstitutions(){
        Bundle params = getIntent().getExtras();
        UnionService unionService = new UnionService();
        if("near".equals(params.getString("typeSearch"))){
            return unionService.findInstitutionByBaseTyIdCityIdChurch(null,null,null,"","",params.getString("typeSearch"));
        }else{
            return unionService.findInstitutionByBaseTyIdCityIdChurch(
                    params.getString("baseTypeId"),
                    params.getString("cityId"),
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
                    NearToMeChurchMapActivity tab2 = new NearToMeChurchMapActivity(listInstitution);
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



