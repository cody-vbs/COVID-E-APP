package com.example.corona_emergencyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.widget.TextView;
import android.widget.Toast;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Pie;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import zone.nora.coronavirus.Coronavirus;
import zone.nora.coronavirus.data.latest.LatestData;

public class Monitor extends AppCompatActivity {

    AnyChartView anyChartView;
    TextView date;
    String dateNow;
    TextView datatv;


    public Monitor() throws IOException {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitor);

        anyChartView = findViewById(R.id.pieChart);
        date = findViewById(R.id.date);
        datatv = findViewById(R.id.data);

        SimpleDateFormat df = new SimpleDateFormat("MMM,dd yyyy");
        dateNow = df.format(Calendar.getInstance().getTime());


//        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//        StrictMode.setThreadPolicy(policy);
        try {
            new NetworkThread().execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void setPieChart() throws IOException {


    }

    class NetworkThread extends AsyncTask<Void, Void, Void> {

        String [] labels   ={"Deaths","Recovered","Confirmed"};
        int [] data;

        private ProgressDialog dialog;

        NetworkThread() throws IOException {
        }

        @Override
        protected void onPreExecute() {
            dialog = new ProgressDialog(Monitor.this);
            dialog.setMessage("Please wait, were processing the data");
            dialog.setCancelable(false);
            dialog.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            Coronavirus coronavirus = new Coronavirus();
            LatestData latestData = null;

            try {
                latestData = coronavirus.getLatestData();
            } catch (IOException e) {
                e.printStackTrace();
            }
            data = new int[3];
            data[0] = latestData.getConfirmed();
            data[1] = latestData.getDeaths();
            data[2] = latestData.getRecovered();



            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            Pie pie = AnyChart.pie();
            List<DataEntry> dataEntries = new ArrayList<>();
            for (int x = 0 ; x<labels.length;x++){
                dataEntries.add(new ValueDataEntry(labels[x],data[x]));
            }
            pie.palette();
            pie.data(dataEntries);
            anyChartView.setChart(pie);
            date.setText("COVID19 Case Report\n as of " + dateNow);
            datatv.setText("Data Provided by the Johns Hopkins University Center \n for Systems Science and Engineering (JHU CSSE).\n");


            if(dialog.isShowing()){
                dialog.dismiss();
            }

        }
    }

    @Override
    public void onBackPressed() {
        finish();
        startActivity(new Intent(Monitor.this,MonitorMenu.class));
    }
}
