package com.example.krishmehta.androidapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    Button get_quote, clear;
    AutoCompleteTextView acTextView;
    Spinner sortbyspinner;
    Spinner sortbyspinner1;
    SharedPreferences favourite;
    private ProgressBar mLoadingIndicator;
    ArrayList<String> beststock;
    ListView mylist;
    Context context;
    Switch switchauto;
    int i = 0;
    LinearLayout mainpage;
    ImageButton refreshbutton;
    ArrayList<MainActivityDetail> list;
    Timer t = new Timer();
    MainActivityCustomAdapter mAdapter;
    ProgressBar progressinlinear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        acTextView = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);
        acTextView.setAdapter(new SuggestionAdapter(this, acTextView.getText().toString()));
        progressinlinear=(ProgressBar) findViewById(R.id.secondBar3);
        progressinlinear.setIndeterminate(true);
        progressinlinear.getIndeterminateDrawable().setColorFilter(Color.BLACK, PorterDuff.Mode.MULTIPLY);
        get_quote = (Button) findViewById(R.id.button_getquote);
        clear = (Button) findViewById(R.id.button_clear);
        refreshbutton = (ImageButton) findViewById(R.id.imageButton);
        refreshbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RefeshStock();
            }
        });
        switchauto = (Switch) findViewById(R.id.switch1);
        switchauto.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                Log.d("TIMER BOOLEAN", String.valueOf(b));
                if (b == true) {
                    t.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            TimerMethod();
                        }

                    }, 0, 5000);
                   /* t.scheduleAtFixedRate(
                            new TimerTask()
                            {

                                public void run()
                                {
                                    RefeshStock();
                                }
                            },
                            0,      // run first occurrence immediatetly
                            5000);*/
                } else {
                    t.cancel();
                    t = new Timer();
                    //t.purge();
                }
            }
        });
        ////////////////////////////////////////////////////////////////
        favourite = getSharedPreferences("favouritestockvalue", MODE_PRIVATE);
        String channel = (favourite.getString("favouritestockvalue", ""));
        Log.d("shared prefrence", channel);
        //////////////////////////////////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////////////
        // load tasks from preference
        sortbyspinner = (Spinner) findViewById(R.id.orderby1);
        sortbyspinner1=(Spinner) findViewById(R.id.sortby1);


        sortbyspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                String sortbyvalue = sortbyspinner.getSelectedItem().toString();
                String sortbyvalue1=sortbyspinner1.getSelectedItem().toString();
                Log.d("sortbyvalue1", sortbyvalue1);
                Log.d("sortbyvalue", sortbyvalue);
                mAdapter=new MainActivityCustomAdapter(context, list);

                if(sortbyvalue.equalsIgnoreCase("Symbol") && sortbyvalue1.equalsIgnoreCase("Descending"))
                {
                    mAdapter.sortByNameDesc();
                }
                else if(sortbyvalue.equalsIgnoreCase("Symbol") && sortbyvalue1.equalsIgnoreCase("Ascending"))
                {
                    mAdapter.sortByNameAsce();
                }else if(sortbyvalue.equalsIgnoreCase("Price") && sortbyvalue1.equalsIgnoreCase("Descending"))
                {
                    mAdapter.sortByPriceDesc();
                }
                else if(sortbyvalue.equalsIgnoreCase("Price") && sortbyvalue1.equalsIgnoreCase("Ascending"))
                {
                    mAdapter.sortByPriceAsce();
                }
                else if(sortbyvalue.equalsIgnoreCase("Change") && sortbyvalue1.equalsIgnoreCase("Ascending"))
                {
                    mAdapter.sortByChangeAsce();
                }
                else if(sortbyvalue.equalsIgnoreCase("Change") && sortbyvalue1.equalsIgnoreCase("Descending"))
                {
                    mAdapter.sortByChangeDesc();
                }
                else if(sortbyvalue.equalsIgnoreCase("Change Percent") && sortbyvalue1.equalsIgnoreCase("Descending"))
                {
                    mAdapter.sortByChangePercentDesc();
                }
                else if(sortbyvalue.equalsIgnoreCase("Change Percent") && sortbyvalue1.equalsIgnoreCase("Ascending"))
                {
                    mAdapter.sortByChangePercentAsce();
                }
                //Log.d("sortbyvalue", String.valueOf(arg2));
                //Log.d("sortbyvalue1", sortbyvalue1);
                //Log.d("value of list", String.valueOf(list));
                //if()


                Log.d("outside the loop", String.valueOf(list));
                mylist.setAdapter(new MainActivityCustomAdapter(context,list));

                mAdapter.notifyDataSetChanged();
                Log.d("Hello", "HelloWOrk");
                //arg1.setEnabled(false);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }

        });

        sortbyspinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                String sortbyvalue = sortbyspinner.getSelectedItem().toString();
                String sortbyvalue1=sortbyspinner1.getSelectedItem().toString();
                Log.d("sortbyvalue1", sortbyvalue1);
                Log.d("sortbyvalue", sortbyvalue);
                mAdapter=new MainActivityCustomAdapter(context, list);

                if(sortbyvalue.equalsIgnoreCase("Symbol") && sortbyvalue1.equalsIgnoreCase("Descending"))
                {
                    mAdapter.sortByNameDesc();
                }
                else if(sortbyvalue.equalsIgnoreCase("Symbol") && sortbyvalue1.equalsIgnoreCase("Ascending"))
                {
                    mAdapter.sortByNameAsce();
                }else if(sortbyvalue.equalsIgnoreCase("Price") && sortbyvalue1.equalsIgnoreCase("Descending"))
                {
                    mAdapter.sortByPriceDesc();
                }
                else if(sortbyvalue.equalsIgnoreCase("Price") && sortbyvalue1.equalsIgnoreCase("Ascending"))
                {
                    mAdapter.sortByPriceAsce();
                }
                else if(sortbyvalue.equalsIgnoreCase("Change") && sortbyvalue1.equalsIgnoreCase("Ascending"))
                {
                    mAdapter.sortByChangeAsce();
                }
                else if(sortbyvalue.equalsIgnoreCase("Change") && sortbyvalue1.equalsIgnoreCase("Descending"))
                {
                    mAdapter.sortByChangeDesc();
                }
                else if(sortbyvalue.equalsIgnoreCase("Change Percent") && sortbyvalue1.equalsIgnoreCase("Descending"))
                {
                    mAdapter.sortByChangePercentDesc();
                }
                else if(sortbyvalue.equalsIgnoreCase("Change Percent") && sortbyvalue1.equalsIgnoreCase("Ascending"))
                {
                    mAdapter.sortByChangePercentAsce();
                }
                //Log.d("sortbyvalue", String.valueOf(arg2));
                //Log.d("sortbyvalue1", sortbyvalue1);
                //Log.d("value of list", String.valueOf(list));
                //if()


                Log.d("outside the loop", String.valueOf(list));
                mylist.setAdapter(new MainActivityCustomAdapter(context,list));

                mAdapter.notifyDataSetChanged();
                Log.d("Hello", "HelloWOrk");
                //arg1.setEnabled(false);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }

        });
        //////////////////////////////////////////////////////////////////////////////
        get_quote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String temp = acTextView.getText().toString();
                Log.d("value_of_temp", temp);
                if (temp.matches("") || temp.trim().length() == 0) {
                    Toast.makeText(MainActivity.this, "Please enter a stock name or a symbol", Toast.LENGTH_LONG).show();
                } else {
                    //Toast.makeText(MainActivity.this, acTextView.getText(), Toast.LENGTH_LONG).show();
                    String[] splited = temp.split("\\s+");
                    //Toast.makeText(MainActivity.this, splited[0], Toast.LENGTH_Short).show();
                    Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("stockname", splited[0]);
                    //getSupportActionBar().setTitle(splited[0]);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }
        });
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                acTextView.setText("");
                ProgressBar progressBarofauto = (ProgressBar) findViewById(R.id.pb_loading_indicator);
                progressBarofauto.setVisibility(View.INVISIBLE);
            }
        });

    }

    private void TimerMethod() {
        //This method is called directly by the timer
        //and runs in the same thread as the timer.

        //We call the method that will work with the UI
        //through the runOnUiThread method.
        this.runOnUiThread(Timer_Tick);
    }


    private Runnable Timer_Tick = new Runnable() {
        public void run() {

            RefeshStock();

        }
    };


    @Override
    protected void onResume() {
        context=this.getApplicationContext();
        i=0;
        super.onResume();
        mainpage=(LinearLayout) findViewById(R.id.mainpageprogressbar);
        mainpage.setVisibility(View.VISIBLE);
        ArrayList<Object> abc;
        SharedPreferences prefs = getSharedPreferences("beststocks", MODE_PRIVATE);
        beststock=new ArrayList<String>();
        try {
            beststock = (ArrayList<String>) ObjectSerializer.deserialize(prefs.getString("beststocks",ObjectSerializer.serialize(new ArrayList<String>())));
            Log.d("my best stock", "My stock");

            for (String member : beststock){
                Log.d("Member name: ", member);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        mylist=(ListView) findViewById(R.id.mainpagelist);
        list=new ArrayList<>();
        if(beststock.size()==0)
        {
            mylist.setAdapter(new MainActivityCustomAdapter(context,list));
            mainpage.setVisibility(View.GONE);
        }
        for(int j=0;j<beststock.size();j++) {
            String url = "http://krishforandroid-env.us-east-2.elasticbeanstalk.com/stock/table/" + beststock.get(j);
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            JsonObjectRequest obreq = new JsonObjectRequest(Request.Method.GET, url, null,
                    // The third parameter Listener overrides the method onResponse() and passes
                    //JSONObject as a parameter
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                //ArrayList<Object> list = new ArrayList<>();
                                JSONObject obj = response.getJSONObject("Meta Data");
                                String stocksymbol = obj.getString("2. Symbol");
                                String stocklastrefreshed = obj.getString("3. Last Refreshed");
                                String splitlastrefreshed[] = stocklastrefreshed.split(" ");
                                JSONObject obj1 = response.getJSONObject("Time Series (Daily)");
                                JSONObject obj2 = obj1.getJSONObject(splitlastrefreshed[0]);
                                String stocklastprice = obj2.getString("4. close");
                                SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
                                Date date1 = sdf1.parse(splitlastrefreshed[0]);
                                Calendar cal = Calendar.getInstance();
                                String someday = "";
                                Date date2 = date1;
                                while (someday == "") {
                                    cal.setTime(date2);
                                    cal.add(Calendar.DATE, -1);
                                    date2 = cal.getTime();
                                    Log.d("subtrating the date", sdf1.format(date2));
                                    String previosdaytofetch = sdf1.format(date2);
                                    try {
                                        obj2 = obj1.getJSONObject(previosdaytofetch);
                                        someday = obj2.getString("4. close");
                                        Log.d("price of previous day", someday);
                                    } catch (JSONException e) {
                                        continue;
                                    }
                                }
                                Double change = Double.parseDouble(stocklastprice) - Float.parseFloat(someday);
                                Double changepercent = (change / Float.parseFloat(stocklastprice)) * 100;
                                double stocklastprice1 = Math.round(Double.parseDouble(stocklastprice) * 100.0) / 100.0;
                                stocklastprice = Double.toString(stocklastprice1);
                                change = Math.round(change * 100.0) / 100.0;
                                changepercent = Math.round(changepercent * 100.0) / 100.0;
                                Log.d("Stock in Loop", stocksymbol);
                                Log.d("The change", Double.toString(change));
                                list.add(new MainActivityDetail(stocksymbol,stocklastprice,Double.toString(change),Double.toString(changepercent)));
                                Log.d("size of the list",Integer.toString(list.size()));
                                Log.d("size of the beststock",Integer.toString(beststock.size()));
                                i++;
                                if(i>=beststock.size())
                                {
                                    Log.d("This is the last","last");
                                    mylist.setAdapter(new MainActivityCustomAdapter(context,list));
                                    mainpage.setVisibility(View.GONE);
                                }
                            } catch (JSONException e) {
                                // If an error occurs, this prints the error to the log
                                e.printStackTrace();
                                i++;
                                if(i>=beststock.size())
                                {
                                    Log.d("This is the last","last");
                                    mylist.setAdapter(new MainActivityCustomAdapter(context,list));
                                    mainpage.setVisibility(View.GONE);
                                }

                            } catch (ParseException e) {
                                e.printStackTrace();
                                i++;
                                if(i>=beststock.size())
                                {
                                    Log.d("This is the last","last");
                                    mylist.setAdapter(new MainActivityCustomAdapter(context,list));
                                    mainpage.setVisibility(View.GONE);
                                }

                            }

                        }
                    }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("Volley", "Error");
                    i++;
                    if(i>=beststock.size())
                    {
                        Log.d("This is the last","last");
                        mylist.setAdapter(new MainActivityCustomAdapter(context,list));
                        mainpage.setVisibility(View.GONE);
                    }
                }
            });
            int socketTimeout = 10000;//30 seconds - change to what you want
            /*RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
            obreq.setRetryPolicy(policy);
            requestQueue.add(obreq);*/
            obreq.setRetryPolicy(new DefaultRetryPolicy(
                    socketTimeout,
                    0,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            requestQueue.add(obreq);
        }
        Log.d("afterfor", "after for");
        mylist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Object item = adapterView.getItemAtPosition(i);
                if (item instanceof MainActivityDetail) {
                    //Log.d("News::",((NewsDetail) item).getUrl());
                    MainActivityDetail stocksvalue = (MainActivityDetail) item;
                    //Log.d("NEWS URL", news.getMainstockchange());
                    String stocksvalue1=stocksvalue.getMainstockname();
                    Intent intent= new Intent(MainActivity.this,Main2Activity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("stockname",stocksvalue1);
                    //getSupportActionBar().setTitle(splited[0]);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }
        });
        registerForContextMenu(mylist);

        //mylist.setAdapter(new MainActivityCustomAdapter(this,list));

    }
    public void RefeshStock()
    {
        context=this.getApplicationContext();
        i=0;
        //super.onResume();
        mainpage=(LinearLayout) findViewById(R.id.mainpageprogressbar);
        mainpage.setVisibility(View.VISIBLE);
        ArrayList<Object> abc;
        SharedPreferences prefs = getSharedPreferences("beststocks", MODE_PRIVATE);
        beststock=new ArrayList<String>();
        try {
            beststock = (ArrayList<String>) ObjectSerializer.deserialize(prefs.getString("beststocks",ObjectSerializer.serialize(new ArrayList<String>())));
            Log.d("my best stock", "My stock");

            for (String member : beststock){
                Log.d("Member name: ", member);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        mylist=(ListView) findViewById(R.id.mainpagelist);
        list=new ArrayList<>();
        if(beststock.size()==0)
        {
            mylist.setAdapter(new MainActivityCustomAdapter(context,list));
            mainpage.setVisibility(View.GONE);
        }
        for(int j=0;j<beststock.size();j++) {
            String url = "http://krishforandroid-env.us-east-2.elasticbeanstalk.com/stock/table/" + beststock.get(j);
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            JsonObjectRequest obreq = new JsonObjectRequest(Request.Method.GET, url, null,
                    // The third parameter Listener overrides the method onResponse() and passes
                    //JSONObject as a parameter
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                //ArrayList<Object> list = new ArrayList<>();
                                JSONObject obj = response.getJSONObject("Meta Data");
                                String stocksymbol = obj.getString("2. Symbol");
                                String stocklastrefreshed = obj.getString("3. Last Refreshed");
                                String splitlastrefreshed[] = stocklastrefreshed.split(" ");
                                JSONObject obj1 = response.getJSONObject("Time Series (Daily)");
                                JSONObject obj2 = obj1.getJSONObject(splitlastrefreshed[0]);
                                String stocklastprice = obj2.getString("4. close");
                                SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
                                Date date1 = sdf1.parse(splitlastrefreshed[0]);
                                Calendar cal = Calendar.getInstance();
                                String someday = "";
                                Date date2 = date1;
                                while (someday == "") {
                                    cal.setTime(date2);
                                    cal.add(Calendar.DATE, -1);
                                    date2 = cal.getTime();
                                    Log.d("subtrating the date", sdf1.format(date2));
                                    String previosdaytofetch = sdf1.format(date2);
                                    try {
                                        obj2 = obj1.getJSONObject(previosdaytofetch);
                                        someday = obj2.getString("4. close");
                                        Log.d("price of previous day", someday);
                                    } catch (JSONException e) {
                                        continue;
                                    }
                                }
                                Double change = Double.parseDouble(stocklastprice) - Float.parseFloat(someday);
                                Double changepercent = (change / Float.parseFloat(stocklastprice)) * 100;
                                double stocklastprice1 = Math.round(Double.parseDouble(stocklastprice) * 100.0) / 100.0;
                                stocklastprice = Double.toString(stocklastprice1);
                                change = Math.round(change * 100.0) / 100.0;
                                changepercent = Math.round(changepercent * 100.0) / 100.0;
                                Log.d("Stock in Loop", stocksymbol);
                                Log.d("The change", Double.toString(change));
                                list.add(new MainActivityDetail(stocksymbol,stocklastprice,Double.toString(change),Double.toString(changepercent)));
                                Log.d("size of the list",Integer.toString(list.size()));
                                Log.d("size of the beststock",Integer.toString(beststock.size()));
                                i++;
                                if(i>=beststock.size())
                                {
                                    Log.d("This is the last","last");
                                    mylist.setAdapter(new MainActivityCustomAdapter(context,list));
                                    mainpage.setVisibility(View.GONE);
                                }
                            } catch (JSONException e) {
                                // If an error occurs, this prints the error to the log
                                e.printStackTrace();
                                i++;
                                if(i>=beststock.size())
                                {
                                    Log.d("This is the last","last");
                                    mylist.setAdapter(new MainActivityCustomAdapter(context,list));
                                    mainpage.setVisibility(View.GONE);
                                }

                            } catch (ParseException e) {
                                e.printStackTrace();
                                i++;
                                if(i>=beststock.size())
                                {
                                    Log.d("This is the last","last");
                                    mylist.setAdapter(new MainActivityCustomAdapter(context,list));
                                    mainpage.setVisibility(View.GONE);
                                }

                            }

                        }
                    }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("Volley", "Error");
                    i++;
                    if(i>=beststock.size())
                    {
                        Log.d("This is the last","last");
                        mylist.setAdapter(new MainActivityCustomAdapter(context,list));
                        mainpage.setVisibility(View.GONE);
                    }
                }
            });
            int socketTimeout = 10000;//30 seconds - change to what you want
            obreq.setRetryPolicy(new DefaultRetryPolicy(
                    socketTimeout,
                    0,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            requestQueue.add(obreq);
        }
        //mylist.setAdapter(new MainActivityCustomAdapter(this,list));

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        //mylist=(ListView) findViewById(R.id.mainpagelist);
        if (v.getId()==R.id.mainpagelist) {
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuInfo;
            menu.setHeaderTitle("Remove from Favourites?");
            String[] menuItems ={"No","Yes"};
            for (int i = 0; i<menuItems.length; i++) {
                menu.add(Menu.NONE, i, i, menuItems[i]);
            }
        }
    }
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        //AdapterView.AdapterContextMenuInfo info1 = (AdapterView.AdapterContextMenuInfo)item;
        int menuItemIndex = item.getItemId();
        String[] menuItems ={"No","Yes"};
        mylist=(ListView) findViewById(R.id.mainpagelist);
        Object item1 =  mylist.getAdapter().getItem(info.position);
        Boolean hello=item1 instanceof MainActivityDetail;
        Log.d("Value of Boolean", String.valueOf(hello));
        String menuItemName = menuItems[menuItemIndex];
        MainActivityDetail stocksvalue = (MainActivityDetail) item1;
        String stocksvalue1=stocksvalue.getMainstockname();
        Log.d("Value of Boolean", stocksvalue1);
        Log.d("Value of Boolean", menuItemName);
        if(menuItemName=="Yes")
        {
            SharedPreferences prefs = context.getSharedPreferences("beststocks", context.MODE_PRIVATE);
            ArrayList<String> beststock=new ArrayList<String>();
            try {
                beststock = (ArrayList<String>) ObjectSerializer.deserialize(prefs.getString("beststocks",ObjectSerializer.serialize(new ArrayList<String>())));
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            beststock.remove(stocksvalue1);
            // save the task list to preference
            prefs = context.getSharedPreferences("beststocks", Context.MODE_PRIVATE);
            for (String member : beststock){
                Log.d("Mname in current: ", member);
            }
            SharedPreferences.Editor editor1 = prefs.edit();
            try {
                editor1.putString("beststocks", ObjectSerializer.serialize(beststock));
            } catch (IOException e) {
                e.printStackTrace();
            }
            editor1.commit();
            RefeshStock();
            Toast.makeText(this, "Selected Yes", Toast.LENGTH_SHORT).show();
        }
        else if(menuItemName=="No")
        {
            Toast.makeText(this, "Selected No", Toast.LENGTH_SHORT).show();
        }
        //mylist.setAdapter(new MainActivityCustomAdapter(context,list));
        //Object item = (Object) getListAdapter().getItem(info.position);
        //String menuItemName = menuItems[menuItemIndex];


        //TextView text = (TextView)findViewById(R.id.footer);
        //text.setText(String.format("Selected %s for item %s", menuItemName, listItemName));
        return true;
    }

}
