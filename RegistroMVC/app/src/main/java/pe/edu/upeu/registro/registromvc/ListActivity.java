package pe.edu.upeu.registro.registromvc;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

import pe.edu.upeu.registro.registromvc.bean.Person;
import pe.edu.upeu.registro.registromvc.dao.PersonDAO;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        PersonDAO personDao=new PersonDAO(this);

        Person person = new Person();
        person.setId(2L);
        person.setName("Omar");
        person.setLastNameF("Calsin");
        person.setLastNameM("Curo");

        //personDao.savePerson(person);
        //personDao.updatePerson(person);
        //personDao.deletePerson(0L);

        //person = personDao.findPersonById("1");
        //Log.i("Nombre>>>",person.getName());

        /*
        List<Person> listPerson = personDao.findPersonAll();
        for (Person personRow:listPerson){
            Log.i("Id>>>",personRow.getId().toString());
            Log.i("Nombre>>>",personRow.getName());
            Log.i("LastNameF>>>",personRow.getLastNameF());
            Log.i("LastNameM>>>",personRow.getLastNameM());
        }
*/

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_lista, menu);
        return true;
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
