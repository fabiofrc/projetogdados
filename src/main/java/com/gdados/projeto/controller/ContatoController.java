/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdados.projeto.controller;

import com.gdados.projeto.facade.ContatoFacade;
import com.gdados.projeto.model.Contato;
import com.gdados.projeto.util.email.Mailer;
import com.gdados.projeto.util.msg.Msg;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@SessionScoped
public class ContatoController implements Serializable {

    private Contato contato;
    @Inject
    private ContatoFacade contatoFacade;
    private List<Contato> contatos;

    @Inject
    private Mailer mailer;

    private GerenciadorEmail gerenciadorEmail;

    public ContatoController() {
        if (contato == null) {
            limpaCampo();
        }
        if (gerenciadorEmail == null) {
            gerenciadorEmail = new GerenciadorEmail();
        }
    }

    public String salvar() {
        try {
            if (contato.getId() == null) {
                contatoFacade.save(contato);
                gerenciadorEmail.enviarEmail(contato);
                limpaCampo();
                return "cadastro_sucesso?faces-redirect=true";
            } else {
                contatoFacade.update(contato);
                limpaCampo();
                return "lista?faces-redirect=true";
            }
        } catch (Exception e) {
            System.err.println(e.getLocalizedMessage());
        }
        return null;
    }

    public String view(Long id) {
        try {
            contato = contatoFacade.getAllByCodigo(id);
            return "detalhes?faces-redirect=true";
        } catch (Exception e) {
            System.err.println(e.getLocalizedMessage());
        }
        return null;
    }

    public String editar(Long id) {
        try {
            contato = contatoFacade.getAllByCodigo(id);
            return "cadastro?faces-redirect=true";
        } catch (Exception e) {
            System.err.println(e.getLocalizedMessage());
        }
        return null;
    }

    public void deletar(Contato contato) {
        try {
            contatoFacade.delete(contato);
            getContatos();
        } catch (Exception e) {
            System.err.println(e.getLocalizedMessage());
        }
    }

    public void enviarEmail() {
        gerenciadorEmail.enviarEmail(contato);
//        MailMessage message = mailer.novaMensagem();
//        message.to(this.contato.getEmail())
//                .subject("Contato" + this.contato.getDescricao())
//                .bodyHtml("<strong>Interesse da proposta: </strong>" + this.contato.getDescricao())
//                .send();
        Msg.addMsgInfo("Email enviado com sucesso");
    }

    public String lista() {
        return "/paginas/adm/contato/lista?faces-redirect=true";
    }

    public String novo() {
        limpaCampo();
        return "/paginas/plb/contato/cadastro?faces-redirect=true";
    }

    private void limpaCampo() {
        contato = new Contato();
    }

    public int getContador() {
        return contatoFacade.count();
    }

    public Contato getContato() {
        return contato;
    }

    public void setContato(Contato contato) {
        this.contato = contato;
    }

    public ContatoFacade getContatoFacade() {
        return contatoFacade;
    }

    public void setContatoFacade(ContatoFacade contatoFacade) {
        this.contatoFacade = contatoFacade;
    }

    public List<Contato> getContatos() {
        contatos = contatoFacade.getAll();
        return contatos;
    }

    public GerenciadorEmail getGerenciadorEmail() {
        return gerenciadorEmail;
    }

    public void setGerenciadorEmail(GerenciadorEmail gerenciadorEmail) {
        this.gerenciadorEmail = gerenciadorEmail;
    }

}
