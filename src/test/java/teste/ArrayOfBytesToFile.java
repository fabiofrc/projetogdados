///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package teste;
//
//import com.gdados.projeto.facade.NoticiaFacade;
//import com.gdados.projeto.model.Noticia;
//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import javax.imageio.ImageIO;
//
///**
// *
// * @author PMBV-164029
// */
//public class ArrayOfBytesToFile {
//
//    private static final String UPLOAD_FOLDER = "C:\\Users\\PMBV-164029\\Documents\\arquivo\\";
//
//    public static void main(String[] args) throws IOException {
//        
//
//        FileInputStream fileInputStream = null;
//
//        try {
//            NoticiaFacade nf = new NoticiaFacade();
//            Noticia n = nf.getAllByCodigo(1L);
//
////            File file = new File(new String(n.getArquivo()));
//            File file = new File("C:\\Users\\PMBV-164029\\Documents\\arquivo\\img.JPG");
//            byte[] bFile = new byte[(int) file.length()];
//
//            //read file into bytes[]
//            fileInputStream = new FileInputStream(file);
//            fileInputStream.read(bFile);
//
//            //save bytes[] into a file
//            writeBytesToFile(bFile, UPLOAD_FOLDER + "img01.png");
//            writeBytesToFileClassic(bFile, UPLOAD_FOLDER + "img01.jpg");
//            writeBytesToFileNio(bFile, UPLOAD_FOLDER + "img01.jpeg");
//
//            System.out.println("Done");
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (fileInputStream != null) {
//                try {
//                    fileInputStream.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//
//        }
//    }
//
//    //Classic, < JDK7
//    private static void writeBytesToFileClassic(byte[] bFile, String fileDest) {
//        FileOutputStream fileOuputStream = null;
//        try {
//            fileOuputStream = new FileOutputStream(fileDest);
//            fileOuputStream.write(bFile);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (fileOuputStream != null) {
//                try {
//                    fileOuputStream.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
//
//    //Since JDK 7 - try resources
//    private static void writeBytesToFile(byte[] bFile, String fileDest) {
//        try (FileOutputStream fileOuputStream = new FileOutputStream(fileDest)) {
//            fileOuputStream.write(bFile);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    //Since JDK 7, NIO
//    private static void writeBytesToFileNio(byte[] bFile, String fileDest) {
//        try {
//            Path path = Paths.get(fileDest);
//            Files.write(path, bFile);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }
//}
