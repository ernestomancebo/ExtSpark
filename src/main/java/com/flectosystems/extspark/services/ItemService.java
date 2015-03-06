package com.flectosystems.extspark.services;

import com.flectosystems.extspark.dao.IItemDao;
import com.flectosystems.extspark.model.Constants;
import com.flectosystems.extspark.model.Item;
import com.flectosystems.extspark.model.ResponseStatus;
import com.flectosystems.extspark.services.transformers.ItemArrayJsonTransformer;
import com.flectosystems.extspark.services.transformers.JsonTransformer;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import spark.servlet.SparkApplication;

import java.util.ArrayList;

import static spark.Spark.*;

@Repository
public class ItemService implements SparkApplication {

    @Autowired
    private IItemDao itemDao;

    private static ItemService INSTANCE = null;

    /**
     * This method handles all the API calls.
     */
    @Override
    public void init() {

        // Methods & params
        final String GET_ITEMS = "getItems";
        final String ADD_ITEMS = "addItems";
        final String DELETE_ITEMS = "deleteItems";
        final String ITEMS_PARAMS = "items";
        final String ADD_PARAM = "add";

        // Get all items
        get(Constants.ITEMS_URL.concat(GET_ITEMS), (req, res) -> {
            itemDao.beginTransaction();

            ArrayList<Item> itemsArray = itemDao.listItem();

            itemDao.closeTransaction();
            return itemsArray;

        }, new ItemArrayJsonTransformer());

        // Add or update items
        post(Constants.ITEMS_URL.concat(ADD_ITEMS), (req, res) -> {
            String items = req.queryParams(ITEMS_PARAMS);
            String isAdd = req.queryParams(ADD_PARAM);

            boolean add = (isAdd != null ? Boolean.valueOf(isAdd) : false);

            ArrayList<Item> itemsArray = parseItemsFromRequest(items);

            try {
                itemDao.beginTransaction();

                if (add) {
                    itemDao.addManyItems(itemsArray);
                } else {
                    itemDao.updateManyItems(itemsArray);
                }

            } catch (HibernateException ex) {
                ex.printStackTrace();
                return new ResponseStatus(ex.toString(), false);
            } finally {
                itemDao.closeTransaction();
            }

            return new ResponseStatus("", true);
        }, new JsonTransformer());

        delete(Constants.ITEMS_URL.concat("test"), (req, res) -> {
            return "OK";
        }, new JsonTransformer());

        // Delete items
        post(Constants.ITEMS_URL.concat(DELETE_ITEMS), (req, res) -> {
            String items = req.queryParams(ITEMS_PARAMS);
            ArrayList<Item> itemsArray = parseItemsFromRequest(items);

            try {
                itemDao.beginTransaction();
                for (Item i : itemsArray)
                    itemDao.removeItem(i);
            } catch (HibernateException ex) {
                ex.printStackTrace();
                return new ResponseStatus(ex.toString(), false);
            } finally {
                itemDao.closeTransaction();
            }

            return new ResponseStatus("", true);
        }, new JsonTransformer());
    }

    public ArrayList<Item> parseItemsFromRequest(String items) {
        ArrayList<Item> itemsArray = new ArrayList<>();

        // parse JSON array, otherwise, a single JSON
        if (items.startsWith("[")) {
            JsonArray jsonElements = new JsonParser().parse(items).getAsJsonArray();

            for (int i = 0; i < jsonElements.size(); i++) {
                JsonObject jsonObject = jsonElements.get(i).getAsJsonObject();
                Item singleItem = new Gson().fromJson(jsonObject, Item.class);
                itemsArray.add(singleItem);
            }
        } else {
            JsonObject jsonObject = new JsonParser().parse(items).getAsJsonObject();
            Item singleItem = new Gson().fromJson(jsonObject, Item.class);
            itemsArray.add(singleItem);
        }

        itemsArray.trimToSize();

        return itemsArray;
    }

    public void setItemDao(IItemDao itemDao) {
        this.itemDao = itemDao;
    }

    public static ItemService getInstance() {
        if (null == INSTANCE)
            INSTANCE = new ItemService();
        return INSTANCE;
    }
}