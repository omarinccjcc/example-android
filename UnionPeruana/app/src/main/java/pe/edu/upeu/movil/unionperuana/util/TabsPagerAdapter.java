package pe.edu.upeu.movil.unionperuana.util;


import android.location.Location;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import pe.edu.upeu.movil.unionperuana.NearToMeChurchListActivity;
import pe.edu.upeu.movil.unionperuana.NearToMeChurchMapActivity;

/**
 * Created by diego on 23/05/17.
 */

public class TabsPagerAdapter extends FragmentPagerAdapter {

    Location loc;

    public TabsPagerAdapter(FragmentManager fm,Location loc) {
        super(fm);
        this.loc=loc;
    }

    @Override
    public Fragment getItem(int index) {

        switch (index) {
            case 0:
                // Top Rated fragment activity
                return new NearToMeChurchListActivity(loc);
            case 1:
                // Games fragment activity
                return new NearToMeChurchMapActivity(loc);
        }
        return null;
    }

    @Override
    public int getCount() {
        // get item count - equal to number of tabs
        return 2;
    }

}
