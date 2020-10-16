package com.example.project;

public class Item {
    private String BREAK = System.lineSeparator();
    private Venda venda;
    private int item;
    private Produto produto;
    private int quantidade;

    public Item(Venda venda, int item, Produto produto, int quantidade) {
        this.venda = venda;
        this.item = item;
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public Produto getProduto() {
        return this.produto;
    }

    public int getItem() {
        return this.item;
    }

    public int getQuantidade() {
        return this.quantidade;
    }

    public double valorItem(){
        return this.quantidade * this.produto.getValorUnitario();
    }

    public String dadosItem(){
        return String.format("%d %d %s %d %s %.2f %s %.2f" + BREAK, this.getItem(), produto.getCodigo(), produto.getDescricao(), this.getQuantidade(), produto.getUnidade(), produto.getValorUnitario(), produto.getSubstituicaoTributaria(),this.valorItem());
    }

}
