package TP1.core;

import java.io.IOException;

/**
 * Created by mdautrey on 27/09/16.
 */
public class TxtInterfaceImpl implements FileInterface {
    public int getNRows(String fileName) throws IOException {
        return FileHelper.countLines(fileName);
    }
}
