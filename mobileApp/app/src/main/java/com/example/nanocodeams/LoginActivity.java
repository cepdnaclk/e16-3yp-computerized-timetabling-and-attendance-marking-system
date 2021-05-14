package com.example.nanocodeams;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
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

import java.io.FileOutputStream;
import java.io.IOException;
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

        final TextView userName = findViewById(R.id.loginUsername);
        final TextView password = findViewById(R.id.loginPassword);
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
                    WebRequest webRequest = new WebRequest();
                    webRequest.execute(name,pw);


                }
            }
        });
    }



    private class WebRequest extends AsyncTask<String,String,String>{


        @Override
        protected String doInBackground(String... strings) {

            OkHttpClient client = new OkHttpClient();
            MediaType Json = MediaType.parse("application/json;charset=utf-8");
            JSONObject data = new JSONObject();
            String val = "";

            //Toast.makeText(getApplicationContext(),"started", Toast.LENGTH_SHORT).show();
            Log.e("work","started");
            try {
                data.put("userName",strings[0]);
                data.put("password",strings[1]);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            RequestBody body = RequestBody.create(Json,data.toString());

            Request request = new Request.Builder().url("https://efac-attendance.herokuapp.com/login").post(body).build();

            Response response = null;
            try {
                response = client.newCall(request).execute();
                String responseBody = response.body().string().trim();
                String role = responseBody.substring(9,21).trim();
                String token = responseBody.substring(32,responseBody.length()-2);
                Log.e("token",token);
                Log.e("role",role);
                int code = response.code();
                Log.e("Code", String.valueOf(code));
                Log.e("response",responseBody);
                if(response.code() != 200) val = "Bad Credentials";
                else if(!role.equals("ROLE_STUDENT"))  val = "Access Denied";
                else{
                    val = token;
                    isLoggedIn = true;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                return val;
            }

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);



            if(!s.equals("Bad Credentials") && !s.equals("Access Denied") && isLoggedIn){
                studentToken = s;

                //write token to internal storage
                GlobalDataClass.setToken(s.trim());
                try {
                    FileOutputStream fileOutputStream = openFileOutput("token.txt",MODE_PRIVATE);
                    fileOutputStream.write(s.getBytes());
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Intent homeIntent = new Intent(LoginActivity.this,HomeActivity.class);
                startActivity(homeIntent);
                finish();
            }
            else{
                Toast.makeText(getApplicationContext(),s, Toast.LENGTH_SHORT).show();
            }


        }
    }
}