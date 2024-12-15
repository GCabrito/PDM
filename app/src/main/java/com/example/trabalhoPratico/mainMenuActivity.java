package com.example.trabalhoPratico;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class mainMenuActivity extends AppCompatActivity {

    TextView usernameText;
    Button startBtn, prizes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_menu);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        usernameText = findViewById(R.id.usernameText);
        startBtn = findViewById(R.id.startBtn);
        prizes = findViewById(R.id.prizes);

        usernameText.setText(getIntent().getStringExtra("username"));

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Questions = new Intent(mainMenuActivity.this, QuestionsActivity.class);
                Questions.putExtra("username", usernameText.getText());
                startActivity(Questions);
            }
        });

        prizes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent prizesMenu = new Intent(mainMenuActivity.this, PrizesActivity.class);
                prizesMenu.putExtra("username", usernameText.getText());
                startActivity(prizesMenu);
            }
        });
    }
}