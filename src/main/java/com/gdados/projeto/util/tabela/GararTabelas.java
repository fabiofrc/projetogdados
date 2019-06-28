
package com.gdados.projeto.util.tabela;

import javax.persistence.Persistence;


public class GararTabelas {

    public static void main(String[] args) {
        Persistence.createEntityManagerFactory("testePU");
        System.out.println("Tabelas forma geradas");
    }
}
