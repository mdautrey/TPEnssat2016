package TP1.spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by mdautrey on 27/09/16.
 */
public class App {
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("META-INF/spring-config.xml");
        FileInfo csvFile = (FileInfo) context.getBean("csvFile");
        FileInfo txtFile = (FileInfo) context.getBean("txtFile");

        Scanner scanner = new Scanner(System.in);
        System.out.println("Chemin vers un fichier CSV ?");
        String csvFileName = scanner.next();
        csvFile.setFileName(csvFileName);
        System.out.println("Chemin vers un fichier txt ?");
        txtFile.setFileName(txtFileName);
        ArrayList<FileInfo> fileInfoArrayList = new ArrayList<FileInfo>();
        fileInfoArrayList.add(csvFile);
        fileInfoArrayList.add(txtFile);
        for(FileInfo f : fileInfoArrayList) {
            System.out.println(f.toString());
        }
    }
}
