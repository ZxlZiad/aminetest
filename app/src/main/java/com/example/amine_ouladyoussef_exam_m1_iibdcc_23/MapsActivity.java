package com.example.amine_ouladyoussef_exam_m1_iibdcc_23;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONException;
import org.json.JSONObject;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {
    private MapView mapView;
    private GoogleMap googleMap;
    private String ipAddress;


    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onMapReady(GoogleMap map) {
        googleMap = map;

        new IPGeolocationTask().execute(ipAddress);
    }

    private class IPGeolocationTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            String ipAddress = params[0];
            return IPInfoAPI.getIPInfo(ipAddress);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if (result != null) {
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String city = jsonObject.getString("city");
                    String region = jsonObject.getString("region");
                    double latitude = Double.parseDouble(jsonObject.getString("loc").split(",")[0]);
                    double longitude = Double.parseDouble(jsonObject.getString("loc").split(",")[1]);

                    LatLng location = new LatLng(latitude, longitude);
                    googleMap.addMarker(new MarkerOptions().position(location).title(city + ", " + region));
                    googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 10f));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Toast.makeText(MapsActivity.this, "Failed to fetch IP info", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
