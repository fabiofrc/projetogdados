/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdados.projeto.controller;

import com.gdados.projeto.facade.SubCategoriaFacade;
import com.gdados.projeto.model.SubCategoria;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@SessionScoped
public class SubCategoriaController implements Serializable {

    private SubCategoria subCategoria;
    @Inject
    private SubCategoriaFacade subCategoriaFacade;

    private List<SubCategoria> subCategorias;

    public SubCategoriaController() {
        if (subCategoria == null) {
            limpaCampo();
        }
    }

    public String salvar() {
        try {
            if (subCategoria.getId() == null) {
                subCategoriaFacade.save(subCategoria);
                limpaCampo();
                return "lista?faces-redirect=true";
            } else {
                subCategoriaFacade.update(subCategoria);
                limpaCampo();
                return "lista?faces-redirect=true";
            }
        } catch (Exception e) {
            System.out.println("com.gdados.projeto.controller.UsuarioController.salvar()");
        }
        return null;
    }

    public String view(Long id) {
        try {
            subCategoria = subCategoriaFacade.getAllByCodigo(id);
            return "detalhes?faces-redirect=true";
        } catch (Exception e) {
        }
        return null;
    }

    public String editar(Long id) {
        try {
            subCategoria = subCategoriaFacade.getAllByCodigo(id);
            return "cadastro?faces-redirect=true";
        } catch (Exception e) {
        }
        return null;
    }

    public void deletar(SubCategoria subCategoria) {
        try {
            subCategoriaFacade.delete(subCategoria);
            getSubCategorias();
        } catch (Exception e) {
        }
    }

    public String lista() {
        return "/paginas/adm/subcategoria/lista?faces-redirect=true";
    }

    public String novo() {
        limpaCampo();
        return "/paginas/adm/subcategoria/cadastro?faces-redirect=true";
    }

    private void limpaCampo() {
        subCategoria = new SubCategoria();
    }

    public SubCategoria getSubCategoria() {
        return subCategoria;
    }

    public void setSubCategoria(SubCategoria subCategoria) {
        this.subCategoria = subCategoria;
    }

    public SubCategoriaFacade getSubCategoriaFacade() {
        return subCategoriaFacade;
    }

    public void setSubCategoriaFacade(SubCategoriaFacade subCategoriaFacade) {
        this.subCategoriaFacade = subCategoriaFacade;
    }

    public List<SubCategoria> getSubCategorias() {
        subCategorias = subCategoriaFacade.getAll();
        return subCategorias;
    }

    public int getContador() {
        return subCategoriaFacade.count();
    }
}
