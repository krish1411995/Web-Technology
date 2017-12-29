package com.example.krishmehta.androidapplication;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by krishmehta on 11/15/17.
 */

public class History extends Fragment {
    WebView theHistorycharts;
    LinearLayout linear;
    ProgressBar progress;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        RequestQueue requestQueue;
        String JsonURL="http://krishforandroid-env.us-east-2.elasticbeanstalk.com/stock/table/";
        View myview=inflater.inflate(R.layout.history_frag,container,false );
        linear=(LinearLayout) myview.findViewById(R.id.progressbarlinear2);
        progress=(ProgressBar)myview.findViewById(R.id.progressBar);
        theHistorycharts=(WebView) myview.findViewById(R.id.historychart);
        final String stockname=((Main2Activity) getActivity()).getResults();
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////
        requestQueue = Volley.newRequestQueue(this.getContext());

        linear.setVisibility(View.GONE);
        theHistorycharts.setVisibility(View.VISIBLE);
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
                            JSONObject obj=response.getJSONObject("Meta Data");
                            String stocksymbol=obj.getString("2. Symbol");
                            String stocklastrefreshed=obj.getString("3. Last Refreshed");
                            //progress.setVisibility(View.GONE);

                        }
                        // Try and catch are included to handle any errors due to JSON
                        catch (JSONException e) {
                            // If an error occurs, this prints the error to the log
                            Log.d("In highcharts", "in highcharts");
                            e.printStackTrace();
                            linear.setVisibility(View.VISIBLE);
                            theHistorycharts.setVisibility(View.GONE);
                            //progress.setVisibility(View.GONE);

                        } /*catch (ParseException e) {
                            e.printStackTrace();
                            linear.setVisibility(View.GONE);

                        }*/
                    }
                },
                // The final parameter overrides the method onErrorResponse() and passes VolleyError
                //as a parameter
                new Response.ErrorListener() {
                    @Override
                    // Handles errors that occur due to Volley
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Volley", "Error in highcharts");
                        linear.setVisibility(View.VISIBLE);
                        theHistorycharts.setVisibility(View.GONE);
                        //progress.setVisibility(View.GONE);

                    }
                }
        );
        requestQueue.add(obreq);
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////
        String url="http://historychartpart2-env.us-east-2.elasticbeanstalk.com/historycharts.html?query="+stockname;
        //String url="http://localhost/practice.html?query=%22Price%22&stock=%22MSFT%22";
        final History.MyJavaScriptInterface myJavaScriptInterface = new History.MyJavaScriptInterface(getActivity());
        theHistorycharts.addJavascriptInterface(myJavaScriptInterface,"AndroidFunction");
        theHistorycharts.getSettings().setJavaScriptEnabled(true);
        //thecharts.getSettings().setBuiltInZoomControls(true);
        //thecharts.getSettings().setSupportZoom(true);
        theHistorycharts.getSettings().setLoadWithOverviewMode(true);
        theHistorycharts.getSettings().setUseWideViewPort(true);
        //thecharts.setInitialScale(100);
        progress.setIndeterminate(true);
        progress.getIndeterminateDrawable().setColorFilter(Color.BLACK, PorterDuff.Mode.MULTIPLY);
        progress.setVisibility(View.VISIBLE);

        theHistorycharts.loadUrl(url);
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
            //sharefburl=toast;
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
}
