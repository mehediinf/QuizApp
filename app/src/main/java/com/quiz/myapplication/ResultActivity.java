package com.quiz.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // Retrieve data from Intent
        int score = getIntent().getIntExtra("score", 0);
        int totalQuestions = getIntent().getIntExtra("totalQuestions", 0);

        // Display the result
        TextView resultTextView = findViewById(R.id.tv_result);
        resultTextView.setText("Your Score: " + score + " / " + totalQuestions);

        // Restart Quiz Button
        Button restartButton = findViewById(R.id.btn_restart);
        restartButton.setOnClickListener(v -> {
            Intent intent = new Intent(ResultActivity.this, QuizActivity.class);
            startActivity(intent);
            finish(); // Close ResultActivity
        });
    }
}
