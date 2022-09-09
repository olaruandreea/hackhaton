package com.example.speed_tracker;

import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;


import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.widget.TextView;

import java.io.IOException;
import java.math.BigDecimal;


public class MainActivity extends AppCompatActivity implements GPSCallback{
    private GPSManager gpsManager = null;
    private double speed = 0.0;
    private double latitude = 0.0;
    private double longitude = 0.0;

    Boolean isGPSEnabled=false;
    LocationManager locationManager;
    double currentSpeed, kmphSpeed;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 101);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        gpsManager = new GPSManager(MainActivity.this);
        isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (isGPSEnabled) {
            gpsManager.startListening(getApplicationContext());
            gpsManager.setGPSCallback(this);
        } else {
            gpsManager.showSettingsAlert();
        }

    }


    @Override
    public void onGPSUpdate(Location location) throws IOException {
        TextView firstTextView = (TextView) findViewById(R.id.textview1);
        TextView secondTextView = (TextView) findViewById(R.id.textview2);
        TextView thirdTextView = (TextView) findViewById(R.id.textview3);
        TextView fourthTextView = (TextView) findViewById(R.id.textview4);
        TextView fifthTextView = (TextView) findViewById(R.id.textview5);
        speed = location.getSpeed();
        currentSpeed = round(speed,3, BigDecimal.ROUND_HALF_UP);
        kmphSpeed = round((currentSpeed*3.6),3,BigDecimal.ROUND_HALF_UP);
        firstTextView.setText("Your current speed is: " + kmphSpeed + "km/h");
        double speedLimit = 50.00;
        fourthTextView.setText("Speed Limit: " + speedLimit + " km/h");


        if (kmphSpeed >= speedLimit){
            secondTextView.setText("You're driving over speed limit");
            thirdTextView.setText("");
        }
        else{
            secondTextView.setText("");
            thirdTextView.setText("You're within speed limit");
        }
    }



    @Override
    protected void onDestroy() {
        gpsManager.stopListening();
        gpsManager.setGPSCallback(null);
        gpsManager = null;
        super.onDestroy();
    }

    public static double round(double unrounded, int precision, int roundingMode) {
        BigDecimal bd = new BigDecimal(unrounded);
        BigDecimal rounded = bd.setScale(precision, roundingMode);
        return rounded.doubleValue();
    }
}