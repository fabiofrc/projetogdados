package com.gdados.projeto.util.converter;

import com.gdados.projeto.facade.NoticiaFacade;
import com.gdados.projeto.model.Noticia;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = Noticia.class)
public class NoticiaConverter implements Converter {

    private final NoticiaFacade noticiaFacade = new NoticiaFacade();

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Noticia retorno = null;
        if (value != null && !value.equals("")) {
            retorno = noticiaFacade.getAllByCodigo(new Long(value));
            if (retorno == null) {
                String descricaoErro = "Notícia não existe.";
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, descricaoErro, descricaoErro);
                throw new ConverterException(message);
            }
        }
        return retorno;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) {
            Long codigo = ((Noticia) value).getId();
            return codigo == null ? "" : codigo.toString();
        }
        return null;
    }

}
