package com.example.caculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText display;

    private String currentInput = "";
    private double result = 0;
    private String operator = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);

        display = findViewById(R.id.display);
    }

    public void processKeyInput(View view) {
        Button button = (Button) view;
        String buttonText = button.getText().toString();

        switch (buttonText) {
            case "Clear":
                clearDisplay();
                break;
            case "=":
                calculateResult();
                break;
            case "Back":
                if (!currentInput.isEmpty()) {
                    currentInput = currentInput.substring(0, currentInput.length() - 1);
                    display.setText(currentInput);
                }
                break;
            case "+":
            case "-":
            case "*":
            case "/":
                if (!currentInput.isEmpty()) {
                    operator = buttonText;
                    result = Double.parseDouble(currentInput);
                    currentInput = "";
                }
                break;
            default:
                appendInput(buttonText);
                break;
        }
    }

    private void appendInput(String input) {
        currentInput += input;
        display.setText(currentInput);
    }

    private void clearDisplay() {
        currentInput = "";
        result = 0; // 重置结果
        operator = ""; // 重置操作符
        display.setText("0");
    }

    private void calculateResult() {
        if (!currentInput.isEmpty() && !operator.isEmpty()) {
            double inputValue = Double.parseDouble(currentInput);
            switch (operator) {
                case "+":
                    result += inputValue;
                    break;
                case "-":
                    result -= inputValue;
                    break;
                case "*":
                    result *= inputValue;
                    break;
                case "/":
                    if (inputValue != 0) {
                        result /= inputValue;
                    } else {
                        // 处理除以零错误
                        display.setText("Error");
                        return;
                    }
                    break;
            }
            currentInput = String.valueOf(result);
            display.setText(currentInput);
            operator = ""; // 重置操作符
        }
    }
}
