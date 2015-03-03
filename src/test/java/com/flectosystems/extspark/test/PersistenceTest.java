package com.flectosystems.extspark.test;

import com.flectosystems.extspark.dao.IItemDao;
import com.flectosystems.extspark.dao.imp.ItemDaoImpl;
import com.flectosystems.extspark.model.Item;
import junit.framework.TestCase;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;

public class PersistenceTest {

    private static IItemDao itemDao;

    @BeforeClass
    public static void setUp() throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("Spring-AutoScan.xml");
        itemDao = (ItemDaoImpl) context.getBean("ItemDaoImpl");
        itemDao.beginTransaction();

        ArrayList<Item> items = itemDao.listItem();

        for (Item i : items) {
            itemDao.removeItem(i);
        }
    }

    @AfterClass
    public static void tearDown() throws Exception {
        itemDao.closeTransaction();
    }

    @Test
    public void saveItem() {
        Item item = new Item();

        item.setItem("ItemTest");
        item.setDesc("Description");
        item.setStatus("Available");
        item.setWeight(2.34);

        itemDao.addItem(item);

        Item savedItem = itemDao.getItem("ItemTest");
        TestCase.assertTrue("Must match", item.getWeight() == savedItem.getWeight());
    }

    @Test
    public void listAllItems() {

        for (int i = 1; i < 4; i++) {
            Item item = new Item("Item" + i, "Available", "Description " + i, 2.2);
            itemDao.addItem(item);
        }

        ArrayList<Item> items = itemDao.listItem();
        TestCase.assertTrue("Must be 4", 4 == items.size());
    }

    @Test
    public void updateItem() {
        Item item = itemDao.getItem("Item1");
        TestCase.assertNotNull(item);

        double oldWeight = item.getWeight();
        item.setWeight(45D);

        itemDao.updateItem(item);

        item = itemDao.getItem("Item1");
        TestCase.assertFalse("Must be diff weights", item.getWeight() == oldWeight);
    }

    @Test
    public void deleteItem() {

        Item item = itemDao.getItem("Item2");
        TestCase.assertNotNull(item);

        itemDao.removeItem(item);

        item = itemDao.getItem("Item2");
        TestCase.assertNull(item);
    }
}