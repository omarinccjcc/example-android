package pe.edu.upeu.registro.repaso.dao;

import java.util.ArrayList;
import java.util.List;

import pe.edu.upeu.registro.repaso.bean.User;

/**
 * Created by omar on 16/05/17.
 */

public class UserDao{


    public static List<User> listUser = new ArrayList<User>();

    public void saveUser(User user){
        listUser.add(user);
    }

    public boolean validateUser(String user, String password){
        return true;
    }


}
