package com.example.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);

        String[] items = {"Learn Numbers", "Math Operations", "Take Test"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            switch (position) {
                case 0:
                    startActivity(NumbersActivity.class);
                    break;
                case 1:
                    startActivity(MathActivity.class);
                    break;
                case 2:
                    startActivity(TestActivity.class);
                    break;

            }
        });
    }

    private void startActivity(Class<?> cls) {
        startActivity(new Intent(this, cls));
    }
}
