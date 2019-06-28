package com.gdados.projeto.facade;

import com.gdados.projeto.dao.DaoGeneric;
import com.gdados.projeto.dao.JpaUtil;
import com.gdados.projeto.model.Contato;
import java.io.Serializable;
import javax.persistence.EntityManager;

public class ContatoFacade extends DaoGeneric<Contato> implements Serializable {

    public ContatoFacade() {
        super(Contato.class);
    }
   
    EntityManager entityManager = new JpaUtil().createEntityManager();

}
