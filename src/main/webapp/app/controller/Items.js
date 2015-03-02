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
                    itemdbclick: this.editItem
                },
                'itemlist button[action=add]': {
                    click: this.addItem
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
        var view = ext.widget('itemedit');
        view.down('form').loadRecord(record);
        view.down('form').getcomponent('itemNumber').setReadOnly(true);
    },

    updateItem: function (button) {
        var win = button.up('window');
        form = win.down('form');

        if (win.addMode) {
            if (form.getForm().isValid()) {
                Ext.Ajax.request(
                    {
                        url: 'api/items/updateItem',
                        params: {
                            company: 1,
                            addData: Ext.encode(form.getValues())
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
            record.set(values);

            win.close();
            this.getItemsStore().sync();
        }
    },

    onSaveFailure: function (err) {
        Ext.MessageBox.alert('Status', 'Error occurred during Item Add');
    },

    onSaveSuccess: function (response) {
        Ex.MessageBox.alert('Status', 'Item successfully Added');
        this.getItemStore().load();
    }

});