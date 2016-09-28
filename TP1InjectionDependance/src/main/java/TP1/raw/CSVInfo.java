package TP1.raw;

/**
 * Created by mdautrey on 26/09/16.
 */
public class CSVInfo extends FileInfo {
    
    public CSVInfo(String fileName){
        setFileName(fileName);
    }
   
    @Override
    public int getNRows() {
        int nRows;
        try{
            nRows = countLines(getFileName());
        }
        catch(IOException e){
            nRows = -1;
        }
        return nRows;
    }
}
