package TP1.design;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by mdautrey on 27/09/16.
 */
public class App {
    public static void main(String args[]){
        // http://alvinalexander.com/java/edu/pj/pj010005
        Scanner scanner = new Scanner(System.in);
        System.out.println("Chemin vers un fichier CSV ?");
        String csvFileName = scanner.next();
        System.out.println("Chemin vers un fichier txt ?");
        String txtFileName = scanner.next();
        ArrayList<FileInfo> fileInfoArrayList = new ArrayList<FileInfo>();
        fileInfoArrayList.add(new CSVInfo(csvFileName));
        fileInfoArrayList.add(new TxtInfo(txtFileName));
        for(FileInfo f : fileInfoArrayList){
            System.out.println(f.toString());
        }
    }
}
