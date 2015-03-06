/**
 * Created by ernesto on 3/1/15.
 */
Ext.define('EXTSPARK.view.item.List', {
    extend: 'Ext.grid.Panel',
    alias: 'widget.itemlist',
    title: 'List of items',
    store: 'Items',
    loadMask: true,
    autoHeight: true,

    dockedItems: [
        {
            xtype: 'pagingtoolbar',
            store: 'Items',
            dock: 'bottom',
            displayInfo: true,

            items: [
                {
                    xtype: 'tbseparator'
                },
                {
                    xtype: 'button',
                    text: 'Add Item',
                    action: 'add'
                },
                {
                    xtype: 'button',
                    text: 'Delete Item',
                    action: 'delete'
                }
            ]
        }
    ],

    initComponent: function () {
        this.columns = [
            {header: 'Item Number', dataIndex: 'item', flex: 1},
            {header: 'Description', dataIndex: 'desc', flex: 1},
            {header: 'Weight', dataIndex: 'weight', flex: 1},
            {xtype: 'checkcolumn', header: '', dataIndex: 'checked'}
        ];

        this.callParent(arguments);
    }

})