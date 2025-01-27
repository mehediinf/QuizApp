package com.quiz.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class QuizActivity extends AppCompatActivity {

    private static final String PREFS_NAME = "QuizAppPrefs";
    private ArrayList<Question> questions;
    private int currentQuestionIndex = 0;
    private int score = 0;

    private TextView questionTextView;
    private RadioGroup optionsGroup;
    private RadioButton option1, option2, option3, option4;

    private JSONArray answeredQuestions = new JSONArray();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        questions = QuestionBank.getQuestions();

        questionTextView = findViewById(R.id.tv_question);
        optionsGroup = findViewById(R.id.options_group);
        option1 = findViewById(R.id.option_1);
        option2 = findViewById(R.id.option_2);
        option3 = findViewById(R.id.option_3);
        option4 = findViewById(R.id.option_4);
        Button nextButton = findViewById(R.id.btn_next);

        loadQuestion();

        nextButton.setOnClickListener(v -> {
            int selectedOptionId = optionsGroup.getCheckedRadioButtonId();
            if (selectedOptionId != -1) {
                RadioButton selectedRadioButton = findViewById(selectedOptionId);
                String selectedAnswer = selectedRadioButton.getText().toString();
                Question currentQuestion = questions.get(currentQuestionIndex);

                // Record the answer
                JSONObject answeredQuestion = new JSONObject();
                try {
                    answeredQuestion.put("question", currentQuestion.getQuestionText());
                    answeredQuestion.put("yourAnswer", selectedAnswer);
                    answeredQuestion.put("correctAnswer", currentQuestion.getOptions()[currentQuestion.getCorrectAnswerIndex()]);
                    answeredQuestions.put(answeredQuestion);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                if (selectedAnswer.equals(currentQuestion.getOptions()[currentQuestion.getCorrectAnswerIndex()])) {
                    score++;
                }

                currentQuestionIndex++;
                if (currentQuestionIndex < questions.size()) {
                    loadQuestion();
                } else {
                    saveResults();
                    Intent intent = new Intent(QuizActivity.this, ResultActivity.class);
                    intent.putExtra("score", score);
                    intent.putExtra("totalQuestions", questions.size());
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    private void loadQuestion() {
        Question question = questions.get(currentQuestionIndex);
        questionTextView.setText(question.getQuestionText());
        option1.setText(question.getOptions()[0]);
        option2.setText(question.getOptions()[1]);
        option3.setText(question.getOptions()[2]);
        option4.setText(question.getOptions()[3]);
        optionsGroup.clearCheck();
    }

    private void saveResults() {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        JSONObject examDetails = new JSONObject();
        try {
            examDetails.put("score", score);
            examDetails.put("totalQuestions", questions.size());
            examDetails.put("answeredQuestions", answeredQuestions);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        editor.putString("examDetails", examDetails.toString());
        editor.apply();
    }
}
