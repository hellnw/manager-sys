layui.define(function(exports) {
  exports('conf', {
    container: 'sys',
    containerBody: 'sys-body',
    v: '2.0',
    base: layui.cache.base,
    css: layui.cache.base + 'css/',
    views: layui.cache.base + 'views/',
    viewLoadBar: true,
    debug: layui.cache.debug,
    name: 'sys',
    entry: '/index',
    engine: '',
    eventName: 'sys-event',
    tableName: 'sys',
    requestUrl: './'
  })
});
