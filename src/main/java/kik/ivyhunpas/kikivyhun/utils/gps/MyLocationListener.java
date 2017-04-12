package kik.ivyhunpas.kikivyhun.utils.gps;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import kik.ivyhunpas.kikivyhun.entities.User;

/**
 * Created by Blaise on 12/04/2017.
 */

public class MyLocationListener implements LocationListener {

    @Override
    public void onLocationChanged(Location loc) {

        Toast.makeText(
                context,
                "Location changed: Lat: " + loc.getLatitude() + " Lng: "
                        + loc.getLongitude(), Toast.LENGTH_SHORT).show();
        String longitude = "Longitude: " + loc.getLongitude();
        Log.v(TAG, longitude);
        String latitude = "Latitude: " + loc.getLatitude();
        Log.v(TAG, latitude);
    }

    private static final String TAG = "Debug";
    private Context context;
    private User user;

    public MyLocationListener(Context context, User user) {
        this.context = context;
        this.user = user;
    }

    @Override
    public void onProviderDisabled(String provider) {
    }

    @Override
    public void onProviderEnabled(String provider) {
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }
}
