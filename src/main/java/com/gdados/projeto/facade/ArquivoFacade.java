package com.gdados.projeto.facade;

import com.gdados.projeto.dao.DaoGeneric;
import com.gdados.projeto.dao.JpaUtil;
import com.gdados.projeto.model.Arquivo;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class ArquivoFacade extends DaoGeneric<Arquivo> implements Serializable {

    public ArquivoFacade() {
        super(Arquivo.class);
    }
    EntityManager em = new JpaUtil().createEntityManager();

    public Arquivo arquivoById(Long id) {
        Query q = em.createQuery("select a from Arquivo a WHERE a.id  = :id");
        q.setParameter("id", id);
        return (Arquivo) q.getSingleResult();
    }

    public List<Arquivo> arquivoByProjeto(Long id) {
        Query q = em.createQuery("select a from Arquivo a JOIN a.projeto p WHERE p.id  = :id");
        q.setParameter("id", id);
        return q.getResultList();
    }

}
