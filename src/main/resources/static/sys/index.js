layui.extend({
    sys: 'lay/modules/sys',
    validate: 'lay/modules/validate'
}).define(['sys', 'conf'], function (exports) {
    layui.sys.initPage();
    exports('index', {});
});