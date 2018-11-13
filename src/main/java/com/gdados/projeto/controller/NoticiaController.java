/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdados.projeto.controller;

import com.gdados.projeto.facade.NoticiaFacade;
import com.gdados.projeto.model.Noticia;
import com.gdados.projeto.model.SubCategoria;
import com.gdados.projeto.util.filter.NoticiaFilter;
import java.io.ByteArrayInputStream;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;

import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

@Named
@ApplicationScoped
public class NoticiaController implements Serializable {

    private Noticia noticia = new Noticia();
    @Inject
    private NoticiaFacade noticiaFacade;
    private List<Noticia> noticias;

    private List<Noticia> noticiasDsiponivel;
    private List<Noticia> noticiasDestaque;

    @Inject
    private NoticiaFilter noticiaFilter;
    @Inject
    private SubCategoria categoria;

    private String file;
    private byte[] arquivo;
    private String paramentroCatagoria;
    private String paramentroTitulo;


    public void inicializar() {
        System.out.println("iniciando.....");
        if (noticia == null) {
            limpaCampo();
        }
    }

    @PostConstruct
    public void init() {
        carregaFilter();
        if (noticia == null) {
            limpaCampo();
        }
    }

    public String salvar() {
        try {
            if (noticia.getId() == null) {
                noticiaFacade.save(noticia);
                limpaCampo();
                return "lista?faces-redirect=true";
            } else {
                noticiaFacade.update(noticia);
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
            noticia = noticiaFacade.getAllByCodigo(id);
            noticia.getSubCategoria();
            return "/paginas/plb/noticia/detalhes?faces-redirect=true";
        } catch (Exception e) {
        }
        return null;
    }

    public String editar(Long id) {
        try {
            noticia = noticiaFacade.getAllByCodigo(id);
            return "cadastro?faces-redirect=true";
        } catch (Exception e) {
        }
        return null;
    }

    public void deletar(Noticia noticia) {
        try {
            noticiaFacade.delete(noticia);
            getNoticias();
        } catch (Exception e) {
        }
    }

    private List<Noticia> carregaFilter() {
        try {
            noticiasDestaque = new ArrayList<>();
            noticiasDestaque = noticiaFacade.getAllDestaque();

            if (noticiaFilter.getTitulo() == null && noticiaFilter.getCategoria() == null) {
                noticiasDsiponivel = noticiaFacade.getAll();
            } else {
                noticiasDsiponivel = noticiaFacade.buscaNoticiaByFiltro1(noticiaFilter);
            }
        } catch (Exception e) {
            System.out.println("erro: " + e);
        }
        return noticiasDsiponivel;
    }

    public String buscaNoticiaByFilter() {
        try {
            noticiaFilter.setTitulo(paramentroTitulo);
            noticiaFilter.setCategoria("");
//            System.out.println("linha 05");

            if (noticiaFilter.getTitulo() != null || noticiaFilter.getCategoria() != null) {
                noticiasDsiponivel = noticiaFacade.buscaNoticiaByFiltro1(noticiaFilter);
                limpaFilter();
                getNoticiasDsiponivel();
                System.err.println("linha 01");
                if (noticiasDsiponivel.isEmpty()) {
                    System.err.println("linha 02");
                    carregaFilter();
                    return "/paginas/plb/noticia/noticia?faces-redirect=true";
                } else {
                    System.err.println("linha 03");
                    getNoticiasDsiponivel();
                    return "/paginas/plb/noticia/noticia?faces-redirect=true";
                }
            } else {
                System.err.println("linha 04");
                noticiasDsiponivel = noticiaFacade.getAll();
                getNoticiasDsiponivel();
                limpaFilter();
                return "/paginas/plb/noticia/noticia?faces-redirect=true";
            }

        } catch (Exception e) {
            System.out.println("erro: " + e);
        }
        return null;
    }

    public void limpaFilter() {
        noticiaFilter = new NoticiaFilter();
        categoria = new SubCategoria();
        setParamentroTitulo(null);
        setParamentroCatagoria(null);
    }

    public List<String> pesquisarPorTitulo(String titulo) {
        return this.noticiaFacade.nomeQueContem(titulo);
    }

    public void handleFileUpload(FileUploadEvent event) {
        byte[] content = event.getFile().getContents();
        System.out.println(content.length);
        noticia.setArquivo(content);
    }

    public String uploadListener(FileUploadEvent evento) {
        UploadedFile file1 = evento.getFile();
        this.noticia.setArquivo(file1.getContents());
        return null;
    }

   
    public StreamedContent getImage() {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
                return new DefaultStreamedContent();
            } else {
                // Map abaixo sempre vazio
                Map<String, String> parameterMap = (Map<String, String>) context.getExternalContext().getRequestParameterMap();
                String anexoID = parameterMap.get("id");
                if (anexoID == null) {
                    return new DefaultStreamedContent();
                }
                noticia = noticiaFacade.getAllByCodigo(Long.valueOf(anexoID));
                StreamedContent retorno = new DefaultStreamedContent(new ByteArrayInputStream(this.noticia.getArquivo()));
                return retorno;
            }
        } catch (NumberFormatException e) {
            System.out.println(e.getLocalizedMessage());
        }
        return null;
    }


    public void addMessageDisponivel() {
        String summary = noticia.isStatus() ? "Disponivel" : "Não disponivel";
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(summary));
    }

    public void addMessageDestaque() {
        String summary = noticia.isDestaque() ? "Em destaque" : "Sem destaque";
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(summary));
    }

    public String lista() {
        return "/paginas/adm/noticia/lista?faces-redirect=true";
    }

    public String novo() {
        limpaCampo();
        return "/paginas/adm/noticia/cadastro?faces-redirect=true";
    }

    public String visualisarNoticias() {
        try {
            noticiasDestaque = noticiaFacade.getAllDestaque();
            noticiasDsiponivel = noticiaFacade.getAll();
            return "/paginas/plb/noticia/noticia?faces-redirect=true";
        } catch (Exception e) {
            System.out.println("erro" + e.getLocalizedMessage());
        }
        return null;
    }

    private void limpaCampo() {
        noticia = new Noticia();
    }

    public Noticia getNoticia() {
        return noticia;
    }

    public void setNoticia(Noticia noticia) {
        this.noticia = noticia;
    }

    public NoticiaFacade getNoticiaFacade() {
        return noticiaFacade;
    }

    public void setNoticiaFacade(NoticiaFacade noticiaFacade) {
        this.noticiaFacade = noticiaFacade;
    }

    public List<Noticia> getNoticias() {
        noticias = noticiaFacade.getAll();
        return noticias;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public byte[] getArquivo() {
        return arquivo;
    }

    public void setArquivo(byte[] arquivo) {
        this.arquivo = arquivo;
    }

    public int getContador() {
        return noticiaFacade.count();
    }

    public boolean isEditando() {
        return this.noticia.getId() != null;
    }

    public List<Noticia> getNoticiasDsiponivel() {
        return noticiasDsiponivel;
    }

    public NoticiaFilter getNoticiaFilter() {
        return noticiaFilter;
    }

    public void setNoticiaFilter(NoticiaFilter noticiaFilter) {
        this.noticiaFilter = noticiaFilter;
    }

    public String getParamentroCatagoria() {
        return paramentroCatagoria;
    }

    public void setParamentroCatagoria(String paramentroCatagoria) {
        this.paramentroCatagoria = paramentroCatagoria;
    }

    public SubCategoria getCategoria() {
        return categoria;
    }

    public void setCategoria(SubCategoria categoria) {
        this.categoria = categoria;
    }

    public String getParamentroTitulo() {
        return paramentroTitulo;
    }

    public void setParamentroTitulo(String paramentroTitulo) {
        this.paramentroTitulo = paramentroTitulo;
    }

    public List<Noticia> getNoticiasDestaque() {
        return noticiasDestaque;
    }

}
