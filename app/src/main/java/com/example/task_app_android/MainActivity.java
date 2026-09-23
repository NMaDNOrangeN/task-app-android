package com.example.task_app_android;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText firstNumber;
    private EditText secondNumber;
    private TextView operationData;
    private TextView resultData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView studentData = findViewById(R.id.textView1);
        TextView groupData = findViewById(R.id.textView2);
        firstNumber = findViewById(R.id.editTextNumber1);
        secondNumber = findViewById(R.id.editTextNumber2);
        operationData = findViewById(R.id.textViewOperation);
        TextView equalsData = findViewById(R.id.textViewEquals);
        resultData = findViewById(R.id.textViewResult);

        Button plusButton = findViewById(R.id.buttonPlus);
        Button minusButton = findViewById(R.id.buttonMinus);
        Button multiplyButton = findViewById(R.id.buttonMultiply);
        Button divideButton = findViewById(R.id.buttonDivide);
        Button clearButton = findViewById(R.id.buttonClear);

        studentData.setText("Shabalin E.K.");
        studentData.setTextSize(20);
        studentData.setTextColor(Color.GRAY);

        groupData.setText("T-433901-IST");
        groupData.setTextSize(20);
        groupData.setTextColor(Color.RED);

        plusButton.setOnClickListener(v -> calculate('+'));
        minusButton.setOnClickListener(v -> calculate('-'));
        multiplyButton.setOnClickListener(v -> calculate('*'));
        divideButton.setOnClickListener(v -> calculate('/'));
        clearButton.setOnClickListener(v -> clearAll());
    }

    private void calculate(char operation) {
        operationData.setText(String.valueOf(operation));

        String number1 = firstNumber.getText().toString().trim();
        String number2 = secondNumber.getText().toString().trim();

        if (number1.isEmpty() || number2.isEmpty()) {
            resultData.setText("Ошибка");
            return;
        }

        try {
            double num1 = Double.parseDouble(number1);
            double num2 = Double.parseDouble(number2);
            double result = 0;

            switch (operation) {
                case '+':
                    result = num1 + num2;
                    break;
                case '-':
                    result = num1 - num2;
                    break;
                case '*':
                    result = num1 * num2;
                    break;
                case '/':
                    if (num2 != 0) {
                        result = num1 / num2;
                    } else {
                        resultData.setText("Ошибка");
                        return;
                    }
                    break;

            }

            if (Double.isNaN(result)) {
                resultData.setText("Ошибка");
            }
            else if (result % 1 == 0) {
                resultData.setText(String.valueOf((int) result));
            } else {
                resultData.setText(String.valueOf(result));
            }
        }

        catch (NumberFormatException e) {
            resultData.setText("Ошибка");
        }
    }

    private void clearAll() {
        firstNumber.setText("");
        secondNumber.setText("");
        operationData.setText("~");
        resultData.setText("0");
    }
}