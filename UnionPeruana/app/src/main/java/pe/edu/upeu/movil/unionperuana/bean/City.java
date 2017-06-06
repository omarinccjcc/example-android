package pe.edu.upeu.movil.unionperuana.bean;

/**
 * Created by omar on 21/05/17.
 */

public class City {

    private int id;
    private String countryName;
    private String latitud;
    private String longitud;

    public City(int id, String countryName, String latitud, String longitud) {
        this.id = id;
        this.countryName = countryName;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public City() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    @Override
    public String toString() {
        return countryName ;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }
}