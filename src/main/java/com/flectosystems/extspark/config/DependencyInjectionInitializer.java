package com.flectosystems.extspark.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by Ernesto Mancebo T on 3/2/15.
 */
public class DependencyInjectionInitializer implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"Spring-AutoScan.xml"});

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
