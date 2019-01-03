var ajaxErrorEvt = function(request, status, error){
    var status = request.status;
    switch(Number(status)){
        case 400:
            alert("[" + status + "]잘못된 접근을 하셨습니다.");
            window.location.href = /*[[@{/home/login}]]*/ '/home/login';
            break;
        default: console.error("[" + status + "] " + error);
            break;
    }
};

var modalOpen = (function(){
    var opendURLs = {};
    return function(url, data){
        if(url in opendURLs) return;
        else{
            $.ajax({
                url: url,
                data: data,
                type: "POST",
                beforeSend : function(xhr){
                    xhr.setRequestHeader("X-is-Ajax", true);
                },
                success: function(data){
                    opendURLs[url] = true;
                    var parent = $('<div></div>'), body = $('body');
                    parent.html(data);
                    body.append(parent);

                    var modal = parent.find('.modal');

                    modal.on('hidden.bs.modal', function(){
                        parent.remove();
                        delete opendURLs[url];
                    });
                    modal.modal();
                    parent.focus();
                },
                error: ajaxErrorEvt
            });
        }
    };
})();


var noticeModalOpen = function(text){
    modalOpen(
        /*[[@{/home/modal/notice}]]*/ '/home/modal/notice',
        {text: text}
    );
};

var confirmModalOpen = function(text, targetId, valid, url){
    var data = {};
    if(text) data.text = text;
    if(targetId) data.targetId = targetId;
    if(url) data.url = url;
    if(valid) data.valid = valid;
    modalOpen(
        /*[[@{/home/modal/confirm}]]*/ '/home/modal/confirm',
        data
    );
};

var confirmCallbackModalOpen = function(text, targetId){
    var data = {};
    if(text) data.text = text;
    if(targetId) data.targetId = targetId;
    modalOpen(
        /*[[@{/home/modal/confirm/callback}]]*/ '/home/modal/confirm/callback',
        data
    );
};

var mergeObject = function(obj1, obj2){
    Object.keys(obj2).forEach(function (k) {
        obj1[k] = o[k];
    });
    return obj1;
};

var milisecondToDate = function(milisecond){
    var date = new Date(milisecond);
    return date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + (date.getDate() + 1);
};

var htmlDecode = (function() {
    var txt = document.createElement('textarea');
    return function(input){
        txt.innerHTML = input;
        return txt.value;
    };
})();
