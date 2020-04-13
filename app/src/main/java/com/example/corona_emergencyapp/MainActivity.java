package com.example.corona_emergencyapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.provider.Settings;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.OnColorSelectedListener;
import com.flask.colorpicker.builder.ColorPickerClickListener;
import com.flask.colorpicker.builder.ColorPickerDialogBuilder;

public class MainActivity extends AppCompatActivity {
    private Button spreads;
    private Button prevention;
    private Button ifsick;
    private Button symptoms;
    private Button stigma;
    private Button track;
    private Button alert;
    private Button about;
    private ConstraintLayout layout;

    MediaPlayer player;
    SharedPreferences sharedPreferences ;
    SharedPreferences.Editor editor;

    int i=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spreads = findViewById(R.id.btn_spreads);
        prevention = findViewById(R.id.btn_prevention);
        ifsick = findViewById(R.id.btn_doIfSick);
        symptoms = findViewById(R.id.btn_symptoms);
        stigma = findViewById(R.id.btn_stigma);
        track = findViewById(R.id.btn_track);
        alert = findViewById(R.id.btn_alert);
        about = findViewById(R.id.btn_about);
        layout = findViewById(R.id.view);

        sharedPreferences =  getApplicationContext().getSharedPreferences("Mypref",MODE_PRIVATE);
        editor = sharedPreferences.edit();

        layout.setBackgroundColor(sharedPreferences.getInt("color",-16777216));



//
//        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//        StrictMode.setThreadPolicy(policy);

        player = MediaPlayer.create(MainActivity.this, R.raw.alertsound);

        spreads.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(MainActivity.this,Spreads.class));
            }
        });
        prevention.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(MainActivity.this,Prevention.class));
            }
        });
        ifsick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(MainActivity.this,IfSick.class));
            }
        });
        symptoms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(MainActivity.this,Symptoms.class));
            }
        });
        stigma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(MainActivity.this,Stigma.class));
            }
        });
        track.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(checkConnection(MainActivity.this)){
                    finish();
                    startActivity(new Intent(MainActivity.this,MonitorMenu.class));
                }else{
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
                    builder1.setTitle("COVID19 E-APP");
                    builder1.setMessage("This feature requires internet connection.Please open your wifi or data connection");
                    builder1.setCancelable(false);

                    builder1.setPositiveButton(
                            "SETTINGS",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    startActivity(new Intent(Settings.ACTION_SETTINGS));
                                }
                            });

                    builder1.setNegativeButton(
                            "Cancel",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });

                    AlertDialog alert11 = builder1.create();
                    alert11.show();
                }

            }
        });
        alert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i++;

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if(i==1){
//                            Toast.makeText(MainActivity.this,"Single Click",Toast.LENGTH_SHORT).show();
                            startService(new Intent(MainActivity.this,MusicService.class));
                        }else if(i==2){
//                            Toast.makeText(MainActivity.this,"Double Click",Toast.LENGTH_SHORT).show();
                            stopService(new Intent(MainActivity.this,MusicService.class));
                        }
                        i=0;
                    }
                },500);
            }
        });

        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(MainActivity.this,About.class));
            }
        });


    }

    @Override
    public void onBackPressed() {
        String titleText = "Exit App";
        String message = "Are you sure you want to quit?";

        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(Color.WHITE);

        SpannableStringBuilder ssBuilder = new SpannableStringBuilder(titleText);

        ssBuilder.setSpan(
                foregroundColorSpan,
                0,
                titleText.length(),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        );

        SpannableStringBuilder ssBuilder2 = new SpannableStringBuilder(message);


        ssBuilder2.setSpan(
                foregroundColorSpan,
                0,
                message.length(),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        );


        AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
        builder1.setTitle(ssBuilder);
        builder1.setMessage(ssBuilder2);
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                    }
                });

        builder1.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert11 = builder1.create();
        alert11.show();
        alert11.getWindow().setBackgroundDrawable(new ColorDrawable(Color.DKGRAY));

    }

    public static boolean checkConnection(Context context) {
        final ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connMgr != null) {
            NetworkInfo activeNetworkInfo = connMgr.getActiveNetworkInfo();

            if (activeNetworkInfo != null) { // connected to the internet
                // connected to the mobile provider's data plan
                if (activeNetworkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                    // connected to wifi
                    return true;
                } else return activeNetworkInfo.getType() == ConnectivityManager.TYPE_MOBILE;
            }
        }
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        switch (id){
            case R.id.picker:
                colorPicker();
        }
        return super.onOptionsItemSelected(item);
    }
    public void colorPicker(){
        ColorPickerDialogBuilder
                .with(this)
                .setTitle("Choose color")
                .initialColor(R.color.colorPrimary)
                .wheelType(ColorPickerView.WHEEL_TYPE.CIRCLE)
                .density(12)
                .setOnColorSelectedListener(new OnColorSelectedListener() {
                    @Override
                    public void onColorSelected(int selectedColor) {

                    }
                })
                .setPositiveButton("Ok", new ColorPickerClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int selectedColor, Integer[] allColors) {
                        editor.putInt("color", selectedColor);
                        editor.commit();
                        editor.apply();

                        layout.setBackgroundColor(sharedPreferences.getInt("color",-16777216));
//                        Toast.makeText(MainActivity.this,Integer.toString(selectedColor),Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        return;
                    }
                })
                .build()
                .show();

    }
}
