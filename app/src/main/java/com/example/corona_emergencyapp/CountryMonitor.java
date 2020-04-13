package com.example.corona_emergencyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Pie;

import org.geonames.GeoNamesException;
import org.geonames.WebService;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import zone.nora.coronavirus.Coronavirus;
import zone.nora.coronavirus.data.latest.LatestData;

public class CountryMonitor extends AppCompatActivity {
    AnyChartView anyChartView;
    TextView date;
    String dateNow;
    String country = "";
    TextView datatv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_monitor);

        anyChartView = findViewById(R.id.pieChart);
        date = findViewById(R.id.date);
        datatv = findViewById(R.id.data);

        SimpleDateFormat df = new SimpleDateFormat("MMM,dd yyyy");
        dateNow = df.format(Calendar.getInstance().getTime());


        new CountryGetter().execute();
        new NetworkThread().execute();


    }
    class NetworkThread extends AsyncTask<Void, Void, Void> {

        Double longitude;
        Double latitude;
        String result;
        OkHttpClient client =new OkHttpClient();
        String url;
        Response response;
        String country;

        String [] labels   ={"Deaths","Recovered","Confirmed"};
        int [] data;
        private ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            longitude = MapView.longi;
            latitude = MapView.lat;
            dialog = new ProgressDialog(CountryMonitor.this);
            dialog.setMessage("Please wait, were processing the data");
            dialog.setCancelable(false);
            dialog.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            WebService.setUserName("syd123");
            try {

                result = WebService.countryCode(latitude,longitude);
                url =  "https://corona.lmao.ninja/countries/" + CountryGetter.str;
                Request request =new Request.Builder().url(url).get().build();
                response =client.newCall(request).execute();
                String responseData =response.body().string();
                JSONObject jsonObject =new JSONObject(responseData);
                data = new int[3];
                data[0] = Integer.parseInt(jsonObject.getString("cases"));
                data[1] = Integer.parseInt(jsonObject.getString("deaths"));
                data[2] = Integer.parseInt(jsonObject.getString("recovered"));

                country = CountryGetter.str;

            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (GeoNamesException e) {
                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            Pie pie = AnyChart.pie();

            List<DataEntry> dataEntries = new ArrayList<>();
            for (int x = 0 ; x<labels.length;x++){
                dataEntries.add(new ValueDataEntry(labels[x],data[x]));
            }
            pie.data(dataEntries);

            anyChartView.setChart(pie);
            date.setText("COVID19 in " + country + " Case Report\n as of " + dateNow);
//            Data Provided by the Johns Hopkins University Center for Systems Science and Engineering (JHU CSSE).
            datatv.setText("Data Provided by the Johns Hopkins University Center \n for Systems Science and Engineering (JHU CSSE).\n");
            if(dialog.isShowing()){
                dialog.dismiss();
            }


        }
    }
    @Override
    public void onBackPressed() {
        finish();
        startActivity(new Intent(CountryMonitor.this,MapView.class));
    }



}
