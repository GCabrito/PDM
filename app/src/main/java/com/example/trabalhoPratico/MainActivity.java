package com.example.trabalhoPratico;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText usernameTextBox;
    Button confirmBtn;

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

        usernameTextBox = findViewById(R.id.usernameTextBox);
        confirmBtn = findViewById(R.id.confirmBtn);

        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!usernameTextBox.getText().toString().isEmpty()) {
                    Intent mainMenu = new Intent(MainActivity.this, mainMenuActivity.class);
                    mainMenu.putExtra("username", usernameTextBox.getText().toString());
                    startActivity(mainMenu);
                } else {
                    Toast toast = Toast.makeText(MainActivity.this, "Nome não pode ser vazio", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
    }
}