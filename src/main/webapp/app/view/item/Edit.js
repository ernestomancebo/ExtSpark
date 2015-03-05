/**
 * Created by ernesto on 3/1/15.
 */
Ext.define('EXTSPARK.view.item.Edit', {
    extend: 'Ext.window.Window',
    alias: 'widget.itemedit',
    addMode: false,
    title: 'Edit Item Information',
    layout: 'fit',
    autoShow: true,

    initComponent: function () {
        this.items = this.buildItems();
        this.buttons = this.buildButtons();
        this.callParent(arguments);
    },

    buildItems: function () {
        return [
            {
                xtype: 'form',
                items: [
                    {
                        xtype: 'textfield',
                        name: 'item',
                        allowBlank: false,
                        msgTarget: 'side',
                        fieldLabel: 'Item Number',
                        size: 11,
                        maxLength: 10
                    },
                    {
                        xtype: 'textfield',
                        name: 'desc',
                        allowBlank: false,
                        msgTarget: 'side',
                        fieldLabel: 'Description',
                        size: 31,
                        maxLength: 30
                    },
                    {
                        xtype: 'textfield',
                        name: 'status',
                        allowBlank: false,
                        msgTarget: 'side',
                        fieldLabel: 'Status',
                        size: 11,
                        maxLength: 10

                    },
                    {
                        xtype: 'numberfield',
                        name: 'weight',
                        value: 0,
                        minValue: 0,
                        fieldLabel: 'Weight',
                        decimalPrecision: 2,
                        step: 0.01
                    }
                ]
            }
        ];
    },

    buildButtons: function () {
        return [
            {
                text: 'Save',
                action: 'save'
            },
            {
                text: 'Cancel',
                scope: this,
                handler: this.close
            }
        ];
    }
})