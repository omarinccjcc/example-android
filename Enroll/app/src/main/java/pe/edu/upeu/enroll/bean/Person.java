package pe.edu.upeu.enroll.bean;

/**
 * Created by Alumnos on 06/04/2017.
 */

public class Person {

    private int id;
    private String name;
    private String lastNames;
    private String address;
    private String sex;
    private String status;

    public Person(){}

    public Person(int id, String name, String lastNames, String address, String sex, String status) {
        this.id = id;
        this.name = name;
        this.lastNames = lastNames;
        this.address = address;
        this.sex = sex;
        this.status = status;
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
        this.name = name;
    }

    public String getLastNames() {
        return lastNames;
    }

    public void setLastNames(String lastNames) {
        this.lastNames = lastNames;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return name +" " + lastNames;
    }
}
