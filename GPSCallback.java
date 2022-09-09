package com.example.speed_tracker;

import android.location.Location;

import java.io.IOException;

public interface GPSCallback
{
    void onGPSUpdate(Location location) throws IOException;
}