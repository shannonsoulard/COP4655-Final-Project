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

   public Stock(JSONObject o) throws JSONException {
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

    public String getSymbol(){ return this.symbol; }
    public String getOpen(){ return this.open;}
    public String getHigh(){ return this.high;}
    public String getLow(){ return this.low;}
    public String getPrice(){ return this.price;}
    public String getVolume(){ return this.volume;}
    public String getLatestTradingDay(){ return this.latest_trading_day;}
    public String getPrevClose(){ return this.previous_close;}
    public String getChange(){ return this.change;}
    public String getChangePercent(){ return this.change_percent;}

    



    public long getTimeStamp(){
        return timestamp.getTime() / 1000;
    }

    @Override
    public String toString(){
        return symbol;
    }


}