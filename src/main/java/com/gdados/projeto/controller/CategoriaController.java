/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdados.projeto.controller;

import com.gdados.projeto.facade.CategoriaFacade;
import com.gdados.projeto.model.Categoria;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@SessionScoped
public class CategoriaController implements Serializable {

    private Categoria categoria;
    @Inject
    private CategoriaFacade categoriaFacade;
    private List<Categoria> categorias;
  
    public CategoriaController() {
        if (categoria == null) {
            limpaCampo();
        }
    }

    public String salvar() {
        try {
            if (categoria.getId() == null) {
                categoriaFacade.save(categoria);
                limpaCampo();
                return "lista?faces-redirect=true";
            } else {
                categoriaFacade.update(categoria);
                limpaCampo();
                return "lista?faces-redirect=true";
            }
        } catch (Exception e) {
            System.out.println("com.gdados.projeto.controller.CategoriaController.salvar()");
        }
        return null;
    }

    public String view(Long id) {
        try {
            categoria = categoriaFacade.getAllByCodigo(id);
            return "detalhes?faces-redirect=true";
        } catch (Exception e) {
        }
        return null;
    }

    public String editar(Long id) {
        try {
            categoria = categoriaFacade.getAllByCodigo(id);
            return "cadastro?faces-redirect=true";
        } catch (Exception e) {
        }
        return null;
    }

    public void deletar(Categoria categoria) {
        try {
            categoriaFacade.delete(categoria);
            getCategorias();
        } catch (Exception e) {
        }
    }

    public String lista() {
        return "/paginas/adm/categoria/lista?faces-redirect=true";
    }

    public String novo() {
        limpaCampo();
        return "/paginas/adm/categoria/cadastro?faces-redirect=true";
    }

    private void limpaCampo() {
        categoria = new Categoria();
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public CategoriaFacade getCategoriaFacade() {
        return categoriaFacade;
    }

    public void setCategoriaFacade(CategoriaFacade categoriaFacade) {
        this.categoriaFacade = categoriaFacade;
    }

    public List<Categoria> getCategorias() {
        categorias = categoriaFacade.getAll();
        return categorias;
    }

    public int getContador() {
        return categoriaFacade.count();
    }
}
