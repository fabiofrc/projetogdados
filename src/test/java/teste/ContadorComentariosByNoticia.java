/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teste;

import com.gdados.projeto.facade.ComentarioFacade;
import com.gdados.projeto.facade.NoticiaFacade;

/**
 *
 * @author PMBV-164029
 */
public class ContadorComentariosByNoticia {

    public static void main(String[] args) {
        ComentarioFacade cf = new ComentarioFacade();

        long cont_comentarioByNoticia = cf.contaComentarioByNoticia(1L);
        System.out.println("comentario para cada noticia: " + cont_comentarioByNoticia);

        long cont_comentarioTotal = cf.contaTotal();
        System.out.println("Total de comentarios: " + cont_comentarioTotal);

        NoticiaFacade nf = new NoticiaFacade();
        long cont_noticia = nf.contaTotal();
        System.out.println("Total de noticias: " + cont_noticia);

        double porcetagemComentrioByNotica = (cont_comentarioByNoticia * 100) / cont_comentarioTotal;
        System.out.println("porcentagem: " + porcetagemComentrioByNotica);

//        double total = (cont_comentarioByNoticia * 100) / cont_comentarioTotal;
        System.out.println("diferença: " + (100.0 - porcetagemComentrioByNotica));
    }
}
