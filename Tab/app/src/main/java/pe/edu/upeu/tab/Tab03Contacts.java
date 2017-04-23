package pe.edu.upeu.tab;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by Alumnos on 11/04/2017.
 */

public class Tab03Contacts extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle bundle){
        View view = inflater.inflate(R.layout.tab03_contacts,container,false);

        String [] nombres={"Omar Calsin Curo","David Mamani Pari","Juan Perez Perez","Maria Diaz Origuela"};
        ListView listView = (ListView)view.findViewById(R.id.listView);
        ArrayAdapter<String> arrayAdapter= new ArrayAdapter<String>(getActivity().getApplicationContext(),MainActivity.simple,nombres);
        listView.setAdapter(arrayAdapter);

        return view;
    }

}
