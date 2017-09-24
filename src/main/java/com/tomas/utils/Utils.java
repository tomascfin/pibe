package com.tomas.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Utils {

    public static String randomString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 8) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;
    }

    public static List<String> buscarArchivos(String idArchivo) {
        List<String> listaNombreArchivos = new ArrayList<>();
        File folder = new File("C:/fotos_pibe/");
        File[] listOfFiles = folder.listFiles();
        for (int i = 0; i < listOfFiles.length; i++) {
            String filename = listOfFiles[i].getName();
            System.out.println(filename);
            String[] parts = filename.split("_");
            String part1 = "";
            if (parts.length > 3){
                 part1 = parts[0]+"_"+parts[1]+"_"+parts[2]; // 004
            }

            System.out.println(part1);
            if (part1.equals(idArchivo+"_pibe_reclamos")) {
                listaNombreArchivos.add(filename);
            }
            for (String listaNombreArchivo : listaNombreArchivos) {
                System.out.println(listaNombreArchivo);
            }
        }
        return listaNombreArchivos;
    }

}
