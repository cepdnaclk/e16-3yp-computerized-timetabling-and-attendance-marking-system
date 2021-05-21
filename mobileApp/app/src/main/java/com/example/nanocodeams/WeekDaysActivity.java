package com.example.nanocodeams;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class WeekDaysActivity extends AppCompatActivity {

    final String  FIND_STU_SCHEDULE_URL = "https://efac-attendance.herokuapp.com/schedule/findscheduledetailsbystudent/";
    final String STU_ID_FROM_SESSION_URL = "https://efac-attendance.herokuapp.com/student/getdetailsfromsession";
    public String stdId;
    public  String token;
    public boolean isTtDataRecved;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week_days);

        BottomNavigationView bottomNavigationView = findViewById(R.id.btm_nav2);
        bottomNavigationView.setSelectedItemId(R.id.tt_des);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if(item.getItemId() == R.id.home_des){
                    startActivity(new Intent(getApplicationContext(),HomeActivity.class));
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

        Button btn1 = findViewById(R.id.btn1);
        Button btn2 = findViewById(R.id.btn2);
        Button btn3 = findViewById(R.id.btn3);
        Button btn4 = findViewById(R.id.btn4);
        Button btn5 = findViewById(R.id.btn5);

        isTtDataRecved = false;
        stdId = "";

        token = GlobalDataClass.getToken();

        getStudentId(STU_ID_FROM_SESSION_URL,token);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isTtDataRecved) {
                    Intent intent = new Intent(WeekDaysActivity.this, TimeTableActivity.class);
                    intent.putExtra("position", 0);
                    intent.putExtra("label", "MONDAY");
                    startActivity(intent);
                }
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isTtDataRecved) {
                    Intent intent = new Intent(WeekDaysActivity.this, TimeTableActivity.class);
                    intent.putExtra("position", 1);
                    intent.putExtra("label", "TUESDAY");
                    startActivity(intent);
                }
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isTtDataRecved) {
                    Intent intent = new Intent(WeekDaysActivity.this, TimeTableActivity.class);
                    intent.putExtra("position", 2);
                    intent.putExtra("label", "WEDNESDAY");
                    startActivity(intent);
                }
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isTtDataRecved) {
                    Intent intent = new Intent(WeekDaysActivity.this, TimeTableActivity.class);
                    intent.putExtra("position", 3);
                    intent.putExtra("label", "THURSDAY");
                    startActivity(intent);
                }
            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isTtDataRecved) {
                    Intent intent = new Intent(WeekDaysActivity.this, TimeTableActivity.class);
                    intent.putExtra("position", 4);
                    intent.putExtra("label", "FRIDAY");
                    startActivity(intent);
                }
            }
        });

    }


    private void getStudentId(String URL,String token){


        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        final String auth = "Bearer " + token;
        final String tkn = token;

        // Request a string response from the provided URL.
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {


                        Log.e("stdres",response.toString());


                        try {
                            stdId = response.getString("result1");
                            getTimetable(FIND_STU_SCHEDULE_URL,stdId,tkn);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),error.getMessage(), Toast.LENGTH_SHORT).show();


                    }
                }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Authorization", auth);
                return params;
            }
        };

        // Add the request to the RequestQueue.
        queue.add(jsonObjectRequest);

    }

    private void getTimetable(String URL,String studentId,String token){


        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        final String url = URL + studentId;
        final String auth = "Bearer " + token;
        Log.e("id",studentId);

        // Request a string response from the provided URL.
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {


                        try {
                            JSONArray timeTable = (JSONArray)response.get("result");
                            GlobalDataClass.setTimeTable(timeTable);
                            isTtDataRecved = true;
                            Log.e("Timetable",timeTable.toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),error.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.e("errormsg",error.getMessage());



                    }
                }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Authorization", auth);
                return params;
            }
        };

        // Add the request to the RequestQueue.
        queue.add(jsonObjectRequest);

    }


}