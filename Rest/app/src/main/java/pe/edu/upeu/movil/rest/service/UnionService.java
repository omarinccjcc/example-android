package pe.edu.upeu.movil.rest.service;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import pe.edu.upeu.movil.rest.bean.Campaign;
import pe.edu.upeu.movil.rest.rest.RequestMethod;
import pe.edu.upeu.movil.rest.rest.RestClient;

/**
 * Created by omar on 29/05/17.
 */

public class UnionService {
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
        }
        return list;
    }
}
