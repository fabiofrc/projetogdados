/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdados.projeto.controller;

import com.gdados.projeto.facade.GrupoFacade;
import com.gdados.projeto.facade.UsuarioFacade;
import com.gdados.projeto.model.Grupo;
import com.gdados.projeto.model.Usuario;
import com.gdados.projeto.util.msg.Msg;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.model.DualListModel;

@Named
@SessionScoped
public class GrupoController implements Serializable {

    private Grupo grupo;

    @Inject
    private GrupoFacade grupoFacade;
    private List<Grupo> grupos;
    private final List<Grupo> grupoSelecionado;
    private List<Grupo> grupoSelecionado1;
    private Grupo grupoUsuario;

    private List<Usuario> usuarios;
    private List<Usuario> usuarioSelecionado;

    private List<Usuario> usuarioAdicionado;
    private List<Usuario> usuarioRemovido;
    private DualListModel<Usuario> dualListModeloUsuario;

    @Inject
    private UsuarioFacade usuarioFacade;

    public GrupoController() {
        if (grupo == null) {
            limpaCampo();
        }
        usuarioAdicionado = new ArrayList<>();
        usuarioRemovido = new ArrayList<>();
        grupoSelecionado = new ArrayList<>();
        grupoSelecionado1 = new ArrayList<>();

        if (grupoSelecionado1 != null) {
            grupoSelecionado.addAll(grupoSelecionado1);
        }

//        usuarioAdicionado = usuarioFacade.getAllDisponivel();
//        usuarioRemovido = usuarioFacade.getAllDisponivel();
//        dualListModeloUsuario = new DualListModel<>(usuarioAdicionado, usuarioRemovido);
    }

    public String salvar() {
        try {
            if (grupo.getId() == null) {
                grupoFacade.save(grupo);
                limpaCampo();
                return "lista?faces-redirect=true";
            } else {
                grupoFacade.update(grupo);
                limpaCampo();
                return "lista?faces-redirect=true";
            }
        } catch (Exception e) {
            System.out.println("com.gdados.projeto.controller.GrupoController.teste()");
        }
        return null;
    }

    public String editar(Long id) {
        try {
            grupo = grupoFacade.getAllByCodigo(id);
            return "cadastro?faces-redirect=true";
        } catch (Exception e) {
        }
        return null;
    }

    public void deletar(Grupo grupo) {
        try {
            grupoFacade.delete(grupo);
            getGrupos();
        } catch (Exception e) {
        }
    }

    public void teste() {
        try {
            System.out.println("linha 01");
            for (Grupo g : grupoSelecionado) {
                System.out.println("grupo selecionado: " + g.getDescricao());
            }
            System.out.println("linha 02");
        } catch (Exception e) {
            System.out.println("linha erro");
            Msg.addMsgFatal(e.getMessage());
        }
    }

    public String lista() {
        return "/paginas/adm/grupo/lista?faces-redirect=true";
    }

    public String novo() {
        limpaCampo();
        return "/paginas/adm/grupo/cadastro?faces-redirect=true";
    }

    public String gerenciarGrupo() {
        return "/paginas/adm/grupo/gerenciar-grupo?faces-redirect=true";
    }

    private void limpaCampo() {
        grupo = new Grupo();
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public GrupoFacade getGrupoFacade() {
        return grupoFacade;
    }

    public void setGrupoFacade(GrupoFacade grupoFacade) {
        this.grupoFacade = grupoFacade;
    }

    public List<Grupo> getGrupos() {
        grupos = grupoFacade.getAll();
        return grupos;
    }

    public List<Usuario> getUsuarioAdicionado() {
        return usuarioAdicionado;
    }

    public void setUsuarioAdicionado(List<Usuario> usuarioAdicionado) {
        this.usuarioAdicionado = usuarioAdicionado;
    }

    public List<Usuario> getUsuarioRemovido() {
        return usuarioRemovido;
    }

    public void setUsuarioRemovido(List<Usuario> usuarioRemovido) {
        this.usuarioRemovido = usuarioRemovido;
    }

    public DualListModel<Usuario> getDualListModeloUsuario() {
        return dualListModeloUsuario;
    }

    public void setDualListModeloUsuario(DualListModel<Usuario> dualListModeloUsuario) {
        this.dualListModeloUsuario = dualListModeloUsuario;
    }

    public UsuarioFacade getUsuarioFacade() {
        return usuarioFacade;
    }

    public void setUsuarioFacade(UsuarioFacade usuarioFacade) {
        this.usuarioFacade = usuarioFacade;
    }

    public List<Grupo> getGrupoSelecionado() {
        return grupoSelecionado;
    }

    public List<Usuario> getUsuarioSelecionado() {
        return usuarioSelecionado;
    }

    public List<Usuario> getUsuarios() {
        usuarios = usuarioFacade.getAll();
        return usuarios;
    }

    public Grupo getGrupoUsuario() {
        return grupoUsuario;
    }

    public void setGrupoUsuario(Grupo grupoUsuario) {
        this.grupoUsuario = grupoUsuario;
    }

    public List<Grupo> getGrupoSelecionado1() {
        return grupoSelecionado1;
    }

    public void setGrupoSelecionado1(List<Grupo> grupoSelecionado1) {
        this.grupoSelecionado1 = grupoSelecionado1;
    }

    public int getContador() {
        return grupoFacade.count();
    }
}
