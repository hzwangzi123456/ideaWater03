/**
 * Created by ziwang on 17/10/26.
 */
function navLoginOut() {
    doAjax(PROJECT_NAME + "/UserController/logout.do", {}, function (data) {
        var redata = strToJson(data);
        console.log(redata);
        if (!judgeAjaxData(redata)) {
            return;
        }
        window.location.href = PROJECT_NAME + INDEX_NAME;
    });
}
