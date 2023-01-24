package com.example.weatherhub;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.os.Bundle;
import android.transition.Fade;
import android.transition.TransitionInflater;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Calendar;
import java.util.Locale;

public class MainActivity2 extends AppCompatActivity {
    public static ImageView moon;
    public static ImageView clouds;
    public static ImageView haze;
    public static ImageView rain;
    public static ImageView thunder;
    public static ImageView sun;
    public static TextView climate;
    public static TextView tempe;
    public static TextView humidity;
    public static TextView wind;
    public static TextView pressure;
    public static TextView description;
    static ProgressBar p1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        p1 = findViewById(R.id.progressBar);
        getSupportActionBar().hide();
        moon = findViewById(R.id.moon);
        rain = findViewById(R.id.rain);
        clouds = findViewById(R.id.clouds);
        thunder = findViewById(R.id.thunder);
        haze = findViewById(R.id.haze);
        sun = findViewById(R.id.sun);
        moon.setVisibility(View.INVISIBLE);
        clouds.setVisibility(View.INVISIBLE);
        haze.setVisibility(View.INVISIBLE);
        rain.setVisibility(View.INVISIBLE);
        thunder.setVisibility(View.INVISIBLE);
        sun.setVisibility(View.INVISIBLE);
        TextView timezone = findViewById(R.id.timezone);
        Calendar c=  Calendar.getInstance();
        String temp = String.valueOf(c.getTime());
        timezone.setText(temp.substring(0,3)+" ," +temp.substring(3,11) +", " + temp.substring(11,16) );
        TextView t1,t2 ;
        t1 = findViewById(R.id.city);
        t2 = findViewById(R.id.country);

        t1.setText(MainActivity.e1.getText().toString().toUpperCase(Locale.ROOT) + ", " + MainActivity.e2.getText().toString().toUpperCase());
         climate = findViewById(R.id.climate);
         tempe = findViewById(R.id.temperature);
         humidity = findViewById(R.id.humidity);
         wind = findViewById(R.id.wind);
         pressure = findViewById(R.id.pressure);
         description = findViewById(R.id.description);
        //FOR ACCESSING DATA WE WAS PREVIOUSLY DOING DATABASE D = NEW DATABASE();
        //IN MAIN ACTIVITY AND SAME IN MAIN ACTIVITY2 RESULT HAVING DIFFERENT OBJECT , THAT'S WHY WE HAVE NULL VALUE DUE TO DIFFERENT OBJECTS
    }
    static void dataSet(){
        climate.setText(database.main_climate.toUpperCase(Locale.ROOT));
        tempe.setText(database.temp+"°C");
        humidity.setText("HUMIDITY : "+database.humidity.toUpperCase(Locale.ROOT)+"%");
        wind.setText("WIND SPEED : " + database.wind.toUpperCase(Locale.ROOT)+" m/s");
        pressure.setText("PRESSURE : "+database.pressure.toUpperCase(Locale.ROOT)+" mbar");
        description.setText("DESCRIPTION : "+database.description.toUpperCase(Locale.ROOT));
        p1.setVisibility(View.INVISIBLE);
        if(database.main_climate.equals("Clear")){
sun.setVisibility(View.VISIBLE);
        }
        if(database.main_climate.equals("Haze")){
haze.setVisibility(View.VISIBLE);
        }
        if(database.main_climate.equals("Sun")){
sun.setVisibility(View.VISIBLE);
        }
        if(database.main_climate.equals("Rain")){
rain.setVisibility(View.VISIBLE);
        }
        if(database.main_climate.equals("Thunder")){
thunder.setVisibility(View.VISIBLE);
        }
        if(database.main_climate.equals("Clouds")){
clouds.setVisibility(View.VISIBLE);
        }
    }

}