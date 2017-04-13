package kik.ivyhunpas.kikivyhun.utils.gps;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.Toast;

import kik.ivyhunpas.kikivyhun.entities.User;

/**
 * Created by Blaise on 12/04/2017.
 */

public class UserLocationListener implements LocationListener {

    private static UserLocationListener instance = null;
    private static final String TAG = "Debug";
    private Context context;
    private User user;

    private UserLocationListener() {
    }

    public static UserLocationListener getInstance() {
        if (instance == null) {
            instance = new UserLocationListener();
        }
        return instance;
    }

    public User getUser() {
        return this.user;
    }

    public Location getUserLocation() {
        Location loc = new Location("");
        loc.setLatitude(this.user.getLat());
        loc.setLongitude(this.user.getLng());
        return loc;
    }


    public UserLocationListener setUserLocationListener(Context context, User user) {
        this.context = context;
        this.user = user;

        LocationManager locationManager = (LocationManager) this.context.getSystemService(Context.LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(
                this.context,
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(
                this.context,
                Manifest.permission.ACCESS_COARSE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {

            locationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER, 5000, 10, this);

            Location loc = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            this.user.setLat(loc.getLatitude());
            this.user.setLng(loc.getLongitude());
        } else {
            Toast.makeText(this.context, "Merci d'activer le gps", Toast.LENGTH_SHORT).show();
        }
        return this;
    }

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

        this.user.setLat(loc.getLatitude());
        this.user.setLng(loc.getLongitude());
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
