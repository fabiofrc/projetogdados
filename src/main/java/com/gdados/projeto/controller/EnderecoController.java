/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdados.projeto.controller;

import com.gdados.projeto.facade.EnderecoFacade;
import com.gdados.projeto.model.Endereco;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@SessionScoped
public class EnderecoController implements Serializable {

    private Endereco endereco;
    @Inject
    private EnderecoFacade enderecoFacade;
    private List<Endereco> enderecos;

    public EnderecoController() {
        if (endereco == null) {
            limpaCampo();
        }
    }

    public String salvar() {
        try {
            if (endereco.getId() == null) {
                enderecoFacade.save(endereco);
                limpaCampo();
                return "lista?faces-redirect=true";
            } else {
                enderecoFacade.update(endereco);
                limpaCampo();
                return "lista?faces-redirect=true";
            }
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
        return null;
    }

    public String view(Long id) {
        try {
            endereco = enderecoFacade.getAllByCodigo(id);
            return "detalhes?faces-redirect=true";
        } catch (Exception e) {
        }
        return null;
    }

    public String editar(Long id) {
        try {
            endereco = enderecoFacade.getAllByCodigo(id);
            return "cadastro?faces-redirect=true";
        } catch (Exception e) {
        }
        return null;
    }

    public void deletar(Endereco endereco) {
        try {
            enderecoFacade.delete(endereco);
            getEnderecos();
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    public String lista() {
        return "/paginas/adm/endereco/lista?faces-redirect=true";
    }

    public String novo() {
        limpaCampo();
        return "/paginas/adm/endereco/cadastro?faces-redirect=true";
    }

    private void limpaCampo() {
        endereco = new Endereco();
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public EnderecoFacade getEnderecoFacade() {
        return enderecoFacade;
    }

    public void setEnderecoFacade(EnderecoFacade enderecoFacade) {
        this.enderecoFacade = enderecoFacade;
    }

    public List<Endereco> getEnderecos() {
        enderecos = enderecoFacade.getAll();
        return enderecos;
    }

    public int getContador() {
        return enderecoFacade.count();
    }
}
