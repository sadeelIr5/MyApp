package com.example.myapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class TestActivity extends AppCompatActivity {

    private TextView textQuestion;
    private RadioGroup radioGroup;
    private RadioButton radioButton1, radioButton2, radioButton3;
    private Button buttonSubmit;
    private TextView textViewResult;
    private int questionCount = 0;
    private int correctCount = 0;
    private int correctAnswer = 0;

    // مصفوفة للعمليات الحسابية البسيطة
    private String[] operations = {"+", "-"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        textQuestion = findViewById(R.id.textQuestionTest);
        radioGroup = findViewById(R.id.radioGroup);
        radioButton1 = findViewById(R.id.radioButton1);
        radioButton2 = findViewById(R.id.radioButton2);
        radioButton3 = findViewById(R.id.radioButton3);
        buttonSubmit = findViewById(R.id.buttonSubmit);
        textViewResult = findViewById(R.id.textViewResult);
        textViewResult.setVisibility(View.GONE);

        generateQuestion();

        // تعيين استماع لنقرات الزر
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(v);
            }
        });
    }

    private void generateQuestion() {
        Random random = new Random();
        int number1 = random.nextInt(10); // عدد عشوائي بين 0 و 9
        int number2 = random.nextInt(10); // عدد عشوائي بين 0 و 9
        String operation = operations[random.nextInt(operations.length)]; // عملية عشوائية

        switch (operation) {
            case "+":
                correctAnswer = number1 + number2;
                break;
            case "-":
                correctAnswer = number1 - number2;
                break;
        }

        textQuestion.setText("what is " + number1 + " " + operation + " " + number2 + "?");

        // توليد الإجابات الخاطئة
        int wrongAnswer1 = generateWrongAnswer(correctAnswer);
        int wrongAnswer2 = generateWrongAnswer(correctAnswer);

        // تعيين الإجابات على الأزرار بطريقة عشوائية
        int correctButton = random.nextInt(3);
        switch (correctButton) {
            case 0:
                radioButton1.setText(String.valueOf(correctAnswer));
                radioButton2.setText(String.valueOf(wrongAnswer1));
                radioButton3.setText(String.valueOf(wrongAnswer2));
                break;
            case 1:
                radioButton1.setText(String.valueOf(wrongAnswer1));
                radioButton2.setText(String.valueOf(correctAnswer));
                radioButton3.setText(String.valueOf(wrongAnswer2));
                break;
            case 2:
                radioButton1.setText(String.valueOf(wrongAnswer1));
                radioButton2.setText(String.valueOf(wrongAnswer2));
                radioButton3.setText(String.valueOf(correctAnswer));
                break;
        }

        // تخزين الإجابة الصحيحة في علامة لاحقة على الأزرار
        radioButton1.setTag(correctAnswer);
        radioButton2.setTag(correctAnswer);
        radioButton3.setTag(correctAnswer);
    }

    private int generateWrongAnswer(int correctAnswer) {
        Random random = new Random();
        int wrongAnswer;
        do {
            wrongAnswer = correctAnswer + random.nextInt(10) - 5; // توليد إجابة عشوائية بجوار الإجابة الصحيحة
        } while (wrongAnswer == correctAnswer); // تأكد من أن الإجابة الخاطئة مختلفة عن الإجابة الصحيحة
        return wrongAnswer;
    }

    public void checkAnswer(View view) {
        RadioButton selectedRadioButton = findViewById(radioGroup.getCheckedRadioButtonId());
        if (selectedRadioButton != null) {
            int selectedAnswer = Integer.parseInt(selectedRadioButton.getText().toString());
            int correctAnswer = (int) selectedRadioButton.getTag(); // الحصول على الإجابة الصحيحة من علامة الزر

            if (selectedAnswer == correctAnswer) {
                correctCount++;
                Toast.makeText(this, "correct answer!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "wrong answer!", Toast.LENGTH_SHORT).show();
            }

            questionCount++;

            if (questionCount < 10) {
                generateQuestion();
                radioGroup.clearCheck();
            } else {
                // عرض النتيجة النهائية
                double percentage = (double) correctCount / questionCount * 100;
                String resultMessage = "Your score:: " + String.format("%.2f", percentage) + "%";
                textViewResult.setText(resultMessage);
                textViewResult.setVisibility(View.VISIBLE);
                buttonSubmit.setText("Start a new test");
                buttonSubmit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        questionCount = 0;
                        correctCount = 0;
                        generateQuestion();
                        radioGroup.clearCheck();
                        textViewResult.setVisibility(View.GONE);
                        buttonSubmit.setText("Next>>");
                    }
                });
            }
        } else {
            Toast.makeText(this, "Please choose an answer.", Toast.LENGTH_SHORT).show();
        }
    }
}
