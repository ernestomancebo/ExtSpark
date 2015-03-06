/**
 * Created by ernesto on 3/1/15.
 */
Ext.define('EXTSPARK.controller.Items', {
    extend: 'Ext.app.Controller',

    stores: ['Items'],
    models: ['Item'],
    views: ['item.List', 'item.Edit'],

    init: function () {
        this.control(
            {
                'viewport > panel': {
                    render: this.onPanelRendered
                },
                'itemlist': {
                    itemdblclick: this.editItem
                },
                'itemlist button[action=add]': {
                    click: this.addItem
                },
                'itemlist button[action=delete]': {
                    click: this.deleteItem
                },
                'itemedit button[action=save]': {
                    click: this.updateItem
                }
            }
        );
    },

    onPanelRendered: function () {
        console.log("The panel was rendered");
    },

    addItem: function () {
        var view = Ext.widget('itemedit');
        view.addMode = true;
    },

    editItem: function (grid, record) {
        var view = Ext.widget('itemedit');
        view.down('form').loadRecord(record);
        // view.down('form').getComponent('itemNumber').setReadOnly(true);
    },

    updateItem: function (button) {
        var win = button.up('window');
        var form = win.down('form');

        if (win.addMode) {
            if (form.getForm().isValid()) {
                Ext.Ajax.request(
                    {
                        url: '/ExtSPark/api/items/addItems',
                        params: {
                            items: Ext.encode(form.getValues()),
                            add: true
                        },
                        scope: this,
                        success: this.onSaveSuccess,
                        failure: this.onSaveFailure
                    }
                );

                win.close();
            }
        } else {
            record = form.getRecord();
            values = form.getValues();

            console.log(values);

            record.set(values);

            win.close();
            this.getItemsStore().sync();
        }
    },

    deleteItem: function () {
        /*
         Capture the grids that are checked.
         Get the Items-item field value.
         Create an array of the item field value.
         Send to server.
         */
        var list = Ext.widget('itemlist');
        var store = list.getStore();

        store.each(function (record, idx) {
                console.log(record.get('weight'));
            }
        )
        ;
    },

    onSaveFailure: function (err) {
        Ext.MessageBox.alert('Status', 'Error occurred during Item Add');
        console.log(err);
    },

    onSaveSuccess: function (response) {
        Ext.MessageBox.alert('Status', 'Item successfully Added');
        this.getItemsStore().load();
    }

})
;