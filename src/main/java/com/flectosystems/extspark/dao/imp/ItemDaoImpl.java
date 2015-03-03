package com.flectosystems.extspark.dao.imp;

import com.flectosystems.extspark.dao.IItemDao;
import com.flectosystems.extspark.model.Item;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * Created by Ernesto Mancebo T on 3/2/15.
 */
@Repository
public class ItemDaoImpl implements IItemDao {

    @Autowired
    private SessionFactory sessionFactory;
    private Session s;

    @Override
    public void addItem(Item item) {
        // beginTransaction();
        s.save(item);
        // closeTransaction();
    }

    @Override
    public Item getItem(String item) {
        // beginTransaction();
        Item i = (Item) s.load(Item.class, item);
        // closeTransaction();

        return i;
    }

    @Override
    public ArrayList<Item> listItem() {
        // beginTransaction();
        ArrayList<Item> items = new ArrayList<Item>(
                s.createQuery("from item").list()
        );

        // closeTransaction();
        return items;
    }

    @Override
    public void updateItem(Item item) {
        if (null != item) {
            // beginTransaction();
            s.update(item);
            // closeTransaction();
        }
    }

    @Override
    public void updateItem(String item) {
        Item i = getItem(item);
        updateItem(i);
    }

    @Override
    public void removeItem(Item item) {
        if (null != item) {
            // beginTransaction();
            s.delete(item);
            // closeTransaction();
        }
    }

    @Override
    public void removeItem(String item) {
        Item i = getItem(item);
        removeItem(i);
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void beginTransaction() {
        s = sessionFactory.openSession();
        s.beginTransaction();
    }

    @Override
    public void closeTransaction() {
        s.getTransaction().commit();
        s.close();
    }
}
