/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdados.projeto.controller;

import com.gdados.projeto.facade.ComentarioFacade;
import com.gdados.projeto.facade.GrupoFacade;
import com.gdados.projeto.facade.ParticipanteFacade;
import com.gdados.projeto.facade.UsuarioFacade;
import com.gdados.projeto.model.Comentario;
import com.gdados.projeto.model.Participante;
import com.gdados.projeto.model.TipoPessoa;
import com.gdados.projeto.model.Usuario;
import com.gdados.projeto.security.MyPasswordEncoder;
import com.gdados.projeto.security.UsuarioLogado;
import com.gdados.projeto.security.UsuarioSistema;
import com.gdados.projeto.util.msg.Msg;
import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Named
@SessionScoped
public class ParticipanteController implements Serializable {

    private static final long serialVersionUID = 1L;

    private Participante participante;
    @Inject
    private ParticipanteFacade participanteFacade;
    private List<Participante> participantes;

    @Inject
    private GrupoFacade grupoFacade;

    @Inject
    private UsuarioFacade usuarioFacade;

    private UsuarioSistema usuario;

    private String confirmaSenha;

    @Inject
    private ComentarioFacade comentarioFacade;
    private List<Comentario> comentarioByParticpantes;

    private double tamanhoArquivo;

    public ParticipanteController() {
        if (participante == null) {
            limpaCampo();
        }
    }

    public String salvar() {
        try {
//            participante.getUsuario().setSenha(MyPasswordEncoder.getPasswordEncoder(participante.getUsuario().getSenha()));
//            setConfirmaSenha(MyPasswordEncoder.getPasswordEncoder(confirmaSenha));
            if (verificarUsuarioExistente() && participante.getId() == null) {
                Msg.addMsgWarn("Já existe um usuário com o e-mail informado.");
            } else {
                if (participante.getId() == null) {
                    participante.setTipo(TipoPessoa.FISICA);
                    participante.getUsuario().getGrupos().clear();
                    participante.getUsuario().getGrupos().add(0, grupoFacade.getAllByCodigo(2L));
                    participante.getUsuario().setSenha(MyPasswordEncoder.getPasswordEncoder(participante.getUsuario().getSenha()));
                    participanteFacade.save(participante);
                    limpaCampo();
                    Msg.addMsgInfo("Operação realizada com sucesso!");
                    return "cadastro_sucesso?faces-redirect=true";
                } else {
                    participante.setTipo(TipoPessoa.FISICA);
                    participante.getUsuario().getGrupos().clear();
                    participante.getUsuario().getGrupos().add(0, grupoFacade.getAllByCodigo(2L));
                    participanteFacade.update(participante);
                    limpaCampo();
                    Msg.addMsgInfo("Operação atualizada com sucesso!");
                    return "cadastro_sucesso?faces-redirect=true";
                }
            }
        } catch (Exception e) {
            Msg.addMsgError("Operação não realizada! " + e.getMessage());
        }
        return null;
    }

    public String atualizarPerfil() {
        try {
            if (verificarUsuarioExistente() && participante.getId() == null) {
                Msg.addMsgWarn("Já existe um usuário com o e-mail informado.");
            } else {
                if (participante.getId() == null) {
                    participante.setTipo(TipoPessoa.FISICA);
                    participante.getUsuario().getGrupos().clear();
                    participante.getUsuario().getGrupos().add(0, grupoFacade.getAllByCodigo(2L));
                    participante.getUsuario().setSenha(MyPasswordEncoder.getPasswordEncoder(participante.getUsuario().getSenha()));
                    participanteFacade.save(participante);
                    limpaCampo();
                    Msg.addMsgInfo("Operação realizada com sucesso!");
                    return "cadastro_perfil?faces-redirect=true";
                } else {
                    participante.setTipo(TipoPessoa.FISICA);
                    participante.getUsuario().getGrupos().clear();
                    participante.getUsuario().getGrupos().add(0, grupoFacade.getAllByCodigo(2L));
                    participanteFacade.update(participante);
                    Msg.addMsgInfo("Operação atualizada com sucesso!");
                    return "cadastro_perfil?faces-redirect=true";
                }
            }
        } catch (Exception e) {
            Msg.addMsgError("Operação não realizada! " + e.getMessage());
        }
        return null;
    }

    public String salvarNovaSenha() {
        try {
            if (participante.getId() != null) {
                participante.getUsuario().setSenha(MyPasswordEncoder.getPasswordEncoder(participante.getUsuario().getSenha()));
                participanteFacade.update(participante);
                limpaCampo();
                Msg.addMsgInfo("Operação realizada com sucesso!");
                return "cadastro_perfil?faces-redirect=true";
            }
        } catch (Exception e) {
            Msg.addMsgError("Operação não realizada! " + e.getMessage());
        }
        return null;
    }

    public Participante guardar(Participante p) {
        if (p.isNovo()) {
            p.setTipo(TipoPessoa.FISICA);
            p.getUsuario().getGrupos().clear();
            p.getUsuario().getGrupos().add(0, grupoFacade.getAllByCodigo(3L));
        }
        participante.getUsuario().setSenha(MyPasswordEncoder.getPasswordEncoder(participante.getUsuario().getSenha()));
        return participante;
    }

