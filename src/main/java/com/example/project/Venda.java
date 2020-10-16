package com.example.project;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Venda {
    private String BREAK = System.lineSeparator();
    private Date datahora;
    private String ccf;
    private String coo;
    private Loja loja;
    private List<Item> itens = new ArrayList<Item>();

    public Venda(Loja loja,Date datahora, String ccf, String coo) {
        this.datahora = datahora;
        this.ccf = ccf;
        this.coo = coo;
        this.loja = loja;
    }

    public static Boolean isNullOrEmpty(String s) {
        return s == null || s.isEmpty();
    }

    public void validarCampos(){
        if (isNullOrEmpty(this.ccf)){
            throw new RuntimeException("O campo ccf da venda é obrigatório");
        }

        if (isNullOrEmpty(this.coo)){
            throw new RuntimeException("O campo coo da venda é obrigatório");
        }

        if (this.itens.isEmpty()){
            throw new RuntimeException("Voce precisa inserir itens na sua venda");
        }

    }

    public void validaItem(int item, Produto produto, int quantidade){
        for (Item i: this.itens) {
            if (i.getItem() != item && i.getProduto().getCodigo() == produto.getCodigo()) {
                throw new RuntimeException("Voce não pode inserir o mesmo produto com itens diferentes");
            }
        }

        if (!(quantidade > 0)){
            throw new RuntimeException("Insira a quantidade de itens");
        }

        if (!(produto.getValorUnitario() > 0)){
            throw new RuntimeException("item não pode ser adicionado na venda com produto nesse estado");
        }
    }

    public void adicionarItem(int item, Produto produto, int quantidade){
        validaItem(item, produto, quantidade);
        Item itemVenda = new Item(this, item, produto, quantidade);
        this.itens.add(itemVenda);
    }

    public String dadosItens(){
        StringBuilder _dados = new StringBuilder("ITEM CODIGO DESCRICAO QTD UN VL UNIT(R$) ST VL ITEM(R$)" + BREAK);
        for (Item i: this.itens) {
            _dados.append(i.dadosItem());
        }
        return _dados.toString();
    }

    public double calcularTotal(){
        double _total = 0.0;
        for (Item i: this.itens) {
            _total += i.valorItem();
        }
        return _total;
    }


    public String dadosVenda(){
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy H:m:s");
        String _texto = "";
        _texto = String.format("%sV CCF:%s COO: %s",formato.format(this.datahora),this.ccf,this.coo);

        return _texto;
    }

    public String imprimeCupom(){
        this.validarCampos();
        String dadosLoja = this.loja.dadosLoja();
        String dadosVenda = this.dadosVenda();
        String dadosItens = this.dadosItens();
        double total = this.calcularTotal();

        return String.format("%s------------------------------" + BREAK + "%s" + BREAK + "CUPOM FISCAL" + BREAK + "%s" + "------------------------------" + BREAK + "TOTAL R$ %.2f" + BREAK,dadosLoja,dadosVenda,dadosItens, total);
    }
}