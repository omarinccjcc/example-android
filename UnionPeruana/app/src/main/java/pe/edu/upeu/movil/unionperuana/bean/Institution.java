package pe.edu.upeu.movil.unionperuana.bean;

//import pe.union.upeu.movil.iglesia.R;

import pe.edu.upeu.movil.unionperuana.R;
import pe.edu.upeu.movil.unionperuana.util.Commons;

/**
 * Created by omar on 21/05/17.
 */

public class Institution {


    private int ID;
    private String url;
    private String nameInstitution;
    private String address;
    private String latitude;
    private String longitude;
    private String typeInstitution;
    private String icono;
    private String city;

//	public void City(String url, String nameInstitution, String address,
//			String latitude, String longitude, String typeInstitution) {
//		this.url = url;
//		this.nameInstitution = nameInstitution;
//		this.address = address;
//		this.latitude = latitude;
//		this.longitude = longitude;
//		this.typeInstitution = typeInstitution;
//	}

    public Institution(int ID,String nameInstitution,String address,String typeInstitution){
        this.ID = ID;
        this.nameInstitution =nameInstitution;
        this.address=address;
        this.typeInstitution = typeInstitution;
    }


    public Institution(){}

    public int getLogoImagen() {
        int image = 0;
        if (typeInstitution.equals(Commons.TYPE_ADRA)) {
            //image = R.drawable.adra;
            image = R.drawable.gmap;
        } else if (typeInstitution.equals(Commons.TYPE_ASOC)) {
            //image = R.drawable.adra;
            image = R.drawable.gmap;
        } else if (typeInstitution.equals(Commons.TYPE_CHURCH)) {
            //image = R.drawable.iglesia;
            image = R.drawable.gmap;
        } else if (typeInstitution.equals(Commons.TYPE_CLINIC)) {
            //image = R.drawable.clinica;
            image = R.drawable.gmap;
        } else if (typeInstitution.equals(Commons.TYPE_SEHS)) {
            //image = R.drawable.sehs;
            image = R.drawable.gmap;
        } else if (typeInstitution.equals(Commons.TYPE_OTHER)) {
            //image = R.drawable.otros;
            image = R.drawable.gmap;
        } else if (typeInstitution.equals(Commons.TYPE_RADIO)) {
            //image = R.drawable.radio;
            image = R.drawable.gmap;
        } else if (typeInstitution.equals(Commons.TYPE_SCHOOL)) {
            //image = R.drawable.colegio;
            image = R.drawable.gmap;
        } else if (typeInstitution.equals(Commons.TYPE_UNIVERSITY)) {
            //image = R.drawable.universidad;
            image = R.drawable.gmap;
        }
        return image;
    }


    public void City() {
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNameInstitution() {
        return nameInstitution;
    }

    public void setNameInstitution(String nameInstitution) {
        this.nameInstitution = nameInstitution;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTypeInstitution() {
        return typeInstitution;
    }

    public void setTypeInstitution(String typeInstitution) {
        this.typeInstitution = typeInstitution;
    }

    public int getID() {
        return ID;
    }

    public void setID(int iD) {
        ID = iD;
    }

    public String getIcono() {
        return icono;
    }

    public void setIcono(String icono) {
        this.icono = icono;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
