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

public class EndActivity extends AppCompatActivity {

    TextView usernameText2, prize;
    Button homePageBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_end);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        usernameText2 = findViewById(R.id.usernameText2);
        homePageBtn = findViewById(R.id.homePageBtn);
        prize = findViewById(R.id.prize);

        String username = getIntent().getStringExtra("username");
        usernameText2.setText(username);
        prize.setText(getIntent().getStringExtra("premioAtual"));

        homePageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainMenu = new Intent(EndActivity.this, mainMenuActivity.class);
                mainMenu.putExtra("username", username);
                startActivity(mainMenu);
            }
        });
    }
}