//弹出添加笔记本对话框
function showAddBookWindow(){
    //弹出对话框
    $(".opacity_bg").show();//显示背景
    //var url = base_url+"/alert/alert_notebook.html";
    var url = "/alert/alert_notebook.html";
    $("#can").load(url);
};

//关闭所有对话框
function closeWindow(){
    $(".opacity_bg").hide();//隐藏背景div
    $("#can").empty();//清空对话框内容
};

//弹出添加笔记对话框
function showAddNoteWindow(){
    //判断是否有选中的笔记本
    var $bookLi = $("#book_list a.checked");
    if($bookLi.length==0){
        alert("请选择笔记本");
    }else{
        //弹出对话框
        $(".opacity_bg").show();//显示背景
        //var url = base_url+"/alert/alert_note.html";
        var url = "/alert/alert_note.html";
        $("#can").load(url);
    }
};

//弹出确认删除笔记对话框
function showRecycleNoteWindow(){
    //弹出对话框
    $(".opacity_bg").show();//显示背景
    //var url = base_url+"/alert/alert_delete_note.html";
    var url ="/alert/alert_delete_note.html";
    $("#can").load(url);
    return false;//阻止冒泡
};

//获取noteId，弹出恢复对话框，加载笔记本列表
function showReplayNoteWindow(){
    //获取noteId
    var $li=$(this).parent();
    var noteId=$li.data("noteId");

    //弹出对话框
    $(".opacity_bg").show();//显示背景
    var url = "/alert/alert_replay.html";
    //这个load也是ajax请求，所以存在的风险是：
    // alert_replay.html还没返回之前，已经执行了loadbooks.form
    //如何避免这种风险：
    $("#can").load(url,function () {
        //将noteId绑定到恢复按钮
        $("#sure_replay").data("noteId",noteId);
        //html载入后执行，加载笔记本列表
        loadReplaySelect();
    });
};

//加载笔记本列表
function loadReplaySelect() {
    $.ajax({
        url:"/notebook/loadbooks.form",
        type:"post",
        data:{"userId":userId},
        dataType:"json",
        success:function(result){
            if(result.status==0){
                //这里笔记本列表是加在弹出窗口上的，每次把列表加上去，
                //原先的列表是不会存在的，所以这里不需要先清空列表
                var books = result.data;//笔记本数组
                for(var i=0;i<books.length;i++){
                    var bookName = books[i].cn_notebook_name;
                    var bookId = books[i].cn_notebook_id;
                    //拼一个option元素
                    var s_opt ='<option value="'+bookId+'">'+bookName+'</option>';
                    //将option元素添加到select   这是加到最前
                    $("#replaySelect").prepend(s_opt);
                }
            }
        },
        error:function(){
            alert("加载笔记本列表失败");
        }
    });
}
//弹出转移笔记对话框
function showMoveNoteWindow(){
    $(".opacity_bg").show();
    //var url = base_url+"/alert/alert_move.html";
    var url = "/alert/alert_move.html";
    $("#can").load(url,function(){
        //在load页面载入#can之后,执行该function逻辑
        //获取笔记本列表,生成option添加到下拉选择框中
        var bookLis = $("#book_list li");//li元素集合
        for(var i=0;i<bookLis.length;i++){
            //bookLis[i]是DOM对象,需要转成jQuery对象
            var bookName = $(bookLis[i]).text();
            var bookId = $(bookLis[i]).data("bookId");
            //拼一个option
            var s_opt = "<option value='"+bookId+"'>"+bookName+"</option>";
            //将option添加到下拉单中
            $("#moveSelect").append(s_opt);
        }
    });
    return false;//阻止li单击
}
