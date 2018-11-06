/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdados.projeto.facade;

import com.gdados.projeto.dao.DaoGeneric;
import com.gdados.projeto.model.Grupo;

public class GrupoFacade extends DaoGeneric<Grupo> {

    public GrupoFacade() {
        super(Grupo.class);
    }
}
