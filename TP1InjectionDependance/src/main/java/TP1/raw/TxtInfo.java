package TP1.raw;

/**
 * Created by mdautrey on 26/09/16.
 */
public class TxtInfo extends FileInfo {

    public TxtInfo(String fileName){setFileName(fileName);}

    @Override
    public int getNRows() {
        return 20;
    }
}
