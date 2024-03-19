package com.example.twonumbers;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText editTextA;
    private EditText editTextB;
    private ListView listView;
    private ArrayList<String> resultsList;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextA = findViewById(R.id.edit_text_a);
        editTextB = findViewById(R.id.edit_text_b);
        listView = findViewById(R.id.list_view);
        resultsList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, resultsList);
        listView.setAdapter(adapter);

        Button plusButton = findViewById(R.id.plus_button);
        Button minusButton = findViewById(R.id.minus_button);
        Button multiplyButton = findViewById(R.id.multiply_button);
        Button divisionButton = findViewById(R.id.division_button);

        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate("+");
            }
        });

        minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate("-");
            }
        });

        multiplyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate("*");
            }
        });

        divisionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate("/");
            }
        });
    }

    private void calculate(String operator) {
        try {
            double numA = Double.parseDouble(editTextA.getText().toString());
            double numB = Double.parseDouble(editTextB.getText().toString());
            double result = 0;

            switch (operator) {
                case "+":
                    result = numA + numB;
                    break;
                case "-":
                    result = numA - numB;
                    break;
                case "*":
                    result = numA * numB;
                    break;
                case "/":
                    if (numB == 0) {
                        Toast.makeText(MainActivity.this, "Cannot divide by zero", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    result = numA / numB;
                    break;
            }

            String calculation = numA + " " + operator + " " + numB + " = " + result;
            resultsList.add(calculation);
            adapter.notifyDataSetChanged();

        } catch (NumberFormatException e) {
            Toast.makeText(MainActivity.this, "Invalid input", Toast.LENGTH_SHORT).show();
        }
    }
}
