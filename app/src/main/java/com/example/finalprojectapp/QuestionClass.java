package com.example.finalprojectapp;

public class QuestionClass {
    private String question;
    private String answerA;
    private String answerB;
    private String answerC;
    private String answerD;
    private String correctAnswer;

    //Set constructor
    QuestionClass(){

    }
    protected void setQuestion(String question) {
        this.question = question;
    }

    protected void setChoiceA(String answer) {
        this.answerA = answer;
    }

    protected void setChoiceB(String answer) {
        this.answerB = answer;
    }

    protected void setChoiceC(String answer) {
        this.answerC = answer;
    }

    protected void setChoiceD(String answer) {
        this.answerD = answer;
    }

    protected void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    protected String getQuestion() {
        return question;
    }

    protected String getChoiceA() {
        return answerA;
    }

    protected String getChoiceB() {
        return answerB;
    }

    protected String getChoiceC() {
        return answerC;
    }

    protected String getChoiceD() {
        return answerD;
    }

    protected boolean isCorrectAnswer(String answer) {

        boolean correctAnswer = answer.equals(this.correctAnswer);
        if (this.correctAnswer == null) {
            // No correct answer has been set.
            return false;
        }else if(correctAnswer){
            return true;
        }
        /////////////////////////////////////////////////
        // TO-DO: Compare the passed in answer with the class' correct answer.
        /////////////////////////////////////////////////

        return false;
    }
}

