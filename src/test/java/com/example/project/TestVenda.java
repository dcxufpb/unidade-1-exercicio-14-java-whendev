package com.example.project;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestVenda {

    private String BREAK = System.lineSeparator();

    private String dataRecebida;
    private SimpleDateFormat formato;
    private Date dataFormatada;

    private String ccf = "021784";
    private String coo = "035804";

    private String NOME_LOJA = "Loja 1";
    private String LOGRADOURO = "Log 1";
    private int NUMERO = 10;
    private String COMPLEMENTO = "C1";
    private String BAIRRO = "Bai 1";
    private String MUNICIPIO = "Mun 1";
    private String ESTADO = "E1";
    private String CEP = "11111-111";
    private String TELEFONE = "(11) 1111-1111";
    private String OBSERVACAO = "Obs 1";
    private String CNPJ = "11.111.111/1111-11";
    private String INSCRICAO_ESTADUAL = "123456789";

    Produto produto1 = new Produto(100, "Banana", "cx", 7.45, "ST");
    Produto produto2 = new Produto(101, "Laranja", "cx", 3.32, "ST");
    Produto produto3 = new Produto(102, "Leite", "l", 2.15, "");
    Produto produto4 = new Produto(102, "Leite", "l", 0, "");

    private String TEXTO_ESPERADO_CENARIO_1 = "Loja 1" + BREAK + "Log 1, 10 C1" + BREAK + "Bai 1 - Mun 1 - E1"
            + BREAK + "CEP:11111-111 Tel (11) 1111-1111" + BREAK + "Obs 1" + BREAK + "CNPJ: 11.111.111/1111-11" + BREAK
            + "IE: 123456789" + BREAK + "------------------------------" + BREAK + "13/10/2020 15:39:0V CCF:021784 COO: 035804" + BREAK
            + "CUPOM FISCAL" + BREAK + "ITEM CODIGO DESCRICAO QTD UN VL UNIT(R$) ST VL ITEM(R$)" + BREAK
            + "1 100 Banana 10 cx 7,45 ST 74.50" + BREAK + "2 101 Laranja 5 cx 3,32 ST 16.60" + BREAK + "------------------------------" + BREAK
            + "TOTAL R$ 91.10" + BREAK;

    private String TEXTO_ESPERADO_CENARIO_2_VENDA_1 = "Loja 1" + BREAK + "Log 1, 10 C1" + BREAK + "Bai 1 - Mun 1 - E1"
            + BREAK + "CEP:11111-111 Tel (11) 1111-1111" + BREAK + "Obs 1" + BREAK + "CNPJ: 11.111.111/1111-11" + BREAK
            + "IE: 123456789" + BREAK + "------------------------------" + BREAK + "13/10/2020 15:39:0V CCF:021784 COO: 035804" + BREAK
            + "CUPOM FISCAL" + BREAK + "ITEM CODIGO DESCRICAO QTD UN VL UNIT(R$) ST VL ITEM(R$)" + BREAK
            + "1 100 Banana 1 cx 7,45 ST 7.45" + BREAK + "------------------------------" + BREAK
            + "TOTAL R$ 7.45" + BREAK;

    private String TEXTO_ESPERADO_CENARIO_2_VENDA_2 = "Loja 1" + BREAK + "Log 1, 10 C1" + BREAK + "Bai 1 - Mun 1 - E1"
            + BREAK + "CEP:11111-111 Tel (11) 1111-1111" + BREAK + "Obs 1" + BREAK + "CNPJ: 11.111.111/1111-11" + BREAK
            + "IE: 123456789" + BREAK + "------------------------------" + BREAK + "13/10/2020 15:39:0V CCF:021784 COO: 035804" + BREAK
            + "CUPOM FISCAL" + BREAK + "ITEM CODIGO DESCRICAO QTD UN VL UNIT(R$) ST VL ITEM(R$)" + BREAK
            + "1 101 Laranja 3 cx 3,32 ST 9.96" + BREAK + "------------------------------" + BREAK
            + "TOTAL R$ 9.96" + BREAK;

    private String TEXTO_ESPERADO_CENARIO_3 = "Loja 1" + BREAK + "Log 1, 10 C1" + BREAK + "Bai 1 - Mun 1 - E1"
            + BREAK + "CEP:11111-111 Tel (11) 1111-1111" + BREAK + "Obs 1" + BREAK + "CNPJ: 11.111.111/1111-11" + BREAK
            + "IE: 123456789" + BREAK + "------------------------------" + BREAK + "13/10/2020 15:39:0V CCF:021784 COO: 035804" + BREAK
            + "CUPOM FISCAL" + BREAK + "ITEM CODIGO DESCRICAO QTD UN VL UNIT(R$) ST VL ITEM(R$)" + BREAK
            + "1 100 Banana 3 cx 7,45 ST 22.35" + BREAK + "2 102 Leite 5 l 2,15  10.75" + BREAK + "------------------------------" + BREAK
            + "TOTAL R$ 33.10" + BREAK;

    public TestVenda() throws ParseException {
        this.dataRecebida = "13/10/2020 15:39:00";
        this.formato = new SimpleDateFormat("dd/MM/yyyy H:m:s");
        this.dataFormatada = formato.parse(dataRecebida);
    }


    @Test
    public void testCenario1Venda1(){
        Endereco endereco = new Endereco(LOGRADOURO,NUMERO,COMPLEMENTO,BAIRRO,MUNICIPIO,ESTADO,CEP);
        Loja loja = new Loja(NOME_LOJA,endereco,TELEFONE,OBSERVACAO,CNPJ,INSCRICAO_ESTADUAL);

        Venda venda = loja.vender(dataFormatada,ccf,coo);
        venda.adicionarItem(1,produto1,10);
        venda.adicionarItem(2,produto2,5);
        rodarTestarRetorno(TEXTO_ESPERADO_CENARIO_1, venda);
    }

    @Test
    public void testCenario2Venda1(){
        Endereco endereco = new Endereco(LOGRADOURO,NUMERO,COMPLEMENTO,BAIRRO,MUNICIPIO,ESTADO,CEP);
        Loja loja = new Loja(NOME_LOJA,endereco,TELEFONE,OBSERVACAO,CNPJ,INSCRICAO_ESTADUAL);

        Venda venda = loja.vender(dataFormatada,ccf,coo);
        venda.adicionarItem(1,produto1,1);
        rodarTestarRetorno(TEXTO_ESPERADO_CENARIO_2_VENDA_1, venda);
    }

    @Test
    public void testCenario2Venda2(){
        Endereco endereco = new Endereco(LOGRADOURO,NUMERO,COMPLEMENTO,BAIRRO,MUNICIPIO,ESTADO,CEP);
        Loja loja = new Loja(NOME_LOJA,endereco,TELEFONE,OBSERVACAO,CNPJ,INSCRICAO_ESTADUAL);

        Venda venda = loja.vender(dataFormatada,ccf,coo);
        venda.adicionarItem(1,produto2,3);
        rodarTestarRetorno(TEXTO_ESPERADO_CENARIO_2_VENDA_2, venda);
    }

    @Test
    public void testCenario3Venda1(){
        Endereco endereco = new Endereco(LOGRADOURO,NUMERO,COMPLEMENTO,BAIRRO,MUNICIPIO,ESTADO,CEP);
        Loja loja = new Loja(NOME_LOJA,endereco,TELEFONE,OBSERVACAO,CNPJ,INSCRICAO_ESTADUAL);

        Venda venda = loja.vender(dataFormatada,ccf,coo);
        venda.adicionarItem(1,produto1,3);
        venda.adicionarItem(2,produto3,5);
        rodarTestarRetorno(TEXTO_ESPERADO_CENARIO_3, venda);
    }

    @Test
    public void testCenario3Venda2(){
        try {
            Endereco endereco = new Endereco(LOGRADOURO,NUMERO,COMPLEMENTO,BAIRRO,MUNICIPIO,ESTADO,CEP);
            Loja loja = new Loja(NOME_LOJA,endereco,TELEFONE,OBSERVACAO,CNPJ,INSCRICAO_ESTADUAL);

            Venda venda = loja.vender(dataFormatada,ccf,coo);
            venda.adicionarItem(1,produto1,3);
            venda.adicionarItem(2,produto1,5);
        } catch (RuntimeException e) {
            assertEquals("Voce não pode inserir o mesmo produto com itens diferentes", e.getMessage());
        }
    }

    @Test
    public void testCenario4Venda1(){
        try {
            Endereco endereco = new Endereco(LOGRADOURO,NUMERO,COMPLEMENTO,BAIRRO,MUNICIPIO,ESTADO,CEP);
            Loja loja = new Loja(NOME_LOJA,endereco,TELEFONE,OBSERVACAO,CNPJ,INSCRICAO_ESTADUAL);

            Venda venda = loja.vender(dataFormatada,ccf,coo);
            venda.adicionarItem(0,produto1,3);
            venda.adicionarItem(2,produto2,5);
        } catch (RuntimeException e) {
            assertEquals("Insira a quantidade de itens", e.getMessage());
        }
    }

    @Test
    public void testCenario4Venda2(){
        try {
            Endereco endereco = new Endereco(LOGRADOURO,NUMERO,COMPLEMENTO,BAIRRO,MUNICIPIO,ESTADO,CEP);
            Loja loja = new Loja(NOME_LOJA,endereco,TELEFONE,OBSERVACAO,CNPJ,INSCRICAO_ESTADUAL);

            Venda venda = loja.vender(dataFormatada,ccf,coo);
            venda.adicionarItem(1,produto1,3);
            venda.adicionarItem(2,produto4,5);
        } catch (RuntimeException e) {
            assertEquals("item não pode ser adicionado na venda com produto nesse estado", e.getMessage());
        }
    }

    @Test
    public void testCenario5Venda1(){
        Endereco endereco = new Endereco(LOGRADOURO,NUMERO,COMPLEMENTO,BAIRRO,MUNICIPIO,ESTADO,CEP);
        Loja loja = new Loja(NOME_LOJA,endereco,TELEFONE,OBSERVACAO,CNPJ,INSCRICAO_ESTADUAL);

        Venda venda = loja.vender(dataFormatada,ccf,coo);
        verificarCampoObrigatorio("Voce precisa inserir itens na sua venda", venda);
    }


    private void rodarTestarRetorno(String expected, Venda venda) {

        //  action
        String retorno = venda.imprimeCupom();

        //  assertion
        assertEquals(expected, retorno);
    }


    private void verificarCampoObrigatorio(String mensagemEsperada, Venda venda) {
        try {
            venda.imprimeCupom();
        } catch (RuntimeException e) {
            assertEquals(mensagemEsperada, e.getMessage());
        }
    }

}