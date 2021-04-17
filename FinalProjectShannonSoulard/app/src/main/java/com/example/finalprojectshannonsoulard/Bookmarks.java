package com.example.finalprojectshannonsoulard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.HashMap;

public class Bookmarks extends AppCompatActivity {

    private String TAG = Bookmarks.class.getSimpleName();
    private ListView listv;
    TextView BookmarkTitle;

    ArrayList<HashMap<String, String>> contactList2;


    String userID = "";
    public static final String transferUserID = "com.example.finalprojectshannonsoulard.userID";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmarks);
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.action_bookmarks);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_home:
                        Intent MainIntent = new Intent(Bookmarks.this, Profile.class);
                        startActivity(MainIntent);
                        break;
                    case R.id.action_search:
                        Intent intent = new Intent(Bookmarks.this, Search.class);
                        intent.putExtra(transferUserID, userID);
                        startActivity(intent);
                        break;
                    case R.id.action_bookmarks:
                        //Toast.makeText(FavoriteActivity.this, "favorites", Toast.LENGTH_SHORT).show();
                        Intent favoritesIntent = new Intent(Bookmarks.this, Bookmarks.class);
                        startActivity(favoritesIntent);
                        break;
                }
                return true;
            }
        });



    }

}