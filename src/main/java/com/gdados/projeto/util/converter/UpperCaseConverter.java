/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdados.projeto.util.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author frc
 */
@FacesConverter(value = "upperCaseConverter")
public class UpperCaseConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String valor) {
        if (valor == null) {
            return null;
        } else {
            return valor.toUpperCase();
        }
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object valor) {
        if (valor == null) {
            return "";
        } else {
            return valor.toString();
        }
    }

}
