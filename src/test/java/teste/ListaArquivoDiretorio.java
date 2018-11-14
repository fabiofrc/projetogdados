/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teste;

import java.io.File;

/**
 *
 * @author PMBV-164029
 */
public class ListaArquivoDiretorio {

    public static void main(String[] args) {
        String diretorio = "src/main/resources/upload/arquivo/";
        File file = new File(diretorio);
        File afile[] = file.listFiles();
        int i = 0;
        for (int j = afile.length; i < j; i++) {
            File arquivos = afile[i];
            System.out.println(arquivos.getName());
        }
    }
}
