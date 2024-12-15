package com.example.trabalhoPratico;

public class Pergunta {
    private int id;
    private String texto;

    public Pergunta(int id, String texto) {
        this.id = id;
        this.texto = texto;
    }

    public int getId() {
        return id;
    }

    public String getTexto() {
        return texto;
    }
}
