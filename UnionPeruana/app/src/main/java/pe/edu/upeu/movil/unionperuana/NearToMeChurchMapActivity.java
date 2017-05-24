package pe.edu.upeu.movil.unionperuana;

import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

/**
 * Created by diego on 23/05/17.
 */

public class NearToMeChurchMapActivity extends Fragment implements AdapterView.OnItemClickListener{

    private Location loc;

    public NearToMeChurchMapActivity(){
    }

    public NearToMeChurchMapActivity(Location loc){
        this.loc=loc;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_neartomechurch_map, container,
                false);
        return v;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
