package com.example.nanocodeams;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;

public class TimeTableActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_table);


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