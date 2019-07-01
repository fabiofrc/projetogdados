/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdados.projeto.util.endereco;

import com.gdados.projeto.model.Endereco;

/**
 *
 * @author fabio
 */

public class BuscadorCep {

    public void buscaCep() {
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

    public Endereco buscaCepByEndereco(Endereco endereco) {
        if (endereco.getCep() != null) {
            //Faz a busca para o cep 58043-280
            WebServiceCep webServiceCep = WebServiceCep.searchCep(endereco.getCep());
            //A ferramenta de busca ignora qualquer caracter que não seja número.

            //caso a busca ocorra bem, imprime os resultados.
            if (webServiceCep.wasSuccessful()) {

                endereco.setCep(webServiceCep.getCep());
                endereco.setLogradouro(webServiceCep.getLogradouroFull());
                endereco.setBairro(webServiceCep.getBairro());
                endereco.setCidade(webServiceCep.getCidade());
                endereco.setUf(webServiceCep.getUf());

                return endereco;

                //caso haja problemas imprime as exce??es.
            } else {
                System.out.println("Erro numero: " + webServiceCep.getResulCode());

                System.out.println("Descrição do erro: " + webServiceCep.getResultText());
            }
        }
        return null;
    }

    public void buscaCepByEndereco1(String cep) {
        if (cep != null) {
            //Faz a busca para o cep 58043-280
            WebServiceCep webServiceCep = WebServiceCep.searchCep(cep);
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
    }
}
