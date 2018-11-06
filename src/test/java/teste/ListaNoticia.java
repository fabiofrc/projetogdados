/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teste;

import com.gdados.projeto.facade.NoticiaFacade;
import com.gdados.projeto.model.Noticia;
import java.util.List;

/**
 *
 * @author PMBV-164029
 */
public class ListaNoticia {
    public static void main(String[] args) {
        NoticiaFacade nf = new NoticiaFacade();
        List<Noticia> lista = nf.getAll();
        for (Noticia noticia : lista) {
            System.out.println(noticia.getId());
        }
    }
}
