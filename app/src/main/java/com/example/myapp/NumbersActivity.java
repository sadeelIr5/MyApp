package com.example.myapp;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.HashMap;

public class NumbersActivity extends AppCompatActivity {

    private TextView textNumber;
    private ImageView imageNumber;
    private MediaPlayer mediaPlayer;
    private HashMap<Integer, Integer> numberImageMap;
    private int currentNumber = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);

        textNumber = findViewById(R.id.textNumber);
        imageNumber = findViewById(R.id.imageNumber);


        updateNumber(currentNumber);
    }

    public void speakNumber(View view) {
        int numberId = getResources().getIdentifier("number_" + currentNumber, "raw", getPackageName());
        mediaPlayer = MediaPlayer.create(this, numberId);
        mediaPlayer.start();
    }

    private void updateNumber(int number) {
        textNumber.setText(String.valueOf(number));
        int imageResourceId = getImageResourceId(number);
        if (imageResourceId != 0) {
            imageNumber.setImageResource(imageResourceId);
        } else {
            imageNumber.setImageResource(R.drawable.placeholder);
        }
    }

    private int getImageResourceId(int number) {
        switch (number) {
            case 0:
                return R.drawable.number_0;
            case 1:
                return R.drawable.number_1;
            case 2:
                return R.drawable.number_2;
            case 3:
                return R.drawable.number_3;
            case 4:
                return R.drawable.number_4;
            case 5:
                return R.drawable.number_5;
            case 6:
                return R.drawable.number_6;
            case 7:
                return R.drawable.number_7;
            case 8:
                return R.drawable.number_8;
            case 9:
                return R.drawable.number_9;
            case 10:
                return R.drawable.number_10;
            case 11:
                return R.drawable.number_11;
            case 12:
                return R.drawable.number_12;
            case 13:
                return R.drawable.number_13;
            case 14:
                return R.drawable.number_14;
            case 15:
                return R.drawable.number_15;
            case 16:
                return R.drawable.number_16;
            case 17:
                return R.drawable.number_17;
            case 18:
                return R.drawable.number_18;
            case 19:
                return R.drawable.number_19;
            case 20:
                return R.drawable.number_20;
            default:
                return R.drawable.placeholder; // Return 0 if no image found
        }
    }



    public void previousNumber(View view) {
        if (currentNumber > 0) {
            currentNumber--;
            updateNumber(currentNumber);
        } else {
            Toast.makeText(this, "Reached the minimum number", Toast.LENGTH_SHORT).show();
        }
    }

    public void nextNumber(View view) {
        if (currentNumber < 20) {
            currentNumber++;
            updateNumber(currentNumber);
        } else {
            Toast.makeText(this, "Reached the maximum number", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
