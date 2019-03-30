package com.example.finalprojectapp;

import android.app.AppComponentFactory;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class results extends AppCompatActivity {
    int curScore = 0;
    int maxScore = 0;

    TextView resultSentence = null;

    Button replayButton = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.results_view);
        this.resultSentence = (TextView)findViewById(R.id.resultSentence);

        this.curScore = getIntent().getIntExtra("currentScore", 0);
        this.maxScore = getIntent().getIntExtra("maxScore", 0);

        String finalScore = Integer.toString(curScore);
        String maxScoreStr = Integer.toString(maxScore);
        resultSentence.setText(curScore + " out of " + maxScore + " correct.");

        this.replayButton = (Button)findViewById(R.id.replayButton);
        this.replayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAgain();
            }
        });
    }

    private String getPercentage(int current, int total) {
        float fPercent = (float)current/(float)total;
        return Integer.toString((int)Math.ceil((fPercent) * 100)) + "%";
    }
    private void startAgain() {
        Intent intent = new Intent(results.this,
                com.example.finalprojectapp.ChooseTopic.class);
        startActivity(intent);
    }
}
