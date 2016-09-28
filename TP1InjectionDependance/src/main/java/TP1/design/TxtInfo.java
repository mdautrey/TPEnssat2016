package TP1.design;

import TP1.core.TxtInterfaceImpl;

/**
 * Created by mdautrey on 26/09/16.
 */
public class TxtInfo extends FileInfo {
    public TxtInfo(String fileName){
        setFileName(fileName);
        setFileInterface(new TxtInterfaceImpl());
    }
}
