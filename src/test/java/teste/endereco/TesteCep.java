/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teste.endereco;

import com.gdados.projeto.model.Endereco;
import com.gdados.projeto.util.endereco.WebServiceCep;

/**
 *
 * @author fabio
 */
public class TesteCep {

    public static void buscaCep() {
        //Faz a busca para o cep 58043-280
        WebServiceCep webServiceCep = WebServiceCep.searchCep("69309393");
        //A ferramenta de busca ignora qualquer caracter que não seja número.

        //caso a busca ocorra bem, imprime os resultados.
        if (webServiceCep.wasSuccessful()) {
            System.out.println("Cep: " + webServiceCep.getCep());
            System.out.println("Logradouro: " + webServiceCep.getLogradouroFull());
            System.out.println("Bairro: " + webServiceCep.getBairro());
            System.out.println("Cidade: " + webServiceCep.getCidade() + "/" + webServiceCep.getUf());

            //caso haja problemas imprime as exce??es.
        } else {
            System.out.println("Erro numero: " + webServiceCep.getResulCode());

            System.out.println("Descrição do erro: " + webServiceCep.getResultText());
        }
    }

    public static void buscaCepByEndereco(Endereco endereco) {
        //Faz a busca para o cep 58043-280
        WebServiceCep webServiceCep = WebServiceCep.searchCep("69309393");
        //A ferramenta de busca ignora qualquer caracter que não seja número.

        //caso a busca ocorra bem, imprime os resultados.
        if (webServiceCep.wasSuccessful()) {

            endereco.setCep(webServiceCep.getCep());
            endereco.setLogradouro(webServiceCep.getLogradouroFull());
            endereco.setBairro(webServiceCep.getBairro());
            endereco.setCidade(webServiceCep.getCidade());
            endereco.setUf(webServiceCep.getUf());

            System.out.println("Cep: " + endereco.getCep());
            System.out.println("Logradouro: " + endereco.getLogradouro());
            System.out.println("Bairro: " + endereco.getBairro());
            System.out.println("Cidade: " + endereco.getCidade() + "/" + endereco.getUf());

            //caso haja problemas imprime as exce??es.
        } else {
            System.out.println("Erro numero: " + webServiceCep.getResulCode());

            System.out.println("Descrição do erro: " + webServiceCep.getResultText());
        }
    }

    public static void main(String[] args) {
        Endereco endereco = new Endereco();
        buscaCepByEndereco(endereco);
    }
}
