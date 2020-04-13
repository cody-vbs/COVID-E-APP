package com.example.corona_emergencyapp;

import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

class CountryGetter extends AsyncTask<Void, Void, Void> {
    static String str;

    //        WebService.setUserName("syd123");
    OkHttpClient client =new OkHttpClient();
    String url;
    Response response;
    static String countryName = "";



    @Override
    protected Void doInBackground(Void... voids) {
        url =  "http://api.geonames.org/countryCodeJSON?lat="+MapView.lat + "&lng=" + MapView.longi+"&username=syd123" ;
        Request request =new Request.Builder().url(url).get().build();
        try {
            response =client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String responseData = null;
        try {
            responseData = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(responseData);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            countryName =jsonObject.getString("countryName");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {

        str = countryName;




    }
}