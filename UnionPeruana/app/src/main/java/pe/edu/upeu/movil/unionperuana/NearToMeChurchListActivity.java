package pe.edu.upeu.movil.unionperuana;


import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import pe.edu.upeu.movil.unionperuana.adapter.InstitutionListAdapter;
import pe.edu.upeu.movil.unionperuana.bean.Institution;

/**
 * Created by omar on 23/05/17.
 */

public class NearToMeChurchListActivity extends Fragment {

    List<Institution> listInstitution;

    public NearToMeChurchListActivity(List<Institution> listInstitution){
        this.listInstitution = listInstitution;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_neartomechurch_list, container,
                false);

        ListView listView=(ListView)v.findViewById(R.id.listViewInstitution);
        InstitutionListAdapter institutionListAdapter = new InstitutionListAdapter(v.getContext(),listInstitution);
        listView.setAdapter(institutionListAdapter);

        return v;
    }


}
