package com.example.krystian.location;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.TextView;

public class LocationNetwork extends Service implements LocationListener {

    private final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10;
    private final long MIN_TIME_BW_UPDATES = 60000;
    private LocationManager manager;
    public Location location;
    private Context context;
    private TextView textView;


    public LocationNetwork(Context context){
        this.context=context;
        manager =(LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        textView = (TextView) ((Activity)context).findViewById(R.id.Network);
        getLocation();
    }

    public void getLocation(){
        if(manager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
            try{
                manager.requestLocationUpdates(
                        LocationManager.NETWORK_PROVIDER,
                        MIN_TIME_BW_UPDATES,
                        MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
                if (manager != null) {
                    location = manager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                }
                textView.setText("Network:"+Double.toString(location.getLatitude())+"  "+ Double.toString(location.getLongitude()));
            }
            catch (SecurityException e){    e.printStackTrace();    }
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onLocationChanged(Location location) {
        getLocation();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
