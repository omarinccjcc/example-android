package pe.edu.upeu.registro.repaso;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import pe.edu.upeu.registro.repaso.bean.User;
import pe.edu.upeu.registro.repaso.dao.UserDao;

public class RegisterUser extends AppCompatActivity {

    EditText name;
    EditText lastName;
    EditText user;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        Button save = (Button)findViewById(R.id.save);
        name=(EditText)findViewById(R.id.name);
        lastName=(EditText)findViewById(R.id.lastName);
        user=(EditText)findViewById(R.id.user);
        password=(EditText)findViewById(R.id.password);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // validate
                // if
                UserDao userDao = new UserDao();
                User userObject=new User(1,name.getText().toString(),lastName.getText().toString(),user.getText().toString(),password.getText().toString());
                userDao.saveUser(userObject);

                Intent intent = new Intent(RegisterUser.this,MainActivity.class);
                startActivity(intent);

            }
        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }



}
