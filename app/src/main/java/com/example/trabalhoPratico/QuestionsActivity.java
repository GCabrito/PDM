package com.example.trabalhoPratico;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class QuestionsActivity extends AppCompatActivity {

    TextView questionView, level, currentPrize;
    Button answer1Btn, answer2Btn, answer3Btn, answer4Btn, quitBtn, help1Btn, help2Btn;

    DBHelper dbHelper = new DBHelper(this);
    List<Pergunta> perguntas;
    int nivel = 1, premioAtual = 0, currentQuestionIndex = 0;
    int fixedPrize = 0;
    private Set<Integer> perguntasUsadas = new HashSet<>();
    private Map<Button, Resposta> buttonToAnswerMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_questions);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        questionView = findViewById(R.id.questionView);
        answer1Btn = findViewById(R.id.answer1Btn);
        answer2Btn = findViewById(R.id.answer2Btn);
        answer3Btn = findViewById(R.id.answer3Btn);
        answer4Btn = findViewById(R.id.answer4Btn);
        level = findViewById(R.id.level);
        currentPrize = findViewById(R.id.currentPrize);
        quitBtn = findViewById(R.id.quitBtn);
        help1Btn = findViewById(R.id.help1Btn);
        help2Btn = findViewById(R.id.help2Btn);

        level.setText(nivel + "/15");
        currentPrize.setText("Valor: " + premioAtual);

        loadQuestionsByLevel();

        help1Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                use5050Help();
            }
        });

        help2Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                useSwitchQuestionHelp();
            }
        });

        quitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quitGame();
            }
        });
    }

    //função que muda a dificuldade e o prémio garantido de acordo com o  nível do jogo
    private void loadQuestionsByLevel() {
        if (nivel <= 5) {
            fixedPrize = 0;
            perguntas = dbHelper.easyQuestions();
            loadQuestion();
        } else if (nivel <= 10) {
            fixedPrize = 1000;
            perguntas = dbHelper.intermediateQuestions();
            loadQuestion();
        } else if (nivel <= 15) {
            fixedPrize = 32000;
            perguntas = dbHelper.hardQuestions();
            loadQuestion();
        }
    }

    // função que carrega as perguntas e as mostra no ecrã
    private void loadQuestion() {
        Pergunta pergunta;
        do {
            pergunta = perguntas.get(currentQuestionIndex);
            currentQuestionIndex++;
            if (currentQuestionIndex == perguntas.size()) {
                currentQuestionIndex = 0;
            }
        } while (perguntasUsadas.contains(pergunta.getId()));
        perguntasUsadas.add(pergunta.getId());

        questionView.setText(pergunta.getTexto());
        loadAnswers(pergunta.getId());
    }

    //função que carrega as respostas da pergunta que está a ser mostrada
    private void loadAnswers(int idPergunta) {
        DBHelper dbHelper = new DBHelper(this);
        List<Resposta> respostas = dbHelper.answersForQuestion(idPergunta);

        if(respostas.size() == 4) {
            setupAnswerButton(answer1Btn, respostas.get(0));
            setupAnswerButton(answer2Btn, respostas.get(1));
            setupAnswerButton(answer3Btn, respostas.get(2));
            setupAnswerButton(answer4Btn, respostas.get(3));
        }
        buttonToAnswerMap = new HashMap<>();
        buttonToAnswerMap.put(answer1Btn, respostas.get(0));
        buttonToAnswerMap.put(answer2Btn, respostas.get(1));
        buttonToAnswerMap.put(answer3Btn, respostas.get(2));
        buttonToAnswerMap.put(answer4Btn, respostas.get(3));
    }

    //função define o prémio atual conforme o nível da pergunta e verifica se a resposta está correta
    private void setupAnswerButton(Button button, Resposta resposta) {
        button.setText(resposta.getTexto());

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (resposta.getValor()) {
                    button.setBackgroundColor(Color.GREEN);
                    if (nivel == 1)
                        premioAtual = 100;
                    else if (nivel <= 4)
                        premioAtual = premioAtual * 2;
                    else if (nivel == 5)
                        premioAtual = 1000;
                    else if (nivel <= 11)
                        premioAtual = premioAtual * 2;
                    else if (nivel == 12)
                        premioAtual = 125000;
                    else if (nivel <= 15)
                        premioAtual = premioAtual * 2;

                    currentPrize.setText("Valor: " + premioAtual);

                    new Handler().postDelayed(() -> {
                        nivel++;
                        if (nivel == 16) {
                            winGame();
                        } else
                            level.setText(nivel + "/15");

                        resetAnswersButtons();
                        loadQuestionsByLevel();
                    }, 1000);
                }else {
                    button.setBackgroundColor(Color.RED);

                    new Handler().postDelayed(() -> {
                        lostGame();
                    }, 1000);
                }
            }
        });
    }

    // função que coloca os botões como estavam ao mudar a pergunta
    private void resetAnswersButtons() {
        answer1Btn.setBackgroundColor(0x152985);
        answer2Btn.setBackgroundColor(0x152985);
        answer3Btn.setBackgroundColor(0x152985);
        answer4Btn.setBackgroundColor(0x152985);

        answer1Btn.setVisibility(View.VISIBLE);
        answer2Btn.setVisibility(View.VISIBLE);
        answer3Btn.setVisibility(View.VISIBLE);
        answer4Btn.setVisibility(View.VISIBLE);
    }

    // ajuda do 50/50
    private void use5050Help() {
        help1Btn.setEnabled(false);

        // Identificar a resposta correta
        Button correctButton = null;
        List<Button> incorrectButtons = new ArrayList<>();

        for (Map.Entry<Button, Resposta> entry : buttonToAnswerMap.entrySet()) {
            if (entry.getValue().getValor()) {
                correctButton = entry.getKey(); // Guardar o botão da resposta correta
            } else {
                incorrectButtons.add(entry.getKey()); // Guardar os botões das respostas erradas
            }
        }

        // Selecionar dois botões incorretos para esconder
        Collections.shuffle(incorrectButtons);
        incorrectButtons.get(0).setVisibility(View.INVISIBLE);
        incorrectButtons.get(1).setVisibility(View.INVISIBLE);
    }

    // ajuda da troca de pergunta
    private void useSwitchQuestionHelp() {
        help2Btn.setEnabled(false);

        currentQuestionIndex = 5;
        resetAnswersButtons();
        loadQuestion();
    }

    // função que termina o jogo ao desistir
    private void quitGame() {
        String username = getIntent().getStringExtra("username");

        Intent end = new Intent(QuestionsActivity.this, EndActivity.class);
        end.putExtra("premioAtual", String.valueOf(premioAtual));
        end.putExtra("username", username);
        startActivity(end);
    }

    //função que termina o jogo caso se acerte todas as perguntas
    private void winGame() {
        String username = getIntent().getStringExtra("username");
        fixedPrize = 1000000;

        Intent end = new Intent(QuestionsActivity.this, EndActivity.class);
        end.putExtra("premioAtual", String.valueOf(fixedPrize));
        end.putExtra("username", username);
        startActivity(end);

    }

    // função que termina o jogo ao responder incorretamente a uma pergunta
    private void lostGame() {
        String username = getIntent().getStringExtra("username");

        Intent end = new Intent(QuestionsActivity.this, EndActivity.class);
        end.putExtra("premioAtual", String.valueOf(fixedPrize));
        end.putExtra("username", username);
        startActivity(end);

    }
}
