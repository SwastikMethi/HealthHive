package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class HomePage extends AppCompatActivity {

    boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        ImageButton profilebtn=findViewById(R.id.profilebtn);
        profilebtn.setOnClickListener(view -> {
            Intent intent=new Intent(this,Profilepg.class);
            startActivity(intent);
        });

        ImageButton bmibtn = findViewById(R.id.checkBmiBtn);
        bmibtn.setOnClickListener(view -> {
            Intent intent = new Intent(this,Bmicalculator.class);
            startActivity(intent);
        });

        findViewById(R.id.backBtn).setVisibility(View.GONE);
        SharedPreferences sharedPreferences = getSharedPreferences("user_data",MODE_PRIVATE);
        TextView title = findViewById(R.id.title);
        title.setText("Health Hive");

        ImageButton diagnozybtn = findViewById(R.id.identifier);
        diagnozybtn.setOnClickListener(view -> {
            Intent intent = new Intent(this,Diagnosticpg.class);
            startActivity(intent);
        });
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            finishAffinity();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }


}