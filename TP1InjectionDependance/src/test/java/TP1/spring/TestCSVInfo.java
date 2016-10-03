package TP1.spring;

import TP1.core.CsvInterfaceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import java.io.IOException;

/**
 * Created by mdautrey on 27/09/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader=AnnotationConfigContextLoader.class)
@TestExecutionListeners(listeners = {DependencyInjectionTestExecutionListener.class})
public class TestCSVInfo {


    @Configuration
    static class ContextConfiguration {

        // this bean will be injected into the OrderServiceTest class
        @Bean
        public CsvInterfaceImpl csvInterface() {
            CsvInterfaceImpl csvInterface= new CsvInterfaceImpl();
            // set properties, etc.
            return csvInterface;
        }
    }

    @Test
    public void csvInfoShouldWork() throws IOException {


    }

}
