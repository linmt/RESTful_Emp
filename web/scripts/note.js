//加载笔记列表
function loadBookNotes(){
    //alert("准备加载笔记列表");
    //切换中间列表显示
    showNoteList(2);//全部列表显示,其他列表隐藏
    //切换成编辑区
    $("#pc_part_3").show();
    $("#pc_part_5").hide();
    //清除非当前笔记本li的选中状态
    $("#book_list li a").removeClass("checked");
    //将当前笔记本li设置成选中状态
    //也可以用children
    $(this).find("a").addClass("checked");
    //获取请求参数
    var bookId = $(this).data("bookId");
    //发送Ajax请求
    $.ajax({
        url:"/note/loadnotes.form",
        type:"post",
        data:{"bookId":bookId},
        dataType:"json",
        success:function(result){
            if(result.status==0){
                var notes = result.data;//笔记数组
                $("#note_list").empty();//清除原有笔记列表
                //循环笔记数组,生成笔记列表
                for(var i=0;i<notes.length;i++){
                    //CN_NOTE_ID必须大写，这里是查询返回map，其他地方返回的是实体类
                    var noteTitle = notes[i].CN_NOTE_TITLE;
                    var noteId = notes[i].CN_NOTE_ID;
                    //创建一个笔记li
                    createNoteLi(noteTitle,noteId);
                }
            }
        },
        error:function(){
            alert("加载笔记列表失败");
        }
    });
};

//切换列表显示
function showNoteList(index){
    um.setContent("");
    $("#input_note_title").val("");//清空标题
    um.setDisabled();
    //将所有列表隐藏
    $(".col-xs-3:not('#save_button_div')").hide();
    //将指定列表显示
    $("#pc_part_"+index).show();
};

//添加一个笔记li
function createNoteLi(noteTitle,noteId){
    var s_li="";
    s_li +='<li class="online">';
    s_li +='	<a>';
    s_li +='		<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>';
    s_li +=noteTitle;
    s_li +='        <button type="button" class="btn btn-default btn-xs btn_position btn_slide_down">';
    s_li +='            <i class="fa fa-chevron-down"></i>';
    s_li +='        </button>';
    s_li +='	</a>';
    s_li +='	<div class="note_menu" tabindex="-1">';
    s_li +='	<dl>';
    s_li +='		<dt><button type="button" class="btn btn-default btn-xs btn_move" title="移动至..."><i class="fa fa-random"></i></button></dt>';
    s_li +='		<dt><button type="button" class="btn btn-default btn-xs btn_share" title="分享"><i class="fa fa-sitemap"></i></button></dt>';
    s_li +='		<dt><button type="button" class="btn btn-default btn-xs btn_delete" title="删除"><i class="fa fa-times"></i></button></dt>';
    s_li +='   </dl>';
    s_li +='   </div>';
    s_li +='</li>';
    //将noteId绑定到li上
    var $li  = $(s_li);
    $li.data("noteId",noteId);
    //将li添加到ul列表区
    $("#note_list").prepend($li);
};

//确认创建笔记
function sureAddNote(){
    //获取请求参数
    var noteTitle = $("#input_note").val().trim();
    var $li = $("#book_list li a.checked").parent();
    var bookId = $li.data("bookId");
    //TODO 检查参数格式
    //发送Ajax请求
    $.ajax({
        url:"/note/add.form",
        //url:base_url+"/note/add.do",
        type:"post",
        data:{"bookId":bookId,"userId":userId,"noteTitle":noteTitle},
        dataType:"json",
        success:function(result){
            if(result.status==0){
                //关闭对话框
                closeWindow();
                //添加一个笔记li
                var noteId = result.data;
                createNoteLi(noteTitle,noteId);
                //提示成功
                alert(result.msg);
            }
        },
        error:function(){
            alert("创建笔记失败");
        }
    });
};

