package TP1.core;

import TP1.core.FileHelper;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

/**
 * Created by mdautrey on 27/09/16.
 */
public class TestFileHelper {
    @Test
    public void testCountLines() throws IOException {
        Reader reader = new StringReader("line1\nline2\nline3\nline4\nline5");
        Assert.assertEquals(5, FileHelper.countLines(reader));
    }
}
