package com.lmt.controller.user;

import com.lmt.entity.NoteResult;
import com.lmt.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.security.NoSuchAlgorithmException;

/**
 * Created by 张洲徽 on 2018/12/3.
 */
//http://localhost:8080/user/regist.form?name=demo&password=1234&nick=demo
//http://localhost:8080/user/regist.form?name=cat&password=1234&nick=demo
@Controller
@RequestMapping("/user")
public class UserRegistController {
    @Resource
    private UserService userService;

    @RequestMapping(value = "/regist",method = RequestMethod.POST)
    @ResponseBody
    public NoteResult execute(@PathVariable String name, @PathVariable String password, @PathVariable String nick)
            throws NoSuchAlgorithmException {
        NoteResult result = userService.regist(name, password, nick);
        return result;
    }
}
