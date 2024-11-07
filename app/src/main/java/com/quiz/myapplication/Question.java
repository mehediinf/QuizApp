package com.quiz.myapplication;

public class Question {
    private String questionText;
    private String[] options;
    private int correctAnswerIndex;

    public Question(String questionText, String[] options, int correctAnswerIndex) {
        this.questionText = questionText;
        this.options = options;
        this.correctAnswerIndex = correctAnswerIndex;
    }

    // প্রশ্নের টেক্সট রিটার্ন করে
    public String getQuestionText() {
        return questionText;
    }

    // অপশনগুলো রিটার্ন করে
    public String[] getOptions() {
        return options;
    }

    // সঠিক উত্তরের ইন্ডেক্স রিটার্ন করে
    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }
}