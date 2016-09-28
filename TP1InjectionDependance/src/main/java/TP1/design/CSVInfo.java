package TP1.design;

import TP1.core.CsvInterfaceImpl;

/**
 * Created by mdautrey on 26/09/16.
 */
public class CSVInfo extends FileInfo {

    public CSVInfo(String fileName) {
        setFileName(fileName);
        setFileInterface(new CsvInterfaceImpl());
    }
}
