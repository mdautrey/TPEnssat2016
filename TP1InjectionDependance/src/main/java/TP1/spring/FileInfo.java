package TP1.spring;

import TP1.core.FileInterface;

import java.io.IOException;

/**
 * Created by mdautrey on 26/09/16.
 */
public class FileInfo {
    private String fileName;
    private FileInterface fileInterface;

    public FileInfo(FileInterface fileInterface){
        this.fileInterface = fileInterface;
    }

    public void setFileName(String fileName){
        this.fileName = fileName;
    }
    public String getFileName(){ return this.fileName;}

    public int getNRows() throws IOException {
        return fileInterface.getNRows(fileName);
    }

    public String toString(){
        try {
            return String.format("Le fichier %s contient %s lignes", fileName, getNRows());
        } catch (IOException e) {
            // do nothing
        }
        return "ERREUR";
    }


}
