$(function(){
    $("#login").click(function () {
        $("#count_span").html("");
        $("#password_span").html("");
        //获取参数
        var name=$("#count").val().trim();
        var password=$("#password").val().trim();
        var ok=true;
        if(name==""){
            $("#count_span").html("用户名不能为空");
            ok=false;
        }
        if(password==""){
            $("#password_span").html("密码不能为空");
            ok=false;
        }
        //发送请求
        if(ok){
            $.ajax({
                //url:base_url+"/user/login.do",
                url:'/user/login/'+name+'/'+password,
                type:'GET',
                dataType:'json',
                success:function(result){
                    if(result.status==0){
                        //14  获取用户ID，写入cookie
                        //var userId=result.data.cn_user_id;
                        var userId=result.data;
                        addCookie("userId",userId,2);
                        window.location.href="edit.html";
                    }else if(result.status==1){
                        $("#count_span").html(result.msg);
                    }else if(result.status==2){
                        $("#password_span").html(result.msg);
                    }
                },
                error:function(){
                    alert("登录失败");
                }
            });
        }

    });
});