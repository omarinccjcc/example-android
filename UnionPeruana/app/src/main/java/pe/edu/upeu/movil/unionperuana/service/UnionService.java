package pe.edu.upeu.movil.unionperuana.service;

import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import pe.edu.upeu.movil.unionperuana.bean.BaseType;
import pe.edu.upeu.movil.unionperuana.bean.Campaign;
import pe.edu.upeu.movil.unionperuana.bean.City;
import pe.edu.upeu.movil.unionperuana.bean.Institution;
import pe.edu.upeu.movil.unionperuana.bean.Sms;
import pe.edu.upeu.movil.unionperuana.rest.RequestMethod;
import pe.edu.upeu.movil.unionperuana.rest.RestClient;
import pe.edu.upeu.movil.unionperuana.util.Commons;


/**
 * Created by omar on 21/05/17.
 */

public class UnionService {

    public List<Institution> findInstitutionByBaseTyIdCityIdChurch(String baseTypeId, String cityId, String church,String lat, String lon, String typeSearch) {
        String URL ="";
        List<Institution> list = new ArrayList<Institution>();
        try {
            RestClient client = null;

            if ("near".equals(typeSearch)){
                URL=Commons.URL_STRING + "institution/latLon";
                client = new RestClient(URL);
                client.addParam("lat",lat);
                client.addParam("lon",lon);
            }else{
                URL=Commons.URL_STRING + "institution/cityTypeName";
                client = new RestClient(URL);
                client.addParam("baseTypeId ",baseTypeId);
                client.addParam("cityId",cityId);
                client.addParam("church",church);
            }

            client.execute(RequestMethod.GET);
            if (client.getResponseCode() == 200) {
                JSONArray nameArray = new JSONArray(client.getResponse());
                for (int i = 0; i < nameArray.length(); i++) {
                    JSONObject oj = nameArray.getJSONObject(i);
                    Institution inst = new Institution();
                    inst.setUrl(oj.getString("url"));
                    inst.setNameInstitution(oj.getString("nameInstitution"));
                    inst.setAddress(oj.getString("address"));
                    inst.setLatitude(oj.getString("latitude"));
                    inst.setLongitude(oj.getString("longitude"));
                    inst.setTypeInstitution(oj.getString("typeInstitution"));
                    list.add(inst);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<BaseType> findTypeInstitutions(){
        List<BaseType> list = new ArrayList<BaseType>();
        try {

            RestClient client = new RestClient(Commons.URL_STRING +"type/all");
            //client.addParam("cityId","3");
            client.execute(RequestMethod.GET);
            if (client.getResponseCode() == 200) {
                JSONArray nameArray = new JSONArray(client.getResponse());
                for (int i = 0; i < nameArray.length(); i++) {
                    JSONObject oj = nameArray.getJSONObject(i);
                    String typeCode = oj.getString("typeCode");
                    String description = oj.getString("description");
                    list.add(new BaseType(typeCode, description));
                }
            }
        } catch (Exception e) {
            Log.e("ServicioRest","Error!", e);
            e.printStackTrace();
        }
        list.add(new BaseType("","Todos"));
        list.add(new BaseType("ADRA","ADRA"));
        list.add(new BaseType("UNIVERSIDAD","Universidad"));
        return list;
    }

    public List<City> findCity(){
        List<City> list = new ArrayList<City>();

        try {
            RestClient client = new RestClient( Commons.URL_STRING + "city/all");
            client.execute(RequestMethod.GET);
            if (client.getResponseCode() == 200) {
                JSONArray nameArray = new JSONArray(client.getResponse());
                for (int i = 0; i < nameArray.length(); i++) {
                    JSONObject oj = nameArray.getJSONObject(i);
                    String cityDescription = oj.getString("cityDescription");
                    String latitud = oj.getString("latitud");
                    String longitud = oj.getString("longitud");
                    String id = oj.getString("id");
                    if (id == null || "null".equals(id)) {
                        id = "0";
                    }
                    list.add(new City(Integer.parseInt(id), cityDescription,latitud,longitud));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        list.add(new City(1,"Lima","-12.0553011","-77.0802424"));
        list.add(new City(2,"Arequipa","-16.4040495","-71.5740312"));
        list.add(new City(3,"Puno","-15.8398204","-70.0213623"));
        return list;
    }

    public List<Sms> findSms(String url) {
        List<Sms> list = new ArrayList<Sms>();
        try {
            RestClient client = new RestClient(url);
            client.execute(RequestMethod.GET);
            if (client.getResponseCode() == 200) {
                JSONArray nameArray = new JSONArray(client.getResponse());
                for (int i = 0; i < nameArray.length(); i++) {
                    JSONObject oj = nameArray.getJSONObject(i);
                    Sms person = new Sms();
                    person.setId(oj.getLong("id"));
                    person.setNumPhone(oj.getString("numPhone"));
                    person.setMessage(oj.getString("message"));
                    list.add(person);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("message error: " + e.getLocalizedMessage());
        }
        return list;
    }


    public List<Campaign> findCampaign(String url) {
        List<Campaign> list = new ArrayList<Campaign>();
        try {
            RestClient client = new RestClient(url);
            client.execute(RequestMethod.GET);
            if (client.getResponseCode() == 200) {
                JSONArray nameArray = new JSONArray(client.getResponse());
                for (int i = 0; i < nameArray.length(); i++) {
                    JSONObject oj = nameArray.getJSONObject(i);
                    list.add(new Campaign(oj.getInt("id"),oj.getString("nameCampaign")));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("message error: " + e.getLocalizedMessage());
        }
        return list;
    }

    public void updateSms(String url) {

        try {
            RestClient client = new RestClient(url);
            client.execute(RequestMethod.GET);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("message error: " + e.getLocalizedMessage());
        }
    }
}
