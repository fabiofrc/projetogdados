/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdados.projeto.controller;

import com.gdados.projeto.facade.ProjetoFacade;
import com.gdados.projeto.model.Projeto;
import com.gdados.projeto.util.msg.Msg;
import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

@Named
@SessionScoped
public class ProjetoController implements Serializable {

    private Projeto projeto = new Projeto();
    @Inject
    private ProjetoFacade projetoFacade;
    private List<Projeto> projetos;

    public ProjetoController() {
        if (projeto == null) {
            limpaCampo();
        }
    }
    
    public void inicializar() {
        System.out.println("iniciando.....");
        if (projeto == null) {
            limpaCampo();
        }
    }

    public String salvar() {
        try {
            if (projeto.getId() == null) {
                projetoFacade.save(projeto);
                limpaCampo();
                return "lista?faces-redirect=true";
            } else {
                projetoFacade.update(projeto);
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
            projeto = projetoFacade.getAllByCodigo(id);
            return "detalhes?faces-redirect=true";
        } catch (Exception e) {
        }
        return null;
    }

    public String editar(Long id) {
        try {
            projeto = projetoFacade.getAllByCodigo(id);
            return "cadastro?faces-redirect=true";
        } catch (Exception e) {
        }
        return null;
    }

    public void deletar(Projeto projeto) {
        try {
            projetoFacade.delete(projeto);
            getProjetos();
        } catch (Exception e) {
        }
    }
    
     public void fileUpload(FileUploadEvent event) throws IOException {
//      String foto = getNumeroRandomico() + ".png";

        FacesContext facesContext = FacesContext.getCurrentInstance();
        ServletContext scontext = (ServletContext) facesContext.getExternalContext().getContext();
        UploadedFile arq = event.getFile();
        InputStream in = new BufferedInputStream(arq.getInputstream());
        String foto = arq.getFileName();

        String pathFile = "/resources/upload/projeto/" + System.currentTimeMillis() + foto;
        String caminho = scontext.getRealPath(pathFile);

        projeto.setArquivo(pathFile);
        System.out.println(caminho);
        try (FileOutputStream fout = new FileOutputStream(caminho)) {
            while (in.available() != 0) {
                fout.write(in.read());
            }
        }
        Msg.addMsgInfo("Arquivo inserido com sucesso!  " + foto);
    }

    public void addMessageDisponivel() {
        String summary = projeto.isStatus() ? "Disponivel" : "Não disponivel";
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(summary));
    }

    public void addMessageDestaque() {
        String summary = projeto.isDestaque() ? "Em destaque" : "Sem destaque";
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(summary));
    }

    public String lista() {
        return "/paginas/adm/projeto/lista?faces-redirect=true";
    }

    public String novo() {
        limpaCampo();
        return "/paginas/adm/projeto/cadastro?faces-redirect=true";
    }
    
    public String visualisarProjetos() {
        limpaCampo();
        return "/paginas/plb/projeto/projeto?faces-redirect=true";
    }

    private void limpaCampo() {
        projeto = new Projeto();
    }

    public Projeto getProjeto() {
        return projeto;
    }

    public void setProjeto(Projeto projeto) {
        this.projeto = projeto;
    }

    public ProjetoFacade getProjetoFacade() {
        return projetoFacade;
    }

    public void setProjetoFacade(ProjetoFacade projetoFacade) {
        this.projetoFacade = projetoFacade;
    }

    public List<Projeto> getProjetos() {
        projetos = projetoFacade.getAll();
        return projetos;
    }

    public int getContador() {
        return projetoFacade.count();
    }
}
