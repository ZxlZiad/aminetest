package com.example.amine_ouladyoussef_exam_m1_iibdcc_23;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class IPInfoAPI {
    private static final String API_URL = "https://ipinfo.io/";

    public static String getIPInfo(String ipAddress) {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(API_URL + ipAddress + "/geo")
                .build();

        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                return response.body().string();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
