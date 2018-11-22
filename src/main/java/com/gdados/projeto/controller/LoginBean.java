package com.gdados.projeto.controller;

import com.gdados.projeto.util.msg.Msg;
import java.io.IOException;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.security.core.context.SecurityContextHolder;

@Named
@SessionScoped
public class LoginBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private FacesContext facesContext;

    @Inject
    private HttpServletRequest request;

    @Inject
    private HttpServletResponse response;

    private String email;

    public void preRender() {
        if ("true".equals(request.getParameter("invalid"))) {
            Msg.addErrorMessage("Usuário ou senha inválido!");
        }
    }

    public void login() throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/login.xhtml?faces-redirect=true");
        dispatcher.forward(request, response);
        facesContext.responseComplete();
    }

    public String logout() throws ServletException, IOException {
        SecurityContextHolder.clearContext();
        return "/Home.xhtml?faces-redirect=true";
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
