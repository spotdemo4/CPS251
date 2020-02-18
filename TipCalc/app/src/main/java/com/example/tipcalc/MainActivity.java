package com.example.tipcalc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    TextView amount;
    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        amount = findViewById(R.id.amount);
        result = findViewById(R.id.result);
    }

    public void calculateTip(View view) {
        DecimalFormat df = new DecimalFormat("0.00");

        double totalAmount = Double.parseDouble(amount.getText().toString());

        String ten = df.format((totalAmount / 100) * 10);
        String fifteen = df.format((totalAmount / 100) * 15);
        String twenty = df.format((totalAmount / 100) * 20);

        result.setText("Ten percent: " + ten + "\nFifteen percent: " + fifteen + "\nTwenty percent: " + twenty);
    }


    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {

        savedInstanceState.putString("resultText", result.getText().toString());

        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {

        super.onRestoreInstanceState(savedInstanceState);

        String resultText = savedInstanceState.getString("resultText");
        result.setText(resultText);
    }

}
