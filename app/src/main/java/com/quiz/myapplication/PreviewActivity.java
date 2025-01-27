package com.quiz.myapplication;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PreviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);

        LinearLayout container = findViewById(R.id.previewContainer);

        SharedPreferences prefs = getSharedPreferences("QuizAppPrefs", MODE_PRIVATE);
        String examDetailsJson = prefs.getString("examDetails", "");

        try {
            JSONObject examDetails = new JSONObject(examDetailsJson);

            CardView cardView = new CardView(this);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            layoutParams.setMargins(16, 16, 16, 16);
            cardView.setLayoutParams(layoutParams);
            cardView.setRadius(16);
            cardView.setCardElevation(8);

            LinearLayout contentLayout = new LinearLayout(this);
            contentLayout.setOrientation(LinearLayout.VERTICAL);
            contentLayout.setPadding(16, 16, 16, 16);

            TextView examTitle = new TextView(this);
            examTitle.setText("Exam Details");
            examTitle.setTextSize(18);
            examTitle.setTypeface(null, android.graphics.Typeface.BOLD);
            contentLayout.addView(examTitle);

            int score = examDetails.getInt("score");
            int totalQuestions = examDetails.getInt("totalQuestions");
            TextView examSummary = new TextView(this);
            examSummary.setText("Score: " + score + " / " + totalQuestions);
            examSummary.setTextSize(16);
            contentLayout.addView(examSummary);

            JSONArray answeredQuestions = examDetails.getJSONArray("answeredQuestions");
            for (int i = 0; i < answeredQuestions.length(); i++) {
                JSONObject question = answeredQuestions.getJSONObject(i);

                // Add question text with margin
                TextView questionText = new TextView(this);
                questionText.setText("Q" + (i + 1) + ": " + question.getString("question"));
                questionText.setTextSize(14);
                LinearLayout.LayoutParams questionParams = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
                questionParams.setMargins(0, 16, 0, 8); // Top margin for spacing between questions
                questionText.setLayoutParams(questionParams);
                contentLayout.addView(questionText);

                // Add your answer with conditional coloring
                TextView yourAnswer = new TextView(this);
                String yourAnswerText = question.getString("yourAnswer");
                String correctAnswerText = question.getString("correctAnswer");

                yourAnswer.setText("Your Answer: " + yourAnswerText);
                yourAnswer.setTextSize(14);

                // Check if the answer is correct
                if (yourAnswerText.equals(correctAnswerText)) {
                    yourAnswer.setTextColor(Color.GREEN); // Correct answer
                } else {
                    yourAnswer.setTextColor(Color.RED); // Incorrect answer
                }

                LinearLayout.LayoutParams answerParams = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
                answerParams.setMargins(16, 8, 16, 8); // Margins for answers
                yourAnswer.setLayoutParams(answerParams);
                contentLayout.addView(yourAnswer);

                // Add correct answer
                TextView correctAnswer = new TextView(this);
                correctAnswer.setText("Correct Answer: " + correctAnswerText);
                correctAnswer.setTextSize(14);
                correctAnswer.setTextColor(Color.GREEN); // Correct answers always green
                contentLayout.addView(correctAnswer);
            }

            cardView.addView(contentLayout);
            container.addView(cardView);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
