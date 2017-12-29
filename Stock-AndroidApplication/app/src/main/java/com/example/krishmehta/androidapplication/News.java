package com.example.krishmehta.androidapplication;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by krishmehta on 11/15/17.
 */

public class News extends Fragment {
    ListView mylist;
    // Defining the Volley request queue that handles the URL request concurrently
    RequestQueue requestQueue;
    LinearLayout linear;
    LinearLayout linear1;
    String JsonURL="http://krishforandroid-env.us-east-2.elasticbeanstalk.com/stock/NEWS/";
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        View myview=inflater.inflate(R.layout.news_frag,container,false );
        linear = (LinearLayout) myview.findViewById(R.id.progressbarlinear1);
        linear1 = (LinearLayout) myview.findViewById(R.id.progressbarlinear2);
        mylist=(ListView) myview.findViewById(R.id.newslist);
        final String stockname=((Main2Activity) getActivity()).getResults();
        Log.d("Stockname in News", stockname);
        requestQueue = Volley.newRequestQueue(this.getContext());
        final Context context=this.getContext();
        JsonURL=JsonURL+stockname;
        JsonObjectRequest obreq = new JsonObjectRequest(Request.Method.GET, JsonURL,null,
                // The third parameter Listener overrides the method onResponse() and passes
                //JSONObject as a parameter
                new Response.Listener<JSONObject>() {

                    // Takes the response from the JSON request
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            ArrayList<Object> list=new ArrayList<>();
                            /*SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
                            date = sdf1.parse("2017-11-16");
                            TimeZone tz = TimeZone.getTimeZone("America/New_York");
                            String name = tz.getDisplayName(tz.inDaylightTime(date), TimeZone.SHORT);
                            Log.d("value of timezone",name);*/
                            JSONObject obj=response.getJSONObject("rss");
                            JSONArray array=obj.getJSONArray("channel");
                            JSONObject obj1=array.getJSONObject(0);
                            JSONArray array1=obj1.getJSONArray("item");
                            for(int i = 0; i < array1.length(); i++) {
                                JSONObject jsonObject = array1.getJSONObject(i);
                                JSONArray linkvalue=jsonObject.getJSONArray("link");
                                String linkvalue1=linkvalue.toString(0);
                                String new_link=linkvalue1.replaceAll("[\\[\\]\"]", "").replaceAll("[\\\n]","");
                                if(new_link.contains("article"))
                                {
                                    JSONArray titlevalue=jsonObject.getJSONArray("title");
                                    String titlevalue1=titlevalue.toString(0);
                                    String new_title=titlevalue1.replaceAll("[\\[\\]\"]", "").replaceAll("[\\\n]","");
                                    JSONArray pubdate=jsonObject.getJSONArray("pubDate");
                                    String pubdate1=pubdate.toString(0);
                                    String new_pubdate=pubdate1.replaceAll("[\\[\\]\"]", "").replaceAll("-0500","").replaceAll("-0400","").replaceAll("[\\\n]","").replaceAll("\\s+$", "");
                                    JSONArray author=jsonObject.getJSONArray("sa:author_name");
                                    String author1=author.toString(0);
                                    String new_author=author1.replaceAll("[\\[\\]\"]", "").replaceAll("[\\\n]","");
                                    Log.d("Length of the channel", new_pubdate);
                                    new_author="Author: "+new_author;
                                    SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd MMM yyyy hh:mm:ss");
                                    Date date1=sdf.parse(new_pubdate);

                                    TimeZone tz = TimeZone.getTimeZone("America/New_York");
                                    String name = tz.getDisplayName(tz.inDaylightTime(date1), TimeZone.LONG);
                                    Log.d("Date in the news", name);
                                    if(name.contains("Standard"))
                                    {
                                        name="EST";
                                    }
                                    else
                                    {
                                        name="EDT";
                                    }
                                    new_pubdate="Date:"+new_pubdate+" "+name;

                                    list.add(new NewsDetail(new_title,new_author,new_pubdate,new_link));
                                }
                            }
                            Log.d("Length of the channel", Integer.toString(array1.length()));
                            mylist.setAdapter(new NewsCustomAdapter(context,list));
                            mylist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                    Object item = adapterView.getItemAtPosition(i);
                                    if (item instanceof NewsDetail) {
                                        //Log.d("News::",((NewsDetail) item).getUrl());
                                        NewsDetail news = (NewsDetail) item;
                                        Uri uri = Uri.parse(news.getNewsurl());
                                        Log.d("NEWS URL", news.getNewsurl());
                                        startActivity(new Intent(Intent.ACTION_VIEW, uri));
                                    }

                                }
                            });

                            //mylist.setAdapter(new NewsCustomAdapter(context,list));
                            linear.setVisibility(View.GONE);

                        }
                        // Try and catch are included to handle any errors due to JSON
                        catch (JSONException e) {
                            // If an error occurs, this prints the error to the log
                            e.printStackTrace();
                            linear.setVisibility(View.GONE);
                            linear1.setVisibility(View.VISIBLE);

                        } /*catch (ParseException e) {
                            e.printStackTrace();
                            linear.setVisibility(View.GONE);

                        }*/ catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                },
                // The final parameter overrides the method onErrorResponse() and passes VolleyError
                //as a parameter
                new Response.ErrorListener() {
                    @Override
                    // Handles errors that occur due to Volley
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Volley", "Error");
                        linear.setVisibility(View.GONE);
                        linear1.setVisibility(View.VISIBLE);

                    }
                }
        );
        requestQueue.add(obreq);
        linear.setVisibility(View.VISIBLE);
        return myview;
    }
    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }
}