//加载笔记详情
function loadNoteDetail(){
    um.setContent("");
    um.enable();
    //将笔记设置成选中状态
    $("#note_list li a").removeClass("checked");
    $(this).find("a").addClass("checked");
    //获取请求参数
    var noteId = $(this).data("noteId");
    //发送Ajax请求
    $.ajax({
        //url:base_url+"/note/load.do",
        url:"/note/load.form",
        type:"post",
        data:{"id":noteId},
        dataType:"json",
        success:function(result){
            if(result.status==0){
                var noteTitle = result.data.cn_note_title;
                var noteBody = result.data.cn_note_body;
                //设置标题
                $("#input_note_title").val(noteTitle);
                //设置内容
                if(noteBody!=null){
                    um.setContent(noteBody);
                }else {
                    um.setContent("（请输入文本）");
                }
            }
        },
        error:function(){
            alert("加载笔记详情失败");
        }
    });
};

//修改笔记
function sureUpdateNote(){
    //检查是否选中笔记
    var $li = $("#note_list li a.checked").parent();
    if($li.length==0){
        alert("请选择要保存的笔记");
    }else{
        //获取请求参数
        var noteId = $li.data("noteId");
        var noteTitle = $("#input_note_title").val().trim();
        var noteBody = um.getContent();
        //TODO 检测格式
        //发送Ajax请求
        $.ajax({
            //url:base_url+"/note/update.do",
            url:"/note/update.form",
            type:"post",
            data:{"noteTitle":noteTitle,"noteBody":noteBody,"noteId":noteId},
            dataType:"json",
            success:function(result){
                if(result.status==0){
                    //更新笔记li的名称
                    var str = '<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>'
                               +noteTitle
                             +'<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down">'
                             +'  <i class="fa fa-chevron-down"></i>'
                             +'</button>';
                    $li.find("a").html(str);
                    //提示成功
                    alert(result.msg);
                }else if(result.status==1){
                    alert(result.msg);
                }
            },
            error:function(){
                alert("保存笔记失败");
            }
        });
    }
};

//笔记菜单控制
function showNoteMenu(){
    $("#note_list").on("click",".btn_slide_down",function(){
        //将所有笔记菜单隐藏
        $("#note_list .note_menu").hide();
        //将当前点击按钮的菜单显示
        var $li = $(this).parents("li");
        var $menu = $li.find(".note_menu");
        $menu.slideDown(200);
        //将当前笔记li选中
        $("#note_list li a.checked").removeClass("checked");
        $li.find("a").addClass("checked");
        //阻止冒泡
        //如果不阻止冒泡，那么点击后下拉菜单显示，同时也触发了下面的下拉菜单隐藏。最后结果是下拉菜单显示的瞬间就被隐藏了
        return false;
    });
    //追加鼠标移动对笔记菜单的处理
    $("#note_list").on("mouseover",".note_menu",function(){
        $(this).show();//保持显示状态
    });
    $("#note_list").on("mouseout",".note_menu",function(){
        $(this).hide();//隐藏菜单
    });
    //点击body部分隐藏菜单
    $("body").click(function(){
        $("#note_list .note_menu").hide();
    });
};

//确认分享笔记操作
function sureShareNote(){
    //获取请求参数
    //也可以
    // var $li = $(this).parents("li");
    var $li = $("#note_list a.checked").parent();
    var noteId = $li.data("noteId");
    //发送Ajax请求
    $.ajax({
        //url:base_url+"/note/share.do",
        url:"/note/share.form",
        type:"post",
        data:{"noteId":noteId},
        dataType:"json",
        success:function(result){
            var noteTitle=$li.text();
            var s_li="";
            s_li +='<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>';
            s_li +=noteTitle;
            s_li +='<i class="fa fa-sitemap"></i>';
            s_li +='<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down">';
            s_li +='    <i class="fa fa-chevron-down"></i>';
            s_li +='</button>';
            $li.find("a").html(s_li);
            alert(result.msg);
        },
        error:function(){
            alert("分享笔记失败");
        }
    });
    //是否需要？
    return false;//阻止冒泡
};

//确认搜索分享笔记
function sureSearchShare(event){
    var code = event.keyCode;
    if(code==13){//按回车键
        //获取查询关键字
        var keyword = $("#search_note").val().trim();
        page=1;
        //new
        loadPageShare(keyword,page);
    }
};

