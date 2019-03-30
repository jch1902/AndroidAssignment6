package com.example.finalprojectapp;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class apec extends AppCompatActivity {
    private ArrayList<QuestionClass> questionList = null;
    QuestionClass currentQuestion = null;
    int currentQuestionNumber = 1;
    private int maxScore = 0;
    private int currentScore = 0;

    RadioGroup radioGroup = null;
    RadioButton answerA = null;
    RadioButton answerB = null;
    RadioButton answerC = null;
    RadioButton answerD = null;

    Button submitButton = null;

    TextView question = null;
    TextView questionNumberView = null;
    TextView scoreView = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.apec_view);

        this.radioGroup = (RadioGroup)findViewById(R.id.RadioGroup);
        this.answerA = (RadioButton)findViewById(R.id.answerA);
        this.answerB = (RadioButton)findViewById(R.id.answerB);
        this.answerC = (RadioButton)findViewById(R.id.answerC);
        this.answerD = (RadioButton)findViewById(R.id.answerD);

        this.question = (TextView)findViewById(R.id.question);
        this.questionNumberView = (TextView)findViewById(R.id.questionNumberView);
        this.scoreView = (TextView)findViewById(R.id.scoreView);

        this.submitButton = (Button)findViewById(R.id.submitButton);
        this.submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(answerCheck()){
                    if(currentQuestionNumber < maxScore){
                        int currentQuestionNumberArray = currentQuestionNumber;
                        currentQuestionNumber++;
                        currentQuestion = questionList.get(currentQuestionNumberArray);
                        setQuestionView(currentQuestion);
                        String mxscore = Integer.toString(maxScore);
                        Log.d("ANSWER: ", mxscore);
                    }else{
                        Intent intent = new Intent(apec.this,
                                com.example.finalprojectapp.results.class);
                        intent.putExtra("currentScore", currentScore);
                        intent.putExtra("maxScore", maxScore);
                        startActivity(intent);
                    }
                }
            }
        });

        this.initQuestions();
        this.setQuestionView(this.currentQuestion);

    }
    private void initQuestions() {
        // Create some questions to ask the questions.

        this.questionList = new ArrayList<QuestionClass>();  // Initialize our question array.
        //First Question
        QuestionClass question1 = new QuestionClass();
        question1.setQuestion("In 'The Prince', what did Machiavelli believe?");
        question1.setChoiceA("It is better to be feared than loved as a ruler");
        question1.setChoiceB("Women are incapable of ruling");
        question1.setChoiceC("It is important to take everyone's needs into consideration");
        question1.setChoiceD("A prince should not keep prisoners and should execute them");
        question1.setCorrectAnswer("It is better to be feared than loved as a ruler");
        questionList.add(question1);
        //Second question
        QuestionClass question2 = new QuestionClass();
        question2.setQuestion("Where was Napoleon Bonaparte exiled to after his defeat in 1812?");
        question2.setChoiceA("Paris");
        question2.setChoiceB("London");
        question2.setChoiceC("Elba");
        question2.setChoiceD("St. Helena");
        question2.setCorrectAnswer("Elba");
        questionList.add(question2);
        //Third question
        QuestionClass question3 = new QuestionClass();
        question3.setQuestion("Which one of the below was an art style developed during the Interwar period?");
        question3.setChoiceA("Abstract");
        question3.setChoiceB("German Expressionism");
        question3.setChoiceC("Realism");
        question3.setChoiceD("Romanticism");
        question3.setCorrectAnswer("German Expressionism");
        questionList.add(question3);
        //Fourth question
        QuestionClass question4 = new QuestionClass();
        question4.setQuestion("Which one of the following was most likely an effect of the second Industrial Revolution?");
        question4.setChoiceA("Development of trains");
        question4.setChoiceB("Urbanization as more people flocked to the cities to be closer to factories");
        question4.setChoiceC("Crop failures as farmers went to work in factories");
        question4.setChoiceD("Agricultural Revolution");
        question4.setCorrectAnswer("Urbanization as more people flocked to the cities to be closer to factories");
        questionList.add(question4);
        //Fifth question
        QuestionClass question5 = new QuestionClass();
        question5.setQuestion("Which of the following was not used by Adolf Hitler to establish his totalitarian regime?");
        question5.setChoiceA("Mass purges of military officers, intellectuals, lawyers, etc. to get rid of any opposition to power");
        question5.setChoiceB("Regulating and promoting state-controlled mass leisure");
        question5.setChoiceC("Hitler youth used to indoctrinate the youth into the beliefs and goals of the regime");
        question5.setChoiceD("Use of propaganda to gain more support for the Nazi party's goals and beliefs");
        question5.setCorrectAnswer("Mass purges of military officers, intellectuals, lawyers, etc. to get rid of any opposition to power");
        questionList.add(question5);

        this.currentQuestion = question1;
        // Set the current, score, and total question size.
        this.currentQuestionNumber = 1;
        this.maxScore = this.questionList.size();
        this.currentScore = 0;
    }
    private void setQuestionView(QuestionClass quizQuestion) {
        if(quizQuestion == null) {
            Log.d("[DEBUG]", "quizQuestion is null in setQuestionView.");
            return;
        }

        // Clear the radio button checks just encase it was been set previously.
        radioGroup.clearCheck();
        String scoreString = Integer.toString(currentScore);
        String currentQuestionNumberString = Integer.toString(currentQuestionNumber);
        scoreView.setText(scoreString);
        question.setText(quizQuestion.getQuestion());
        answerA.setText(quizQuestion.getChoiceA());
        answerB.setText(quizQuestion.getChoiceB());
        answerC.setText(quizQuestion.getChoiceC());
        answerD.setText(quizQuestion.getChoiceD());
        questionNumberView.setText(currentQuestionNumberString);
    }
    private boolean answerCheck(){
        int selectedButtonId = this.radioGroup.getCheckedRadioButtonId();
        if(selectedButtonId != -1) {
            String answerSelectedStr = ((RadioButton)findViewById(selectedButtonId)).getText().toString();

            if (currentQuestion.isCorrectAnswer(answerSelectedStr)) {
                Log.d("ANSWER: ", "Correct");
                currentScore++;
            }
            else {
                Log.d("ANSWER: ", "Incorrect");
            }
            return true; // Allow to continue to next question.
        }
        else {
            // No answer selected.
            Toast.makeText(getApplicationContext(), "Please Select An Answer",
                    Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}
