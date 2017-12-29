package com.example.krishmehta.androidapplication;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by krishmehta on 11/18/17.
 */

public class MainActivityCustomAdapter extends BaseAdapter{
    ArrayList<MainActivityDetail> list;
    private static final int STOCK_VALUE=0;
    private static final int STOCK_IMAGE=1;
    private LayoutInflater inflater;
    ArrayList<MainActivityDetail>mylist=new ArrayList<MainActivityDetail>();


    public MainActivityCustomAdapter(Context context, ArrayList<MainActivityDetail> list) {
        this.list=list;
        inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getItemViewType(int position)
    {
        if(list.get(position) instanceof MainActivityDetail)
        {
            return STOCK_VALUE;
        }
        else
        {
            return STOCK_IMAGE;
        }
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view==null)
        {
            switch(getItemViewType(i)){
                case STOCK_VALUE:
                    view = inflater.inflate(R.layout.custommainactivity,null);
                    break;
                case STOCK_IMAGE:
                    break;
            }
        }
        switch(getItemViewType(i)){
            case STOCK_VALUE:
                TextView text=(TextView) view.findViewById(R.id.mainpagestocksymbol);
                TextView text1=(TextView)view.findViewById(R.id.mainpageprice);
                TextView text2=(TextView)view.findViewById(R.id.mainpagechange);
                TextView text3=(TextView)view.findViewById(R.id.mainpagechangepercent);
                TextView text4=(TextView)view.findViewById(R.id.mainpagerightsymbol);
                TextView text5=(TextView)view.findViewById(R.id.mainpageleftsymbol);
                text.setText(((MainActivityDetail)list.get(i)).getMainstockname());
                text1.setText(((MainActivityDetail)list.get(i)).getMainstockprice());
                text2.setText(((MainActivityDetail)list.get(i)).getMainstockchange());
                text3.setText(((MainActivityDetail)list.get(i)).getMainstockchangepercent());
                if(Double.parseDouble(((MainActivityDetail)list.get(i)).getMainstockchange())>0)
                {
                    text2.setTextColor(Color.parseColor("#00FF00"));
                    text3.setTextColor(Color.parseColor("#00FF00"));
                    text4.setTextColor(Color.parseColor("#00FF00"));
                    text5.setTextColor(Color.parseColor("#00FF00"));
                }
                else
                {
                    text2.setTextColor(Color.parseColor("#FF0000"));
                    text3.setTextColor(Color.parseColor("#FF0000"));
                    text4.setTextColor(Color.parseColor("#FF0000"));
                    text5.setTextColor(Color.parseColor("#FF0000"));
                }
                break;
            case STOCK_IMAGE:
                break;
        }
        return view;
    }
   /* public void sortByPriceDesc() {
        Log.d("sort", "innsdode");
        Comparator<MainActivityDetail> comparator = new Comparator<MainActivityDetail>() {

            @Override
            public int compare(MainActivityDetail object1, MainActivityDetail object2) {
                return Double.compare(Double.parseDouble(object2.getMainstockchange()), Double.parseDouble(object1.getMainstockchange()));
            }
        };
        Collections.sort(list, comparator);
        notifyDataSetChanged();
    }*/
    public void sortByNameDesc() {
        Comparator<MainActivityDetail> comparator = new Comparator<MainActivityDetail>() {
            @Override
            public int compare(MainActivityDetail object1, MainActivityDetail object2) {
                Log.d("name of the stock", "hello world");
                return object2.getMainstockname().compareToIgnoreCase(object1.getMainstockname());
            }
        };
        Collections.sort(list,comparator);
        Log.d("inside sort by name", String.valueOf(list));
    }
    public void sortByNameAsce() {
        Comparator<MainActivityDetail> comparator = new Comparator<MainActivityDetail>() {
            @Override
            public int compare(MainActivityDetail object1, MainActivityDetail object2) {
                Log.d("name of the stock", "hello world");
                return object1.getMainstockname().compareToIgnoreCase(object2.getMainstockname());
            }
        };
        Collections.sort(list,comparator);
        Log.d("inside sort by name", String.valueOf(list));
    }
    public void sortByPriceDesc() {
        Comparator<MainActivityDetail> comparator = new Comparator<MainActivityDetail>() {
            @Override
            public int compare(MainActivityDetail object1, MainActivityDetail object2) {
                Log.d("name of the stock", "hello world");
                return Double.compare(Double.parseDouble(object2.getMainstockprice()), Double.parseDouble(object1.getMainstockprice()));
            }
        };
        Collections.sort(list,comparator);
        Log.d("inside sort by name", String.valueOf(list));
    }
    public void sortByPriceAsce() {
        Comparator<MainActivityDetail> comparator = new Comparator<MainActivityDetail>() {
            @Override
            public int compare(MainActivityDetail object1, MainActivityDetail object2) {
                Log.d("name of the stock", "hello world");
                return Double.compare(Double.parseDouble(object1.getMainstockprice()), Double.parseDouble(object2.getMainstockprice()));
            }
        };
        Collections.sort(list,comparator);
        Log.d("inside sort by name", String.valueOf(list));
    }
    public void sortByChangeAsce() {
        Comparator<MainActivityDetail> comparator = new Comparator<MainActivityDetail>() {
            @Override
            public int compare(MainActivityDetail object1, MainActivityDetail object2) {
                Log.d("name of the stock", "hello world");
                return Double.compare(Double.parseDouble(object1.getMainstockchange()), Double.parseDouble(object2.getMainstockchange()));
            }
        };
        Collections.sort(list,comparator);
        Log.d("inside sort by name", String.valueOf(list));
    }
    public void sortByChangeDesc() {
        Comparator<MainActivityDetail> comparator = new Comparator<MainActivityDetail>() {
            @Override
            public int compare(MainActivityDetail object1, MainActivityDetail object2) {
                Log.d("name of the stock", "hello world");
                return Double.compare(Double.parseDouble(object2.getMainstockchange()), Double.parseDouble(object1.getMainstockchange()));
            }
        };
        Collections.sort(list,comparator);
        Log.d("inside sort by name", String.valueOf(list));
    }
    public void sortByChangePercentDesc() {
        Comparator<MainActivityDetail> comparator = new Comparator<MainActivityDetail>() {
            @Override
            public int compare(MainActivityDetail object1, MainActivityDetail object2) {
                Log.d("name of the stock", "hello world");
                return Double.compare(Double.parseDouble(object2.getMainstockchangepercent()), Double.parseDouble(object1.getMainstockchangepercent()));
            }
        };
        Collections.sort(list,comparator);
        Log.d("inside sort by name", String.valueOf(list));
    }
    public void sortByChangePercentAsce() {
        Comparator<MainActivityDetail> comparator = new Comparator<MainActivityDetail>() {
            @Override
            public int compare(MainActivityDetail object1, MainActivityDetail object2) {
                Log.d("name of the stock", "hello world");
                return Double.compare(Double.parseDouble(object1.getMainstockchangepercent()), Double.parseDouble(object2.getMainstockchangepercent()));
            }
        };
        Collections.sort(list,comparator);
        Log.d("inside sort by name", String.valueOf(list));
    }
    //http://www.geeksforgeeks.org/comparator-interface-java/

}
