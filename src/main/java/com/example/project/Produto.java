package com.example.project;

public class Produto {
    private int codigo;
    private String descricao;
    private String unidade;
    private double valorUnitario;
    private String substituicaoTributaria;

    public Produto(int codigo, String descricao, String unidade, double valorUnitario, String substituicaoTributaria) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.unidade = unidade;
        this.valorUnitario = valorUnitario;
        this.substituicaoTributaria = substituicaoTributaria;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getUnidade() {
        return unidade;
    }

    public double getValorUnitario() {
        return valorUnitario;
    }

    public String getSubstituicaoTributaria() {
        return substituicaoTributaria;
    }
}
