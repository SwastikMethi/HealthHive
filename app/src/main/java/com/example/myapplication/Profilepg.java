package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Profilepg extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profilepg);

        ImageButton backbtn=findViewById(R.id.backBtn);
        backbtn.setOnClickListener(view -> {
            finish();
        });

        findViewById(R.id.profilebtn).setVisibility(View.GONE);

        SharedPreferences sharedPreferences = getSharedPreferences("user_data",MODE_PRIVATE);

        TextView name=findViewById(R.id.name);
        name.setText(sharedPreferences.getString("name","null_name"));
        TextView bmi = findViewById(R.id.bmiTxt);
        bmi.setText(sharedPreferences.getString("bmi","Calculate BMI First"));
        TextView gender=findViewById(R.id.gend);
        gender.setText(sharedPreferences.getString("Gender","Enter Gender First"));
        TextView age=findViewById(R.id.age);
        age.setText(sharedPreferences.getString("Age","Enter Age First"));

        TextView title=findViewById(R.id.title);
        title.setText("Profile");
    }
}