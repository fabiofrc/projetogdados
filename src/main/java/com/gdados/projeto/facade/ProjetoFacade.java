package com.gdados.projeto.facade;

import com.gdados.projeto.dao.DaoGeneric;
import com.gdados.projeto.dao.JpaUtil;
import com.gdados.projeto.model.Projeto;
import java.io.Serializable;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class ProjetoFacade extends DaoGeneric<Projeto> implements Serializable {

    public ProjetoFacade() {
        super(Projeto.class);
    }

    EntityManager em = new JpaUtil().createEntityManager();

    public Long contaTotal() {
        Query q = em.createQuery("select count(c) FROM Projeto c");
        Long contador = (Long) q.getSingleResult();
        return contador;
    }

}
