package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Bmicalculator extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmicalculator);

        findViewById(R.id.backBtn).setOnClickListener(view -> {
            finish();
        });
        TextView title = findViewById(R.id.title);
        title.setText("BMI calculator");

        TextView age=findViewById(R.id.age);

        TextView gender= findViewById(R.id.gender);
        ArrayList<String>list = new ArrayList<>();
        list.add("male");
        list.add("female");
        list.add("m");
        list.add("f");

        SharedPreferences sharedPreferences = getSharedPreferences("user_data",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        findViewById(R.id.button2).setOnClickListener(view -> {

            Intent intent=new Intent(this,dietplan.class);

            EditText heightinput=findViewById(R.id.height);
            EditText weightinput=findViewById(R.id.weight);
            String h = heightinput.getText().toString();
            String w = weightinput.getText().toString();
            if(!h.equals("") && !w.equals("") && list.contains(gender.getText().toString().toLowerCase())){
                float height=Integer.parseInt(h);
                float weight=Integer.parseInt(w);
                height =height/100;
                float Bmi=weight/(height*height);

                editor.putString("Age",age.getText().toString());
                editor.putString("Gender",gender.getText().toString());
                editor.putString("bmi",String.valueOf(Bmi));
                editor.apply();
                startActivity(intent);

            }else {
                if(!list.contains(gender.getText().toString().toLowerCase())){
                    gender.setText("");
                    Toast.makeText(this, "Invalid Gender", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(this, "Invalid input", Toast.LENGTH_SHORT).show();
                }
            }
        });
        findViewById(R.id.profilebtn).setVisibility(View.GONE);
    }
}