package com.example.krishmehta.androidapplication;

/**
 * Created by krishmehta on 11/18/17.
 */

public class MainActivityDetail {
    private String mainstockname;
    private String mainstockprice,mainstockchange,mainstockchangepercent;
    public MainActivityDetail(String mainstockname, String mainstockprice,String mainstockchange,String mainstockchangepercent) {
        this.mainstockprice=mainstockprice;
        this.mainstockname=mainstockname;
        this.mainstockchange=mainstockchange;
        this.mainstockchangepercent=mainstockchangepercent;
    }

    public MainActivityDetail()
    {

    }

    public String getMainstockname() {
        return mainstockname;
    }

    public void setMainstockname(String mainstockname) {
        this.mainstockname = mainstockname;
    }

    public String getMainstockprice() {
        return mainstockprice;
    }

    public void setMainstockprice(String mainstockprice) {
        this.mainstockprice = mainstockprice;
    }
    public String getMainstockchange() {
        return mainstockchange;
    }

    public void setMainstockchange(String mainstockchange) {
        this.mainstockchange = mainstockchange;
    }
    public String getMainstockchangepercent() {
        return mainstockchangepercent;
    }

    public void setMainstockchangepercent(String mainstockchangepercent) {
        this.mainstockchangepercent = mainstockchangepercent;
    }
}
