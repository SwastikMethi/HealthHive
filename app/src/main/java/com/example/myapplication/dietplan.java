package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class dietplan extends AppCompatActivity {

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dietplan);

        findViewById(R.id.backBtn).setOnClickListener(view -> {
            finish();
        });

        SharedPreferences sharedPreferences = getSharedPreferences("user_data",MODE_PRIVATE);

        float bmi = Float.parseFloat(sharedPreferences.getString("bmi","null_value"));

        JSONObject data02 = loadJSONObjectFromRaw(this,R.raw.data02);

        try {
            JSONArray bmiPlans = data02.getJSONArray("diet_plan");
            for(int i = 0;i<bmiPlans.length();i++){
                JSONObject bmiPlan = bmiPlans.getJSONObject(i);
                JSONObject bmiRange = bmiPlan.getJSONObject("bmiRange");
                float min = Float.parseFloat(bmiRange.getString("min"));
                float max = Float.parseFloat(bmiRange.getString("max"));
                if(bmi>min && bmi<max){
                    TextView diet=findViewById(R.id.plans);
                    JSONObject menu = bmiPlan.getJSONObject("menu");
                    String Breakfast = menu.getString("Breakfast");
                    String Snacks = menu.getString("Snacks");
                    String Lunch = menu.getString("Lunch");
                    String Supper = menu.getString("Supper");
                    String Dinner = menu.getString("Dinner");
                    diet.setText(Breakfast+"\n\n"+Snacks+"\n\n"+Lunch+"\n\n"+Supper+"\n\n"+Dinner+"\n\n");
                }
            }
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        TextView title=findViewById(R.id.title);
        title.setText("Diet Plan");

        TextView value=findViewById(R.id.value);
        value.setText(String.valueOf(bmi));

        findViewById(R.id.profilebtn).setVisibility(View.GONE);

        TextView statement=findViewById(R.id.stm);

        if(bmi<18.5){
            statement.setText("Unfortunately you are underweight. Follow the Diet.");
        }else  if(bmi<24.9){
            statement.setText("Congrats! Your BMI is in healthy spectrum.");
        }else{
            statement.setText("Unfortunately you are overweight. Follow the Diet.");
        }

        ScrollView scrollView = findViewById(R.id.scrollView);
        scrollView.setVisibility(View.INVISIBLE);
        Button scrollbtn = findViewById(R.id.dietbtn);
        scrollbtn.setOnClickListener(view -> {
            scrollView.setVisibility(View.VISIBLE);
            findViewById(R.id.dietbtn).setVisibility(View.INVISIBLE);
        });

    }

    public JSONObject loadJSONObjectFromRaw(Context context, int resourceId) {
        JSONObject jsonObject = null;
        try {
            InputStream inputStream = context.getResources().openRawResource(resourceId);
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            String jsonString = new String(buffer, "UTF-8");
            jsonObject = new JSONObject(jsonString);
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
}



