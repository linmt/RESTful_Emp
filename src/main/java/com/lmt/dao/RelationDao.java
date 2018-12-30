package com.lmt.dao;

import com.lmt.annotation.MybatisDao;
import com.lmt.entity.NoteBook;
import com.lmt.entity.User;

import java.util.List;

/**
 * Created by 热带雨林 on 2018/12/22.
 */
@MybatisDao
public interface RelationDao {
    //通过userId查询用户信息和关联的笔记本信息案例（两条SQL）（关联多个对象）
    public User findUserAndBooks(String userId);
    //通过userId查询用户信息和关联的笔记本信息案例（单条SQL）（关联多个对象）
    public User findUserAndBooks2(String userId);
    //通过查询笔记信息，关联用户信息（关联单个对象）（两条SQL）
    public List<NoteBook> findBookAndUser();
    //通过查询笔记信息，关联用户信息（关联单个对象）（单条SQL）
    public List<NoteBook> findBookAndUser2();
}
