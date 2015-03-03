package com.flectosystems.extspark.config;

import com.flectosystems.extspark.dao.imp.ItemDaoImpl;
import com.flectosystems.extspark.services.ItemService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by Ernesto Mancebo T on 3/2/15.
 */
public class DependencyInjectionInitializer implements ServletContextListener {
    ApplicationContext context;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        context = new ClassPathXmlApplicationContext(new String[]{"Spring-AutoScan.xml"});

        ItemService.getInstance().setItemDao((ItemDaoImpl) context.getBean("ItemDaoImpl"));
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ((ItemDaoImpl) context.getBean("ItemDaoImpl")).closeTransaction();
    }
}
