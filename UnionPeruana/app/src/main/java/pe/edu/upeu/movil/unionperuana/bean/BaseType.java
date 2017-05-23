package pe.edu.upeu.movil.unionperuana.bean;

/**
 * Created by omar on 21/05/17.
 */

public class BaseType {

    private String ID;
    private String description;

    public BaseType(String ID, String description) {
        this.ID = ID;
        this.description = description;
    }

    public BaseType() {}

    public String getID() {
        return ID;
    }

    public void setID(String iD) {
        ID = iD;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return  description ;
    }
}
