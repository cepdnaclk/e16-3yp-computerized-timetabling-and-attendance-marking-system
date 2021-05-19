package com.example.nanocodeams;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.telecom.Call;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LoginActivity extends AppCompatActivity {

    String studentToken = null;
    boolean isLoggedIn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final TextView userName = findViewById(R.id.name);
        final TextView password = findViewById(R.id.pass);
        Button submitButton = findViewById(R.id.submit);

        isLoggedIn = false;

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RequestQueue requestQueue = Volley.newRequestQueue(LoginActivity.this);


                final String name = userName.getText().toString().trim();
                final String pw = password.getText().toString().trim();

                if(name.length()==0 || pw.length()==0){
                    Toast.makeText(getApplicationContext(),"Username or password can not be empty", Toast.LENGTH_SHORT).show();
                }
                else {

                    Log.e("Button","....................clicked.....................");
                   /* WebRequest webRequest = new WebRequest();
                    webRequest.execute(name,pw);*/


                }
            }
        });
    }


}