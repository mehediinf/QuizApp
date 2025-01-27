package com.quiz.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ResultActivity extends AppCompatActivity {

    private static final String PREFS_NAME = "QuizAppPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        TextView resultTextView = findViewById(R.id.tv_result);
        Button previewButton = findViewById(R.id.btn_preview);
        Button restartButton = findViewById(R.id.btn_restart);

        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String examDetailsJson = prefs.getString("examDetails", "");

        try {
            JSONObject examDetails = new JSONObject(examDetailsJson);
            int score = examDetails.getInt("score");
            int totalQuestions = examDetails.getInt("totalQuestions");
            resultTextView.setText("Your Score: " + score + " / " + totalQuestions);

            JSONArray answeredQuestions = examDetails.getJSONArray("answeredQuestions");
            // Optional: Display detailed question info here if needed

        } catch (JSONException e) {
            e.printStackTrace();
        }

        previewButton.setOnClickListener(v -> {
            Intent intent = new Intent(ResultActivity.this, PreviewActivity.class);
            startActivity(intent);
        });

        restartButton.setOnClickListener(v -> {
            Intent intent = new Intent(ResultActivity.this, QuizActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
