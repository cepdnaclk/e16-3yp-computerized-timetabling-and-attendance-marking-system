package com.example.nanocodeams;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class NotificationActivity extends AppCompatActivity {

    String remainder[],time[],course[],message[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

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