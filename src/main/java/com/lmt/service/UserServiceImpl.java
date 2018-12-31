package com.lmt.service;

import com.lmt.dao.UserDao;
import com.lmt.entity.NoteResult;
import com.lmt.entity.User;
import com.lmt.util.NoteUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.security.NoSuchAlgorithmException;

/**
 * Created by 张洲徽 on 2018/11/29.
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    /*
    public NoteResult<User> checkLogin(String name, String password)
            throws NoSuchAlgorithmException {
        NoteResult<User> result=new NoteResult<User>();
        User user=userDao.findByName(name);
        if(user==null){
            result.setStatus(1);
            result.setMsg("用户名不存在");
            return result;
        }
        if(!user.getCn_user_password().equals(NoteUtil.md5(password))){
            result.setStatus(2);
            result.setMsg("密码错误");
            return result;
        }
        result.setStatus(0);
        result.setMsg("登录成功");
        //14 发送用户id
        //result.setData(user.getCn_user_id());
        result.setData(user);
        return result;
    }
    */
    public NoteResult<String> checkLogin(String name, String password)
            throws NoSuchAlgorithmException {
        NoteResult<String> result=new NoteResult<String>();
        User user=userDao.findByName(name);
        if(user==null){
            result.setStatus(1);
            result.setMsg("用户名不存在");
            return result;
        }
        if(!user.getCn_user_password().equals(NoteUtil.md5(password))){
            result.setStatus(2);
            result.setMsg("密码错误");
            return result;
        }
        result.setStatus(0);
        result.setMsg("登录成功");
        //14 发送用户id
        result.setData(user.getCn_user_id());
        //result.setData(user);
        return result;
    }
    public NoteResult regist(User user)
            throws NoSuchAlgorithmException {
        NoteResult result = new NoteResult();
        //必要数据检查
        User has_user = userDao.findByName(user.getCn_user_name());
        if(has_user != null){
            result.setStatus(1);
            result.setMsg("用户名已被占用");
            return result;
        }
        //添加处理
//        User user = new User();
//        user.setCn_user_name(name);//用户名
//        user.setCn_user_desc(nickName);//昵称
        String md5_password = NoteUtil.md5(user.getCn_user_password());
        user.setCn_user_password(md5_password);//设置密码
        user.setCn_user_token(null);//令牌
        String userId = NoteUtil.createId();
        user.setCn_user_id(userId);//用户ID
        userDao.save(user);
        result.setStatus(0);
        result.setMsg("注册成功");
        return result;
    }
}
