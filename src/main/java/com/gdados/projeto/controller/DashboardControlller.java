/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdados.projeto.controller;

import javax.faces.annotation.ManagedProperty;
import javax.inject.Named;

/**
 *
 * @author PMBV-164029
 */
@Named
public class DashboardControlller {

    @ManagedProperty("#{participanteController}")
    private ParticipanteController participanteController;
    @ManagedProperty("#{usuarioController}")
    private UsuarioController usuarioController;
    @ManagedProperty("#{noticiaController}")
    private NoticiaController noticiaController;

    public DashboardControlller() {
        participanteController = new ParticipanteController();
        usuarioController = new UsuarioController();
        noticiaController = new NoticiaController();
    }

    public String dashboard() {
        return "/paginas/adm/dashboard/dashboard?faces-redirect=true";
    }

    public ParticipanteController getParticipanteController() {
        return participanteController;
    }

    public void setParticipanteController(ParticipanteController participanteController) {
        this.participanteController = participanteController;
    }

    public UsuarioController getUsuarioController() {
        return usuarioController;
    }

    public void setUsuarioController(UsuarioController usuarioController) {
        this.usuarioController = usuarioController;
    }

    public NoticiaController getNoticiaController() {
        return noticiaController;
    }

    public void setNoticiaController(NoticiaController noticiaController) {
        this.noticiaController = noticiaController;
    }

}
