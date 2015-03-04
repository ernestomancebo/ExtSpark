/**
 * Created by ernesto on 3/1/15.
 */
Ext.define('EXTSPARK.store.Items', {
    extend: 'Ext.data.Store',
    model: 'EXTSPARK.model.Item',
    autoload: true,
    pageSize: 10,

    proxy: {
        type: 'ajax',
        api: {
            read: 'ExtSPark/api/items/getItems'
        },
//        extraParams: {
//            company: 1
//        },
        reader: {
            type: 'json',
//            totalProperty: 'totalCount',
            root: 'items',
            successProperty: 'success'
        }
    },

    listeners: {
        // Only changes are sent when synced
        load: function (store) {
            store.each(function (record) {
                record.commit();
            });
        }
    }
})