package com.example.finalprojectshannonsoulard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Home extends AppCompatActivity {

    String theUserEmail = "";

    private ArrayList<News> news=new ArrayList<>();
    private RecyclerView mRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        theUserEmail = getIntent().getStringExtra("TRANSFER_USER_EMAIL");
        mRecyclerView=(RecyclerView)findViewById(R.id.news_list);
        setContentView(R.layout.activity_home);
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.action_home);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_profile:
                        Intent MainIntent = new Intent(Home.this, Profile.class);
                        MainIntent.putExtra("TRANSFER_USER_EMAIL", theUserEmail);
                        startActivity(MainIntent);
                        break;
                    case R.id.action_home:
                        Intent homeIntent = new Intent(Home.this, Home.class);
                        startActivity(homeIntent);
                        break;
                    case R.id.action_search:
                        Intent intent = new Intent(Home.this, Search.class);
                        intent.putExtra("TRANSFER_USER_EMAIL", theUserEmail);
                        startActivity(intent);
                        break;
                    case R.id.action_bookmarks:
                        //Toast.makeText(FavoriteActivity.this, "favorites", Toast.LENGTH_SHORT).show();
                        Intent favoritesIntent = new Intent(Home.this, Bookmarks.class);
                        favoritesIntent.putExtra("TRANSFER_USER_EMAIL", theUserEmail);
                        startActivity(favoritesIntent);
                        break;
                }
                return true;
            }
        });

        String newsURL = "https://finnhub.io/api/v1/news?category=general&token=c1hqs0748v6sod8ljr20";

        JsonArrayRequest getNewsData = new JsonArrayRequest(Request.Method.GET, newsURL,  new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray newsResponse) {
                try {

                    News n = new News(newsResponse);
                    newsResponse.length();
                    Log.e("RESPONSE_LENGTH", String.valueOf(newsResponse.length()));
                    //String headline = n.getHeadline();


                    Log.e("NEWS_STRING", String.valueOf(n));

                    String theImage = n.getImage();
                    String theHeadline = n.getHeadline();
                    Log.e("HEADLINE", theHeadline);
                    String theSummary = n.getSummary();
                    String theURL = n.getURL();
                    String theCategory = n.getCategory();
                    String theSource = n.getSource();
                    String theDateTime = n.getDateTime();

                    //theHeadline = (mRecyclerView).setText(R.id.headline);

                    //displayNews();


                    // fix how the info is displayed after


                } catch(JSONException theError){
                    theError.printStackTrace();
                }
            }

            private void displayNews(ArrayList<News> News, Response<ArrayList<News>> response) {

            //News.add(new News(theImage, headline, summary, url, category, source, datetime));


            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error){

            }
        });

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(getNewsData);



    }
}