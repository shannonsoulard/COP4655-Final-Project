package com.example.finalprojectshannonsoulard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.DocumentReference;

import java.util.ArrayList;
import java.util.HashMap;

public class Bookmarks extends AppCompatActivity {

    private String TAG = Bookmarks.class.getSimpleName();
    private ListView listv;
    TextView BookmarkTitle;

    ArrayList<HashMap<String, String>> BookmarkedList;

    ListAdapter adapter;
    String theUserEmail = "";
    //public static final String transferUserID = "com.example.finalprojectshannonsoulard.userID";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




        theUserEmail = getIntent().getStringExtra("TRANSFER_USER_EMAIL");
        Log.e("TRANSFER_USER_EMAIL", theUserEmail);


        /*if (theUserEmail != "") {
            loadBookmarks();
        }*/

        setContentView(R.layout.activity_bookmarks);
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.action_bookmarks);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_profile:
                        Intent MainIntent = new Intent(Bookmarks.this, Profile.class);
                        MainIntent.putExtra("TRANSFER_USER_EMAIL", theUserEmail);
                        startActivity(MainIntent);
                        break;
                    case R.id.action_home:
                        Intent homeIntent = new Intent(Bookmarks.this, Home.class);
                        homeIntent.putExtra("TRANSFER_USER_EMAIL", theUserEmail);
                        startActivity(homeIntent);
                        break;
                    case R.id.action_search:
                        Intent intent = new Intent(Bookmarks.this, Search.class);
                        intent.putExtra("TRANSFER_USER_EMAIL", theUserEmail);
                        startActivity(intent);
                        break;
                    case R.id.action_bookmarks:
                        //Toast.makeText(FavoriteActivity.this, "favorites", Toast.LENGTH_SHORT).show();
                        Intent favoritesIntent = new Intent(Bookmarks.this, Bookmarks.class);
                        favoritesIntent.putExtra("TRANSFER_USER_EMAIL", theUserEmail);
                        startActivity(favoritesIntent);
                        break;
                }
                return true;
            }
        });

        BookmarkedList = new ArrayList<>();
        listv = (ListView) findViewById(R.id.list2);

        Task<QuerySnapshot> query = FirebaseFirestore.getInstance().collection(theUserEmail).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        int count = 0;
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot document : task.getResult()) {
                                if (document.exists()) {
                                    String bSymbol = document.getString("symbol");
                                    String bPrice = document.getString("price");
                                    String bChange = document.getString("change");

                                    HashMap<String, String> theList = new HashMap<>();
                                    theList.put("symbol", bSymbol);
                                    theList.put("price", bPrice);
                                    theList.put("change", bChange);
                                    BookmarkedList.add(theList);
                                }
                                final ListAdapter adapter = new SimpleAdapter(Bookmarks.this, BookmarkedList,
                                        R.layout.list_item, new String[]{"symbol", "price", "change"},
                                        new int[]{R.id.symbol, R.id.price, R.id.change});
                                listv.setAdapter(adapter);


                            }

                        }
                    }
                });
    }

    /*public void loadBookmarks() {
        Log.i("LOAD","loading favorites");
        final ArrayList<HashMap<String, String>> dataF = new ArrayList<>();
        final ListView lvF = (ListView) findViewById(R.id.list2);
        Task<QuerySnapshot> query = FirebaseFirestore.getInstance().collection(theUserEmail).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot document : task.getResult()) {//need
                                if (document.exists()) {
                                    HashMap<String, String> infoF = new HashMap<>();

                                    // adding each child node to HashMap key => value
                                    infoF.put("symbolB", document.getString("symbol"));
                                    infoF.put("priceB", document.getString("price"));
                                    infoF.put("changeB", document.getString("change"));
                                    infoF.put("change_percentB", document.getString("change_percent"));
                                    infoF.put("latest_trading_dayB", document.getString("latest_trading_day"));
                                    infoF.put("openB", document.getString("open"));
                                    infoF.put("previous_closeB", document.getString("previous_close"));
                                    infoF.put("highB", document.getString("high"));
                                    infoF.put("lowB", document.getString("low"));
                                    infoF.put("volumeB", document.getString("volume"));

                                    // adding contact to contact list
                                    dataF.add(infoF);
                                } else {
                                    Log.i("ERROR", "Error getting documents: ", task.getException());
                                }
                                adapter = new SimpleAdapter(Bookmarks.this, dataF,
                                        R.layout.list_item, new String[]{ "symbol","price", "change"},
                                        new int[]{R.id.symbol, R.id.price, R.id.change});
                                lvF.setAdapter(adapter);
                                lvF.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                                        HashMap<String, String> businessF = dataF.get(position);
                                        favResults.setName(businessF.get("nameF"));
                                        favResults.setTitle(businessF.get("titleF"));
                                        favResults.setAddress(businessF.get("addressF"));
                                        favResults.setPhoneNum(businessF.get("phoneNumF"));
                                        favResults.setRating(Double.parseDouble(businessF.get("ratingF")));
                                        favResults.setHours(businessF.get("hoursF"));
                                        favResults.setCityLat(businessF.get("latF"));
                                        favResults.setCityLon(businessF.get("lonF"));
                                        theUserEmail.setShowingFavs(true);
                                        SearchDetails.transferData();
                                        Intent intent = new Intent(FavsActivity.this, InfoActivity.class);
                                        startActivity(intent);
                                    }
                                });
                            }
                        }
                    }
                });
    }*/





}
