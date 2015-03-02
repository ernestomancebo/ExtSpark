Ext.loader.setConfig({
    enabled: true
});

Ext.application({
    name: 'EXTSPARK',
    controllers: ['Items'],

    launch: function () {
        Ext.create('Ext.container.Viewport', {
            items: [
                {
                    xtype: 'itemlist'
                }
            ]
        });
    }
})