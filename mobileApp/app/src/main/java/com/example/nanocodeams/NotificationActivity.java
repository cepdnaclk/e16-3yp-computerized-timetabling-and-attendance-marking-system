package com.example.nanocodeams;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class NotificationActivity extends AppCompatActivity {

    String remainder[],time[],course[],message[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        BottomNavigationView bottomNavigationView = findViewById(R.id.btm_nav4);
        bottomNavigationView.setSelectedItemId(R.id.noti_des);

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

                return false;
            }
        });

        remainder = getResources().getStringArray(R.array.remainder);
        time = getResources().getStringArray(R.array.time);
        course = getResources().getStringArray(R.array.Course);
        message = getResources().getStringArray(R.array.message);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);

        NotificationAdapter notificationAdapter = new NotificationAdapter(this,remainder,time,course,message);
        recyclerView.setAdapter(notificationAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this))  ;
    }
}