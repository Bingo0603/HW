package com.example.midterm;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.EditText;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 在onCreate中尋找RadioButton
        final RadioButton boy = findViewById(R.id.rdbBoy);
        final RadioButton girl = findViewById(R.id.rdbGirl);


        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public void onClick(View v) {
                String outputStr = "";

                RadioButton boy = (RadioButton) findViewById(R.id.rdbBoy);
                RadioButton girl = (RadioButton) findViewById(R.id.rdbGirl);
                if (boy.isChecked())
                    outputStr += "男生\n";
                else if (girl.isChecked())
                    outputStr += "女生\n";


                RadioGroup type = (RadioGroup) findViewById(R.id.rgType);
                RadioButton adult = findViewById(R.id.rdbAdult);
                RadioButton child = findViewById(R.id.rdbChild);
                RadioButton student = findViewById(R.id.rdbStudent);
                int ticketPrice = 0; // 初始化票價
                int checkedId = type.getCheckedRadioButtonId();
//                switch(checkedId) {
//                    case R.id.rdbAdult:
//                        outputStr += getResources().getString(R.string.regularticket) + "\n";
//                        break;
//                    case R.id.rdbChild:
//                        outputStr += getResources().getString(R.string.childticket )+ "\n";
//                        break;
//                    case R.id.rdbStudent:
//                        outputStr += getResources().getString(R.string.studentticket) + "\n";
//                        break;
//            }
                if (checkedId == R.id.rdbAdult) {
                    outputStr += getResources().getString(R.string.regularticket) + "\n";
                    ticketPrice = 500; // 全票價格
                } else if (checkedId == R.id.rdbChild) {
                    outputStr += getResources().getString(R.string.childticket) + "\n";
                    ticketPrice = 250; // 兒童票價格
                } else if (checkedId == R.id.rdbStudent) {
                    outputStr += getResources().getString(R.string.studentticket) + "\n";
                    ticketPrice = 400; // 學生票價格
                }

                // 獲取用戶輸入的張數
                EditText etquantity = findViewById(R.id.etQuantity);
                String quantityStr = etquantity.getText().toString();
                int quantity;
                try {
                    quantity = Integer.parseInt(quantityStr);
                } catch (NumberFormatException e) {
                    // 如果轉換失敗，將 quantity 設置為 0 或其他預設值
                    quantity = 0;
                    // 或者您可以向用戶顯示錯誤消息，例如：
                     Toast.makeText(MainActivity.this, "請輸入有效的數字", Toast.LENGTH_SHORT).show();
                }

                int totalAmount = quantity * ticketPrice;
                outputStr += "金額: " + totalAmount + " 元\n";


                TextView output = (TextView) findViewById(R.id.lblOutput);
                output.setText(outputStr);
                // 創建一個 Intent 將 outputStr 傳遞到新的活動
                Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                intent.putExtra("output", outputStr);
                startActivity(intent);
            }
        });
    }
}