package com.example.krishmehta.androidapplication;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by krishmehta on 11/13/17.
 */
class JsonPasre {
    List<SuggestGetSet> ListData;
    RequestQueue requestQueue;
    static int z;
    public JsonPasre(){}
    public List<SuggestGetSet> getParseJsonWCF(String sName)
    {
        ListData = new ArrayList<SuggestGetSet>();
        z=0;
        JsonArrayRequest request = new JsonArrayRequest("http://www.krishforandroid-env.us-east-2.elasticbeanstalk.com/stock/completename/"+sName,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray jsonArray) {
                        for(int i = 0; i < jsonArray.length(); i++) {
                            try {
                                JSONObject r = jsonArray.getJSONObject(i);
                                ListData.add(new SuggestGetSet(r.getString("Symbol"),r.getString("Name"),r.getString("Exchange")));
                                z++;
                            }
                            catch(JSONException e) {

                            }
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                });
        /*try {
            String temp=sName;
            Log.d("myTag", temp);
            URL js = new URL("http://dev.markitondemand.com/MODApis/Api/v2/Lookup/json?input="+temp);
            URLConnection jc = js.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(jc.getInputStream()));
            String line = reader.readLine();
            //JSONObject jsonResponse = new JSONObject(line);
            JSONArray jsonArray = new JSONArray(line);
            //JSONArray jsonArray = jsonResponse.getJSONArray("results");
            for(int i = 0; i < jsonArray.length(); i++){
                JSONObject r = jsonArray.getJSONObject(i);

                //String hello=new SuggestGetSet(r.getString("Symbol"),r.getString("Name"));
                ListData.add(new SuggestGetSet(r.getString("Symbol"),r.getString("Name"),r.getString("Exchange")));

            }
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }*/
        requestQueue.add(request);
        //while()
        return ListData;

    }
}




/*public class JsonParse {
    public JsonParse(){}
    public List<SuggestGetSet> getParseJsonWCF(String sName)
    {

        List<SuggestGetSet> ListData = new ArrayList<SuggestGetSet>();
        try {
            String temp=sName;
            Log.d("myTag", temp);
            URL js = new URL("http://www.krishforandroid-env.us-east-2.elasticbeanstalk.com/stock/completename/"+temp);
            URLConnection jc = js.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(jc.getInputStream()));
            String line = reader.readLine();
            //JSONObject jsonResponse = new JSONObject(line);
            JSONArray jsonArray = new JSONArray(line);
            //JSONArray jsonArray = jsonResponse.getJSONArray("results");
            for(int i = 0; i < jsonArray.length(); i++){
                JSONObject r = jsonArray.getJSONObject(i);

                //String hello=new SuggestGetSet(r.getString("Symbol"),r.getString("Name"));
                ListData.add(new SuggestGetSet(r.getString("Symbol"),r.getString("Name"),r.getString("Exchange")));

            }
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        return ListData;

    }
}
*/