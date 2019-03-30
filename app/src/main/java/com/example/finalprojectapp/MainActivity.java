/*
 * CSE41246 - Android Java Fundamentals
 * Winter 2019
 * Project #1
 * Hoong, Jasper
 */
package com.example.finalprojectapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button start = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.start = (Button)findViewById(R.id.startbutton);
        this.start.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this,
                        com.example.finalprojectapp.ChooseTopic.class);
                startActivity(intent);
            }
        });
    }
}
