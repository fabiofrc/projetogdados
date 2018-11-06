///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package teste;
//
//import com.gdados.projeto.facade.NoticiaFacade;
//import com.gdados.projeto.model.Noticia;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import sun.misc.BASE64Encoder;
//
///**
// *
// * @author PMBV-164029
// */
//public class TesteIMG {
//
//    public static String convertByteArrayToBase64String(byte[] imagem) {
//        String retorno = "";
//
//        try {
//            BASE64Encoder encoder = new BASE64Encoder();
//            retorno = encoder.encode(imagem);
//        } catch (Exception e) {
//            // TODO: handle exception
//            e.printStackTrace();
//        }
//
//        return retorno;
//    }
//
//    private static void writeBytesToFile(byte[] bFile, String fileDest) {
//        try (FileOutputStream fileOuputStream = new FileOutputStream(fileDest)) {
//            fileOuputStream.write(bFile);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    public static void main(String[] args) throws IOException {
//        String UPLOAD_FOLDER = "C:\\Users\\PMBV-164029\\Documents\\arquivo\\";
////        String imagePath = UPLOAD_FOLDER + "analise.jpg";
////        BufferedImage myPicture = ImageIO.read(new File(imagePath));
//
////        System.out.println(myPicture);
//        NoticiaFacade nf = new NoticiaFacade();
//        Noticia n = nf.getAllByCodigo(2L);
////        System.out.println(n.getArquivo().length);
////        File file = new File(Arrays.toString(n.getArquivo()));
////        long tamanho = file.length();
////        System.out.println(tamanho);
//////        System.out.println(file);
////        byte[] bFile = new byte[(int) file.length()];
////
//////       
//
//        String img = convertByteArrayToBase64String(n.getArquivo());
//        System.out.println(img.length());
//
////        File file = new File(img);
////        BufferedImage myPicture = ImageIO.read(new File(img));
////         System.out.println(myPicture.getHeight());
////        File file = new File(UPLOAD_FOLDER + "teste.JPG");
//        File file = new File(new String(n.getArquivo()));
//        byte[] bFile = new byte[(int) file.length()];
//        System.out.println(file.length());
//        //read file into bytes[]
//        FileInputStream fileInputStream = new FileInputStream(file);
//        fileInputStream.read(bFile);
//
//        writeBytesToFile(bFile, UPLOAD_FOLDER + "teste1" + ".JPG");
//
//    }
//}
