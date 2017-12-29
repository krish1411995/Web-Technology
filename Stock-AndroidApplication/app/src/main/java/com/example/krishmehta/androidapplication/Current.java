package com.example.krishmehta.androidapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.share.Sharer;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by krishmehta on 11/15/17.
 */

public class Current extends Fragment {
    String stockname1;
    ListView mylist;
    // Defining the Volley request queue that handles the URL request concurrently
    RequestQueue requestQueue;
    LinearLayout linear;
    ImageButton staredbutton;
    String JsonURL="http://krishforandroid-env.us-east-2.elasticbeanstalk.com/stock/table/";
    SharedPreferences favorite;
    Spinner selectchart;
    String selectedchart="";
    String initalvalue="";
    Button displaychart;
    ImageButton shareonfb;
    String sharefburl;
    ShareDialog shareDialog;
    LinearLayout linear1;
    ProgressBar progress;
    ProgressBar progressinlinear;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        final View myview=inflater.inflate(R.layout.current_frag,container,false );
        linear = (LinearLayout) myview.findViewById(R.id.progressbarlinear);
        linear1 = (LinearLayout) myview.findViewById(R.id.progressbarlinear1);
        mylist=(ListView) myview.findViewById(R.id.stocktable);
        shareonfb=(ImageButton) myview.findViewById(R.id.facebookbutton);
        progressinlinear=(ProgressBar) myview.findViewById(R.id.secondBar);
        progressinlinear.setIndeterminate(true);
        progressinlinear.getIndeterminateDrawable().setColorFilter(Color.BLACK, PorterDuff.Mode.MULTIPLY);
        shareonfb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FacebookCallback<Sharer.Result> shareCallback = new FacebookCallback<Sharer.Result>() {
                    @Override
                    public void onCancel() {
                        Log.d("cancled", "fb SHARE canceled");
                    }

                    @Override
                    public void onError(FacebookException error) {
                        Log.d("Error", "fb SHARE error");
                    }

                    @Override
                    public void onSuccess(Sharer.Result result) {

                        String postId = result.getPostId();
                        if (postId != null)
                        {
                            // record successful FB share
                            Log.d("Success", "fb SHARE success");
                        }
                    }
                };
                Log.d("facebook", "inside");
                shareDialog = new ShareDialog(getActivity());
                CallbackManager callbackManager = CallbackManager.Factory.create();
                ShareLinkContent content = new ShareLinkContent.Builder()
                        .setContentUrl(Uri.parse(sharefburl))
                        .build();
                shareDialog.show(content,ShareDialog.Mode.FEED);
                shareDialog.registerCallback(callbackManager, shareCallback);
            }
        });
        //String stockname = getArguments().getString("stockname1");
        //Log.d("Stockname in current", stockname);
        staredbutton = (ImageButton) myview.findViewById(R.id.starbutton);
        final String stockname=((Main2Activity) getActivity()).getResults();
        Log.d("Stockname in current", stockname);
        // Creates the Volley request queue
        requestQueue = Volley.newRequestQueue(this.getContext());
        final Context context=this.getContext();
        /////////////////////////////////////////////////////get the shared preference1st
        SharedPreferences prefs = context.getSharedPreferences("beststocks", context.MODE_PRIVATE);
        ArrayList<String> beststock=new ArrayList<String>();
        try {
            beststock = (ArrayList<String>) ObjectSerializer.deserialize(prefs.getString("beststocks",ObjectSerializer.serialize(new ArrayList<String>())));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        staredbutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                SharedPreferences prefs = context.getSharedPreferences("beststocks", context.MODE_PRIVATE);
                ArrayList<String> beststock=new ArrayList<String>();
                try {
                    beststock = (ArrayList<String>) ObjectSerializer.deserialize(prefs.getString("beststocks",ObjectSerializer.serialize(new ArrayList<String>())));
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                if(beststock.contains(stockname))
                {
                    staredbutton.setImageResource(R.drawable.empty);
                    beststock.remove(stockname);
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

                }
                else
                {
                    staredbutton.setImageResource(R.drawable.filled);
                    beststock.add(stockname);
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
                }

            }
        });


        /////////////////////////////////////////////////////


        if(beststock.contains(stockname))
        {
            staredbutton.setImageResource(R.drawable.filled);
        }
        else
        {
            staredbutton.setImageResource(R.drawable.empty);
        }
        /////////////////////////////////////////////////////
        /////////////////////////////////////////////////////
        /*ArrayList<String> favouritestock=new ArrayList<>();
        // save the task list to preference
        prefs = context.getSharedPreferences("beststocks", Context.MODE_PRIVATE);
        for (String member : favouritestock){
            Log.d("Mname in current: ", member);
        }
        SharedPreferences.Editor editor1 = prefs.edit();
        try {
            editor1.putString("beststocks", ObjectSerializer.serialize(favouritestock));
        } catch (IOException e) {
            e.printStackTrace();
        }
        editor1.commit();*/
        /////////////////////////////////////////////////////
        JsonURL=JsonURL+stockname;
        // Casts results into the TextView found within the main layout XML with id jsonData
        // Creating the JsonObjectRequest class called obreq, passing required parameters:
        //GET is used to fetch data from the server, JsonURL is the URL to be fetched from.
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
                            JSONObject obj=response.getJSONObject("Meta Data");
                            String stocksymbol=obj.getString("2. Symbol");
                            String stocklastrefreshed=obj.getString("3. Last Refreshed");
                            String splitlastrefreshed[]= stocklastrefreshed.split(" ");
                            JSONObject obj1=response.getJSONObject("Time Series (Daily)");
                            JSONObject obj2= obj1.getJSONObject(splitlastrefreshed[0]);
                            String stocklastprice=obj2.getString("4. close");
                            String stockhigh=obj2.getString("2. high");
                            String stocklow=obj2.getString("3. low");
                            String stockopen=obj2.getString("1. open");
                            String stockvolume=obj2.getString("5. volume");
                            Date date = null;
                            if(stocklastrefreshed!=splitlastrefreshed[0])
                            {
                                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                date = sdf.parse(stocklastrefreshed);
                            }
                            else
                            {
                                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                                date = sdf.parse(stocklastrefreshed);

                            }


                            Calendar calendar = Calendar.getInstance();
                            calendar.setTime(date);
                            int hours = calendar.get(Calendar.HOUR_OF_DAY);
                            int minutes = calendar.get(Calendar.MINUTE);
                            int seconds = calendar.get(Calendar.SECOND);
                            Log.d("value of hour min",Integer.toString(hours));
                            Log.d("value of min",Integer.toString(minutes));
                            Log.d("value of sec",Integer.toString(seconds));
                            TimeZone tz = TimeZone.getTimeZone("America/New_York");
                            String name = tz.getDisplayName(tz.inDaylightTime(date), TimeZone.LONG);
                            if(name.contains("Standard"))
                            {
                                name="EST";
                            }
                            else
                            {
                                name="EDT";
                            }
                            Log.d("value of timezone",name);
                            // get the value of the previous close
                            SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd");
                            Date date1=sdf1.parse(splitlastrefreshed[0]);
                            Calendar cal = Calendar.getInstance();
                            String someday="";
                            Date date2=date1;
                            while(someday=="")
                            {
                                cal.setTime(date2);
                                cal.add(Calendar.DATE, -1);
                                date2 = cal.getTime();
                                Log.d("subtrating the date", sdf1.format(date2));
                                String previosdaytofetch=sdf1.format(date2);
                                try
                                {
                                    obj2= obj1.getJSONObject(previosdaytofetch);
                                    someday=obj2.getString("4. close");
                                    Log.d("price of previous day",someday);
                                }catch(JSONException e)
                                {
                                    continue;
                                }
                            }
                            Double change=Double.parseDouble(stocklastprice)-Float.parseFloat(someday);
                            Double changepercent=(change/Float.parseFloat(stocklastprice))*100;
                            String previousclose;
                            String timestamp;
                            if(stocklastrefreshed!=splitlastrefreshed[0])
                            {
                                previousclose=someday;
                                timestamp=stocklastrefreshed+" "+name;

                            }
                            else
                            {
                                previousclose=stocklastprice;
                                timestamp=stocklastrefreshed+" 16:00:00"+" "+name;
                            }
                            //////////////////////////////////
                            double stocklastprice1= Math.round(Double.parseDouble(stocklastprice) * 100.0) / 100.0;
                            stocklastprice=Double.toString(stocklastprice1);
                            change=Math.round(change*100.0)/100.0;
                            changepercent=Math.round(changepercent*100.0)/100.0;
                            double stockopen1=Math.round(Double.parseDouble(stockopen) * 100.0) / 100.0;
                            stockopen=Double.toString(stockopen1);
                            double previousclose1=Math.round(Double.parseDouble(previousclose) * 100.0) / 100.0;
                            previousclose=Double.toString(previousclose1);
                            double stocklow1=Math.round(Double.parseDouble(stocklow) * 100.0) / 100.0;
                            stocklow=Double.toString(stocklow1);
                            double stockhigh1=Math.round(Double.parseDouble(stockhigh) * 100.0) / 100.0;
                            stockhigh=Double.toString(stockhigh1);


                            list.add(new StockDetail("Stock Symbol",stocksymbol));
                            list.add(new StockDetail("Last Price",stocklastprice));
                            if(change>0)
                            {
                                list.add(new StockDetailImage("Change",change+"("+changepercent+"%)",R.drawable.up_arrow));
                            }
                            else
                            {
                                list.add(new StockDetailImage("Change",change+"("+changepercent+"%)",R.drawable.down));
                            }

                            list.add(new StockDetail("Timestamp",timestamp));
                            list.add(new StockDetail("Open",stockopen));
                            list.add(new StockDetail("Close",previousclose));
                            list.add(new StockDetail("Day's Range",stocklow+"-"+stockhigh));
                            list.add(new StockDetail("Volume",stockvolume));
                            for (int i = 0 ; i < list.size() ; i++)
                                Log.d("value is" , list.get(i).toString());
                            mylist.setAdapter(new CurrentCustomAdapter(context,list));
                            setListViewHeightBasedOnChildren(mylist);


                            linear.setVisibility(View.GONE);

                        }
                        // Try and catch are included to handle any errors due to JSON
                        catch (JSONException e) {
                            // If an error occurs, this prints the error to the log
                            e.printStackTrace();
                            linear1.setVisibility(View.VISIBLE);
                            WebView thecharts1=(WebView) myview.findViewById(R.id.displayallgraph);
                            thecharts1.setVisibility(View.GONE);
                            linear.setVisibility(View.GONE);

                        } catch (ParseException e) {
                            e.printStackTrace();

                            linear1.setVisibility(View.VISIBLE);
                            WebView thecharts1=(WebView) myview.findViewById(R.id.displayallgraph);
                            thecharts1.setVisibility(View.GONE);
                            linear.setVisibility(View.GONE);

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

                        linear1.setVisibility(View.VISIBLE);
                        WebView thecharts1=(WebView) myview.findViewById(R.id.displayallgraph);
                        thecharts1.setVisibility(View.GONE);
                        linear.setVisibility(View.GONE);

                    }
                }
        );

        // Adds the JSON object request "obreq" to the request queue
        int socketTimeout = 10000;//30 seconds - change to what you want
        obreq.setRetryPolicy(new DefaultRetryPolicy(
                socketTimeout,
                0,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(obreq);
        linear.setVisibility(View.VISIBLE);
        //initialize the progress dialog and show it
        displaychart=(Button)myview.findViewById(R.id.buttontograph);
        displaychart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("inside the display", "Hello world");
                initalvalue=selectedchart;
                displaychart.setEnabled(false);
                WebView thecharts=(WebView) myview.findViewById(R.id.displayallgraph);
                Log.d("value of context", String.valueOf(getActivity()));
                final MyJavaScriptInterface myJavaScriptInterface = new MyJavaScriptInterface(getActivity());
                thecharts.addJavascriptInterface(myJavaScriptInterface,"AndroidFunction");
                String url="http://krishonlychart-env.us-east-2.elasticbeanstalk.com/practice.html?query="+selectedchart+"&stock="+stockname;
                //String url="http://localhost/practice.html?query=%22Price%22&stock=%22MSFT%22";
                thecharts.getSettings().setJavaScriptEnabled(true);
                //thecharts.getSettings().setBuiltInZoomControls(true);
                //thecharts.getSettings().setSupportZoom(true);
                thecharts.getSettings().setLoadWithOverviewMode(true);
                thecharts.getSettings().setUseWideViewPort(true);
                //thecharts.setInitialScale(100);
                progress = (ProgressBar) myview.findViewById(R.id.secondBar3);
                progress.setIndeterminate(true);
                progress.getIndeterminateDrawable().setColorFilter(Color.BLACK, PorterDuff.Mode.MULTIPLY);
                progress.setVisibility(View.VISIBLE);
                thecharts.loadUrl(url);
            }
        });
        selectchart=(Spinner)myview.findViewById(R.id.graphspinner);
        selectchart.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                selectedchart=selectchart.getSelectedItem().toString();
                if(initalvalue==selectedchart)
                {
                    displaychart.setEnabled(false);
                }
                else
                {
                    displaychart.setEnabled(true);
                }
                //Log.d("sortbyvalue", String.valueOf(arg2));
                //Log.d("sortbyvalue1", sortbyvalue);
                //arg1.setEnabled(false);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });
        if(selectedchart=="")
        {

            WebView thecharts=(WebView) myview.findViewById(R.id.displayallgraph);
            String url="http://krishonlychart-env.us-east-2.elasticbeanstalk.com/practice.html?query=\"Price\"&stock="+stockname;
            final MyJavaScriptInterface myJavaScriptInterface = new MyJavaScriptInterface(getActivity());
            thecharts.addJavascriptInterface(myJavaScriptInterface,"AndroidFunction");
            //String url="http://localhost/practice.html?query=%22Price%22&stock=%22MSFT%22";
            thecharts.getSettings().setJavaScriptEnabled(true);
            //thecharts.getSettings().setBuiltInZoomControls(true);
            //thecharts.getSettings().setSupportZoom(true);
            thecharts.getSettings().setLoadWithOverviewMode(true);
            thecharts.getSettings().setUseWideViewPort(true);
            //thecharts.setInitialScale(100);
            thecharts.loadUrl(url);
            thecharts.setWebViewClient(new WebViewClient() {

                public void onPageFinished(WebView view, String url) {
                    // do your stuff here
                    Log.d("IN pagefiished", "oFinishedloading");
                    //progress.setVisibility(View.GONE);
                }
            });
            //////////////////////////////////
            progress = (ProgressBar) myview.findViewById(R.id.secondBar3);
            progress.setIndeterminate(true);
            progress.getIndeterminateDrawable().setColorFilter(Color.BLACK, PorterDuff.Mode.MULTIPLY);
            progress.setVisibility(View.VISIBLE);
            ///////////////////////////////////
        }




        return myview;

    }
    public class MyJavaScriptInterface {

        Context mContext;

        MyJavaScriptInterface(Context c) {
            mContext = c;
        }

        @JavascriptInterface
        public void showtheimageurl(String toast) {
            Log.d("insodejava", "showtheimageurl:");
            Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
            sharefburl=toast;
            //hidetheprogressbar();
            TimerMethod();
            //progress = (ProgressBar) .findViewById(R.id.secondBar3);
            //progress.setVisibility(ProgressBar.GONE);
        }
    }
    private void TimerMethod() {
        //This method is called directly by the timer
        //and runs in the same thread as the timer.

        //We call the method that will work with the UI
        //through the runOnUiThread method.
        getActivity().runOnUiThread(Timer_Tick);
    }


    private Runnable Timer_Tick = new Runnable() {
        public void run() {

            hidetheprogressbar();

        }
    };
    public void hidetheprogressbar()
    {
        progress.setVisibility(View.GONE);
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
