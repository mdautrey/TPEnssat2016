package TP1.raw;

import TP1.raw.CSVInfo;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/** 
* CSVInfo Tester. 
* 
* @author <Authors name> 
* @since <pre>Sep 26, 2016</pre> 
* @version 1.0 
*/ 
public class TestCSVInfo {
    CSVInfo csvInfo = new CSVInfo();
@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: getNRows() 
* 
*/ 
@Test
public void testGetNRows() throws Exception {
    Assert.assertEquals(10, csvInfo.getNRows());
}


} 
