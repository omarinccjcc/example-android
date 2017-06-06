package pe.edu.upeu.movil.unionperuana.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import pe.edu.upeu.movil.unionperuana.R;


import java.util.List;

import pe.edu.upeu.movil.unionperuana.bean.Institution;

/**
 * Created by ocalsin on 4/06/17.
 */

public class InstitutionListAdapter extends ArrayAdapter<Institution> {

    private class ViewHolder{
        ImageView logoImagen;
        TextView nameInstitution;
        TextView address;
    }

    public InstitutionListAdapter(Context context, List<Institution> rowItem) {
        super(context, R.layout.list_institution, rowItem);
    }

    public View getView(int position, View contentView, ViewGroup parent){
        Institution institution = getItem(position);

        ViewHolder viewHolder;
        if(contentView==null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater=LayoutInflater.from(getContext());
            contentView = inflater.inflate(R.layout.list_institution,null);
            viewHolder.nameInstitution = (TextView)contentView.findViewById(R.id.nameInstitution);
            viewHolder.address = (TextView)contentView.findViewById(R.id.address);
            viewHolder.logoImagen = (ImageView)contentView.findViewById(R.id.photo);
            contentView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)contentView.getTag();
        }

        viewHolder.nameInstitution.setText(institution.getNameInstitution());
        viewHolder.address.setText(institution.getAddress());
        viewHolder.logoImagen.setImageResource(institution.getLogoImagen());
        return contentView;
    }

}
