package com.flectosystems.extspark.dao;

import com.flectosystems.extspark.model.Item;

import java.util.ArrayList;

/**
 * Created by Ernesto Mancebo T on 3/2/15.
 */
public interface IItemDao extends SimpleDao{

    public void addItem(Item item);

    public Item getItem(String item);

    public ArrayList<Item> listItem();

    public void updateItem(Item oldItem);

    public void updateItem(String item);

    public void removeItem(Item item);

    public void removeItem(String item);
}
