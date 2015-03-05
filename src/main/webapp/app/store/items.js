/**
 * Created by ernesto on 3/1/15.
 */
Ext.define('EXTSPARK.store.Items', {
    extend: 'Ext.data.Store',
    model: 'EXTSPARK.model.Item',
    autoLoad: true,

    proxy: {
        type: 'ajax',
        api: {
            read: '/ExtSPark/api/items/getItems',
            update: 'data/updateUsers.json'
        },
        reader: {
            type: 'json',
            rootProperty: 'items',
            successProperty: 'status.success'
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
});

