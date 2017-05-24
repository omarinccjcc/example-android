package pe.edu.upeu.movil.unionperuana.service;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import pe.edu.upeu.movil.unionperuana.bean.BaseType;
import pe.edu.upeu.movil.unionperuana.bean.City;
import pe.edu.upeu.movil.unionperuana.bean.Institution;
import pe.edu.upeu.movil.unionperuana.rest.RequestMethod;
import pe.edu.upeu.movil.unionperuana.rest.RestClient;
import pe.edu.upeu.movil.unionperuana.util.Commons;


/**
 * Created by omar on 21/05/17.
 */

public class UnionService {

    public List<Institution> findInstitutionByBaseTyIdCityIdChurch(String baseTypeId, String cityId, String church,String lat, String log, String typeSearch) {
        String URL ="";
        if ("near".equals(typeSearch)){
            URL=Commons.URL_STRING + "searchInstitutionByAll/"+baseTypeId+"-"+cityId+"-"+church;
        }else{
            URL=Commons.URL_STRING + "searchInstitutionByAll/"+lat+"-"+log;
        }
        List<Institution> list = new ArrayList<Institution>();
        try {
            RestClient client = new RestClient(URL);
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

            RestClient client = new RestClient(Commons.URL_STRING + "typeInstitution");
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
            list.add(new BaseType("0", "-- Selecciones --"));
            list.add(new BaseType("1", "Adra"));
            list.add(new BaseType("2", "Asociación"));
            list.add(new BaseType("3", "Iglesia"));
            list.add(new BaseType("4", "Clínica"));
            list.add(new BaseType("5", "Universidad"));
            list.add(new BaseType("6", "Radio"));
            list.add(new BaseType("7", "Colegio/Escuela"));
            list.add(new BaseType("8", "Otros"));
            e.printStackTrace();
        }
        return list;
    }

    public List<City> findCity(){
        List<City> list = new ArrayList<City>();

        try {
            RestClient client = new RestClient(Commons.URL_STRING + "searchCityAll");
            client.execute(RequestMethod.GET);
            if (client.getResponseCode() == 200) {
                JSONArray nameArray = new JSONArray(client.getResponse());
                for (int i = 0; i < nameArray.length(); i++) {
                    JSONObject oj = nameArray.getJSONObject(i);
                    String cityDescription = oj.getString("cityDescription");
                    String id = oj.getString("id");
                    if (id == null || "null".equals(id)) {
                        id = "0";
                    }
                    list.add(new City(Integer.parseInt(id), cityDescription));
                }
            }
        } catch (Exception e) {
            list.add(new City(0, "-- Selecciones --"));
            list.add(new City(1,"Juliaca, Puno, Peru"));
            list.add(new City(2,"Puno, Puno, Peru"));
            list.add(new City(3,"Arequipa, Arequipa, Peru"));
            e.printStackTrace();
        }
        return list;
    }
}
