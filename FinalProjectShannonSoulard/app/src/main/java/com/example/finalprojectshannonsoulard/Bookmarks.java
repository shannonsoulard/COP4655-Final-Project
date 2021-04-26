package com.example.finalprojectshannonsoulard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
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
                    case R.id.action_profile:
                        Intent MainIntent = new Intent(Bookmarks.this, Profile.class);
                        startActivity(MainIntent);
                        break;
                    case R.id.action_home:
                        Intent homeIntent = new Intent(Bookmarks.this, Home.class);
                        startActivity(homeIntent);
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

       /* BookmarkedList = new ArrayList<>();
        listv = (ListView) findViewById(R.id.list2);

        Task<QuerySnapshot> query = FirebaseFirestore.getInstance().collection(userID).get()
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
                });*/
    }
}
