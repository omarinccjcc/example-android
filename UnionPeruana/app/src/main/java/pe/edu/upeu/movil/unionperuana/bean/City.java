package pe.edu.upeu.movil.unionperuana.bean;

/**
 * Created by omar on 21/05/17.
 */

public class City {

    private int id;
    private String countryName;

    public City(int id, String countryName) {
        this.id = id;
        this.countryName = countryName;
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
}