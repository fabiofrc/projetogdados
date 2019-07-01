package com.gdados.projeto.facade;

import com.gdados.projeto.dao.DaoGeneric;
import com.gdados.projeto.dao.JpaUtil;
import com.gdados.projeto.model.Endereco;
import java.io.Serializable;
import javax.persistence.EntityManager;

public class EnderecoFacade extends DaoGeneric<Endereco> implements Serializable {

    public EnderecoFacade() {
        super(Endereco.class);
    }
   
    EntityManager entityManager = new JpaUtil().createEntityManager();

}
