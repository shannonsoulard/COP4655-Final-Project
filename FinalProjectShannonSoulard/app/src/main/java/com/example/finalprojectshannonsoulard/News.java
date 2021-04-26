package com.example.finalprojectshannonsoulard;

import android.util.Log;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

public class News {
    private String image;
    private String headline;
    private String summary;
    private String url;
    private String source;
    private String category;
    private String datetime;

    public News(JSONArray a) throws JSONException {

        int length = a.length();
        Log.e("ARRAY_LENGTH", String.valueOf(a.length()));


        for (int i = 0; i < length; i++) {
            JSONObject jsonobject = a.getJSONObject(i);
            Log.e("NEWS_CLASS", String.valueOf(jsonobject));


            image = jsonobject.getString("image");
            Log.e("IMAGE", image);
            headline = jsonobject.getString("headline");
            Log.e("LOOP_HEADLINES", headline);
            summary = jsonobject.getString("summary");
            url = jsonobject.getString("url");
            source = jsonobject.getString("source");
            category = jsonobject.getString("category");
            datetime = jsonobject.getString("datetime");


        }


    }


    public String getImage(){ return this.image; }
    public String getHeadline(){ return this.headline;}
    public String getSummary(){ return this.summary;}
    public String getURL(){ return this.url;}
    public String getSource(){ return this.source;}
    public String getCategory(){ return this.category;}
    public String getDateTime(){ return this.datetime;}


}