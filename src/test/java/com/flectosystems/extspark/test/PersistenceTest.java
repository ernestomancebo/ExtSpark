package com.flectosystems.extspark.test;

import com.flectosystems.extspark.dao.imp.ItemDaoImpl;
import com.flectosystems.extspark.model.Item;
import junit.framework.TestCase;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PersistenceTest {

    private static ItemDaoImpl itemDao;

    @BeforeClass
    public static void setUp() throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("Spring-AutoScan.xml");
        itemDao = (ItemDaoImpl) context.getBean("ItemDaoImpl");
    }

    @AfterClass
    public static void tearDown() throws Exception {
        itemDao.removeItem("ItemTest");
    }

    @Test
    public void saveItem() {
        Item item = new Item();

        item.setItem("ItemTest");
        item.setDesc("Description");
        item.setStatus("Available");
        item.setWeight(2.34);

        itemDao.addItem(item);

        Item savedItem = itemDao.getItem("Item1");
        TestCase.assertTrue("Must match", item.getWeight() == savedItem.getWeight());
    }
}