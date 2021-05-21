package com.example.nanocodeams;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView recyclerView_col;
    ArrayList<MainModel> mainModels;
    MainAdoptor mainAdoptor;
    ListAdaptor listAdaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        BottomNavigationView bottomNavigationView = findViewById(R.id.btm_nav);
        bottomNavigationView.setSelectedItemId(R.id.home_des);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if (item.getItemId() == R.id.tt_des) {
                    startActivity(new Intent(getApplicationContext(), WeekDaysActivity.class));
                    overridePendingTransition(0, 0);
                    return true;
                } else if (item.getItemId() == R.id.noti_des) {
                    startActivity(new Intent(getApplicationContext(), NotificationActivity.class));
                    overridePendingTransition(0, 0);
                    return true;
                }
                return false;
            }
        });

        recyclerView = findViewById(R.id.recycler_view);

        String[] sub = {"CO324", "CO322", "CO321"};
        String[] time = {"8-10 am", "10-12 am", "1-3 pm"};
        String[] room = {"room 1", "room 2", "room 3"};
        String[] course_ID = {"CO321", "CO322", "CO323", "CO324", "CO325", "EE386"};
        String[] courses = {"Embedded Systems",
                "Data Structures and Algorithms", "Computer Communication Networks II",
                "Network and Web Application Design", "Computer and Network Security"
                , "Electronics II"
        };

        mainModels = new ArrayList<>();

        for (int i = 0; i < sub.length; i++) {

            MainModel models = new MainModel(time[i], room[i], sub[i]);
            mainModels.add(models);
        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(
                HomeActivity.this, LinearLayoutManager.HORIZONTAL, false);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        mainAdoptor = new MainAdoptor(mainModels, HomeActivity.this);

        recyclerView.setAdapter(mainAdoptor);

        recyclerView_col = findViewById(R.id.recycler_col_view);
        recyclerView_col.setLayoutManager(new LinearLayoutManager(this));
        listAdaptor = new ListAdaptor(this, course_ID, courses);
        recyclerView_col.setAdapter(listAdaptor);


    }

    public String getToken() {

        String token = null;

        FileInputStream fileInputStream = null;
        try {
            fileInputStream = openFileInput("token.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);

            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuffer stringBuffer = new StringBuffer();

            String lines;
            while ((lines = bufferedReader.readLine()) != null) {
                stringBuffer.append(lines + "\n");

            }
            token = stringBuffer.toString();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return token;
    }


}