package com.example.midterm1;
//A109221217王秉鑫

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RadioButton boy = findViewById(R.id.rdbBoy);
        final RadioButton girl = findViewById(R.id.rdbGirl);

        final RadioGroup type = findViewById(R.id.rgType);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public void onClick(View v) {
                String outputStr = "";

                if (boy.isChecked())
                    outputStr += getResources().getString(R.string.male) + "\n";
                else if (girl.isChecked())
                    outputStr += getResources().getString(R.string.female) + "\n";

                int ticketPrice = 0;
                int checkedId = type.getCheckedRadioButtonId();

                if (checkedId == R.id.rdbAdult) {
                    outputStr += getResources().getString(R.string.regularticket) + "\n";
                    ticketPrice = 500;
                } else if (checkedId == R.id.rdbChild) {
                    outputStr += getResources().getString(R.string.childticket) + "\n";
                    ticketPrice = 250;
                } else if (checkedId == R.id.rdbStudent) {
                    outputStr += getResources().getString(R.string.studentticket) + "\n";
                    ticketPrice = 400;
                }

                EditText etquantity = findViewById(R.id.etQuantity);
                String quantityStr = etquantity.getText().toString();
                int quantity;

                quantity = Integer.parseInt(quantityStr);

                int totalAmount = quantity * ticketPrice;
                outputStr += "金額: " + totalAmount + " 元\n";

                TextView output = findViewById(R.id.lblOutput);
                output.setText(outputStr);

                Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                intent.putExtra("output", outputStr);
                startActivity(intent);
            }
        });

        // 为男性和女性 RadioButton 设置 OnCheckedChangeListener
        boy.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    updateOutputText();
                }
            }
        });

        girl.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    updateOutputText();
                }
            }
        });

        // 为 RadioGroup 设置 OnCheckedChangeListener 监听器
        type.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                updateOutputText();
            }
        });
    }

    private void updateOutputText() {
        String outputStr = "";

        RadioButton boy = findViewById(R.id.rdbBoy);
        RadioButton girl = findViewById(R.id.rdbGirl);
        RadioGroup type = findViewById(R.id.rgType);

        if (boy.isChecked()) {
            outputStr += getResources().getString(R.string.male) + "\n";
        } else if (girl.isChecked()) {
            outputStr +=getResources().getString(R.string.female) + "\n";
        }

        int checkedId = type.getCheckedRadioButtonId();
        if (checkedId == R.id.rdbAdult) {
            outputStr +=   getResources().getString(R.string.regularticket) + "\n";
        } else if (checkedId == R.id.rdbChild) {
            outputStr += getResources().getString(R.string.childticket) + "\n";
        } else if (checkedId == R.id.rdbStudent) {
            outputStr +=  getResources().getString(R.string.studentticket) + "\n";
        }

        EditText etquantity = findViewById(R.id.etQuantity);
        String quantityStr = etquantity.getText().toString();
        int quantity;
        try {
            quantity = Integer.parseInt(quantityStr);
        } catch (NumberFormatException e) {
            quantity = 0;
            Toast.makeText(MainActivity.this, "請輸入有效的數字", Toast.LENGTH_SHORT).show();
            return;
        }

        int ticketPrice = 0;
        if (checkedId == R.id.rdbAdult) {
            ticketPrice = 500;
        } else if (checkedId == R.id.rdbChild) {
            ticketPrice = 250;
        } else if (checkedId == R.id.rdbStudent) {
            ticketPrice = 400;
        }

        int totalAmount = quantity * ticketPrice;
        outputStr += "金額: " + totalAmount + " 元\n";

        TextView output = findViewById(R.id.lblOutput);
        output.setText(outputStr);
    }
}
