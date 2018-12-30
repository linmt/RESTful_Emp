package com.lmt.dao;

/**
 * Created by 张洲徽 on 2018/12/4.
 */

import com.lmt.annotation.MybatisDao;
import com.lmt.entity.NoteBook;
import org.springframework.stereotype.Repository;

import java.util.List;

@MybatisDao
@Repository
public interface BookDao {
    public List<NoteBook> findByUserId(String userId);
    public void save(NoteBook book);
}
