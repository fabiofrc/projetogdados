/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdados.projeto.util.Relatorio;

import com.gdados.projeto.facade.ComentarioFacade;

/**
 *
 * @author PMBV-164029
 */
public class ContadorComentariosByNoticia {

    public static double diferenca(double porcetagemComentrioByNotica) {
        double diferenca = 100.0 - porcetagemComentrioByNotica;
        return diferenca;
    }

    public static double porcentagemComentarioByNoticia(long cont_comentarioByNoticia, long cont_comentarioTotal) {
        double porcetagemComentrioByNotica = (cont_comentarioByNoticia * 100) / cont_comentarioTotal;
        return porcetagemComentrioByNotica;
    }

    public static long contaComentarioTotal(ComentarioFacade cf) {
        long cont_comentarioTotal = cf.contaTotal();
        return cont_comentarioTotal;
    }

    public static long comentarioBYNoticia(ComentarioFacade cf) {
        long cont_comentarioByNoticia = cf.contaComentarioByNoticia(1L);
        return cont_comentarioByNoticia;
    }
}
