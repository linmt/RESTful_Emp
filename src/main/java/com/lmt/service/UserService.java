package com.lmt.service;

import com.lmt.entity.NoteResult;
import com.lmt.entity.User;

import java.security.NoSuchAlgorithmException;

/**
 * Created by 张洲徽 on 2018/11/29.
 */
public interface UserService {
    //public NoteResult<User> checkLogin(String name,String password) throws NoSuchAlgorithmException;
    public NoteResult<String> checkLogin(String name, String password) throws NoSuchAlgorithmException;
    public NoteResult regist(User user) throws NoSuchAlgorithmException;
}
