package pe.edu.upeu.sms.service;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import pe.edu.upeu.sms.rest.RequestMethod;
import pe.edu.upeu.sms.rest.RestClient;
import pe.edu.upeu.sms.util.Campaign;
import pe.edu.upeu.sms.util.Sms;


/**
 * 
 * @author ocalsin
 * 
 */

public class SmsService {

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
