package com.example.weatherhub;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.StrictMode;
import android.transition.Slide;
import android.transition.TransitionInflater;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    static public String description = "";
    static  public String main_climate = "";
    static  public String temp  = "";
    static  public String humidity = "";
    static  public String pressure = "";
    static  public String wind = "";
    static EditText e1;
    static EditText e2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();
 e1 = findViewById(R.id.city);
 e2 = findViewById(R.id.country);
String city = e1.getText().toString();
String country= e2.getText().toString();
Button check = findViewById(R.id.check);

check.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
                if(!e1.getText().toString().isEmpty() && !e2.getText().toString().isEmpty()){
            if(CheckNetwork.isInternetAvailable(MainActivity.this)) //returns true if internet available
            {
                ui();
                startActivity(new Intent(MainActivity.this,MainActivity2.class));            }
            else
            {
                Toast.makeText(MainActivity.this,"No Internet Connection",Toast.LENGTH_SHORT).show();
            }
}
else{
    Toast.makeText(MainActivity.this,"FILL BOTH BOXES !",Toast.LENGTH_SHORT).show();
}
    }
});

    }
     void ui(){
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        String url = "https://api.openweathermap.org/data/2.5/weather?q=" + e1.getText().toString() + ","+ e2.getText().toString() +"&units=metric&appid=c327c6ce036684733609975b0b276a93";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//data


                            try {
                                JSONObject js = new JSONObject(response);
                                JSONArray js2 = js.getJSONArray("weather");
                                JSONObject js3 = js2.getJSONObject(0);
                                description = js3.getString("description");
                                main_climate = js3.getString("main");
                                JSONObject js4 = js.getJSONObject("main");
                                temp = js4.getString("temp");
                                humidity = js4.getString("humidity");
                                pressure = js4.getString("pressure");
                                JSONObject js5 = js.getJSONObject("wind");
                                wind = js5.getString("speed");
                                Log.d("dd",description);
                                database d = new database(description , main_climate,temp,wind,humidity,pressure);
                                MainActivity2.dataSet();

                            }catch (JSONException e){
                                e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
Toast.makeText(MainActivity.this,"ENTER THE VALID DATA !" , Toast.LENGTH_SHORT).show();
            }
        });

// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

}
 class CheckNetwork {


    private static final String TAG = CheckNetwork.class.getSimpleName();



    public static boolean isInternetAvailable(Context context)
    {
        NetworkInfo info = (NetworkInfo) ((ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();

        if (info == null)
        {
            Log.d(TAG,"no internet connection");
            return false;
        }
        else
        {
            if(info.isConnected())
            {
                Log.d(TAG," internet connection available...");
                return true;
            }
            else
            {
                Log.d(TAG," internet connection");
                return true;
            }

        }
    }
}

