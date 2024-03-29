package com.example.finalprojectshannonsoulard;


import android.os.Build;

import androidx.annotation.RequiresApi;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;


public class AlphaVantage {
    static AlphaVantage instance;
    HashMap<String,Stock> cache;
    static final String API_URL = "https://www.alphavantage.co/query?function=GLOBAL_QUOTE&symbol={{SYMBOL}}&apikey={{APIKEY}}";
    static String API_KEY = "72E0CVCZ7BK15ME1";
    static final int MAX_USES = 5;
    static final int MAX_CACHE_AGE = 60 * 15; // 15 minutes


    //Private constructor
    public AlphaVantage(){
        cache = new HashMap<String,Stock>();
    }

    //Standard singleton getInstance()
    public static AlphaVantage getInstance(){
        if (instance == null){
            instance = new AlphaVantage();
        }
        return instance;
    }

    private String getAPIkey(){
        return API_KEY;
    }
    public Stock getStock(String symbol){
        //Here's where we trick android into letting us run network I/O in the main thread but just starting another thread and immediately waiting on it
        //Daniel - 1, Google - 0
        //Daniel putting me at ease with jokes while I do this until the time it is due lol
        Thread t;
        ArrayList<Stock> stocks = new ArrayList<Stock>();
        t = new Thread(new Runnable() {
            ArrayList<Stock> mStocks = stocks;
            String arg = symbol;
            AlphaVantage network = AlphaVantage.getInstance();

            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void run() {
                Stock st = network._getStock(arg);
                mStocks.add(st);
            }
        });
        t.start();
        try {
            t.join();
        }
        catch(Exception e){
            return null;
        }
        return stocks.get(0);

    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public Stock _getStock(String symbol){
        //First check if the stock is cached
        if (cache.containsKey(symbol)){
            //Next check the age
            Stock cStock = cache.get(symbol);
            //If the stock is fresh, return it
            long currentTimestamp = Instant.now().getEpochSecond();
            long stockTimestamp = cStock.getTimeStamp();
            if(currentTimestamp - MAX_CACHE_AGE <= stockTimestamp){
                return cStock;
            }
        }
        String completeURL = API_URL.replace("{{SYMBOL}}",symbol).replace("{{APIKEY}}",getAPIkey());
        System.out.println(completeURL);
        URL url;
        HttpURLConnection connection;
        InputStream iStream;
        try {
            url = new URL(completeURL);
        }
        catch(MalformedURLException e){

            System.out.println("Malformed URL");
            return null;
        }
        try {
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setReadTimeout(1000);
            connection.setConnectTimeout(1000);
            iStream = connection.getInputStream();

            InputStreamReader iReader = new InputStreamReader(iStream);
            BufferedReader reader = new BufferedReader(iReader);
            StringBuffer sb = new StringBuffer();
            String s;
            while((s = reader.readLine())!= null){
                sb.append(s);
            }
            s = sb.toString();
            System.out.println(s);
            JSONObject jo = new JSONObject(s);
            Stock stock = new Stock(jo);
            //Cache this stock
            iStream.close();
            connection.disconnect();
            cache.put(symbol,stock);
            return stock;


        }
        catch(JSONException e){
            System.out.println("JSON ERROR");
            return null;
        }
        catch(IOException e){
            System.out.println("IO Error on HTTPUrlConnection");
            return null;
        }

    }

}