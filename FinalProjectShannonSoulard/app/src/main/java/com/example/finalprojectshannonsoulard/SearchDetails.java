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
    String Bsymbol = "";
    String Bprice = "";
    String Bchange = "";
    String userEmail ="";

    private DocumentReference docRef;
   //TextView items on the display
    TextView tSymbol, tOpen, tHigh, tPrice, tLow, tChangePercent, tLTD, tChange, tPrevClose, tVolume;
   //Button on display
    Button btnBookmark;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_details);

        //Intent intent = getIntent();
        //keyword = intent.getStringExtra(Search.transferID);
        userEmail = getIntent().getStringExtra("TRANSFER_USER_EMAIL");
        Log.e("TRANSFER_USER_EMAIL", userEmail);

        //linking variables to the created items in the corresponding .xml file
        String dSymbol = getIntent().getStringExtra("STOCK_SYMBOL");
        tSymbol = (TextView) findViewById(R.id.symbol);
        tSymbol.setText(dSymbol);
        Bsymbol = dSymbol;

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
        Bprice = dPrice;

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
        Bchange = dChange;

        String dChangePercent = getIntent().getStringExtra("STOCK_CHANGE_PERCENT");
        tChangePercent = (TextView) findViewById(R.id.changePercent);
        tChangePercent.setText("( " + dChangePercent + " )");






}

        //btnBookmark.setOnClickListener(new View.OnClickListener() {
        //@Override
        public void onBookmark(View v) {
            //docRef = FirebaseFirestore.getInstance().collection(userEmail).document(Bsymbol);
            Map<String, Object> document = new HashMap<>();
            document.put("symbol", Bsymbol);
            document.put("price", Bprice);
            document.put("change", Bchange);
            //If saving to document is a success
            db.collection(userEmail).document(Bsymbol)
                    .set(document)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Log.d("SUCCESS_BK", "Document saved" );
                            Toast.makeText(getApplicationContext(), "Stock Saved to Bookmarks", Toast.LENGTH_LONG).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w("FAILURE_BK", "Error adding document", e);
                        }
                    });
            //Toast.makeText(getApplicationContext(), "Stock Saved to Bookmarks", Toast.LENGTH_LONG).show();
        }
    //});


}

