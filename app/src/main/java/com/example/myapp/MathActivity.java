package com.example.myapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class MathActivity extends AppCompatActivity {

    private TextView textEquation;
    private TextView textAnswer;
    private Button buttonGenerate;

    private int number1, number2, correctAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math);

        textEquation = findViewById(R.id.textEquation);
        textAnswer = findViewById(R.id.textAnswer);
        buttonGenerate = findViewById(R.id.buttonGenerate);

        generateEquation(); // Generate initial equation
    }

    private void generateEquation() {
        Random random = new Random();
        number1 = random.nextInt(10); // Random number between 0 and 9
        number2 = random.nextInt(10); // Random number between 0 and 9

        // Randomly choose addition or subtraction
        boolean isAddition = random.nextBoolean();
        if (isAddition) {
            correctAnswer = number1 + number2; // Correct answer for addition
            textEquation.setText(number1 + " + " + number2 + " = ?");
        } else {
            // Ensure the result is positive
            if (number1 < number2) {
                int temp = number1;
                number1 = number2;
                number2 = temp;
            }
            correctAnswer = number1 - number2; // Correct answer for subtraction
            textEquation.setText(number1 + " - " + number2 + " = ?");
        }

        textAnswer.setText(""); // Clear previous answer
    }

    public void generateNewEquation(View view) {
        generateEquation(); // Generate a new equation on button click
    }

    public void showAnswer(View view) {
        textAnswer.setText(String.valueOf(correctAnswer));
    }
}
