/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdados.projeto.util.tabela;

import javax.persistence.Persistence;

/**
 *
 * @author PMBV-164029
 */
public class GararTabelas {

    public static void main(String[] args) {
        Persistence.createEntityManagerFactory("testePU");
        System.out.println("Tabelas forma geradas");
    }
}
