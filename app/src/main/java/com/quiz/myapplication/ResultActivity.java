package com.quiz.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    private TextView resultTextView, scoreTextView, totalQuestionsTextView, maxResultTxtView;
    private Button buttonBack;
    private int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // UI এলিমেন্ট ইনিশিয়ালাইজ
        resultTextView = findViewById(R.id.resultTextView);
        maxResultTxtView = findViewById(R.id.bestResultTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        totalQuestionsTextView = findViewById(R.id.totalQuestionsTextView);
        buttonBack = findViewById(R.id.btnBackId);

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ResultActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        // Intent থেকে স্কোর এবং মোট প্রশ্ন সংখ্যা পাওয়া
        Intent intent = getIntent();
        score = intent.getIntExtra("score", 0);
        int totalQuestions = intent.getIntExtra("totalQuestions", 0);

        // সর্বোচ্চ স্কোর লোড করে তা দেখানো
        int maxScore = loadScore();
        maxResultTxtView.setText("Best Score: " + maxScore);

        // যদি বর্তমান স্কোর সর্বোচ্চ স্কোর থেকে বেশি হয়, তাহলে তা সংরক্ষণ করা
        if (score > maxScore) {
            saveScore(score);
            maxResultTxtView.setText("Best Score: " + score);
        }

        // ফলাফল মেসেজ দেখানো
        if (score == 0 && totalQuestions ==0 ) {
            resultTextView.setText("You didn't play the game!! You scored 0%");
        }
        else if (score == totalQuestions) {
            resultTextView.setText("Excellent! You answered all questions correctly.");
        } else if (score >= totalQuestions / 2) {
            resultTextView.setText("Good Job! You scored above 50%");
        }
        else {
            resultTextView.setText("Better Luck Next Time!");
        }


        // স্কোর এবং মোট প্রশ্ন সংখ্যা UI-তে সেট করা
        scoreTextView.setText("Score: " + score);
        totalQuestionsTextView.setText("Total Questions: " + totalQuestions);
    }

    private void saveScore(int score) {
        SharedPreferences sharedPreferences = getSharedPreferences("gameScore", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("lastScore", score);
        editor.apply();  // apply() ব্যবহার করা ভালো practice
    }

    private int loadScore() {
        SharedPreferences sharedPreferences = getSharedPreferences("gameScore", Context.MODE_PRIVATE);
        return sharedPreferences.getInt("lastScore", 0);  // ডিফল্ট মান 0, যদি কোনো স্কোর না থাকে
    }
}
