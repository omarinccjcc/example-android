package pe.edu.upeu.registro.repaso;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import pe.edu.upeu.registro.repaso.dao.UserDao;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }

    public void login(View view){
        EditText user = (EditText) findViewById(R.id.user);
        EditText passsword = (EditText) findViewById(R.id.passsword);
        UserDao userDao = new UserDao();

        if(userDao.validateUser(user.getText().toString(),passsword.getText().toString())){
            Intent intent = new Intent(this,Main2Activity.class);
            startActivity(intent);
        }else{
            user.setText("");
            passsword.setText("");
            Toast.makeText(getApplicationContext(),"Usuario o clave incorrecto", Toast.LENGTH_SHORT).show();
        }
    }

    public void register(View view){
        Intent intent = new Intent(this,RegisterUser.class);
        startActivity(intent);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
