package com.flectosystems.extspark.dao.imp;

import com.flectosystems.extspark.dao.IItemDao;
import com.flectosystems.extspark.model.Item;
import org.hibernate.SessionFactory;
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

    @Override
    public void addItem(Item item) {
        sessionFactory.getCurrentSession().save(item);
    }

    @Override
    public Item getItem(String item) {
        return (Item) sessionFactory.getCurrentSession().load(Item.class, item);
    }

    @Override
    public ArrayList<Item> listItem() {
        return new ArrayList<Item>(
                sessionFactory.getCurrentSession().createQuery("from Item").list()
        );
    }

    @Override
    public void updateItem(Item item) {
        if (null != item) {
            sessionFactory.getCurrentSession().update(item);
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
            sessionFactory.getCurrentSession().delete(item);
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
}
