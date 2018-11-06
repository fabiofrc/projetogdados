///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package teste;
//
//import com.gdados.projeto.facade.UsuarioFacade;
//import com.gdados.projeto.model.Usuario;
//import javax.inject.Inject;
//import org.junit.Test;
//
///**
// *
// * @author PMBV-164029
// */
//public class AdicionaUsuario {
//
//    @Inject
//    private Usuario usuario;
//    @Inject
//    private UsuarioFacade usuarioFacade;
//
//    @Test
//    public void adiciona() {
//        usuario = new Usuario();
//        usuario.setEmail("tads1");
//        usuario.setSenha("frc");
//        usuarioFacade = new UsuarioFacade();
//        usuarioFacade.save(usuario);
//        System.out.println("deu certo");
//    }
//
//    public Usuario getUsuario() {
//        return usuario;
//    }
//
//    public void setUsuario(Usuario usuario) {
//        this.usuario = usuario;
//    }
//
//    public UsuarioFacade getUsuarioFacade() {
//        return usuarioFacade;
//    }
//
//    public void setUsuarioFacade(UsuarioFacade usuarioFacade) {
//        this.usuarioFacade = usuarioFacade;
//    }
//
//}
