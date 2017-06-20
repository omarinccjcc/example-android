package pe.edu.upeu.movil.unionperuana;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;

import pe.edu.upeu.movil.unionperuana.adapter.InstitutionListAdapter;
import pe.edu.upeu.movil.unionperuana.bean.Institution;

/**
 * Created by Itecs on 18/06/17.
 */

public class NearToMeChurchListActivity extends Fragment {

    List<Institution> listInstitution;

    @SuppressLint("ValidFragment")
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