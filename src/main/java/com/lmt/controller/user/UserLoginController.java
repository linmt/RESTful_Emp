package com.lmt.controller.user;

import com.lmt.entity.NoteResult;
import com.lmt.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.security.NoSuchAlgorithmException;

/**
 * Created by 张洲徽 on 2018/11/29.
 */
@Controller
@RequestMapping("/user")
public class UserLoginController {
    @Resource
    private UserService userService;

    @RequestMapping(value = "/login/{name}/{password}",method = RequestMethod.GET)
    @ResponseBody
    public NoteResult execute(@PathVariable String name, @PathVariable String password) throws NoSuchAlgorithmException {
        NoteResult result =userService.checkLogin(name, password);
        System.out.println(userService);
        System.out.println(userService.getClass().getName());
        return result;
        /*
            开启AOP注解扫描方式一：<aop:aspectj-autoproxy/>
            com.UserServiceImpl@3a0d4a07
            com.sun.proxy.$Proxy134

            开启AOP注解扫描方式一：<aop:aspectj-autoproxy proxy-target-class="true"/>
            com.UserServiceImpl@21c51306
            com.UserServiceImpl$$EnhancerBySpringCGLIB$$387dba2f
         */
    }
}
