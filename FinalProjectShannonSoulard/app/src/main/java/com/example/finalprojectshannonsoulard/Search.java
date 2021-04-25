package com.example.finalprojectshannonsoulard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import static android.content.ContentValues.TAG;

public class Search extends AppCompatActivity {

    public static Stock s;
    private String TAG = Search.class.getSimpleName();
    private ListView lv;

    ArrayList<HashMap<String, String>> AssetList;

    EditText userInput;
    String userTerm= "";
    //String keyword = "";
    Button btnSearch;

    //String IDvalue = "";
    String url = "";
    public static final String transferID = "com.example.finalprojectshannonsoulard.IDvalue";


    TextView nameZnumber;

    String userID = "";
    public static final String transfertheID = "com.example.finalprojectshannonsoulard.userID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.action_search);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_profile:
                        Intent MainIntent = new Intent(Search.this, Profile.class);
                        startActivity(MainIntent);
                        break;
                    case R.id.action_home:
                        Intent homeIntent = new Intent(Search.this, Home.class);
                        startActivity(homeIntent);
                        break;
                    case R.id.action_search:
                        Intent intent = new Intent(Search.this, Search.class);
                        startActivity(intent);
                        break;
                    case R.id.action_bookmarks:
                        Intent bookmarkIntent = new Intent(Search.this, Bookmarks.class);
                        bookmarkIntent.putExtra(transfertheID, userID);
                        startActivity(bookmarkIntent);
                        break;
                }
                return true;
            }
        });


        userInput = (EditText) findViewById(R.id.searchBar);
        btnSearch = (Button) findViewById(R.id.btnSearch);
        nameZnumber = (TextView) findViewById((R.id.nameZnumber));


        Intent intent = getIntent();
        userID = intent.getStringExtra(Profile.transferUserEmail); //go add if user navigates from search tab
        if (userID == null) {
            userID = intent.getStringExtra(Bookmarks.transferUserID); //go add if user navigates from search tab
        }
        //nameZnumber.setText("user: " + userID);

        //ListView
        //AssetList = new ArrayList<>();
        //lv = (ListView) findViewById(R.id.list);


        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                //new GetResults().execute();
                userTerm = userInput.getText().toString();
                url = "https://www.alphavantage.co/query?function=GLOBAL_QUOTE&symbol=" + userTerm + "&apikey=72E0CVCZ7BK15ME1";
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Stock s = new Stock(response);
                            String symbol = s.getSymbol();
                            //Log.e("SYMBOL", symbol);
                            String open = s.getOpen();
                            String high = s.getHigh();
                            String low = s.getLow();
                            String price = s.getPrice();
                            String volume = s.getVolume();
                            String latest_trading_day = s.getLatestTradingDay();
                            String previous_close = s.getPrevClose();
                            String change = s.getChange();
                            String change_percent = s.getChangePercent();

                            Intent intent = new Intent(getBaseContext(), SearchDetails.class);
                            intent.putExtra("STOCK_SYMBOL", symbol);
                            intent.putExtra("STOCK_OPEN", open);
                            intent.putExtra("STOCK_HIGH", high);
                            intent.putExtra("STOCK_LOW", low);
                            intent.putExtra("STOCK_PRICE", price);
                            intent.putExtra("STOCK_VOLUME", volume);
                            intent.putExtra("STOCK_LTD", latest_trading_day);
                            intent.putExtra("STOCK_PREV_CLOSE", previous_close);
                            intent.putExtra("STOCK_CHANGE", change);
                            intent.putExtra("STOCK_CHANGE_PERCENT", change_percent);

                            startActivity(intent);


                        }catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Log.i("RESPONSE", response.toString());
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("ERROR", error.toString());
                    }
                });
                requestQueue.add(jsonObjectRequest);
            }



        });

}

   /* public void openSearchDetails() {
        Intent intent = new Intent(this, SearchDetails.class);
        startActivity(intent);
    }*/


   /*public class GetResults extends AsyncTask<Void, Void, Void> {
       @Override
       protected void onPreExecute() {
           super.onPreExecute();
           Toast.makeText(Search.this, "Fetching results", Toast.LENGTH_LONG).show();

       }
   }

        @Override
        protected Void doInBackground(Void... voids) {
            AlphaVantage sh = new AlphaVantage();
            userTerm = userInput.getText().toString();
            url = "https://www.alphavantage.co/query?function=GLOBAL_QUOTE&symbol=" + userTerm + "&apikey=72E0CVCZ7BK15ME1";
            sh.getStock(userTerm);





            return null;

        }

    }
        */


    }
