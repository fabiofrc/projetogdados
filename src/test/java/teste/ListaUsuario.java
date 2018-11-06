/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teste;

import com.gdados.projeto.facade.UsuarioFacade;
import com.gdados.projeto.model.Usuario;

/**
 *
 * @author PMBV-164029
 */
public class ListaUsuario {

    public static boolean verificarUsuarioExistente() {
        UsuarioFacade usuarioFacade = new UsuarioFacade();
        Usuario usuarioExistente = usuarioFacade.verificaUsuario("admin@admin");
        return usuarioExistente != null;
    }

    public static void main(String[] args) {

//         List<Usuario> lista = usuarioFacade.getAll();
//        lista.forEach((usuario) -> {
//            System.out.println("Lista:" + usuario.getEmail());
//        });
//        UsuarioFacade usuarioFacade = new UsuarioFacade();
//        Usuario resultado = usuarioFacade.verificaUsuario("fabio@gmail.com");
        if (verificarUsuarioExistente()) {
            System.out.println("linha 01");
        } else {
            System.out.println("linha 02");
        }
    }
}
