package TP1.design;

import TP1.core.FileInterface;

import java.io.IOException;

/**
 * Created by mdautrey on 26/09/16.
 */
public abstract class FileInfo {
    private FileInterface fileInterface;
    private String fileName;

    public void setFileInterface(FileInterface fileInterface){
        this.fileInterface = fileInterface;
    }

    public void setFileName(String fileName){
        this.fileName = fileName;
    }

    public String getFileName(){
        return this.fileName;
    }

    int getNRows() {
        try {
            return fileInterface.getNRows(fileName);
        } catch(IOException e){
            // do nothing
        }
        return -1;
    }
    public String toString(){
        return String.format("Le fichier %s contient %s ligne", 
                             fileName, getNRows());
    }
}
