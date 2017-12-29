package com.example.krishmehta.androidapplication;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by krishmehta on 11/16/17.
 */

public class CurrentCustomAdapter extends BaseAdapter {
    ArrayList<Object> list;
    private static final int STOCK_VALUE=0;
    private static final int STOCK_IMAGE=1;
    private LayoutInflater inflater;

    public CurrentCustomAdapter(Context context,ArrayList<Object> list){
        this.list=list;
        inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }
    @Override
    public int getItemViewType(int position)
    {
        if(list.get(position) instanceof StockDetail)
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
                    view = inflater.inflate(R.layout.customcurrent,null);
                    break;
                case STOCK_IMAGE:
                    view=inflater.inflate(R.layout.customcurrentwithimage,null);
                    break;
            }
        }
        Log.d("value of i in adapter",Integer.toString(i));
        switch(getItemViewType(i)){
            case STOCK_VALUE:
                TextView text=(TextView) view.findViewById(R.id.staticname);
                TextView text1=(TextView)view.findViewById(R.id.dynamicname);
                text.setText(((StockDetail)list.get(i)).getStaticvalue());
                text1.setText(((StockDetail)list.get(i)).getDynamicvalue());
                break;
            case STOCK_IMAGE:
                TextView text2=(TextView)view.findViewById(R.id.staticname1);
                TextView text3=(TextView)view.findViewById(R.id.dynamicname1);
                ImageView image=(ImageView) view.findViewById(R.id.imagestatus);
                text2.setText(((StockDetailImage)list.get(i)).getStaticvalue1());
                text3.setText(((StockDetailImage)list.get(i)).getDynamicvalue1());
                image.setImageResource(((StockDetailImage)list.get(i)).getImagevalue1());

                break;
        }
        return view;
    }
}
