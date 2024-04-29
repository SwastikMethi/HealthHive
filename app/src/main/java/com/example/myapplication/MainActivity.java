package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.*;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText Usernameinput=findViewById(R.id.editTextTextPersonName);
        EditText Password=findViewById(R.id.editTextTextPassword);
        Intent intent = new Intent(this,HomePage.class);
        SharedPreferences sharedPreferences = getSharedPreferences("user_data",MODE_PRIVATE);

        if(!sharedPreferences.getString("name","null_name").equals("null_name")) startActivity(intent);


        Button loginBtn = findViewById(R.id.button);
        loginBtn.setOnClickListener(view -> {
            //code to exectue
            //Toast.makeText(this, "Button clicked", Toast.LENGTH_SHORT).show();
            String name = Usernameinput.getText().toString();
            String pswd = Password.getText().toString();

            SharedPreferences.Editor editor = sharedPreferences.edit();
            if(!name.equals("") && !pswd.equals("")){

                editor.putString("name",name);
                editor.putString("pswd",pswd);
                editor.apply();
                startActivity(intent);
            }else {
                Toast.makeText(this, "Invalid Inputs!", Toast.LENGTH_SHORT).show();
            }
        });
        
        findViewById(R.id.img).setOnClickListener(view -> {
            //Toast.makeText(this, "img clicked", Toast.LENGTH_SHORT).show();
        });
        
    }
}