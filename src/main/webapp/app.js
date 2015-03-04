//Ext.loader.setConfig({
//    enabled: true
//});

Ext.application({
    name: 'EXTSPARK',
    appFolder: 'app',
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