package com.example.finalprojectshannonsoulard;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

public class Stock {
    private String symbol;
    private String open;
    private String high;
    private String low;
    private String price;
    private String volume;
    private String latest_trading_day;
    private String previous_close;
    private String change;
    private String change_percent;
    private Date timestamp;

    Stock(JSONObject o) throws JSONException {
        timestamp = new Date();
        JSONObject stock = o.getJSONObject("Global Quote");
        symbol = stock.getString("01. symbol");
        open = stock.getString("02. open");
        high = stock.getString("03. high");
        low = stock.getString("04. low");
        price = stock.getString("05. price");
        volume = stock.getString("06. volume");
        latest_trading_day = stock.getString("07. latest trading day");
        previous_close = stock.getString("08. previous close");
        change = stock.getString("09. change");
        change_percent = stock.getString("10. change percent");
// added the rest of the strings




    }

    public long getTimeStamp(){
        return timestamp.getTime() / 1000;
    }

    @Override
    public String toString(){
        return symbol;
    }


}