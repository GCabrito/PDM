package com.example.trabalhoPratico;

public class Resposta {
    private int id;
    private String texto;
    private boolean valor;

    public Resposta(int id, String texto, boolean valor) {
        this.id = id;
        this.texto = texto;
        this.valor = valor;
    }

    public int getId() {
        return id;
    }

    public String getTexto() {
        return texto;
    }

    public boolean getValor() {
        return valor;
    }
}
