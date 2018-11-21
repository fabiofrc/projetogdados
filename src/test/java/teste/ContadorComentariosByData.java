/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teste;

import com.gdados.projeto.facade.ComentarioFacade;
import com.gdados.projeto.model.Comentario;
import java.util.Date;
import java.util.List;

/**
 *
 * @author PMBV-164029
 */
public class ContadorComentariosByData {

    public static void main(String[] args) {
        ComentarioFacade cf = new ComentarioFacade();
        Comentario c = new Comentario();

        Date d = new Date();

        long cont = cf.contaComentarioByData(d);
        System.out.println("Quantidade" + cont);

        List<Comentario> comentarios = cf.getAll();
        for (Comentario comentario : comentarios) {
            long quantidade = cf.contaComentarioByData(comentario.getDataRegistro());
            System.out.println("Data: " + comentario.getDataRegistro() + " Quant: " + quantidade);
        }
    }
}
