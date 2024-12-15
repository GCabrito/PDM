package com.example.trabalhoPratico;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "QQSMilionario";
    private static final String TABELA_DIFICULDADE = "dificuldade";
    private static final String TABELA_PERGUNTAS = "perguntas";
    private static final String TABELA_RESPOSTAS = "respostas";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //criação das tabelas
        db.execSQL("create table " + TABELA_DIFICULDADE + "(" +
                "idDificuldade int, " +
                "nomeDificuldade varchar(50), " +
                "primary key (idDificuldade));");

        db.execSQL("create table " + TABELA_PERGUNTAS + "(" +
                "idPergunta int, " +
                "pergunta varchar(1000), " +
                "dificuldade int, " +
                "primary key (idPergunta), " +
                "foreign key (dificuldade) references dificuldade(idDificuldade));");

        db.execSQL("create table " + TABELA_RESPOSTAS + "(" +
                "idResposta int, " +
                "resposta varchar(1000), " +
                "perguntaAssociada int, " +
                "valor boolean, " +
                "primary key (idResposta), " +
                "foreign key (perguntaAssociada) references perguntas(idPergunta));");

        //inserção dos dados
        db.execSQL("insert into " + TABELA_DIFICULDADE +
                " values (1, 'Facil')," +
                         "(2, 'Intermedio'), " +
                         "(3, 'Dificil');");

        db.execSQL("insert into " + TABELA_PERGUNTAS +
                " values (1, 'Quem foi o 1º rei de Portugal?', 1), " +
                "(2, 'Qual é a capital da França?', 1)," +
                "(3, 'Qual é o maior planeta do sistema solar?', 1)," +
                "(4, 'Quem pintou a Mona Lisa?', 1)," +
                "(5, 'Qual é a fórmula química da água?', 1)," +
                "(6, 'Em que continente está localizado o Brasil?', 1)," +
                "(7, 'Quantas cores tem o arco-íris?', 1)," +
                "(8, 'Qual é o animal conhecido como rei da selva?', 1)," +
                "(9, 'Qual é o nome do escritor de Harry Potter?', 1)," +
                "(10, 'Quantos minutos tem uma hora?', 1)," +
                "(11, 'Quem foi o primeiro homem a pisar a Lua?', 2)," +
                "(12, 'Qual é a unidade básica de energia no sistema internacional de unidades?', 2)," +
                "(13, 'Qual é o nome do rio mais longo do mundo?', 2)," +
                "(14, 'Em que país se encontra a famosa cidade de Petra?', 2)," +
                "(15, 'O que significa a sigla www na internet?', 2)," +
                "(16, 'Em que ano começou a primeira guerra mundial?', 2)," +
                "(17, 'Quem é conhecido como o pai da Física moderna?', 2)," +
                "(18, 'Qual é o idioma oficial falado na Suíça além do alemão e francês?', 2)," +
                "(19, 'Qual é o maior oceano do mundo?', 2)," +
                "(20, 'O que é um átomo?', 2)," +
                "(21, 'Qual é a terceira lei de Newton?', 3)," +
                "(22, 'Em que ano ocorreu a queda do Império Romano do Ocidente?', 3)," +
                "(23, 'Qual é a raiz quadrada de 49?', 3)," +
                "(24, 'Qual é a raiz cubica de 8?', 3)," +
                "(25, 'Qual foi a causa principal da Grande Depressão em 1929?', 3)," +
                "(26, 'Qual a capital da Lituânia?', 3)," +
                "(27, 'Como se chama a ponta de plástico do atacador do sapato?', 3)," +
                "(28, 'Qual é o nome do cientista que descobriu a penicilina?', 3)," +
                "(29, 'Quem escreveu A Origem das Espécies?', 3)," +
                "(30, 'Qual é o menor osso do corpo humano?', 3);");

        db.execSQL("insert into " + TABELA_RESPOSTAS +
                " values (1, 'D. Afonso Henriques', 1, true), " +
                "(2, 'D. João I', 1, false), " +
                "(3, 'D. Sancho I', 1, false)," +
                "(4, 'D. Dinis', 1, false), " +
                "(5, 'Paris', 2, true), " +
                "(6, 'Londres', 2, false), " +
                "(7, 'Berlim', 2, false)," +
                "(8, 'Madrid', 2, false), " +
                "(9, 'Júpiter', 3, true), " +
                "(10, 'Terra', 3, false), " +
                "(11, 'Marte', 3, false)," +
                "(12, 'Vénus', 3, false), " +
                "(13, 'Leonardo da Vinci', 4, true), " +
                "(14, 'Michelangelo', 4, false), " +
                "(15, 'Picasso', 4, false)," +
                "(16, 'Van Gogh', 4, false), " +
                "(17, 'H2O', 5, true), " +
                "(18, 'CO2', 5, false), " +
                "(19, 'O2', 5, false)," +
                "(20, 'CH4', 5, false), " +
                "(21, 'América do Sul', 6, true), " +
                "(22, 'Europa', 6, false), " +
                "(23, 'Ásia', 6, false)," +
                "(24, 'África', 6, false)," +
                "(25, 'Sete', 7, true), " +
                "(26, 'Seis', 7, false), " +
                "(27, 'Oito', 7, false)," +
                "(28, 'Cinco', 7, false)," +
                "(29, 'Leão', 8, true), " +
                "(30, 'Tigre', 8, false), " +
                "(31, 'Águia', 8, false)," +
                "(32, 'Elefante', 8, false)," +
                "(33, 'J.K. Rowling', 9, true), " +
                "(34, 'George R.R. Martin', 9, false), " +
                "(35, 'Stephen King', 9, false)," +
                "(36, 'Agatha Christie', 9, false)," +
                "(37, '60 minutos', 10, true), " +
                "(38, '45 minutos', 10, false), " +
                "(39, '100 minutos', 10, false)," +
                "(40, '30 minutos', 10, false)," +
                "(41, 'Neil Armstrong', 11, true), " +
                "(42, 'Buzz Aldrin', 11, false), " +
                "(43, 'Yuri Gagarin', 11, false)," +
                "(44, 'Alan Shepard', 11, false)," +
                "(45, 'Joule', 12, true), " +
                "(46, 'Newton', 12, false), " +
                "(47, 'Watt', 12, false)," +
                "(48, 'Ampere', 12, false), " +
                "(49, 'Rio Nilo', 13, true), " +
                "(50, 'Rio Amazonas', 13, false), " +
                "(51, 'Rio Yangtzé', 13, false)," +
                "(52, 'Rio Mississipi', 13, false)," +
                "(53, 'Jordânia', 14, true), " +
                "(54, 'Egito', 14, false), " +
                "(55, 'Grécia', 14, false)," +
                "(56, 'Turquia', 14, false)," +
                "(57, 'World Wide Web', 15, true), " +
                "(58, 'Web World Wide', 15, false), " +
                "(59, 'Wide Web World', 15, false)," +
                "(60, 'World Web Wide', 15, false)," +
                "(61, '1914', 16, true), " +
                "(62, '1918', 16, false), " +
                "(63, '1939', 16, false)," +
                "(64, '1920', 16, false)," +
                "(65, 'Albert Einstein', 17, true), " +
                "(66, 'Isaac Newton', 17, false), " +
                "(67, 'Galileo Galilei', 17, false)," +
                "(68, 'Max Planck', 17, false)," +
                "(69, 'Italiano', 18, true), " +
                "(70, 'Espanhol', 18, false), " +
                "(71, 'Inglês', 18, false)," +
                "(72, 'Russo', 18, false)," +
                "(73, 'Oceano Pacífico', 19, true), " +
                "(74, 'Oceano Atlântico', 19, false), " +
                "(75, 'Oceano Índico', 19, false)," +
                "(76, 'Oceano Ártico', 19, false)," +
                "(77, 'Menor partícula da matéria', 20, true), " +
                "(78, 'Molécula básica', 20, false), " +
                "(79, 'Bloco de energia', 20, false)," +
                "(80, 'Partícula de luz', 20, false)," +
                "(81, 'A toda ação há uma reação de igual intensidade e direção oposta', 21, true), " +
                "(82, 'A energia não pode ser criada ou destruída', 21, false), " +
                "(83, 'A força é igual à massa vezes a aceleração', 21, false)," +
                "(84, 'Todo corpo permanece em repouso ou em movimento uniforme', 21, false)," +
                "(85, '476 d.C.', 22, true), " +
                "(86, '410 d.C.', 22, false), " +
                "(87, '1453 d.C.', 22, false)," +
                "(88, '1000 d.C.', 22, false)," +
                "(89, '7', 23, true), " +
                "(90, '6', 23, false), " +
                "(91, '8', 23, false)," +
                "(92, '5', 23, false)," +
                "(93, '2', 24, true), " +
                "(94, '4', 24, false), " +
                "(95, '6', 24, false)," +
                "(96, '8', 24, false)," +
                "(97, 'Quebra do mercado de ações', 25, true), " +
                "(98, 'Guerra Fria', 25, false), " +
                "(99, 'Crise do petróleo', 25, false)," +
                "(100, 'Segunda Guerra Mundial', 25, false)," +
                "(101, 'Vilnius', 26, true), " +
                "(102, 'Riga', 26, false), " +
                "(103, 'Tallinn', 26, false)," +
                "(104, 'Helsinque', 26, false)," +
                "(105, 'Aglete', 27, true), " +
                "(106, 'Broche', 27, false), " +
                "(107, 'Cordão', 27, false)," +
                "(108, 'Ilhós', 27, false)," +
                "(109, 'Alexander Fleming', 28, true), " +
                "(110, 'Louis Pasteur', 28, false), " +
                "(111, 'Marie Curie', 28, false)," +
                "(112, 'Albert Einstein', 28, false)," +
                "(113, 'Charles Darwin', 29, true), " +
                "(114, 'Gregor Mendel', 29, false), " +
                "(115, 'Isaac Newton', 29, false)," +
                "(116, 'Galileo Galilei', 29, false)," +
                "(117, 'Estapediano', 30, true), " +
                "(118, 'Fémur', 30, false), " +
                "(119, 'Rádio', 30, false)," +
                "(120, 'Clavícula', 30, false);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists " + TABELA_RESPOSTAS);
        db.execSQL("drop table if exists " + TABELA_PERGUNTAS);
        db.execSQL("drop table if exists " + TABELA_DIFICULDADE);
        onCreate(db);
    }

    public List<Pergunta> easyQuestions() {
        List<Pergunta> perguntas = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT distinct p.idPergunta, p.pergunta " +
                "FROM " + TABELA_PERGUNTAS + " p " +
                "WHERE p.dificuldade = 1 " +
                "ORDER BY random()" +
                "LIMIT 6";

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                int idPergunta = cursor.getInt(0);
                String textoPergunta = cursor.getString(1);
                perguntas.add(new Pergunta(idPergunta, textoPergunta));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return perguntas;
    }

    public List<Pergunta> intermediateQuestions() {
        List<Pergunta> perguntas = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT distinct p.idPergunta, p.pergunta " +
                "FROM " + TABELA_PERGUNTAS + " p " +
                "WHERE p.dificuldade = 2 " +
                "ORDER BY random()" +
                "LIMIT 6";

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                int idPergunta = cursor.getInt(0);
                String textoPergunta = cursor.getString(1);
                perguntas.add(new Pergunta(idPergunta, textoPergunta));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return perguntas;
    }

    public List<Pergunta> hardQuestions() {
        List<Pergunta> perguntas = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT distinct p.idPergunta, p.pergunta " +
                "FROM " + TABELA_PERGUNTAS + " p " +
                "WHERE p.dificuldade = 3 " +
                "ORDER BY random()" +
                "LIMIT 6";

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                int idPergunta = cursor.getInt(0);
                String textoPergunta = cursor.getString(1);
                perguntas.add(new Pergunta(idPergunta, textoPergunta));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return perguntas;
    }

    public List<Resposta> answersForQuestion(int idPergunta) {
        List<Resposta> respostas = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "select idResposta, resposta, valor " +
                "from " + TABELA_RESPOSTAS + " " +
                "where perguntaAssociada = ? " +
                "order by random()";

        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(idPergunta)});

        if(cursor.moveToFirst()) {
            do {
                int idResposta = cursor.getInt(0);
                String textoResposta = cursor.getString(1);
                boolean valor = cursor.getInt(2) > 0;
                respostas.add(new Resposta(idResposta, textoResposta, valor));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return respostas;
    }
}
