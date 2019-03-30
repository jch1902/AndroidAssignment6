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

public class apbio extends AppCompatActivity {
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
        setContentView(R.layout.apbio_view);

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
                        Intent intent = new Intent(apbio.this,
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
        question1.setQuestion("What is a good example of a negative feedback loop?");
        question1.setChoiceA("Regulation of blood glucose levels");
        question1.setChoiceB("Regulation of body temperature");
        question1.setChoiceC("Ripening apples");
        question1.setChoiceD("Child birth");
        question1.setCorrectAnswer("Regulation of body temperature");
        questionList.add(question1);
        //Second Question
        QuestionClass question2 = new QuestionClass();
        question2.setQuestion("What are homeotic genes?");
        question2.setChoiceA("Genes that control the development of sex cells");
        question2.setChoiceB("Genes that code for the secretion of enzymes that attach methyl groups to the histone proteins that package DNA into compact coils");
        question2.setChoiceC("Genes that specifiy the developmental patterns of groups of cells and drive the formation of basic body structures");
        question2.setChoiceD("Genes that stimulate memory B-Cells in the immune system");
        question2.setCorrectAnswer("Genes that specifiy the developmental patterns of groups of cells and drive the formation of basic body structures");
        questionList.add(question2);
        //Third Question
        QuestionClass question3 = new QuestionClass();
        question3.setQuestion("The axes of an embryo is influenced by the distribution of");
        question3.setChoiceA("microRNAs");
        question3.setChoiceB("the gray crescant");
        question3.setChoiceC("GnRH");
        question3.setChoiceD("morphogens");
        question3.setCorrectAnswer("morphogens");
        questionList.add(question3);
        //Fourth Question
        QuestionClass question4 = new QuestionClass();
        question4.setQuestion("All of the following may be associated with mating behavior EXCEPT:");
        question4.setChoiceA("aggressive behavior");
        question4.setChoiceB("releaser pheromones");
        question4.setChoiceC("search image");
        question4.setChoiceB("territoriality");
        question4.setCorrectAnswer("search image");
        questionList.add(question4);
        //Fifth Question
        QuestionClass question5 = new QuestionClass();
        question5.setQuestion("Gas diffusion in human lungs occurs across membranes of");
        question5.setChoiceA("alveoli");
        question5.setChoiceB("bronchi");
        question5.setChoiceC("the diaphragm");
        question5.setChoiceD("the larynx");
        question5.setCorrectAnswer("alveoli");
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
