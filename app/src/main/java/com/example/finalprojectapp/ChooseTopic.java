package com.example.finalprojectapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class ChooseTopic extends AppCompatActivity {
    private Button APEC = null;
    private Button APBIO = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choosetopic_activity);
        this.APEC = (Button)findViewById(R.id.APEC);
        this.APEC.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(ChooseTopic.this,
                        com.example.finalprojectapp.apec.class);
                startActivity(intent);
            }
        });
        this.APBIO = (Button)findViewById(R.id.APBIO);
        this.APBIO.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(ChooseTopic.this,
                        com.example.finalprojectapp.apbio.class);
                startActivity(intent);
            }
        });
    }
}
