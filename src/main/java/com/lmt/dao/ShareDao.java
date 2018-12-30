package com.lmt.dao;

import com.lmt.annotation.MybatisDao;
import com.lmt.entity.Share;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by 热带雨林 on 2018/12/8.
 */
@MybatisDao
@Repository
public interface ShareDao {
    public void save(Share share);
    public Share findByNoteId(String noteId);
    //public List<Share> findLikeTitle(String title);
    //public List<Map> findLikeTitle(String title);
    //改成带分页功能的
    public List<Map> findLikeTitle(Map params);
    public Share findById(String id);
}
