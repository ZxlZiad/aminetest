package com.example.amine_ouladyoussef_exam_m1_iibdcc_23;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private EditText ipEditText;
    private Button searchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ipEditText = findViewById(R.id.ipEditText);
        searchButton = findViewById(R.id.searchButton);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ipAddress = ipEditText.getText().toString().trim();
                if (!ipAddress.isEmpty()) {
                    Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                    intent.putExtra("ipAddress", ipAddress);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Please enter an IP address", Toast.LENGTH_SHORT).show();
                }
            }
        });




    }

    private class IPGeolocationTask extends AsyncTask<String, Void, String> {
        private Context context;

        public IPGeolocationTask() {
            this.context = MainActivity.this;
        }

        @Override
        protected String doInBackground(String... params) {
            String ipAddress = params[0];
            String url = "https://ipinfo.io/" + ipAddress + "/geo";
            return NetworkUtils.makeHttpRequest(url);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if (result != null) {
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String city = jsonObject.getString("city");
                    String region = jsonObject.getString("region");
                    String country = jsonObject.getString("country");

                    String locationText = "City: " + city + "\nRegion: " + region + "\nCountry: " + country;
                    TextView responseTextView = findViewById(R.id.responseTextView);
                    responseTextView.setText(locationText);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Toast.makeText(context, "Failed to fetch IP info", Toast.LENGTH_SHORT).show();
            }
        }
    }
}