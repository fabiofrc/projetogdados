package com.gdados.projeto.util.converter;

import com.gdados.projeto.controller.GrupoController;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author frc
 */
@FacesConverter(value = "pickListConverter")
public class PickListConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if (value != null && value.trim().length() > 0) {
            try {
                GrupoController grupoController = (GrupoController) fc.getExternalContext().getApplicationMap().get("grupoController");
                return grupoController.getUsuarioAdicionado().get(Integer.parseInt(value));
            } catch (NumberFormatException e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid theme."));
            }
        } else {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object object) {
//        if (object != null) {
//            return String.valueOf(((Theme) object).getId());
//        } else {
//            return null;
//        }
        return null;
    }
}
