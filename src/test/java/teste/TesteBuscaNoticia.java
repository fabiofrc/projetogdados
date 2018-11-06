/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teste;


import com.gdados.projeto.facade.NoticiaFacade;
import com.gdados.projeto.model.Noticia;
import com.gdados.projeto.util.filter.NoticiaFilter;
import java.util.List;

/**
 *
 * @author PMBV-164029
 */
public class TesteBuscaNoticia {

    @SuppressWarnings("null")
    public static void main(String[] args) {
        NoticiaFacade nf = new NoticiaFacade();
        NoticiaFilter noticiaFilter = new NoticiaFilter();
        noticiaFilter.setTitulo("LG");
        noticiaFilter.setCategoria("Analise espacial");

        if (noticiaFilter.getTitulo() != null || noticiaFilter.getCategoria() != null) {
            List<Noticia> noticias = nf.buscaNoticiaByFiltro1(noticiaFilter);
            for (Noticia noticia : noticias) {
                System.out.println("Noticia: " + noticia.getTitulo() + " Categoria: " + noticia.getSubCategoria().getNome());
//                System.out.println("Categoria: " + noticia.getCategoria().getNome());
            }
        }

    }
}
