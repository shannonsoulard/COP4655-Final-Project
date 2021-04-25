package com.example.finalprojectshannonsoulard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.action_home);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_profile:
                        Intent MainIntent = new Intent(Home.this, Profile.class);
                        startActivity(MainIntent);
                        break;
                    case R.id.action_home:
                        Intent homeIntent = new Intent(Home.this, Home.class);
                        startActivity(homeIntent);
                        break;
                    case R.id.action_search:
                        Intent intent = new Intent(Home.this, Search.class);
                        startActivity(intent);
                        break;
                    case R.id.action_bookmarks:
                        //Toast.makeText(FavoriteActivity.this, "favorites", Toast.LENGTH_SHORT).show();
                        Intent favoritesIntent = new Intent(Home.this, Bookmarks.class);
                        startActivity(favoritesIntent);
                        break;
                }
                return true;
            }
        });
    }
}