package com.example.finalprojectshannonsoulard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class SearchDetails extends AppCompatActivity {
   //Strings  values
    String keyword="";
   //TextView items on the display
    TextView symbol, itemName, assetType, description, Currency, high52wk, low52wk, marketCap;
   //Button on display
    Button btnBookmark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_details);



        Intent intent = getIntent();
        keyword = intent.getStringExtra(Search.transferID);

        //linking variables to the created items in the corresponding .xml file
        symbol = (TextView) findViewById(R.id.symbol);
        itemName = (TextView)findViewById(R.id.itemName);
        assetType = (TextView) findViewById(R.id.AssetType);
        description = (TextView) findViewById(R.id.description);
        Currency = (TextView) findViewById(R.id.Currency);
        high52wk = (TextView) findViewById(R.id.high52wk);
        low52wk = (TextView) findViewById(R.id.low52wk);
        marketCap = (TextView) findViewById(R.id.marketCap);
        btnBookmark = (Button) findViewById(R.id.btnBookmark);

        String userID = intent.getStringExtra(Search.transfertheID);



        // String for the Alpha Vantage URL
        String AlphaURL = "https://www.alphavantage.co/query?function=OVERVIEW&symbol=" + keyword + "&apikey=72E0CVCZ7BK15ME1";
        //calling a function to get the data
       /* getData(AlphaURL);

    public void getData(AlphaURL){

        JsonObjectRequest getData = new JsonObjectRequest(Request.Method.GET, AlphaURL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    //API data into the listview
                    String TheSymbol = String.valueOf("Symbol");
                    String TheItemName = String.valueOf("Name");
                    String TheAssetType = String.valueOf("AssetType");
                    String TheCurrency = String.valueOf("Currency");
                    String TheDescription = String.valueOf("Description");
                    String The52wkhigh = String.valueOf("52WeekHigh");
                    String The52wklow = String.valueOf("52WeekLow");
                    String TheMarketCap = String.valueOf("MarketCapitalization");

                } catch(JSONException theError){
                    theError.printStackTrace();
                }
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error){

            }
        });

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(getData);

    }*/

}
}

