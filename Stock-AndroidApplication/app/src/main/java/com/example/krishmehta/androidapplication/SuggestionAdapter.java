package com.example.krishmehta.androidapplication;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Filter;
import android.widget.ProgressBar;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by krishmehta on 11/13/17.
 */

public class SuggestionAdapter extends ArrayAdapter<String> {
    ProgressBar progressofauto;
    Activity context1;

    protected static final String TAG = "SuggestionAdapter";
    private List<String> suggestions;
    public SuggestionAdapter(Activity context, String nameFilter) {
        super(context, android.R.layout.simple_dropdown_item_1line);
        suggestions = new ArrayList<String>();
        context1=context;
    }

    @Override
    public int getCount() {
        return suggestions.size();
    }

    @Override
    public String getItem(int index) {
        return suggestions.get(index);
    }

    @Override
    public Filter getFilter() {
        Filter myFilter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults = new FilterResults();
                progressofauto=(ProgressBar) context1.findViewById(R.id.pb_loading_indicator);
                Log.d("Value of Progress auto", String.valueOf(progressofauto));

                //progressofauto.setVisibility(View.VISIBLE);
                TimerMethod();
                AutoCompleteTextView accomplete = (AutoCompleteTextView) context1.findViewById(R.id.autoCompleteTextView);
                if(accomplete.getText().toString().matches("") )
                {
                    TimerMethod1();
                }
                JsonParse jp=new JsonParse();

                if (constraint != null) {
                    // A class that queries a web API, parses the data and
                    // returns an ArrayList<GoEuroGetSet>
                    List<SuggestGetSet> new_suggestions =jp.getParseJsonWCF(constraint.toString());
                    TimerMethod1();
                    suggestions.clear();
                    if(new_suggestions.size()<=5)
                    {
                        for (int i=0;i<new_suggestions.size();i++) {
                            suggestions.add(new_suggestions.get(i).getId()+" - "+new_suggestions.get(i).getName()+"("+new_suggestions.get(i).getExchange()+")");
                        }
                    }
                    else
                    {
                        for (int i=0;i<5;i++) {
                            suggestions.add(new_suggestions.get(i).getId()+" - "+new_suggestions.get(i).getName()+"("+new_suggestions.get(i).getExchange()+")");
                        }
                    }


                    // Now assign the values and count to the FilterResults
                    // object
                    filterResults.values = suggestions;
                    filterResults.count = suggestions.size();
                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence contraint,
                                          FilterResults results) {
                if (results != null && results.count > 0) {

                    notifyDataSetChanged();
                } else {
                    notifyDataSetInvalidated();
                }
            }
        };
        return myFilter;
    }
    public class JsonParse {
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
    private void TimerMethod() {
        //This method is called directly by the timer
        //and runs in the same thread as the timer.

        //We call the method that will work with the UI
        //through the runOnUiThread method.
        context1.runOnUiThread(Timer_Tick);
    }
    private void TimerMethod1() {
        //This method is called directly by the timer
        //and runs in the same thread as the timer.

        //We call the method that will work with the UI
        //through the runOnUiThread method.
        context1.runOnUiThread(Timer_Tick1);
    }
    private Runnable Timer_Tick1 = new Runnable() {
        public void run() {

            hidetheprogressbar1();

        }
    };
    public void hidetheprogressbar1()
    {
        progressofauto.setIndeterminate(true);
        progressofauto.getIndeterminateDrawable().setColorFilter(Color.BLACK, PorterDuff.Mode.MULTIPLY);
        progressofauto.setVisibility(View.INVISIBLE);
    }

    private Runnable Timer_Tick = new Runnable() {
        public void run() {

            hidetheprogressbar();

        }
    };
    public void hidetheprogressbar()
    {
        progressofauto.setIndeterminate(true);
        progressofauto.getIndeterminateDrawable().setColorFilter(Color.BLACK, PorterDuff.Mode.MULTIPLY);
        progressofauto.setVisibility(View.VISIBLE);
    }

}

