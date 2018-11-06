/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teste;

import com.gdados.projeto.facade.ParticipanteFacade;
import com.gdados.projeto.model.Participante;
import java.util.List;

/**
 *
 * @author PMBV-164029
 */
public class TesteParticipante {

    public static void main(String[] args) {
        ParticipanteFacade pf = new ParticipanteFacade();
        List<Participante> lista = pf.getAll();
        for (Participante participante : lista) {
            System.out.println(participante.getId());
        }
    }
}