//预览分享笔记内容
function viewShareNote(){
    //获取请求参数
    var shareId = $(this).data("shareId");
    //发送Ajax请求
    $.ajax({
        //url:base_url+"/note/loadShare.do",
        url:"/note/loadShare.form",
        type:"post",
        data:{"shareId":shareId},
        dataType:"json",
        success:function(result){
            if(result.status==0){
                var title = result.data.cn_share_title;
                var body = result.data.cn_share_body;
                $("#noput_note_title").html(title);
                $("#noput_note_body").html(body);
            }
        },
        error:function(){
            alert("加载笔记信息失败");
        }
    });
};

//点击回收站按钮，显示回收站笔记
function showRecycleNotes() {
    //显示回收站列表区
    showNoteList(4);
    //切换成预览状态
    $("#pc_part_5").show();
    $("#pc_part_3").hide();

    //发送ajax请求
    $.ajax({
        url:"/note/loadDelete.form",
        type:"post",
        data:{"userId":userId},
        dataType:"json",
        success:function(result){
            if(result.status==0){
                var notes=result.data;
                $("#recycle_list").empty();//清除原有笔记列表
                for(var i=0;i<notes.length;i++){
                    var title = notes[i].CN_NOTE_TITLE;
                    var noteId = notes[i].CN_NOTE_ID;
                    //拼一个li
                    var s_li ='<li >';
                    s_li +='	<a >';
                    s_li +='		<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>';
                    s_li +=			title;
                    s_li +='		<button type="button" class="btn btn-default btn-xs btn_position btn_delete">';
                    s_li +='			<i class="fa fa-times"></i>';
                    s_li +='		</button>';
                    s_li +='		<button type="button" class="btn btn-default btn-xs btn_position_2 btn_replay">';
                    s_li +='			<i class="fa fa-reply"></i>';
                    s_li +='		</button>';
                    s_li +='	</a>';
                    s_li +='</li>';
                    //将noteId绑定到li上
                    var $li  = $(s_li);
                    $li.data("noteId",noteId);
                    //将li添加到ul列表区
                    $("#recycle_list").append($li);
                }
            }
        },
        error:function(){
            alert("加载笔记信息失败");
        }
    });
};

//确定转移笔记
function sureMoveNote(){
    //获取请求参数
    var $noteLi = $("#note_list a.checked").parent();
    var noteId = $noteLi.data("noteId");
    var bookId = $("#moveSelect").val();
    //TODO 检查是否选择笔记本
    //TODO 检查是否和原笔记本一样
    //发送Ajax请求
    $.ajax({
        url:base_url+"/note/move.do",
        type:"post",
        data:{"noteId":noteId,"bookId":bookId},
        dataType:"json",
        success:function(result){
            if(result.status==0){
                closeWindow();//关闭对话框
                $noteLi.remove();//移除笔记
                alert(result.msg);//提示成功
            }
        },
        error:function(){
            alert("转移笔记失败");
        }
    });
};

//加载更多分享笔记
function moreSearchShare(){
    var keyword=$("#search_note").val().trim();
    page=page+1;
    //加载列表
    loadPageShare(keyword,page);
};

//根据页码和关键字加载分享笔记
function loadPageShare(keyword,page){
    $.ajax({
        url:"/note/search.form",
        type:"post",
        data:{"keyword":keyword,"page":page},
        dataType:"json",
        success:function(result){
            if(result.status==0){
                //切换列表显示区
                showNoteList(6);//搜索结果列表显示,其他隐藏
                //切换成预览状态
                $("#pc_part_5").show();
                $("#pc_part_3").hide();
                //清空原有查询结果列表
                $("#search_list").empty();
                //循环生成笔记列表
                var notes = result.data;
                for(var i=0;i<notes.length;i++){
                    //结果返回的是map，所以这里要大写
                    var title = notes[i].CN_SHARE_TITLE;
                    var shareId = notes[i].CN_SHARE_ID;
                    //拼一个li
                    var s_li ='<li class="online">';
                    s_li +='	<a>';
                    s_li +='		<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>';
                    s_li += title;
                    s_li += '      <button type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-star"></i></button>';
                    s_li +='	</a>';
                    s_li +='</li>';
                    //绑定shareId
                    var $li = $(s_li);
                    $li.data("shareId",shareId);
                    //将li添加到搜索结果列表区
                    $("#search_list").append($li);
                }
            }
        },
        error:function(){
            alert("检索笔记失败");
        }
    });
};

//确定恢复回收站笔记
//没写完
function sureReplayNote() {
    alert("恢复了");
}