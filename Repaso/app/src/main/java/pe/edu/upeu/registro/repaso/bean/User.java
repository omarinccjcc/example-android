package pe.edu.upeu.registro.repaso.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by omar on 16/05/17.
 */

public class User {

    private int id;
    private String name;
    private String lastNme;
    private String user;
    private String pass;


    public User(int id, String name, String lastNme, String user, String pass) {
        this.id = id;
        this.name = name;
        this.lastNme = lastNme;
        this.user = user;
        this.pass = pass;
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

    public String getLastNme() {
        return lastNme;
    }

    public void setLastNme(String lastNme) {
        this.lastNme = lastNme;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }


    @Override
    public String toString() {
        return name + " " + lastNme + " - "+user;
    }
}
