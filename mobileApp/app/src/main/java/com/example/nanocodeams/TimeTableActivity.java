package com.example.nanocodeams;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;

public class TimeTableActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_table);

        BottomNavigationView bottomNavigationView = findViewById(R.id.btm_nav3);
        bottomNavigationView.setSelectedItemId(R.id.tt_des);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if(item.getItemId() == R.id.home_des){
                    startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                    overridePendingTransition(0,0);
                    return  true;
                }
                else if(item.getItemId() == R.id.tt_des){
                    startActivity(new Intent(getApplicationContext(),WeekDaysActivity.class));
                    overridePendingTransition(0,0);
                    return  true;
                }
                else if(item.getItemId() == R.id.noti_des){
                    startActivity(new Intent(getApplicationContext(),NotificationActivity.class));
                    overridePendingTransition(0,0);
                    return  true;
                }

                return false;
            }
        });


        Intent intent = getIntent();
        int position = intent.getIntExtra("position",0);

        this.setTitle(intent.getStringExtra("label"));

        RecyclerView recyclerView = findViewById(R.id.reView);
        MyListAdapter adapter = null;
        try {
            adapter = new MyListAdapter((JSONArray) GlobalDataClass.getTimeTable().get(position));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}