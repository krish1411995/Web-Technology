package com.example.krishmehta.androidapplication;

/**
 * Created by krishmehta on 11/17/17.
 */

public class StockDetailImage {
    private String staticvalue1,dynamicvalue1;
    private int image;
    public StockDetailImage(String staticvalue, String dynamicvalue,int image) {
        this.staticvalue1 = staticvalue;
        this.dynamicvalue1 = dynamicvalue;
        this.image=image;
    }

    public StockDetailImage()
    {

    }

    public String getStaticvalue1() {
        return staticvalue1;
    }

    public void setImagevalue1(int image) {
        this.image = image;
    }

    public int getImagevalue1() {
        return image;
    }

    public void setStaticvalue1(String staticvalue) {
        this.staticvalue1 = staticvalue;
    }

    public String getDynamicvalue1() {
        return dynamicvalue1;
    }

    public void setDynamicvalue1(String dynamicvalue) {
        this.dynamicvalue1 = dynamicvalue;
    }

}
