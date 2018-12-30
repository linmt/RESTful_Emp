function sureRecycleNote(){
    //获取笔记ID
    var $li = $("#note_list a.checked").parent();
    var noteId = $li.data("noteId");
    //发送Ajax请求
    $.ajax({
        //url:base_url+"/recycle/recycle.do",
        url:"/recycle/recycle.form",
        type:"post",
        data:{"noteId":noteId},
        dataType:"json",
        success:function(result){
            if(result.status==0){
                $li.remove();//删除li
                $("#input_note_title").val("");//清空标题
                um.setContent("");//清空编辑区
                alert(result.msg);//提示
            }
        },
        error:function(){
            alert("将笔记放入回收站失败");
        }
    });
    return false;
};