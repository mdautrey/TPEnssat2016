package TP1.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import java.io.IOException;

/**
 * Created by mdautrey on 27/09/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@TestExecutionListeners(listeners = {DependencyInjectionTestExecutionListener.class})
public class TestCSVInfo {


    @Test
    public void csvInfoShouldWork() throws IOException {


    }

}
