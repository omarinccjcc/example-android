package pe.edu.upeu.movil.rest.bean;

/**
 * Created by omar on 28/05/17.
 */

public class Campaign {

    private int id;
    private String name;

    public Campaign(){}

    public Campaign(int id, String name){
        this.id = id;
        this.name= name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name= name;
    }

    public String toString() {
        return this.name;
    }

}
