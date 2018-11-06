package com.gdados.projeto.facade;

import com.gdados.projeto.dao.DaoGeneric;
import com.gdados.projeto.dao.JpaUtil;
import com.gdados.projeto.model.Comentario;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class ComentarioFacade extends DaoGeneric<Comentario> implements Serializable {

    public ComentarioFacade() {
        super(Comentario.class);
    }

    EntityManager em = new JpaUtil().createEntityManager();

    public List<Comentario> listaComentarioByNoticia(Long id) {
        try {
            Query q = em.createQuery("SELECT c FROM Comentario c JOIN c.noticia n WHERE n.id = :id ORDER BY c.id DESC");
            q.setParameter("id", id);
            return q.getResultList();
        } catch (Exception e) {
            System.out.println("erro: " + e.getLocalizedMessage());
        }
        return null;
    }

    public List<Comentario> listaComentarioByParticipante(Long id) {
        try {
            Query q = em.createQuery("SELECT c FROM Comentario c JOIN c.participante n WHERE n.id = :id");
            q.setParameter("id", id);
            return q.getResultList();
        } catch (Exception e) {
            System.out.println("erro: " + e.getLocalizedMessage());
        }
        return null;
    }

    public Long contaComentarioByNoticia(Long id) {
        Query q = em.createQuery("select count(c) FROM Comentario c JOIN c.noticia n WHERE n.id = :id");
        q.setParameter("id", id);
        Long contador = (Long) q.getSingleResult();
        return contador;
    }

    public Long contaComentarioByData(Date dataRegistro) {
        Query q = em.createQuery("select count(c) FROM Comentario c WHERE c.dataRegistro = :dataRegistro");
        q.setParameter("dataRegistro", dataRegistro);
        Long contador = (Long) q.getSingleResult();
        return contador;
    }

    public Long contaTotal() {
        Query q = em.createQuery("select count(c) FROM Comentario c");
        Long contador = (Long) q.getSingleResult();
        return contador;
    }

}
