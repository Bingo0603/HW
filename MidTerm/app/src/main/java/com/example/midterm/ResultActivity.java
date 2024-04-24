package com.example.midterm;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // 獲取從 MainActivity 傳遞過來的數據
        Intent intent = getIntent();
        if(intent != null) {
            String outputStr = intent.getStringExtra("output");

            // 在新的 Activity 中顯示數據，將其設置為 TextView 的文本
            TextView textView = findViewById(R.id.textViewResult);
            textView.setText(outputStr);
        }
    }
}
