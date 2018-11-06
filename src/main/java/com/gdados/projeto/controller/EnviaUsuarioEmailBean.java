/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdados.projeto.controller;

import com.gdados.projeto.util.email.Mailer;
import com.gdados.projeto.util.msg.Msg;
import com.outjected.email.api.MailMessage;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author frc
 */
@Named
@RequestScoped
public class EnviaUsuarioEmailBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private Mailer mailer;

    public EnviaUsuarioEmailBean() {
    }
    
   

    public void enviarPedido() {
        MailMessage message = mailer.novaMensagem();

        message.to("programadetalentospmbv@gmail.com");
        message.subject("Teste de envio de email ");
        message.bodyHtml("<strong>Projeto Gdados: transformando dados em soluções</strong> ");
        message.send();

        Msg.addInfoMessage("teste enviado por e-mail com sucesso!");
    }
    
    public String novoEmail() {
        return "/paginas/adm/usuario/usuario-email?faces-redirect=true";
    }

    public Mailer getMailer() {
        return mailer;
    }

    public void setMailer(Mailer mailer) {
        this.mailer = mailer;
    }
    
    
    
}
