package com.quiz.myapplication;

import java.util.ArrayList;
import java.util.List;

public class QuestionBank {

    public List<Question> getQuestions() {
        List<Question> questionList = new ArrayList<>();

        // প্রশ্ন ১
        questionList.add(new Question(
                "What is the capital of Bangladesh?",
                new String[]{"Dhaka", "Chittagong", "Khulna", "Sylhet"},
                0 // সঠিক উত্তর Dhaka
        ));

        // প্রশ্ন ২
        questionList.add(new Question(
                "Which planet is known as the Red Planet?",
                new String[]{"Earth", "Mars", "Jupiter", "Venus"},
                1 // সঠিক উত্তর Mars
        ));

        // প্রশ্ন ৩
        questionList.add(new Question(
                "What is the largest mammal?",
                new String[]{"Elephant", "Blue Whale", "Giraffe", "Human"},
                1 // সঠিক উত্তর Blue Whale
        ));

        // প্রশ্ন ৪
        questionList.add(new Question(
                "What is the boiling point of water?",
                new String[]{"50°C", "100°C", "150°C", "200°C"},
                1 // সঠিক উত্তর 100°C
        ));

        return questionList;
    }
}
