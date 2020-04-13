package com.example.corona_emergencyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;

public class Stigma extends AppCompatActivity {
    private PDFView pdfView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stigma);

        pdfView = findViewById(R.id.pdfView);
        pdfView.fromAsset("stigma.pdf").load();
    }
    @Override
    public void onBackPressed() {
        finish();
        startActivity(new Intent(Stigma.this,MainActivity.class));
    }
}
