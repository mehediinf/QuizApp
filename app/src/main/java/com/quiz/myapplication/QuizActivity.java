package com.quiz.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;

public class QuizActivity extends AppCompatActivity {

    private TextView questionTextView;
    private RadioGroup radioGroupOptions;
    private RadioButton radioOption1, radioOption2, radioOption3, radioOption4;
    private Button btnSubmit;

    private List<Question> questionList;
    private int currentQuestionIndex = 0;
    private int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        // UI এলিমেন্ট ইনিশিয়ালাইজ
        questionTextView = findViewById(R.id.questionTextView);
        radioGroupOptions = findViewById(R.id.radioGroupOptions);
        radioOption1 = findViewById(R.id.radioOption1);
        radioOption2 = findViewById(R.id.radioOption2);
        radioOption3 = findViewById(R.id.radioOption3);
        radioOption4 = findViewById(R.id.radioOption4);
        btnSubmit = findViewById(R.id.btnSubmit);

        // QuestionBank থেকে প্রশ্ন লোড করা
        QuestionBank questionBank = new QuestionBank();
        questionList = questionBank.getQuestions();

        // প্রথম প্রশ্ন দেখানো
        displayQuestion();

        // Submit বাটনের ক্লিক লিসেনার
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // ইউজারের নির্বাচিত অপশন যাচাই করা
                int selectedOptionId = radioGroupOptions.getCheckedRadioButtonId();
                if (selectedOptionId == -1) {
                    Toast.makeText(QuizActivity.this, "Please select an answer", Toast.LENGTH_SHORT).show();
                    return;
                }

                // সঠিক উত্তরের সাথে মিল পরীক্ষা
                checkAnswer(selectedOptionId);

                // পরবর্তী প্রশ্ন দেখানো বা কুইজ শেষ হলে ফলাফল স্ক্রিনে যাওয়া
                currentQuestionIndex++;
                if (currentQuestionIndex < questionList.size()) {
                    displayQuestion();
                } else {
                    showResult();
                }
            }
        });
    }

    private void displayQuestion() {
        // বর্তমান প্রশ্ন এবং অপশনগুলো সেট করা
        Question currentQuestion = questionList.get(currentQuestionIndex);
        questionTextView.setText(currentQuestion.getQuestionText());
        String[] options = currentQuestion.getOptions();

        // প্রতিটি অপশন সেট করা
        radioOption1.setText(options[0]);
        radioOption2.setText(options[1]);
        radioOption3.setText(options[2]);
        radioOption4.setText(options[3]);

        // পূর্বের সিলেকশন ক্লিয়ার করা
        radioGroupOptions.clearCheck();
    }

    private void checkAnswer(int selectedOptionId) {
        // সঠিক অপশন যাচাই করা
        int correctAnswerIndex = questionList.get(currentQuestionIndex).getCorrectAnswerIndex();
        RadioButton selectedRadioButton = findViewById(selectedOptionId);
        int selectedAnswerIndex = radioGroupOptions.indexOfChild(selectedRadioButton);

        if (selectedAnswerIndex == correctAnswerIndex) {
            score++;
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Wrong answer!", Toast.LENGTH_SHORT).show();
        }
    }

    private void showResult() {
        // ফলাফল দেখানোর জন্য ResultActivity তে যাওয়া
        Intent intent = new Intent(QuizActivity.this, ResultActivity.class);
        intent.putExtra("score", score);
        intent.putExtra("totalQuestions", questionList.size());
        startActivity(intent);
        finish();
    }
}