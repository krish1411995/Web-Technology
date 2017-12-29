package com.example.krishmehta.androidapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by krishmehta on 11/17/17.
 */

public class NewsCustomAdapter extends BaseAdapter{
    ArrayList<Object> list;
    private static final int STOCK_VALUE=0;
    private static final int STOCK_IMAGE=1;
    private LayoutInflater inflater;

    public NewsCustomAdapter(Context context, ArrayList<Object> list) {
        this.list=list;
        inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getItemViewType(int position)
    {
        if(list.get(position) instanceof NewsDetail)
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
                    view = inflater.inflate(R.layout.customnews,null);
                    break;
                case STOCK_IMAGE:
                    break;
            }
        }
        switch(getItemViewType(i)){
            case STOCK_VALUE:
                TextView text=(TextView) view.findViewById(R.id.newstitle);
                TextView text1=(TextView)view.findViewById(R.id.newsauthor);
                TextView text2=(TextView)view.findViewById(R.id.newspublish);
                text.setText(((NewsDetail)list.get(i)).getNewstitle());
                text1.setText(((NewsDetail)list.get(i)).getNewsauthor());
                text2.setText(((NewsDetail)list.get(i)).getNewspublish());
                break;
            case STOCK_IMAGE:
                break;
        }
        return view;
    }
}
