package pe.edu.upeu.registro.registromvc.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import pe.edu.upeu.registro.registromvc.bean.Person;

/**
 * Created by omar on 01/05/17.
 */

public class PersonDAO extends SQLiteOpenHelper {

    private static final String DATABASE = "RegisterTest";
    private static final int VERSION =2;

    public PersonDAO(Context context) {
        super(context, DATABASE, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String ddl="CREATE TABLE Person (" +
                " id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                " name TEXT," +
                " lastNameF TEXT," +
                " lastNameM TEXT," +
                " site TEXT," +
                " address TEXT," +
                " sex TEXT," +
                " status TEXT," +
                " statusMarried TEXT," +
                " score REAL," +
                " photo TEXT" +
                ");";

        db.execSQL(ddl);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String ddl = "DROP TABLE IF EXISTS Person";
        db.execSQL(ddl);
        this.onCreate(db);
    }

    public void deletePerson(Long id){
        getWritableDatabase().delete("Person",null,null);
    }

    public void updatePerson(Person person){
        ContentValues values = new ContentValues();
        values.put("name", person.getName());
        values.put("lastNameF", person.getLastNameF());
        values.put("lastNameM", person.getLastNameM());
        values.put("site", person.getSite());
        values.put("address", person.getAddress());
        values.put("sex", person.getSex());
        values.put("status", person.getStatus());
        values.put("statusMarried", person.getStatusMarried());
        values.put("score", person.getScore());
        values.put("photo", person.getPhoto());

        getWritableDatabase().update("Person",values,"id="+person.getId(),null);
    }


    public void savePerson(Person person){

        ContentValues values =new ContentValues();
        values.put("id", person.getId());
        values.put("name", person.getName());
        values.put("lastNameF", person.getLastNameF());
        values.put("lastNameM", person.getLastNameM());
        values.put("site", person.getSite());
        values.put("address", person.getAddress());
        values.put("sex", person.getSex());
        values.put("status", person.getStatus());
        values.put("statusMarried", person.getStatusMarried());
        values.put("score", person.getScore());
        values.put("photo", person.getPhoto());

        getWritableDatabase().insert("Person",null,values);

    }

    public Person findPersonById(String id){
        String column[]={"id","name","lastNameF","lastNameM","site","address","sex","status","statusMarried","score","photo"};
        String where = "id = "+id;

        Person person=null;
        Cursor cursor = getReadableDatabase().query("Person",column,where,null,null,null,null);
        if(cursor.moveToFirst()){
            person=new Person();
            person.setId(cursor.getLong(0));
            person.setName(cursor.getString(1));
            person.setLastNameF(cursor.getString(2));
            person.setLastNameM(cursor.getString(3));
            person.setSite(cursor.getString(4));
            person.setAddress(cursor.getString(5));
            person.setSex(cursor.getString(6));
            person.setStatus(cursor.getString(7));
            person.setStatusMarried(cursor.getString(8));
            person.setScore(cursor.getDouble(9));
            person.setPhoto(cursor.getString(10));
        }
        cursor.close();
        return person;
    }

    public List<Person> findPersonAll(){
        List<Person> listPerson=new ArrayList<Person>();
        String column[]={"id","name","lastNameF","lastNameM","site","address","sex","status","statusMarried","score","photo"};
        //String where = "name ='Jose'";
        String where = null;

        Cursor cursor = getReadableDatabase().query("Person",column,where,null,null,null,null);
        Person person;
        while (cursor.moveToNext()){
            person=new Person();
            person.setId(cursor.getLong(0));
            person.setName(cursor.getString(1));
            person.setLastNameF(cursor.getString(2));
            person.setLastNameM(cursor.getString(3));
            person.setSite(cursor.getString(4));
            person.setAddress(cursor.getString(5));
            person.setSex(cursor.getString(6));
            person.setStatus(cursor.getString(7));
            person.setStatusMarried(cursor.getString(8));
            person.setScore(cursor.getDouble(9));
            person.setPhoto(cursor.getString(10));
            listPerson.add(person);
        }
        cursor.close();
        return listPerson;
    }

}
