package com.example.krishmehta.androidapplication;

/**
 * Created by krishmehta on 11/13/17.
 */

public class SuggestGetSet {
    String symbol,stock_name,exchange;
    public SuggestGetSet(String symbol, String stock_name, String exchange){
        this.setId(symbol);
        this.setName(stock_name);
        this.setExchange(exchange);
    }
    public String getId() {
        return symbol;
    }

    public String getExchange() {
        return exchange;
    }

    public void setId(String symbol) {
        this.symbol = symbol;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getName() {
        return stock_name;
    }

    public void setName(String stock_name) {
        this.stock_name = stock_name;
    }
}
