/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdados.projeto.controller;

import com.gdados.projeto.facade.ArquivoFacade;
import com.gdados.projeto.model.Arquivo;
import com.gdados.projeto.util.msg.Msg;
import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Usuario
 */
@Named
@SessionScoped
public class ArquivoController implements Serializable {

    @Inject
    private ArquivoFacade arquivoFacade;
    private Arquivo arquivo;
    private List<Arquivo> arquivos;
    private String imagem;

    private DefaultStreamedContent file;

    public ArquivoController() {
        if (arquivo == null) {
            arquivo = new Arquivo();
        }
        if (arquivoFacade == null) {
            arquivoFacade = new ArquivoFacade();
        }

        if (arquivos == null) {
            arquivos = new ArrayList<>();
        }
    }

    public String salvar() {
        try {
            if (arquivo.getId() == null) {
                arquivoFacade.save(arquivo);
                limpaCampo();
                Msg.addMsgInfo("Operação realizada com sucesso!");
                return "/paginas/adm/arquivo/lista?faces-redirect=true";
            } else {
                arquivoFacade.update(arquivo);
                Msg.addMsgInfo("Operação atualizada com sucesso.");
                return "/paginas/adm/arquivo/lista?faces-redirect=true";
            }
        } catch (Exception e) {
            Msg.addErrorMessage("Operação não realizada!");
        }
        return null;
    }

    public String view(Long id) {
        try {
            arquivo = arquivoFacade.getAllByCodigo(id);
            return "detalhes?faces-redirect=true";
        } catch (Exception e) {
        }
        return null;
    }

    public String viewTudo() {
        try {
            return "/paginas/adm/arquivo/arquivo?faces-redirect=true";
        } catch (Exception e) {
        }
        return null;
    }

    public String editar(Long id) {
        try {
            arquivo = arquivoFacade.getAllByCodigo(id);
            return "cadastro?faces-redirect=true";
        } catch (Exception e) {
        }
        return null;
    }

    public void deletar(Arquivo arquivo) {
        try {
            arquivoFacade.delete(arquivo);
            getArquivos();
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

        String pathFile = "/resources/upload/arquivo/" + System.currentTimeMillis() + foto;
        String caminho = scontext.getRealPath(pathFile);

        arquivo.setFoto(pathFile);
        System.out.println(caminho);
        try (FileOutputStream fout = new FileOutputStream(caminho)) {
            while (in.available() != 0) {
                fout.write(in.read());
            }
        }
        Msg.addMsgInfo("Arquivo inserido com sucesso!  " + foto);
    }

    public void fileUploadParticipanteCSV(FileUploadEvent event) throws IOException {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ServletContext scontext = (ServletContext) facesContext.getExternalContext().getContext();

        UploadedFile arq = event.getFile();
        InputStream in = new BufferedInputStream(arq.getInputstream());
        String foto = arq.getFileName();

        String file = scontext.getRealPath("/upload/arquivo/" + foto);
        try (FileOutputStream fout = new FileOutputStream(file)) {
            while (in.available() != 0) {
                fout.write(in.read());
            }
        }
        Msg.addMsgInfo("Arquivo inserido com sucesso!  " + foto);
    }

    private void limpaCampo() {
        arquivo = new Arquivo();
    }

    public String novo() {
        arquivo = new Arquivo();
        return "/paginas/adm/arquivo/cadastro?faces-redirect=true";
    }

    public String listar() {
        return "/paginas/adm/arquivo/lista?faces-redirect=true";
    }

    public ArquivoFacade getArquivoFacade() {
        return arquivoFacade;
    }

    public void setArquivoFacade(ArquivoFacade arquivoFacade) {
        this.arquivoFacade = arquivoFacade;
    }

    public Arquivo getArquivo() {
        return arquivo;
    }

    public void setArquivo(Arquivo arquivo) {
        this.arquivo = arquivo;
    }

    public List<Arquivo> getArquivos() {
        arquivos = arquivoFacade.getAll();
        return arquivos;
    }

    public void setArquivos(List<Arquivo> arquivos) {
        this.arquivos = arquivos;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

//    public DefaultStreamedContent getFile() throws FileNotFoundException {
//
//        try {
//            arquivo = dataModeloArquivoByProduto.getRowData();
//            FacesContext facesContext = FacesContext.getCurrentInstance();
//            ServletContext scontext = (ServletContext) facesContext.getExternalContext().getContext();
//            String caminho = scontext.getRealPath(arquivo.getFoto());
//            if (caminho == null || arquivo.getFoto() == null) {
//                Msg.addMsgWarn("Não foi possivel encontrar arquivo!");
//            } else {
//                FileInputStream fileInputStream = new FileInputStream(caminho);
//                file = new DefaultStreamedContent(fileInputStream, caminho, arquivo.getFoto());
//                return file;
//            }
//        } catch (FileNotFoundException e) {
//            System.err.println("arquivo: " + file.getName());
//        }
//        return file;
//    }
    public void setFile(DefaultStreamedContent file) {
        this.file = file;
    }

}
