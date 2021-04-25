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
    TextView tSymbol, tOpen, tHigh, tPrice, tLow, tChangePercent, tLTD, tChange, tPrevClose, tVolume;
   //Button on display
    Button btnBookmark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_details);

        //Intent intent = getIntent();
        //keyword = intent.getStringExtra(Search.transferID);

        //linking variables to the created items in the corresponding .xml file
        String dSymbol = getIntent().getStringExtra("STOCK_SYMBOL");
        tSymbol = (TextView) findViewById(R.id.symbol);
        tSymbol.setText(dSymbol);

        String dOpen = getIntent().getStringExtra("STOCK_OPEN");
        tOpen = (TextView) findViewById(R.id.open);
        tOpen.setText("Open: " + dOpen);

        String dHigh = getIntent().getStringExtra("STOCK_HIGH");
        tHigh = (TextView) findViewById(R.id.high);
        tHigh.setText("High: " + dHigh);

        String dLow = getIntent().getStringExtra("STOCK_LOW");
        tLow = (TextView) findViewById(R.id.low);
        tLow.setText("Low: " + dLow);

        String dPrice = getIntent().getStringExtra("STOCK_PRICE");
        tPrice = (TextView) findViewById(R.id.price);
        tPrice.setText("Price: " + dPrice);

        String dVolume = getIntent().getStringExtra("STOCK_VOLUME");
        tVolume = (TextView) findViewById(R.id.volume);
        tVolume.setText("Volume: " + dVolume);

        String dLTD = getIntent().getStringExtra("STOCK_LTD");
        tLTD = (TextView) findViewById(R.id.latest_trading_day);
        tLTD.setText("Latest Trading Day: " + dLTD);

        String dPrevClose = getIntent().getStringExtra("STOCK_PREV_CLOSE");
        tPrevClose = (TextView) findViewById(R.id.prevClose);
        tPrevClose.setText("Previous Close: " + dPrevClose);

        String dChange = getIntent().getStringExtra("STOCK_CHANGE");
        tChange = (TextView) findViewById(R.id.change);
        tChange.setText("Change: " + dChange);

        String dChangePercent = getIntent().getStringExtra("STOCK_CHANGE_PERCENT");
        tChangePercent = (TextView) findViewById(R.id.changePercent);
        tChangePercent.setText("( " + dChangePercent + " )");

       /* tItemName = (TextView)findViewById(R.id.itemName);
        tAssetType = (TextView) findViewById(R.id.AssetType);
        tDescription = (TextView) findViewById(R.id.description);
        tCurrency = (TextView) findViewById(R.id.Currency);
        tHigh52wk = (TextView) findViewById(R.id.high52wk);
        tLow52wk = (TextView) findViewById(R.id.low52wk);
        tMarketCap = (TextView) findViewById(R.id.marketCap);
        btnBookmark = (Button) findViewById(R.id.btnBookmark);

        String userID = intent.getStringExtra(Search.transfertheID); */



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

