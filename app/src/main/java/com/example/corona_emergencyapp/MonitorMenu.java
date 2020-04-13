package com.example.corona_emergencyapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.OnColorSelectedListener;
import com.flask.colorpicker.builder.ColorPickerClickListener;
import com.flask.colorpicker.builder.ColorPickerDialogBuilder;

public class MonitorMenu extends AppCompatActivity {
    Button viewAll;
    Button byCountry;

    private ConstraintLayout layout;
    SharedPreferences sharedPreferences ;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitor_menu);

        viewAll = findViewById(R.id.btnViewAll);
        byCountry = findViewById(R.id.btnByCountry);
        layout =findViewById(R.id.view);

        sharedPreferences =  getApplicationContext().getSharedPreferences("Mypref",MODE_PRIVATE);
        editor = sharedPreferences.edit();

        layout.setBackgroundColor(sharedPreferences.getInt("color",-16777216));

        viewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(MonitorMenu.this,Monitor.class));
            }
        });
        byCountry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(MonitorMenu.this,MapView.class));
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
        startActivity(new Intent(MonitorMenu.this,MainActivity.class));
    }
}
