package com.gdados.projeto.security;

import com.gdados.projeto.cdi.CDIServiceLocator;
import com.gdados.projeto.facade.UsuarioFacade;
import com.gdados.projeto.model.Grupo;
import com.gdados.projeto.model.Usuario;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class AppUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UsuarioFacade usuarios = CDIServiceLocator.getBean(UsuarioFacade.class);
        Usuario usuario = usuarios.getAllByEmail(email);

        UsuarioSistema user = null;

        if (usuario != null) {
            user = new UsuarioSistema(usuario, getGrupos(usuario));
        } else {
            throw new UsernameNotFoundException("Usuário não encontrado.");
        }

        return user;
    }

    private Collection<? extends GrantedAuthority> getGrupos(Usuario usuario) {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        for (Grupo grupo : usuario.getGrupos()) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + grupo.getNome().toUpperCase()));
        }

        return authorities;
    }

}
