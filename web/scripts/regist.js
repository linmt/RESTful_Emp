$(function(){
    $("#regist_button").click(function(){
        //清除提示信息
        $("#warning_1").hide();
        $("#warning_2").hide();
        $("#warning_3").hide();
        $("#warning_4").hide();
        //获取请求参数
        var name = $("#regist_username").val().trim();
        var nick = $("#nickname").val().trim();
        var password = $("#regist_password").val().trim();
        var final_password = $("#final_password").val().trim();
        //检查格式
        var ok = true;
        if(name==""){
            ok = false;
            $("#warning_1 span").html("用户名为空");
            $("#warning_1").show();
        }
        if(nick==""){
            ok = false;
            $("#warning_4 span").html("昵称为空");
            $("#warning_4").show();
        }
        if(password==""){
            ok = false;
            $("#warning_2 span").html("密码为空");
            $("#warning_2").show();
        }else{
            if(password.length<6){
                ok = false;
                $("#warning_2 span").html("密码需要6位以上");
                $("#warning_2").show();
            }
        }
        if(final_password != password){
            ok = false;
            $("#warning_3 span").html("与密码不一致");
            $("#warning_3").show();
        }
        //发送Ajax
        if(ok){
            $.ajax({
                contentType: 'application/json',
                url:"/user/regist",
                type:"POST",
                //data:{"name":name,"password":password,"nick":nick},
                data:JSON.stringify({cn_user_name:name,cn_user_password:password,cn_user_desc:nick}),
                dataType:"json",
                success:function(result){
                    if(result.status==0){//成功
                        alert(result.msg);//提示
                        $("#back").click();//切换到登录界面
                    }else if(result.status==1){//用户名占用
                        $("#warning_1 span").html(result.msg);
                        $("#warning_1").show();
                    }
                },
                error:function(){
                    alert("注册失败,稍后重试");
                }
            });
        }
    });
});