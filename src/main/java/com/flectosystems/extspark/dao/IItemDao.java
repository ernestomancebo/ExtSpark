package com.flectosystems.extspark.dao;

import com.flectosystems.extspark.model.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ernesto Mancebo T on 3/2/15.
 */
public interface IItemDao extends SimpleDao {

    public void addItem(Item item);

    public void addManyItems(List<Item> items);

    public Item getItem(String item);

    public ArrayList<Item> listItem();

    public void updateItem(Item oldItem);

    public void updateManyItems(List<Item> items);

    public void updateItem(String item);

    public void removeItem(Item item);

    public void removeItem(String item);
}
