/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdados.projeto.facade;

import com.gdados.projeto.dao.DaoGeneric;
import com.gdados.projeto.dao.JpaUtil;
import com.gdados.projeto.model.Usuario;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class UsuarioFacade extends DaoGeneric<Usuario> {

    public UsuarioFacade() {
        super(Usuario.class);
    }

    EntityManager em = new JpaUtil().createEntityManager();

    public Usuario verificaUsuario(String email) {
        try {
            Query q = em.createQuery("SELECT u FROM Usuario u WHERE u.email = :email");
            q.setParameter("email", email.toLowerCase());
            return (Usuario) q.getSingleResult();
        } catch (Exception e) {
            System.out.println("erro: " + e.getLocalizedMessage());
        }
        return null;
    }

}
