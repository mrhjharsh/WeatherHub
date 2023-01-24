package com.example.weatherhub;

import android.widget.EditText;

public class database {
    public database() {
    }
    public database(String description,String main_climate,String temp,String wind, String humidity,String pressure) {
        database.description = description;
        database.main_climate = main_climate;
        database.temp = temp;
        database.wind = wind;
        database.humidity = humidity;
        database.pressure = pressure;
    }

    public static String description;
    public static String main_climate;
    public static String temp;
    public static String wind;
    public static String humidity;
    public static String pressure;


}
