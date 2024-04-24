package com.example.bmi;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView txvShow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txvShow = (TextView) findViewById(R.id.txvShow);
        txvShow.setTextSize(36);
        Button btnCalc = (Button) findViewById(R.id.btnCalc);
        Button btnClear = (Button) findViewById(R.id.btnClear);
        btnCalc.setOnClickListener(this);
        btnClear.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        EditText edtHeight = findViewById(R.id.edtHeight);
        EditText edtWeight = findViewById(R.id.edtWeight);

        if (v.getId() == R.id.btnCalc) {
            if (edtHeight.getText().toString().isEmpty() || edtWeight.getText().toString().isEmpty()) {
                // EditText為空，顯示錯誤消息
                Toast.makeText(this, "輸入身高體重", Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                double height = Double.parseDouble(edtHeight.getText().toString());
                double weight = Double.parseDouble(edtWeight.getText().toString());

                // 检查身高和體重是否大於0
                if (height <= 0 || weight <= 0) {
                    Toast.makeText(this, "身高和体重必须大於0", Toast.LENGTH_SHORT).show();
                    return;
                }

                double bmi = weight / Math.pow(height / 100.0, 2);
                if (bmi >= 24)
                    txvShow.setTextColor(Color.RED);
                else if (bmi < 18.5)
                    txvShow.setTextColor(Color.BLUE);
                else
                    txvShow.setTextColor(Color.GREEN);

                txvShow.setText(String.format("%.2f", bmi));
            } catch (NumberFormatException e) {
                // 用戶輸入無法解析為浮點數時，顯示錯誤消息
                Toast.makeText(this, "请输入有效的身高和體重", Toast.LENGTH_SHORT).show();
            }
        } else {
            edtHeight.setText("0");
            edtWeight.setText("0");
            txvShow.setText("");
}