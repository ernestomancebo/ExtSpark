package com.flectosystems.extspark.services;

import com.flectosystems.extspark.dao.IItemDao;
import com.flectosystems.extspark.services.transformers.ItemArrayJsonTransformer;
import com.flectosystems.extspark.services.transformers.JsonTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import spark.servlet.SparkApplication;

import static spark.Spark.get;

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

        // Get all items
        get("api/items/getItems", (req, res) -> {
            itemDao.beginTransaction();
            return itemDao.listItem();
        }, new ItemArrayJsonTransformer());

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