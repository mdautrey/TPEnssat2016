package TP1.raw;

/**
 * Created by mdautrey on 26/09/16.
 */
public abstract class FileInfo {
    private String fileName;

    public String getFileName(){
        return this.fileName;
    }
    public void setFileName(String fileName){
        this.fileName = fileName;
    }
    public abstract int getNRows();
    public String toString(){
        return String.format("Le fichier %s contient %s lignes", getFileName(), getNRows());
    }

}
