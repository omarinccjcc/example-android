package pe.edu.upeu.registro.repaso;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by omar on 16/05/17.
 */

public class Favorites extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle){
        View view = inflater.inflate(R.layout.favorities,viewGroup,false);

        return view;
    }

}