    public String editar(Long id) {
        try {
            participante = participanteFacade.getAllByCodigo(id);
            return "cadastro?faces-redirect=true";
        } catch (Exception e) {
        }
        return null;
    }

    public String editarPerfil() {
        try {
            usuario = getUsuarioLogado();
            participante = participanteFacade.buscaParticipanteByIdUsuario(usuario.getUsuario().getId());
            System.out.println("Usuario: " + usuario.getUsuario().getId());
            return "/paginas/pf/participante/cadastro?faces-redirect=true";
        } catch (Exception e) {
            System.out.println("erro: " + e.getLocalizedMessage());
        }
        return null;
    }

    public void deletar(Participante participante) {
        try {
            participanteFacade.delete(participante);
            getParticipantes();
        } catch (Exception e) {
        }
    }

    public boolean verificarUsuarioExistente() {
        Usuario usuarioExistente = usuarioFacade.verificaUsuario(participante.getUsuario().getEmail());
        return usuarioExistente != null && usuarioExistente.getEmail().equalsIgnoreCase(participante.getUsuario().getEmail());
    }

    public void fileUpload(FileUploadEvent event) throws IOException {
//      String foto = getNumeroRandomico() + ".png";

        FacesContext facesContext = FacesContext.getCurrentInstance();
        ServletContext scontext = (ServletContext) facesContext.getExternalContext().getContext();
        UploadedFile arq = event.getFile();
        InputStream in = new BufferedInputStream(arq.getInputstream());
        String foto = arq.getFileName();

        String pathFile = "/resources/upload/participante/" + System.currentTimeMillis() + foto;
        String caminho = scontext.getRealPath(pathFile);

        participante.setArquivo(pathFile);
        System.out.println(caminho);
        try (FileOutputStream fout = new FileOutputStream(caminho)) {
            while (in.available() != 0) {
                fout.write(in.read());
            }
        }
        Msg.addMsgInfo("Arquivo inserido com sucesso!  " + foto);
    }

    @Produces
    @UsuarioLogado
    public UsuarioSistema getUsuarioLogado() {
        usuario = null;
        UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();

        if (auth != null && auth.getPrincipal() != null) {
            usuario = (UsuarioSistema) auth.getPrincipal();
        }
        return usuario;
    }

    public void existe() {
        try {
            if (verificarUsuarioExistente()) {
                Msg.addMsgWarn("já exite um registro com esse email");
            }
        } catch (Exception e) {
            Msg.addMsgError("Erro: " + e.getLocalizedMessage());
        }
    }

    public String lista() {
        return "/paginas/adm/participante/lista?faces-redirect=true";
    }

    public String novo() {
        limpaCampo();
        return "/paginas/plb/participante/cadastro?faces-redirect=true";
    }

    public String novaSenha() {
        participante.getUsuario().setSenha(null);
        return "/paginas/pf/participante/cadastro_senha?faces-redirect=true";
    }

    public String visualisarComentario() {
        return "/paginas/pf/participante/meus_comentarios?faces-redirect=true";
    }

    public boolean isEditando() {
        return this.participante.getId() != null;
    }

    public void limpaCampoParticipante() {
        participante = new Participante();
    }

    private void limpaCampo() {
        participante = new Participante();
    }

    public void limpaCampoNovo() {
        participante = new Participante();
    }

    public Participante getParticipante() {
        return participante;
    }

    public void setParticipante(Participante participante) {
        this.participante = participante;
    }

    public ParticipanteFacade getParticipanteFacade() {
        return participanteFacade;
    }

    public void setParticipanteFacade(ParticipanteFacade participanteFacade) {
        this.participanteFacade = participanteFacade;
    }

    public List<Participante> getParticipantes() {
        participantes = participanteFacade.getAll();
        return participantes;
    }

    public UsuarioFacade getUsuarioFacade() {
        return usuarioFacade;
    }

    public void setUsuarioFacade(UsuarioFacade usuarioFacade) {
        this.usuarioFacade = usuarioFacade;
    }

    public GrupoFacade getGrupoFacade() {
        return grupoFacade;
    }

    public void setGrupoFacade(GrupoFacade grupoFacade) {
        this.grupoFacade = grupoFacade;
    }

    public int getContador() {
        return participanteFacade.count();
    }

    public UsuarioSistema getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioSistema usuario) {
        this.usuario = usuario;
    }

    public String getConfirmaSenha() {
        return confirmaSenha;
    }

    public void setConfirmaSenha(String confirmaSenha) {
        this.confirmaSenha = confirmaSenha;
    }

    public List<Comentario> comentarioByParticpantes() {
        return comentarioByParticpantes;
    }

    public List<Comentario> buscaComentarioByParticipante(Long id) {
        comentarioByParticpantes = comentarioFacade.listaComentarioByParticipante(id);
        return comentarioByParticpantes;
    }

    public double getTamanhoArquivo() {
        return tamanhoArquivo;
    }

    public void setTamanhoArquivo(double tamanhoArquivo) {
        this.tamanhoArquivo = tamanhoArquivo;
    }

}
