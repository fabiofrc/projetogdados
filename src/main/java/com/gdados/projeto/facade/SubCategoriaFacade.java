package com.gdados.projeto.facade;



import com.gdados.projeto.dao.DaoGeneric;
import com.gdados.projeto.model.SubCategoria;
import java.io.Serializable;

public class SubCategoriaFacade extends DaoGeneric<SubCategoria> implements Serializable {

    public SubCategoriaFacade() {
        super(SubCategoria.class);
    }
  
}
