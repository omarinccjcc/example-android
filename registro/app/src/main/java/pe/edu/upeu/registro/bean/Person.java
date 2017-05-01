package pe.edu.upeu.registro.bean;

import android.content.Intent;

/**
 * Created by Alumnos on 04/04/2017.
 */

public class Person {

    private int id;
    private String name;
    private String lastNameF;
    private String lastNameM;
    private String site;
    private String address;
    private String sex;
    private String status;
    private String statusMarried;
    private String score;
    private Integer photo;

    public Person(){}

    public Person(int id, String name, String lastNameF, String lastNameM, String site, String address,Integer photo,String sex) {
        this.id = id;
        this.name = name;
        this.lastNameF = lastNameF;
        this.lastNameM = lastNameM;
        this.site = site;
        this.address = address;
        this.photo = photo;
        this.sex = sex;

    }
    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
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

    public String getLastNameF() {
        return lastNameF;
    }

    public void setLastNameF(String lastNameF) {
        this.lastNameF = lastNameF;
    }

    public String getLastNameM() {
        return lastNameM;
    }

    public void setLastNameM(String lastNameM) {
        this.lastNameM = lastNameM;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getPhoto() {
        return photo;
    }

    public void setPhoto(Integer photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return name + " " + lastNameF +" " + lastNameM+" - "+sex + " - "+status ;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusMarried() {
        return statusMarried;
    }

    public void setStatusMarried(String statusMarried) {
        this.statusMarried = statusMarried;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
