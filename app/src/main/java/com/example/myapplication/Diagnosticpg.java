package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Diagnosticpg extends AppCompatActivity {

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diagnosticpg);
        findViewById(R.id.profilebtn).setVisibility(View.GONE);

        findViewById(R.id.backBtn).setOnClickListener(view ->{
            finish();
        });

        TextView title = findViewById(R.id.title);
        title.setText("Diagnostic");
    }
}