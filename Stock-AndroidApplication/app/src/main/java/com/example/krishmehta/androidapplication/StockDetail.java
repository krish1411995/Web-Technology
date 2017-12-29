package com.example.krishmehta.androidapplication;

/**
 * Created by krishmehta on 11/16/17.
 */

public class StockDetail {
    private String staticvalue,dynamicvalue;
    public StockDetail(String staticvalue, String dynamicvalue) {
        this.staticvalue = staticvalue;
        this.dynamicvalue = dynamicvalue;
    }

    public StockDetail()
    {

    }

    public String getStaticvalue() {
        return staticvalue;
    }

    public void setStaticvalue(String staticvalue) {
        this.staticvalue = staticvalue;
    }

    public String getDynamicvalue() {
        return dynamicvalue;
    }

    public void setDynamicvalue(String dynamicvalue) {
        this.dynamicvalue = dynamicvalue;
    }
}
