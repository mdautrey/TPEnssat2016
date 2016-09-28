package TP1.raw;

import TP1.raw.TxtInfo;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/** 
* TxtInfo Tester. 
* 
* @author <Authors name> 
* @since <pre>Sep 26, 2016</pre> 
* @version 1.0 
*/ 
public class TestTxtInfo {
    TxtInfo txtInfo;

    @Before
public void before() throws Exception {
    txtInfo = new TxtInfo();
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
    Assert.assertEquals(20, txtInfo.getNRows());
}


} 
