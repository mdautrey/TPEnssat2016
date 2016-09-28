package TP1.core;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class FileHelper {
    public static int countLines(String fileName) throws IOException {
        //http://stackoverflow.com/questions/1277880/how-can-i-get-the-count-of-line-in-a-file-in-an-efficient-way
        FileReader fileReader = new FileReader(fileName);
        int lines  = countLines(fileReader);
        fileReader.close();
        return lines;
    }
    public static int countLines(Reader reader) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(reader);
        int lines  = 0;
        try {
            while (bufferedReader.readLine() != null) lines++;
        }
        catch (IOException e){
            lines = -1;
        }
        reader.close();
        return lines;
    }
}
