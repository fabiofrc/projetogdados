package com.gdados.projeto.facade;

import com.gdados.projeto.dao.DaoGeneric;
import com.gdados.projeto.dao.JpaUtil;
import com.gdados.projeto.model.Categoria;
import java.io.Serializable;
import javax.persistence.EntityManager;

public class CategoriaFacade extends DaoGeneric<Categoria> implements Serializable {

    public CategoriaFacade() {
        super(Categoria.class);
    }
   
    EntityManager entityManager = new JpaUtil().createEntityManager();

}
